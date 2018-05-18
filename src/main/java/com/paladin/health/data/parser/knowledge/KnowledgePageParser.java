package com.paladin.health.data.parser.knowledge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paladin.framework.utils.StringUtil;
import com.paladin.health.data.parser.PageParser;

public class KnowledgePageParser extends PageParser {

	private static Logger logger = LoggerFactory.getLogger(KnowledgePageParser.class);

	public Knowledge parse(String disease, String category) throws IOException {
		String url = "http://jbk.39.net/" + disease + "/" + category;
		return parse(url, disease, category);
	}

	public Knowledge parse(String url, String disease, String category) throws IOException {

		Document doc = getDocument(url);

		Elements artElements = doc.getElementsByClass("art-box");

		if (artElements == null || artElements.size() == 0) {
			logger.error("找不到疾病[" + disease + ":" + category + "]");
			return null;
		}

		List<List<ArticleElement>> articleElements = new ArrayList<>();

		for (Element artElement : artElements) {
			Elements pElements = artElement.children();
			List<ArticleElement> myElements = parseArticleElement(pElements);
			articleElements.add(myElements);
		}

		Date updateTime = null;

		try {
			Elements i3 = doc.getElementsByClass("i3");
			if (i3 != null && i3.size() > 0) {
				Elements i3spans = i3.get(0).children();
				if (i3spans != null && i3spans.size() > 0) {
					String time = i3spans.get(0).text();
					time = StringUtil.strongTrim(time);
					int yearIndex = time.indexOf("年");
					int monthIndex = time.indexOf("月");
					int dayIndex = time.indexOf("日");

					String year = time.substring(0, yearIndex);
					String month = time.substring(yearIndex + 1, monthIndex);
					String day = time.substring(monthIndex + 1, dayIndex);

					int y = Integer.valueOf(year);
					int m = Integer.valueOf(month);
					int d = Integer.valueOf(day);

					Calendar calendar = Calendar.getInstance();
					calendar.set(y, m - 1, d);

					updateTime = calendar.getTime();
				}
			}
		} catch (Exception e) {

		}

		Knowledge know = new Knowledge();
		know.articles = articleElements;
		know.updateTime = updateTime;

		return know;
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
					ArticleTitle title = ArticleTitle.getTitle(text);

					if (title != null) {
						myElements.add(new ArticleElement(title));
					} else {
						myElements.add(new ArticleElement(text, style, "p"));
					}
					
				} else {
					myElements.add(new ArticleElement(text));
				}
			} else if ("dl".equals(tag.getName())) {
				String clazz = element.attr("class");
				if ("links".equals(clazz)) {
					Element dt = element.child(0);
					String content = dt == null ? element.text() : dt.text();
					content = repairHtmlText(content);
					myElements.add(new ArticleElement(content, true));
				} else if ("intro".equals(clazz)) {
					break;
				} else {
					throw new RuntimeException("无法解析：" + element.outerHtml());
				}
			}
		}

		return myElements;
	}

}
