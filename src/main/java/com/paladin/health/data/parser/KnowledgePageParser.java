package com.paladin.health.data.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class KnowledgePageParser extends PageParser {

	private static Map<String, Integer> indexMap = new HashMap<>();
	private static Set<String> startMap = new HashSet<>();
	private static Set<String> endMap = new HashSet<>();

	static {
		indexMap.put("一", 1);
		indexMap.put("二", 2);
		indexMap.put("三", 3);
		indexMap.put("四", 4);
		indexMap.put("五", 5);
		indexMap.put("六", 6);
		indexMap.put("七", 7);
		indexMap.put("八", 8);
		indexMap.put("九", 9);
		indexMap.put("十", 10);
		indexMap.put("十一", 11);
		indexMap.put("十二", 12);
		indexMap.put("十三", 13);
		indexMap.put("十四", 14);
		indexMap.put("十五", 15);
		indexMap.put("十六", 16);
		indexMap.put("十七", 17);
		indexMap.put("十八", 18);
		indexMap.put("十九", 19);

		for (int i = 1; i < 50; i++) {
			indexMap.put(String.valueOf(i), i);
		}

		indexMap.put("①", 1);
		indexMap.put("②", 2);
		indexMap.put("③", 3);
		indexMap.put("④", 4);
		indexMap.put("⑤", 5);
		indexMap.put("⑥", 6);
		indexMap.put("⑦", 7);
		indexMap.put("⑧", 8);
		indexMap.put("⑨", 9);
		indexMap.put("⑩", 10);
		indexMap.put("⑪", 11);
		indexMap.put("⑫", 12);
		indexMap.put("⑬", 13);
		indexMap.put("⑭", 14);
		indexMap.put("⑮", 15);
		indexMap.put("⑯", 16);
		indexMap.put("⑰", 17);
		indexMap.put("⑱", 18);
		indexMap.put("⑲", 19);

		startMap.add("(");
		startMap.add("（");
		startMap.add("【");

		endMap.add(")");
		endMap.add("）");
		endMap.add(",");
		endMap.add("，");
		endMap.add(".");
		endMap.add("、");
		endMap.add("】");
		endMap.add("。");
		endMap.add("．");
	}

	public List<List<ArticleElement>> parse(String disease, String category) throws IOException {

		Document doc = getDocument("http://jbk.39.net/" + disease + "/" + category);

		Elements artElements = doc.getElementsByClass("art-box");

		if (artElements == null || artElements.size() == 0) {
			System.out.println("找不到疾病[" + disease + ":" + category + "]");
			return null;
		}

		List<List<ArticleElement>> articleElements = new ArrayList<>();

		for (Element artElement : artElements) {
			Elements pElements = artElement.children();
			List<ArticleElement> myElements = parseArticleElement(pElements);

			articleElements.add(myElements);
		}

		/* 更新时间
		 * Elements i3 = doc.getElementsByClass("i3");
		if (i3 != null && i3.size() > 0) {
			Elements i3spans = i3.get(0).children();
			if (i3spans != null && i3spans.size() > 0) {
				String time = i3spans.get(0).text();

				int yearIndex = time.indexOf("年");
				// int monthIndex = time.indexOf("月");
				// int dayIndex = time.indexOf("日");

				String year = time.substring(0, yearIndex);
				// String month = time.substring(yearIndex + 1, monthIndex);
				// String day = time.substring(monthIndex + 1, dayIndex);

				int y = Integer.valueOf(year);
				if (y > 2017) {
					System.out.println("[" + disease + ":" + category + "]更新时间大于2017年");
				}

			}
		}*/

		return articleElements;
	}

	private List<ArticleElement> parseArticleElement(Elements elements) {

		List<ArticleElement> myElements = new ArrayList<>(elements.size());

		for (int i = 0; i < elements.size(); i++) {

			Element element = elements.get(i);
			Tag tag = element.tag();

			if ("p".equals(tag.getName())) {
				String text = element.text();
				if (text == null || text.length() == 0) {
					continue;
				}

				text = repairHtmlText(text);
				String style = element.attr("style");

				if (style != null && style.contains("bold")) {
					ArticleTitle title = getTitle(text);

					if (title != null) {
						myElements.add(new ArticleElement(ElementType.TYPE_TITLE, title));
					} else {
						String clazz = element.attr("class");
						if ("target".equals(clazz)) {
							myElements.add(new ArticleElement(ElementType.TYPE_CATEGORY, text));
						} else {
							myElements.add(new ArticleElement(ElementType.TYPE_CONTENT, text));
						}
					}
				} else {
					myElements.add(new ArticleElement(ElementType.TYPE_CONTENT, text));
				}
			} else if ("dl".equals(tag.getName())) {
				String clazz = element.attr("class");
				if ("links".equals(clazz)) {
					Element dt = element.child(0);
					String content = dt == null ? element.text() : dt.text();
					content = repairHtmlText(content);
					myElements.add(new ArticleElement(ElementType.TYPE_CATEGORY, content));
				} else if ("intro".equals(clazz)) {
					break;
				} else {
					throw new RuntimeException("无法解析：" + element.outerHtml());
				}
			}
		}

		return myElements;
	}

	private ArticleTitle getTitle(String text) {

		if (text.length() < 3) {
			return null;
		}

		String s1 = text.substring(0, 1);
		String s2 = text.substring(1, 2);
		String s3 = text.substring(2, 3);
		String s4 = null;
		String s5 = null;

		if (text.length() >= 5) {
			s4 = text.substring(3, 4);
			s5 = text.substring(4, 5);
		}

		Integer index = null;
		String title = null;
		String style = "";
		int titleLevel = 1;
		int start = 0;

		Integer index1 = indexMap.get(s1);
		if (index1 != null) {
			char c = s1.charAt(0);
			if (c >= '①' && c <= '⑲') {
				titleLevel = 3;
			} else if (c >= '1' && c <= '9') {
				titleLevel = 2;
			} else {
				titleLevel = 1;
			}

			Integer index2 = indexMap.get(text.substring(0, 2));
			if (index2 != null) {
				index = index2;
				start = 2;
				if (endMap.contains(s3)) {
					start = 3;
				} else if (s4 != null && endMap.contains(s4)) {
					start = 4;
				}
				title = text.substring(start);
				style = text.substring(2, start);
			} else {
				index = index1;

				start = 1;
				if (endMap.contains(s2)) {
					start = 2;
				} else if (endMap.contains(s3)) {
					start = 3;
				}
				title = text.substring(start);
				style = text.substring(1, start);
			}
		} else {
			if (startMap.contains(s1)) {
				style += s1;
				Integer ix = indexMap.get(s2);
				if (ix != null) {
					char c = s2.charAt(0);
					if (c >= '①' && c <= '⑲') {
						titleLevel = 3;
					} else if (c >= '1' && c <= '9') {
						titleLevel = 2;
					} else {
						titleLevel = 1;
					}

					index = ix;
					ix = indexMap.get(text.substring(1, 3));
					if (ix != null) {
						index = ix;
						start = 3;
						if (s4 != null && endMap.contains(s4)) {
							start = 4;
						} else if (s5 != null && endMap.contains(s5)) {
							start = 5;
						}
						title = text.substring(start);
						style += text.substring(3, start);
					} else {
						start = 2;
						if (endMap.contains(s3)) {
							start = 3;
						} else if (s4 != null && endMap.contains(s4)) {
							start = 4;
						}
						title = text.substring(start);
						style += text.substring(2, start);
					}
				}
			}
		}

		if (index != null && title != null) {
			return new ArticleTitle(index, title, style, titleLevel);
		}

		return null;
	}

	public static enum ElementType {
		TYPE_TITLE, TYPE_CATEGORY, TYPE_CONTENT;
	}

	public static class ArticleElement {
		ElementType type;
		String content;
		ArticleTitle title;

		ArticleElement(ElementType type, ArticleTitle title) {
			this.type = type;
			this.title = title;
			this.content = title.title;

			repair();
		}

		ArticleElement(ElementType type, String content) {
			this.type = type;
			this.content = content;

			repair();
		}

		private void repair() {

			if (title != null) {
				if (content.startsWith(".") || content.startsWith("．")) {
					content = content.substring(1);
				}

				if (content.length() == 0) {
					return;
				}

				char c = content.charAt(content.length() - 1);
				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= 0x4E00 && c <= 0x9FA5)) {

				} else {
					content = content.substring(0, content.length() - 1);
				}

				title.title = content;
			}

			if (type == ElementType.TYPE_TITLE && (content.endsWith("：") || content.endsWith(":"))) {
				content = content.substring(0, content.length() - 1);
			}
		}

		public ElementType getType() {
			return type;
		}

		public String getContent() {
			return content;
		}

		public ArticleTitle getTitle() {
			return title;
		}

	}

	public static class ArticleTitle {

		String title;
		Integer index;
		String style;
		int titleLevel;

		ArticleTitle(Integer index, String title, String style, int titleLevel) {
			this.index = index;
			this.title = title;
			this.style = style;
			this.titleLevel = titleLevel;
		}

		public String getTitle() {
			return title;
		}

		public Integer getIndex() {
			return index;
		}

		public String getStyle() {
			return style;
		}

		public int getTitleLevel() {
			return titleLevel;
		}

	}

}
