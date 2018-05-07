package com.paladin.health.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.utils.StringUtil;
import com.paladin.health.mapper.origin.OriginDiseaseKnowledgeMapper;
import com.paladin.health.model.origin.OriginDiseaseKnowledge;
import com.paladin.health.model.origin.OriginDiseaseKnowledgeContent;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeContentService;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Component
public class OriginDiseaseKnowledgeRepairer {

	private static Logger logger = LoggerFactory.getLogger(OriginDiseaseKnowledgeRepairer.class);

	@Autowired
	OriginDiseaseNameService diseaseNameService;
	@Autowired
	OriginDiseaseKnowledgeService diseaseKnowledgeService;
	@Autowired
	OriginDiseaseKnowledgeContentService diseaseKnowledgeContentService;
	@Autowired
	OriginDiseaseKnowledgeMapper diseaseKnowledgeMapper;

	volatile int threadCount;

	public void repair() {

		final List<OriginDiseaseName> names = diseaseNameService.findAllDiseaseName();

		int count = names.size();
		int i = 0;
		int size = 1000;
		boolean goon = true;

		logger.info("开始修复疾病数据");

		while (goon) {

			if (i > count) {
				break;
			}

			int end = i + size;
			if (end > count) {
				end = count;
				goon = false;
			}

			final int s = i;
			final int e = end - 1;

			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("开始修复第" + s + "条到第" + e + "条疾病知识");

					for (int x = s; x <= e; x++) {
						OriginDiseaseName name = names.get(x);
						try {
							repairDisease(name);
						} catch (Exception e) {
							logger.error("修复疾病[" + name.getNameKey() + "]出错", e);
						}
					}

					logger.info("成功修复第" + s + "条到第" + e + "条疾病知识");
					endThread();
				}
			});

			th.start();
			threadCount++;

			i = end;
		}
	}

	private void endThread() {
		if (--threadCount == 0) {
			logger.info("结束修复疾病知识数据");
			showCount();
		}
	}

	public void repairDisease(OriginDiseaseName diseaseName) {

		String disease = diseaseName.getNameKey();

		List<OriginDiseaseKnowledge> originDiseaseKnowledges = diseaseKnowledgeService.findAllDiseaseKnowledge(disease);
		List<OriginDiseaseKnowledgeContent> originDiseaseContents = diseaseKnowledgeContentService.findDiseaseContent(disease);

		List<DiseaseKnowledge> diseaseKnowledges = assemble(originDiseaseKnowledges, originDiseaseContents);

		repaireKnowledge(diseaseKnowledges, disease);

		persistence(diseaseKnowledges, disease);
	}

	private void persistence(List<DiseaseKnowledge> knowledges, String disease) {

		for (DiseaseKnowledge k : knowledges) {

			if (k.deleted) {

				DiseaseKnowledge x = k.parent;
				while (x != null && x.deleted) {
					x = x.parent;
				}

				Integer id = x == null ? null : x.origin.getId();

				for (DiseaseKnowledge ck : k.children) {
					if (!ck.deleted) {
						ck.origin.setParentId(id);
						diseaseKnowledgeService.update(ck.origin);
						ck.changed = false;
					}
				}

				diseaseKnowledgeService.removeByPrimaryKey(k.origin.getId());

				for (DiseaseKnowledgeContent c : k.contents) {
					c.origin.setKnowledgeId(id);
					c.changed = true;
				}

				// logger.info("需要删除重复标题[" + k.origin.getName() + "][" + disease + ":" +
				// k.origin.getCategoryKey() + "]");
			} else if (k.changed) {
				diseaseKnowledgeMapper.updateByPrimaryKey(k.origin);
			}

			for (DiseaseKnowledgeContent c : k.contents) {
				if (c.deleted) {
					diseaseKnowledgeContentService.removeByPrimaryKey(c.origin.getId());
				} else if (c.changed) {
					diseaseKnowledgeContentService.update(c.origin);
				}
			}

			persistence(k.children, disease);
		}
	}

	private void repaireKnowledge(List<DiseaseKnowledge> knowledges, String disease) {

		for (DiseaseKnowledge k : knowledges) {

			String originTitle = k.origin.getName();
			String title = originTitle;

			if (title == null || title.length() == 0) {
				//putInfo("空标题", disease);
				k.deleted = true;
			} else {

				char[] val = title.toCharArray();

				int len = val.length;
				int st = 0;

				while (st < len) {
					if (isInvalidChar(val[st])) {
						st++;
					} else {
						break;
					}
				}
				while (st < len) {
					if (isInvalidChar(val[len - 1])) {
						len--;
					} else {
						break;
					}
				}

				int[][] kuohaoCounts = new int[kuohaoChars.length][2];

				if (st >= val.length) {
					k.deleted = true;
				} else {
					char first = val[st];

					for (int i = st; i < len; i++) {
						char c = val[i];
						for (int m = 0; m < kuohaoChars.length; m++) {
							if (kuohaoChars[m][0] == c) {
								kuohaoCounts[m][0] += 1;
							} else if (kuohaoChars[m][1] == c) {
								kuohaoCounts[m][1] += 1;
							}
						}
					}

					title = ((st > 0) || (len < val.length)) ? title.substring(st, len) : title;
					if (title.length() == 0) {
						
					} else {
						for (int m = 0; m < kuohaoCounts.length; m++) {
							if (kuohaoCounts[m][0] != kuohaoCounts[m][1]) {
								if (kuohaoCounts[m][0] - kuohaoCounts[m][1] == 1) {
									if (first == kuohaoChars[m][0]) {
										title = title.substring(1);
									} else {
										title = title + String.valueOf(kuohaoChars[m][1]);
									}
								} else {
								}
							} else {
								first = title.charAt(0);
								char last = title.charAt(title.length() - 1);
								if (first == kuohaoChars[m][0]) {
									if (last == kuohaoChars[m][1]) {
										title = title.substring(1, title.length() - 1);
									} else {
										
									}
								}
							}
						}
					}

					first = title.charAt(0);
					
					if(first>='0' && first<='9') {
						logger.info("数字开头[" + String.valueOf(first) + "][" + disease + ":" + k.origin.getCategoryKey() + "]");
					}

					if (first == '的') {
						logger.info("的开头[" + String.valueOf(first) + "][" + disease + ":" + k.origin.getCategoryKey() + "]");
					}

					if (isSpecial(first)) {
						//logger.info("其他特殊字符[" + String.valueOf(first) + "][" + disease + ":" + k.origin.getCategoryKey() + "]");
					}

					if (!originTitle.equals(title)) {
						k.origin.setName(title);
						k.changed = true;
					}
					
					if("".equals(title)) {
						k.deleted = true;
					}
				}

				repaireContent(k, disease);
				repaireKnowledge(k.children, disease);

				if (k.parent == null) {
					repairRepeat(k);
					repairTop(k);
					count(k);
				}
			}
		}
	}

	private void repaireContent(DiseaseKnowledge knowledge, String disease) {
		List<DiseaseKnowledgeContent> contents = knowledge.contents;
		if (contents != null && contents.size() > 0) {

			for (int i = 0; i < contents.size(); i++) {
				DiseaseKnowledgeContent dkc = contents.get(i);
				String orginContent = dkc.origin.getContent();
				String content = orginContent;
				content = StringUtil.strongTrim(content);

				if (content.length() < 2) {
					dkc.deleted = true;
					continue;
				}

				char first = content.charAt(0);
				if (first >= '0' && first <= '9') {
					int s = 1;
					char sec = content.charAt(1);
					while (sec >= '0' && sec <= '9') {
						sec = content.charAt(++s);
					}

					if (sec == '、' || sec == '。' || sec == '．') {
						content = content.substring(0, s) + "." + content.substring(s + 1);
					} else if (sec == ']' || sec == '】') {
						content = content.substring(0, s) + "）" + content.substring(s + 1);
					}
				} else {

					if (first == '?' || first == '？') {
						content = content.substring(1);
						content = StringUtil.strongTrim(content);
					} else if (isSpecial(first)) {
						
					}
				}

				if (content.contains("仅供参考") && content.contains("咨询医生")) {
					if (content.length() < 25) {
						dkc.deleted = true;
					} else {
						
					}
				}

				if (!orginContent.equals(content)) {
					dkc.changed = true;
					dkc.origin.setContent(content);
				}
			}
		}
	}

	private void repairRepeat(DiseaseKnowledge k) {

		DiseaseKnowledge x = k;
		if (x.children.size() == 1 && x.contents.size() == 0) {
			DiseaseKnowledge y = x.children.get(0);
			if (y.origin.getName().equals(x.origin.getName())) {
				x.deleted = true;
			}
		}

		for (DiseaseKnowledge ck : k.children) {
			repairRepeat(ck);
		}
	}
	
	static String[][] categories = new String[][] { { "yyzl", "治疗" }, { "zztz", "典型症状", "症状" }, { "blby", "发病原因", "病因", "原因","疾病病因" }, { "yfhl", "预防" }, { "jcjb", "临床检查", "检查" },
		{ "jb", "鉴别", "鉴别诊断" }, { "hl", "护理" }, { "ysbj", "饮食保健", "饮食" }, { "bfbz", "并发症" } };
	
	static Map<String, String[]> categoryNameMap = new HashMap<>();
	
	static {
		for (String[] ss : categories) {
			String[] v = new String[ss.length - 1];
			System.arraycopy(ss, 1, v, 0, ss.length - 1);
			categoryNameMap.put(ss[0], v);
		}
	}
			
	private void repairTop(DiseaseKnowledge k) {

		if(k.children.size() == 1) {
			DiseaseKnowledge ck = k.children.get(0);
			OriginDiseaseKnowledge odk = ck.origin;
			String title = odk.getName();
			String cate = odk.getCategoryKey();
			
			String[] strs = categoryNameMap.get(cate);
			for(String str : strs) {
				if(str.equals(title)) {
					ck.deleted = true;
					break;
				}
			}
			
			repairTop(ck);
		}
	}

	Map<String, Map<String, AtomicInteger>> countMap = new HashMap<>();

	private void count(DiseaseKnowledge k) {
		String category = k.origin.getCategoryKey();
		Map<String, AtomicInteger> map = countMap.get(category);
		if (map == null) {
			synchronized (countMap) {
				map = countMap.get(k.origin.getCategoryKey());
				if (map == null) {
					map = new ConcurrentHashMap<>();
				}
				countMap.put(category, map);
			}
		}

		for (DiseaseKnowledge ck : k.children) {
			String name = ck.origin.getName();
			AtomicInteger ai = map.get(name);
			if (ai == null) {
				synchronized (map) {
					ai = map.get(name);
					if (ai == null) {
						ai = new AtomicInteger();
					}
					map.put(name, ai);
				}
			}

			ai.incrementAndGet();
		}
	}

	private void showCount() {

		StringBuilder sb = new StringBuilder();

		for (Entry<String, Map<String, AtomicInteger>> entry : countMap.entrySet()) {
			String key = entry.getKey();
			Map<String, AtomicInteger> values = entry.getValue();
			sb.append(key).append(":\r\n");
			for (Entry<String, AtomicInteger> en : values.entrySet()) {
				sb.append("\t").append(en.getKey()).append(":").append(en.getValue().get()).append("\r\n");
			}
		}

		System.out.println(sb.toString());
	}
	
	static char[][] kuohaoChars = new char[][] { { '(', ')' }, { '（', '）' }, { '[', ']' }, { '【', '】' } };

	static char[] invalidChars = new char[] { ' ', '　', '、', '.', '．', '。', ':', '：', '？', '?' };

	private boolean isInvalidChar(char ch) {
		for (char c : invalidChars) {
			if (c == ch) {
				return true;
			}
		}
		return false;
	}


	private boolean isSpecial(char c) {
		return !((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= 0x4E00 && c <= 0x9FA5));
	}

	private List<DiseaseKnowledge> assemble(List<OriginDiseaseKnowledge> diseaseKnowledges, List<OriginDiseaseKnowledgeContent> diseaseContents) {

		if (diseaseContents == null || diseaseContents.size() == 0) {
			return new ArrayList<>();
		}

		Map<Integer, List<DiseaseKnowledgeContent>> contentMap = new HashMap<>();
		Map<Integer, List<OriginDiseaseKnowledge>> knowledgeMap = new HashMap<>();

		List<OriginDiseaseKnowledge> roots = new ArrayList<>();

		for (OriginDiseaseKnowledge k : diseaseKnowledges) {
			if (k.getParentId() == null) {
				roots.add(k);
			} else {
				Integer pid = k.getParentId();
				List<OriginDiseaseKnowledge> ks = knowledgeMap.get(pid);

				if (ks == null) {
					ks = new ArrayList<>();
					knowledgeMap.put(pid, ks);
				}
				ks.add(k);
			}
		}

		if (diseaseContents != null) {
			for (OriginDiseaseKnowledgeContent c : diseaseContents) {
				Integer kid = c.getKnowledgeId();
				List<DiseaseKnowledgeContent> contents = contentMap.get(kid);

				if (contents == null) {
					contents = new ArrayList<>();
					contentMap.put(kid, contents);
				}
				contents.add(new DiseaseKnowledgeContent(c));
			}
		}

		return g(roots, null, knowledgeMap, contentMap);
	}

	private List<DiseaseKnowledge> g(List<OriginDiseaseKnowledge> ks, DiseaseKnowledge parent, Map<Integer, List<OriginDiseaseKnowledge>> knowledgeMap,
			Map<Integer, List<DiseaseKnowledgeContent>> contentMap) {

		if (ks == null) {
			return new ArrayList<>();
		}

		List<DiseaseKnowledge> dks = new ArrayList<>();

		for (OriginDiseaseKnowledge k : ks) {

			DiseaseKnowledge dk = new DiseaseKnowledge(k);

			Integer kid = k.getId();
			List<OriginDiseaseKnowledge> children = knowledgeMap.get(kid);

			dk.parent = parent;
			dk.contents = contentMap.get(kid);
			dk.children = g(children, dk, knowledgeMap, contentMap);

			if (dk.contents == null)
				dk.contents = new ArrayList<>();

			dks.add(dk);
		}

		return dks;
	}

	public static class DiseaseKnowledge {

		OriginDiseaseKnowledge origin;

		boolean changed = false;
		boolean deleted = false;

		DiseaseKnowledge parent;
		List<DiseaseKnowledge> children = new ArrayList<>();
		List<DiseaseKnowledgeContent> contents = new ArrayList<>();

		DiseaseKnowledge(OriginDiseaseKnowledge origin) {
			this.origin = origin;
		}

	}

	public static class DiseaseKnowledgeContent {

		OriginDiseaseKnowledgeContent origin;
		boolean changed = false;
		boolean deleted = false;

		DiseaseKnowledgeContent(OriginDiseaseKnowledgeContent origin) {
			this.origin = origin;
		}

	}

}
