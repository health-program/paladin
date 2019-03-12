package com.paladin.health.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.health.data.parser.knowledge.ArticleElement;
import com.paladin.health.data.parser.knowledge.ArticleElement.ElementType;
import com.paladin.health.data.parser.knowledge.ArticleTitle;
import com.paladin.health.data.parser.knowledge.Knowledge;
import com.paladin.health.data.parser.knowledge.KnowledgePageParser;
import com.paladin.health.mapper.origin.OriginDiseaseKnowledgeContentMapper;
import com.paladin.health.mapper.origin.OriginDiseaseKnowledgeMapper;
import com.paladin.health.model.origin.OriginDiseaseKnowledge;
import com.paladin.health.model.origin.OriginDiseaseKnowledgeContent;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Component
public class OriginDiseaseKnowledgeReader {

	private static Logger logger = LoggerFactory.getLogger(OriginDiseaseKnowledgeReader.class);

	static KnowledgePageParser knowledgePageParser = new KnowledgePageParser();

	public final static String[][] categories = new String[][] { { "yyzl", "治疗" }, { "zztz", "典型症状", "症状" }, { "blby", "发病原因", "病因", "原因", "疾病病因" },
			{ "yfhl", "预防" }, { "jcjb", "临床检查", "检查" }, { "jb", "鉴别", "鉴别诊断" }, { "hl", "护理" }, { "ysbj", "饮食保健", "饮食" }, { "bfbz", "并发症" } };

	static Map<String, String[]> categoryNameMap = new HashMap<>();

	static {
		for (String[] ss : categories) {
			String[] v = new String[ss.length - 1];
			System.arraycopy(ss, 1, v, 0, ss.length - 1);
			categoryNameMap.put(ss[0], v);
		}
	}

	@Autowired
	OriginDiseaseNameService diseaseNameService;
	@Autowired
	OriginDiseaseKnowledgeService diseaseKnowledgeService;
	@Autowired
	OriginDiseaseKnowledgeMapper diseaseKnowledgeMapper;
	@Autowired
	OriginDiseaseKnowledgeContentMapper diseaseKnowledgeContentMapper;

	volatile int threadCount;

	public void readDiseaseKnowledge(Map<String, Set<String>> ignoreMap) {

		logger.info("开始读取疾病知识数据");

		final List<OriginDiseaseName> names = diseaseNameService.findAllDiseaseName();

		int count = names.size();
		int i = 0;
		int size = 1000;
		boolean goon = true;

		logger.info("开始读取疾病名称数据");

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
			final int e = end;
			final int ti = ++threadCount;

			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("开始读取第" + s + "条到第" + e + "条疾病知识");
					List<OriginDiseaseName> subnames = names.subList(s, e);
					readDiseaseKnowledge(ti, subnames, ignoreMap);
					logger.info("成功读取第" + s + "条到第" + e + "条疾病知识");
					endThread();
				}
			});

			th.start();

			i = end;
		}
	}

	private void endThread() {
		if (--threadCount == 0) {
			logger.info("结束读取疾病知识数据");
		}
	}

	public void readDiseaseKnowledgeIgnoreError(int base, String errorLogPath) {

		List<String> errorDiseases = getErrorDisease(errorLogPath);

		for (String disease : errorDiseases) {
			OriginDiseaseName name = diseaseNameService.getDiseaseName(disease);
			String diseaseName = name.getName();

			int baseEffect = 1000000;
			int kbase = base * baseEffect;
			int cbase = kbase;
			int underId = kbase + baseEffect;

			Integer kmax = diseaseKnowledgeMapper.getMaxId(underId);
			Integer cmax = diseaseKnowledgeMapper.getContentMaxId(underId);

			if (cmax != null && cmax > cbase) {
				cbase = cmax;
			}

			if (kmax != null && kmax > kbase) {
				kbase = kmax;
			}

			int[] ids = new int[] { kbase, cbase };
			boolean isNew = false;

			for (String[] category : categories) {
				String categoryKey = category[0];

				if (!isNew && diseaseKnowledgeService.searchAll(new Condition("diseaseKey", QueryType.EQUAL, disease)).size() > 0) {
					continue;
				} else {
					isNew = true;
				}

				Knowledge know = null;
				try {
					try {
						know = knowledgePageParser.parse(disease, categoryKey);
					} catch (IOException e) {
						logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，尝试再次获取", e);
						try {
							know = knowledgePageParser.parse(disease, categoryKey);
						} catch (IOException e1) {
							logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，放弃尝试", e);
							continue;
						}
					}
				} catch (Exception e2) {
					logger.error("解析数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e2);
					continue;
				}

				try {
					ArticleItem articleItem = doCategory(disease, categoryKey, know == null ? new ArrayList<>() : know.getArticles());
					saveDiseaseKnowledge(articleItem, disease, categoryKey, know == null ? null : know.getUpdateTime(), null, ids);
				} catch (Exception e) {
					logger.error("读取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e);
					continue;
				}
			}
		}
	}

	public void readErrorDiseaseKnowledge(int base, String errorLogPath) {

		List<String[]> errorDiseases = getErrorDisease2(errorLogPath);

		int baseEffect = 1000000;
		int kbase = base * baseEffect;
		int cbase = kbase;
		int underId = kbase + baseEffect;

		Integer kmax = diseaseKnowledgeMapper.getMaxId(underId);
		Integer cmax = diseaseKnowledgeMapper.getContentMaxId(underId);

		if (cmax != null && cmax > cbase) {
			cbase = cmax;
		}

		if (kmax != null && kmax > kbase) {
			kbase = kmax;
		}

		int[] ids = new int[] { kbase, cbase };

		List<String[]> errors = new ArrayList<>();

		for (String[] errorDisease : errorDiseases) {

			String disease = errorDisease[0];
			String categoryKey = errorDisease[1];

			OriginDiseaseName name = diseaseNameService.getDiseaseName(disease);
			String diseaseName = name.getName();

			Knowledge know = null;
			try {
				try {
					know = knowledgePageParser.parse(disease, categoryKey);
				} catch (IOException e) {
					logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，尝试再次获取", e);
					try {
						know = knowledgePageParser.parse(disease, categoryKey);
					} catch (IOException e1) {
						logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，放弃尝试", e);
						errors.add(errorDisease);
						continue;
					}
				}
			} catch (Exception e2) {
				logger.error("解析数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e2);
				errors.add(errorDisease);
				continue;
			}

			try {
				ArticleItem articleItem = doCategory(disease, categoryKey, know == null ? new ArrayList<>() : know.getArticles(), true);
				saveDiseaseKnowledge(articleItem, disease, categoryKey, know == null ? null : know.getUpdateTime(), null, ids);
			} catch (Exception e) {
				logger.error("读取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e);
				errors.add(errorDisease);
				continue;
			}
		}

		for (String[] error : errors) {
			System.out.println(error[0] + "/" + error[1]);
		}

	}

	public void readModifiedDiseaseKnowledge(int base, List<String[]> params) {

		int baseEffect = 1000000;
		int kbase = base * baseEffect;
		int cbase = kbase;
		int underId = kbase + baseEffect;

		Integer kmax = diseaseKnowledgeMapper.getMaxId(underId);
		Integer cmax = diseaseKnowledgeMapper.getContentMaxId(underId);

		if (cmax != null && cmax > cbase) {
			cbase = cmax;
		}

		if (kmax != null && kmax > kbase) {
			kbase = kmax;
		}

		int[] ids = new int[] { kbase, cbase };

		List<String[]> errors = new ArrayList<>();

		for (String[] param : params) {

			String disease = param[0];
			String categoryKey = param[1];
			String url = param[2];

			OriginDiseaseName name = diseaseNameService.getDiseaseName(disease);
			String diseaseName = name.getName();

			if (diseaseKnowledgeService
					.searchAll(new Condition("diseaseKey", QueryType.EQUAL, disease), new Condition("categoryKey", QueryType.EQUAL, categoryKey)).size() > 0) {
				logger.info("已经存在数据[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]");
				continue;
			}

			Knowledge know = null;
			try {
				try {
					know = knowledgePageParser.parse(url, disease, categoryKey);
				} catch (IOException e) {
					logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，尝试再次获取", e);
					try {
						know = knowledgePageParser.parse(disease, categoryKey);
					} catch (IOException e1) {
						logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，放弃尝试", e);
						errors.add(param);
						continue;
					}
				}
			} catch (Exception e2) {
				logger.error("解析数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e2);
				errors.add(param);
				continue;
			}

			try {
				ArticleItem articleItem = doCategory(disease, categoryKey, know == null ? new ArrayList<>() : know.getArticles(), true);
				saveDiseaseKnowledge(articleItem, disease, categoryKey, know == null ? null : know.getUpdateTime(), null, ids);
			} catch (Exception e) {
				logger.error("读取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e);
				errors.add(param);
				continue;
			}
		}

		for (String[] error : errors) {
			System.out.println(error[0] + "/" + error[1]);
		}

	}

	private List<String> getErrorDisease(String errorLogPath) {

		ArrayList<String> diseases = new ArrayList<>();

		FileReader reader;
		try {
			reader = new FileReader(errorLogPath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("找不到错误日志文件");
		}

		BufferedReader br = new BufferedReader(reader);
		try {
			String str = null;
			while ((str = br.readLine()) != null) {
				int s = str.indexOf("删除疾病");
				if (s >= 0) {
					int e = str.indexOf("数据");
					diseases.add(str.substring(s + 5, e - 1));
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("解析错误日志文件异常");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}

		return diseases;
	}

	private List<String[]> getErrorDisease2(String errorLogPath) {

		ArrayList<String[]> diseases = new ArrayList<>();

		FileReader reader;
		try {
			reader = new FileReader(errorLogPath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("找不到错误日志文件");
		}

		BufferedReader br = new BufferedReader(reader);
		try {
			String str = null;
			while ((str = br.readLine()) != null) {
				int s = str.indexOf("读取数据异常");
				if (s >= 0) {

					str = str.substring(s);
					s = str.indexOf(':');
					int e = str.indexOf(']');

					String disease = str.substring(s + 1, e);

					str = str.substring(e + 1);
					s = str.indexOf(':');
					e = str.indexOf(']');

					String category = str.substring(s + 1, e);

					diseases.add(new String[] { disease, category });
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("解析错误日志文件异常");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}

		return diseases;
	}

	public void readDiseaseKnowledge(int base, List<OriginDiseaseName> names, Map<String, Set<String>> ignoreMap) {

		int baseEffect = 1000000;
		int kbase = base * baseEffect;
		int cbase = kbase;
		int underId = kbase + baseEffect;

		Integer kmax = diseaseKnowledgeMapper.getMaxId(underId);
		Integer cmax = diseaseKnowledgeMapper.getContentMaxId(underId);

		if (cmax != null && cmax > cbase) {
			cbase = cmax;
		}

		if (kmax != null && kmax > kbase) {
			kbase = kmax;
		}

		int[] ids = new int[] { kbase, cbase };
		List<String[]> errors = new ArrayList<>();

		for (int x = 0; x < names.size(); x++) {
			OriginDiseaseName name = names.get(x);
			String disease = name.getNameKey();
			String diseaseName = name.getName();

			for (String[] category : categories) {
				String categoryKey = category[0];

				if (ignoreMap != null) {
					Set<String> cates = ignoreMap.get(disease);
					if (cates != null && cates.contains(categoryKey)) {
						logger.info("忽略爬取数据[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]");
						continue;
					}
				}

				Knowledge know = null;
				try {
					try {
						know = knowledgePageParser.parse(disease, categoryKey);
					} catch (IOException e) {
						logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，尝试再次获取", e);
						try {
							know = knowledgePageParser.parse(disease, categoryKey);
						} catch (IOException e1) {
							logger.error("从网络获取数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]，放弃尝试", e);
							errors.add(new String[] { disease, diseaseName });
							continue;
						}
					}
				} catch (Exception e2) {
					logger.error("解析第" + x + "条数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e2);
					errors.add(new String[] { disease, diseaseName });
					continue;
				}

				try {
					ArticleItem articleItem = doCategory(disease, categoryKey, know == null ? new ArrayList<>() : know.getArticles(), true);
					saveDiseaseKnowledge(articleItem, disease, categoryKey, know == null ? null : know.getUpdateTime(), null, ids);
				} catch (Exception e) {
					logger.error("读取第" + x + "条数据异常[" + diseaseName + ":" + disease + "][类型:" + categoryKey + "]", e);
					errors.add(new String[] { disease, diseaseName });
					continue;
				}
			}
		}

		for (String[] error : errors) {
			logger.info(error[0] + "/" + error[1]);
		}
	}

	public void saveDiseaseKnowledge(ArticleItem articleItem, String diseaseKey, String categoryKey, Date updateTime, Integer parentId, int[] ids) {
		OriginDiseaseKnowledge k = new OriginDiseaseKnowledge();

		int kid = ids[0];
		ids[0] = ++kid;

		k.setId(kid);
		k.setDiseaseKey(diseaseKey);
		k.setName(articleItem.title);
		k.setParentId(parentId);
		k.setCategoryKey(categoryKey);
		k.setUpdateTime(updateTime == null ? new Date() : updateTime);

		diseaseKnowledgeMapper.insert(k);

		for (String content : articleItem.contents) {

			OriginDiseaseKnowledgeContent kc = new OriginDiseaseKnowledgeContent();
			kc.setContent(content);
			kc.setDiseaseKey(diseaseKey);
			kc.setKnowledgeId(kid);

			int cid = ids[1];
			ids[1] = ++cid;

			kc.setId(cid);
			diseaseKnowledgeContentMapper.insert(kc);
		}

		for (ArticleItem child : articleItem.children) {
			saveDiseaseKnowledge(child, diseaseKey, categoryKey, updateTime, kid, ids);
		}
	}

	public ArticleItem doCategory(String disease, String category, List<List<ArticleElement>> knowledge) {
		return doCategory(disease, category, knowledge, false);
	}

	public ArticleItem doCategory(String disease, String category, List<List<ArticleElement>> knowledge, boolean ignoreError) {
		ArticleItem item = new ArticleItem();
		item.title = categoryNameMap.get(category)[0];

		if (knowledge == null) {
			return item;
		}

		for (List<ArticleElement> list : knowledge) {
			if (list.size() > 0) {
				Cursor cursor = new Cursor();
				cursor.setList(list);

				doContent(disease, category, cursor, item, item, ignoreError);

				if (cursor.hasNext()) {
					throw new RuntimeException("处理章节错误，无法完整解析");
				}
			}
		}

		repair(item);
		return item;
	}

	private void doContent(String disease, String category, Cursor cursor, ArticleItem item, ArticleItem main, boolean ignoreError) {

		Set<String> maybe = new HashSet<>();

		while (cursor.hasNext()) {
			ArticleElement element = cursor.next();
			ElementType type = element.getType();

			if (type == ElementType.TYPE_CONTENT) {
				item.contents.add(element.getContent());
			} else if (type == ElementType.TYPE_TITLE) {

				ArticleTitle title = element.getTitle();
				int index = title.getIndex();

				if (ignoreError && item.isCategory && item.islink) {
					title.setIndex(1);
				} else {
					if (index > 1) {
						if (item.articleTitle == null || !item.articleTitle.isNext(title)) {
							cursor.back();
							break;
						}
					}
				}

				ArticleItem child = new ArticleItem();
				child.parent = item;
				child.level = item.level + 1;
				child.title = element.getContent();
				child.style = element.getStyle();
				child.tag = element.getTag();
				child.isCategory = false;

				item.children.add(child);
				item.articleTitle = title;
				item.currentIndex++;

				doContent(disease, category, cursor, child, main, ignoreError);
			} else {

				if (element.isLink()) {
					if (item.level > 1) {
						cursor.back();
						break;
					}
				} else {

					ArticleItem p = item;
					boolean isChild = false;

					if (p.children.size() > 0) {
						ArticleItem c = p.getChildren().get(0);
						if (c.isCategory && !c.islink && c.fontSize == element.getFontSize()) {
							isChild = true;
						}
					}

					boolean hasParent = false;
					boolean hasTitleParent = false;

					if (!isChild) {
						while (p != null) {
							if (p.isCategory && !p.islink && p.fontSize == element.getFontSize()) {
								hasParent = true;
								break;
							}
							p = p.parent;
						}
					} else {
						while (p != null) {
							if (!p.isCategory) {
								hasTitleParent = true;
								break;
							}
							p = p.parent;
						}
					}

					if (hasParent) {
						cursor.back();
						break;
					}

					if (hasTitleParent || item.level > 3) {
						maybe.add(disease + ":" + category);
					}
				}

				String name = element.getContent();

				ArticleItem child = new ArticleItem();
				child.parent = item;
				child.level = item.level + 1;
				child.title = name;
				child.style = element.getStyle();
				child.tag = element.getTag();
				child.islink = element.isLink();
				child.fontSize = element.getFontSize();

				item.children.add(child);

				if (!item.isCategory) {
					item.currentIndex++;
				}

				doContent(disease, category, cursor, child, main, ignoreError);
			}
		}

		for (String str : maybe) {
			logger.info("可能存在数据解析问题[" + str + "]");
		}
	}

	private void repair(ArticleItem item) {

		/*
		 * 修复部分数据，标题头字符不正确，①排序错误
		 */
		char[] cs = new char[100];

		int i = 0;
		int s = -1;
		int e = 0;
		for (int j = 0; j < item.contents.size(); j++) {
			String content = item.contents.get(j);
			if (content.length() > 0) {
				char c = content.charAt(0);
				if (c >= '①' && c <= '⑲') {
					cs[i++] = c;

					if (s == -1) {
						s = j;
					}
					e = j;
				}
			}
		}

		if (i > 0) {

			boolean wrong = false;
			int b = '①';
			if (cs[0] != b) {
				wrong = true;
			} else {
				for (int m = 0; m < i; m++) {
					if (cs[m] != (b + m)) {
						wrong = true;
						break;
					}
				}
			}

			if (wrong) {
				if (i == (e - s + 1)) {
					// 可修复
					int k = 0;
					for (; s <= e; s++) {
						String content = item.contents.get(s);
						content = String.valueOf((char) (b + k)) + content.substring(1);
						k++;
						item.contents.set(s, content);
					}
				}
			}
		}

		if (item.children != null) {
			for (ArticleItem sub : item.children) {
				repair(sub);
			}
		}

	}

	private static class Cursor {

		int index;
		List<ArticleElement> list;

		void setList(List<ArticleElement> list) {
			/*
			 * boolean isWrong = false; int count = 0; // 处理分类和标题错误情况 for (int i = 0; i <
			 * list.size(); i++) { ArticleElement e = list.get(i); if (e.getType() ==
			 * ElementType.TYPE_CATEGORY) { i++; if (i >= list.size()) { break; }
			 * 
			 * e = list.get(i); if (e.getType() == ElementType.TYPE_TITLE) { if
			 * (e.getTitle().getIndex() != 1) { isWrong = true; break; } }
			 * 
			 * if (isWrong) { break; } } }
			 * 
			 * for (ArticleElement e : list) { if (e.getType() == ElementType.TYPE_CATEGORY)
			 * { count++; } }
			 * 
			 * if (isWrong || count == 1) { List<ArticleElement> newList = new
			 * ArrayList<>(); for (ArticleElement e : list) { if (e.getType() !=
			 * ElementType.TYPE_CATEGORY) { newList.add(e); } }
			 * 
			 * list = newList; }
			 */

			this.list = list;
		}

		boolean hasNext() {
			return index < list.size();
		}

		ArticleElement next() {
			if (index < list.size()) {
				return list.get(index++);
			}
			return null;
		}

		void back() {
			index--;
		}

	}

	public static class ArticleItem {

		String title;
		List<String> contents = new ArrayList<>();
		boolean isCategory = true;

		Integer currentIndex = 0;
		int level = 1;
		ArticleTitle articleTitle;

		String style;
		String tag;
		boolean islink = false;
		int fontSize;

		ArticleItem parent;
		List<ArticleItem> children = new ArrayList<>();

		public String getTitle() {
			return title;
		}

		public List<String> getContents() {
			return contents;
		}

		public List<ArticleItem> getChildren() {
			return children;
		}

	}

}
