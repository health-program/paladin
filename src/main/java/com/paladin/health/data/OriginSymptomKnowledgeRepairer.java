package com.paladin.health.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.utils.StringUtil;
import com.paladin.health.mapper.origin.OriginSymptomKnowledgeMapper;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.model.origin.OriginSymptomKnowledge;
import com.paladin.health.model.origin.OriginSymptomKnowledgeContent;
import com.paladin.health.service.origin.OriginDiseaseNameService;
import com.paladin.health.service.origin.OriginSymptomKnowledgeContentService;
import com.paladin.health.service.origin.OriginSymptomKnowledgeService;

@Component
public class OriginSymptomKnowledgeRepairer {

	private static Logger logger = LoggerFactory.getLogger(OriginSymptomKnowledgeRepairer.class);

	@Autowired
	OriginDiseaseNameService diseaseNameService;
	@Autowired
	OriginSymptomKnowledgeService symptomKnowledgeService;
	@Autowired
	OriginSymptomKnowledgeContentService symptomKnowledgeContentService;
	@Autowired
	OriginSymptomKnowledgeMapper symptomKnowledgeMapper;

	volatile int threadCount;

	public void repair() {

		final List<OriginDiseaseName> names = diseaseNameService.findAllSymptomName();

		int count = names.size();
		int i = 0;
		int size = 1000;
		boolean goon = true;

		logger.info("开始修复症状数据");

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
					logger.info("开始修复第" + s + "条到第" + e + "条症状知识");

					for (int x = s; x <= e; x++) {
						OriginDiseaseName name = names.get(x);
						try {
							repairSymptom(name);
						} catch (Exception e) {
							logger.error("修复症状[" + name.getNameKey() + "]出错", e);
						}
					}

					logger.info("成功修复第" + s + "条到第" + e + "条症状知识");
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
			logger.info("结束修复症状知识数据");
		}
	}

	public void repairSymptom(OriginDiseaseName symptomName) {

		String symptom = symptomName.getNameKey();

		List<OriginSymptomKnowledge> originSymptomKnowledges = symptomKnowledgeService.findAllSymptomKnowledge(symptom);
		List<OriginSymptomKnowledgeContent> originSymptomContents = symptomKnowledgeContentService.findSymptomContent(symptom);

		List<SymptomKnowledge> symptomKnowledges = assemble(originSymptomKnowledges, originSymptomContents);

		repaireKnowledge(symptomKnowledges, symptomName);
		persistence(symptomKnowledges, symptomName);
	}

	private void persistence(List<SymptomKnowledge> knowledges, OriginDiseaseName name) {

		for (SymptomKnowledge k : knowledges) {

			if (k.deleted) {

				SymptomKnowledge x = k.parent;
				while (x != null && x.deleted) {
					x = x.parent;
				}

				Integer id = x == null ? null : x.origin.getId();

				for (SymptomKnowledge ck : k.children) {
					if (!ck.deleted) {
						ck.origin.setParentId(id);
						symptomKnowledgeService.update(ck.origin);
						ck.changed = false;
					}
				}

				symptomKnowledgeService.removeByPrimaryKey(k.origin.getId());
				
				for (SymptomKnowledgeContent c : k.contents) {
					c.origin.setKnowledgeId(id);
					c.changed = true;
				}
			} else if (k.changed) {
				symptomKnowledgeMapper.updateByPrimaryKey(k.origin);
			}

			for (SymptomKnowledgeContent c : k.contents) {
				if (c.deleted) {
					symptomKnowledgeContentService.removeByPrimaryKey(c.origin.getId());
				} else if (c.changed) {
					symptomKnowledgeContentService.update(c.origin);
				}
			}

			persistence(k.children, name);
		}
	}

	private void repaireKnowledge(List<SymptomKnowledge> knowledges, OriginDiseaseName name) {

		String symptom = name.getNameKey();
		
		for (SymptomKnowledge k : knowledges) {

			String originTitle = k.origin.getName();
			String title = originTitle;

			if (title == null || title.length() == 0) {
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

					if (first >= '0' && first <= '9') {
						logger.info("数字开头[" + String.valueOf(first) + "][" + symptom + ":" + k.origin.getCategoryKey() + "]");
					}

					if(title.startsWith("的")) {
						title = title.substring(1);
					}

					if (!originTitle.equals(title)) {
						k.origin.setName(title);
						k.changed = true;
					}

					if ("".equals(title)) {
						k.deleted = true;
					}
				}

				repaireContent(k, symptom);
				repaireKnowledge(k.children, name);

				if (k.parent == null) {
				}
			}
		}
	}

	private void repaireContent(SymptomKnowledge knowledge, String symptom) {
		List<SymptomKnowledgeContent> contents = knowledge.contents;
		if (contents != null && contents.size() > 0) {

			for (int i = 0; i < contents.size(); i++) {
				SymptomKnowledgeContent dkc = contents.get(i);
				String orginContent = dkc.origin.getContent();
				String content = orginContent;
				content = StringUtil.strongTrim(content);

				if (content.length() == 0) {
					dkc.deleted = true;
					continue;
				}

				boolean hasCh = false;
				char[] cs = content.toCharArray();
				for (char c : cs) {
					if (isChinese(c)) {
						hasCh = true;
						break;
					}
				}

				if (!hasCh && i == contents.size() - 1) {
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

	static char[][] kuohaoChars = new char[][] { { '(', ')' }, { '（', '）' }, { '[', ']' }, { '【', '】' } };

	static char[] invalidChars = new char[] { ' ', '　',  '，',',','、', '.', '．', '。', ':', '：', '？', '?', '●', '◆', ')', ';', '·', '☆', '⊙', '▲' };

	private boolean isInvalidChar(char ch) {
		for (char c : invalidChars) {
			if (c == ch) {
				return true;
			}
		}
		return false;
	}

	private boolean isChinese(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;
	}

	private boolean isSpecial(char c) {
		return !((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= 0x4E00 && c <= 0x9FA5));
	}

	private List<SymptomKnowledge> assemble(List<OriginSymptomKnowledge> symptomKnowledges, List<OriginSymptomKnowledgeContent> symptomContents) {

		if (symptomContents == null || symptomContents.size() == 0) {
			return new ArrayList<>();
		}

		Map<Integer, List<SymptomKnowledgeContent>> contentMap = new HashMap<>();
		Map<Integer, List<OriginSymptomKnowledge>> knowledgeMap = new HashMap<>();

		List<OriginSymptomKnowledge> roots = new ArrayList<>();

		for (OriginSymptomKnowledge k : symptomKnowledges) {
			if (k.getParentId() == null) {
				roots.add(k);
			} else {
				Integer pid = k.getParentId();
				List<OriginSymptomKnowledge> ks = knowledgeMap.get(pid);

				if (ks == null) {
					ks = new ArrayList<>();
					knowledgeMap.put(pid, ks);
				}
				ks.add(k);
			}
		}

		if (symptomContents != null) {
			for (OriginSymptomKnowledgeContent c : symptomContents) {
				Integer kid = c.getKnowledgeId();
				List<SymptomKnowledgeContent> contents = contentMap.get(kid);

				if (contents == null) {
					contents = new ArrayList<>();
					contentMap.put(kid, contents);
				}
				contents.add(new SymptomKnowledgeContent(c));
			}
		}

		return g(roots, null, knowledgeMap, contentMap);
	}

	private List<SymptomKnowledge> g(List<OriginSymptomKnowledge> ks, SymptomKnowledge parent, Map<Integer, List<OriginSymptomKnowledge>> knowledgeMap,
			Map<Integer, List<SymptomKnowledgeContent>> contentMap) {

		if (ks == null) {
			return new ArrayList<>();
		}

		List<SymptomKnowledge> dks = new ArrayList<>();

		for (OriginSymptomKnowledge k : ks) {

			SymptomKnowledge dk = new SymptomKnowledge(k);

			Integer kid = k.getId();
			List<OriginSymptomKnowledge> children = knowledgeMap.get(kid);

			dk.parent = parent;
			dk.contents = contentMap.get(kid);
			dk.children = g(children, dk, knowledgeMap, contentMap);

			if (dk.contents == null)
				dk.contents = new ArrayList<>();

			dks.add(dk);
		}

		return dks;
	}

	public static class SymptomKnowledge {

		OriginSymptomKnowledge origin;

		boolean changed = false;
		boolean deleted = false;

		SymptomKnowledge parent;
		List<SymptomKnowledge> children = new ArrayList<>();
		List<SymptomKnowledgeContent> contents = new ArrayList<>();

		SymptomKnowledge(OriginSymptomKnowledge origin) {
			this.origin = origin;
		}

	}

	public static class SymptomKnowledgeContent {

		OriginSymptomKnowledgeContent origin;
		boolean changed = false;
		boolean deleted = false;

		SymptomKnowledgeContent(OriginSymptomKnowledgeContent origin) {
			this.origin = origin;
		}

	}

}
