package com.paladin.health.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.health.data.parser.knowledge.ArticleElement;
import com.paladin.health.data.parser.knowledge.ArticleElement.ElementType;
import com.paladin.health.data.parser.knowledge.ArticleTitle;
import com.paladin.health.data.parser.knowledge.SymptomPageParser;
import com.paladin.health.mapper.origin.OriginSymptomKnowledgeContentMapper;
import com.paladin.health.mapper.origin.OriginSymptomKnowledgeMapper;
import com.paladin.health.model.origin.OriginSymptomKnowledge;
import com.paladin.health.model.origin.OriginSymptomKnowledgeContent;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginSymptomKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Component
public class OriginSymptomKnowledgeReader {

	private static Logger logger = LoggerFactory.getLogger(OriginSymptomKnowledgeReader.class);

	static SymptomPageParser knowledgePageParser = new SymptomPageParser();

	public final static String[][] categories = new String[][] { { "zs", "简介" }, { "zzqy", "起因" }, { "zdxs", "诊断" } };

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
	OriginSymptomKnowledgeService symptomKnowledgeService;
	@Autowired
	OriginSymptomKnowledgeMapper symptomKnowledgeMapper;
	@Autowired
	OriginSymptomKnowledgeContentMapper symptomKnowledgeContentMapper;

	volatile int threadCount;

	public void readSymptomKnowledge() {

		logger.info("开始读取症状知识数据");

		final List<OriginDiseaseName> names = diseaseNameService.findAllSymptomName();

		int count = names.size();
		int i = 0;
		int size = 1000;
		boolean goon = true;

		logger.info("开始读取症状名称数据");

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
					logger.info("开始读取第" + s + "条到第" + e + "条症状知识");
					List<OriginDiseaseName> subnames = names.subList(s, e);
					readSymptomKnowledge(ti, subnames);
					logger.info("成功读取第" + s + "条到第" + e + "条症状知识");
					endThread();
				}
			});

			th.start();

			i = end;
		}
	}

	private void endThread() {
		if (--threadCount == 0) {
			logger.info("结束读取症状知识数据");
		}
	}

	public void readModifiedSymptomKnowledge(int base, List<String[]> params) {

		int baseEffect = 1000000;
		int kbase = base * baseEffect;
		int cbase = kbase;
		int underId = kbase + baseEffect;

		Integer kmax = symptomKnowledgeMapper.getMaxId(underId);
		Integer cmax = symptomKnowledgeMapper.getContentMaxId(underId);

		if (cmax != null && cmax > cbase) {
			cbase = cmax;
		}

		if (kmax != null && kmax > kbase) {
			kbase = kmax;
		}

		int[] ids = new int[] { kbase, cbase };

		List<String[]> errors = new ArrayList<>();

		for (String[] param : params) {

			String symptom = param[0];
			String categoryKey = param[1];
			String url = param[2];

			OriginDiseaseName name = diseaseNameService.getSymptomName(symptom);
			String symptomName = name.getName();

			if (symptomKnowledgeService
					.searchAll(new GeneralCriteriaBuilder.Condition[] { new GeneralCriteriaBuilder.Condition("symptomKey", QueryType.EQUAL, symptom),
							new GeneralCriteriaBuilder.Condition("categoryKey", QueryType.EQUAL, categoryKey) })
					.size() > 0) {
				logger.info("已经存在数据[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]");
				continue;
			}

			List<List<ArticleElement>> know = null;
			try {
				try {
					know = knowledgePageParser.parseArticle(url, symptom, categoryKey);
				} catch (IOException e) {
					logger.error("从网络获取数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]，尝试再次获取", e);
					try {
						know = knowledgePageParser.parseArticle(url, symptom, categoryKey);
					} catch (IOException e1) {
						logger.error("从网络获取数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]，放弃尝试", e);
						errors.add(param);
						continue;
					}
				}
			} catch (Exception e2) {
				logger.error("解析数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]", e2);
				errors.add(param);
				continue;
			}

			try {
				ArticleItem articleItem = doCategory(categoryKey, know == null ? new ArrayList<>() : know, true);
				saveSymptomKnowledge(articleItem, symptom, categoryKey, null, ids);
			} catch (Exception e) {
				logger.error("读取数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]", e);
				errors.add(param);
				continue;
			}
		}

		for (String[] error : errors) {
			System.out.println(error[0] + "/" + error[1]);
		}
	}

	public void readSymptomKnowledge(int base, List<OriginDiseaseName> names) {

		int baseEffect = 1000000;
		int kbase = base * baseEffect;
		int cbase = kbase;
		int underId = kbase + baseEffect;

		Integer kmax = symptomKnowledgeMapper.getMaxId(underId);
		Integer cmax = symptomKnowledgeMapper.getContentMaxId(underId);

		if (cmax != null && cmax > cbase) {
			cbase = cmax;
		}

		if (kmax != null && kmax > kbase) {
			kbase = kmax;
		}

		int[] ids = new int[] { kbase, cbase };
		boolean isNew = false;

		for (int x = 0; x < names.size(); x++) {
			OriginDiseaseName name = names.get(x);
			String symptom = name.getNameKey();
			String symptomName = name.getName();

			if (!isNew && symptomKnowledgeService.searchAll(new GeneralCriteriaBuilder.Condition("symptomKey", QueryType.EQUAL, symptom)).size() > 0) {
				continue;
			} else {
				isNew = true;
			}

			for (String[] category : categories) {
				String categoryKey = category[0];

				List<List<ArticleElement>> know = null;
				try {
					try {
						know = knowledgePageParser.parseArticle(symptom, categoryKey);
					} catch (IOException e) {
						logger.error("从网络获取数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]，尝试再次获取", e);
						try {
							know = knowledgePageParser.parseArticle(symptom, categoryKey);
						} catch (IOException e1) {
							logger.error("从网络获取数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]，放弃尝试", e);
							symptomKnowledgeMapper.deleteSymptomContent(symptom);
							symptomKnowledgeMapper.deleteSymptomKnowledge(symptom);
							logger.info("删除症状[" + symptom + "]数据");
							break;
						}
					}
				} catch (Exception e2) {
					logger.error("解析第" + x + "条数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]", e2);
					symptomKnowledgeMapper.deleteSymptomContent(symptom);
					symptomKnowledgeMapper.deleteSymptomKnowledge(symptom);
					logger.info("删除症状[" + symptom + "]数据");
					break;
				}

				try {
					ArticleItem articleItem = doCategory(categoryKey, know == null ? new ArrayList<>() : know);
					saveSymptomKnowledge(articleItem, symptom, categoryKey, null, ids);
				} catch (Exception e) {
					logger.error("读取第" + x + "条数据异常[" + symptomName + ":" + symptom + "][类型:" + categoryKey + "]", e);
					symptomKnowledgeMapper.deleteSymptomContent(symptom);
					symptomKnowledgeMapper.deleteSymptomKnowledge(symptom);
					logger.info("删除症状[" + symptom + "]数据");
					break;
				}
			}
		}
	}

	public void saveSymptomKnowledge(ArticleItem articleItem, String symptomKey, String categoryKey, Integer parentId, int[] ids) {
		OriginSymptomKnowledge k = new OriginSymptomKnowledge();

		int kid = ids[0];
		ids[0] = ++kid;

		k.setId(kid);
		k.setSymptomKey(symptomKey);
		k.setName(articleItem.title);
		k.setParentId(parentId);
		k.setCategoryKey(categoryKey);

		symptomKnowledgeMapper.insert(k);

		for (String content : articleItem.contents) {

			OriginSymptomKnowledgeContent kc = new OriginSymptomKnowledgeContent();
			kc.setContent(content);
			kc.setSymptomKey(symptomKey);
			kc.setKnowledgeId(kid);

			int cid = ids[1];
			ids[1] = ++cid;

			kc.setId(cid);
			symptomKnowledgeContentMapper.insert(kc);
		}

		for (ArticleItem child : articleItem.children) {
			saveSymptomKnowledge(child, symptomKey, categoryKey, kid, ids);
		}
	}

	public ArticleItem doCategory(String category, List<List<ArticleElement>> knowledge) {
		return doCategory(category, knowledge, false);
	}

	public ArticleItem doCategory(String category, List<List<ArticleElement>> knowledge, boolean ignoreError) {
		ArticleItem item = new ArticleItem();
		item.title = categoryNameMap.get(category)[0];

		if (knowledge == null) {
			return item;
		}

		for (List<ArticleElement> list : knowledge) {
			if (list.size() > 0) {
				Cursor cursor = new Cursor();
				cursor.setList(list);

				doContent(cursor, item, item, ignoreError);

				if (cursor.hasNext()) {
					throw new RuntimeException("处理章节错误，无法完整解析");
				}
			}
		}

		repair(item);

		return item;
	}

	private void doContent(Cursor cursor, ArticleItem item, ArticleItem main, boolean ignoreError) {

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

				doContent(cursor, child, main, ignoreError);

			} else {

				if (element.isLink()) {

					if (item.level > 1) {
						cursor.back();
						break;
					}

				} else {
					if (item.islink) {

						if (item.children.size() == 0) {

						} else {
							item.contents.add(element.getContent());
							continue;
						}

					} else {
						boolean iscontent = false;

						if (item.level == 1) {

							if (item.children.size() > 0) {
								ArticleItem c = item.children.get(0);
								if (!c.isCategory || c.islink || c.fontSize != element.getFontSize()) {
									iscontent = true;
								}
							}

						} else if (item.level == 2) {

							if (item.isCategory && item.islink) {
								if (item.children.size() > 0) {
									ArticleItem c = item.children.get(0);
									if (!c.isCategory || c.islink || c.fontSize != element.getFontSize()) {
										iscontent = true;
									}
								}
							} else {
								iscontent = true;
							}

						} else {

							ArticleItem l2 = null;
							ArticleItem l3 = null;
							ArticleItem p = item;

							while (p != null) {
								if (p.level == 3) {
									l3 = p;
								}
								if (p.level == 2) {
									l2 = p;
								}
								p = p.parent;
							}

							if (l2.isCategory) {
								if (l2.islink) {
									if (l3 != null && l3.isCategory && l3.fontSize == element.getFontSize()) {
										cursor.back();
										break;
									}
								} else {
									if (l2.fontSize == element.getFontSize()) {
										cursor.back();
										break;
									}
								}
							}

							iscontent = true;
						}

						if (iscontent) {
							item.contents.add(element.getContent());
							continue;
						}
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

				doContent(cursor, child, main, ignoreError);
			}
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
