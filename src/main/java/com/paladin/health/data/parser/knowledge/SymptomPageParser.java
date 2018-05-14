package com.paladin.health.data.parser.knowledge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paladin.health.data.parser.PageParser;

public class SymptomPageParser extends PageParser {

	private static Logger logger = LoggerFactory.getLogger(SymptomPageParser.class);

	public Object parse(String symptom) throws IOException {

		return null;
	}

	public String parseSummary(String symptom) throws IOException {
		String url = "http://jbk.39.net/zhengzhuang/" + symptom + "";

		Document doc = getDocument(url);

		Element introElement = doc.getElementById("intro");

		if (introElement == null) {

			Elements introElements = doc.getElementsByClass("intro");
			if (introElements == null || introElements.size() == 0) {
				logger.error("找不到症状简介[" + symptom + "]");
				return null;
			}

			introElement = introElements.get(0);
		}

		return introElement.text();
	}

	public List<List<ArticleElement>> parseArticle(String symptom, String category) throws IOException {
		String url = "http://jbk.39.net/zhengzhuang/" + symptom + "/" + category;
		return parseArticle(url, symptom, category);
	}

	public List<List<ArticleElement>> parseArticle(String url, String symptom, String category) throws IOException {

		List<List<ArticleElement>> result = new ArrayList<>();

		if (category == "zs") {
			String content = parseSummary(symptom);

			ArticleElement ae = new ArticleElement(content);
			ArrayList<ArticleElement> aes = new ArrayList<>();
			aes.add(ae);

			result.add(aes);
		} else {

			Document doc = getDocument(url);

			Elements artElements = doc.getElementsByClass("catalogItem");

			if (artElements == null || artElements.size() == 0) {
				logger.error("找不到症状起因[" + symptom + "]");
				return null;
			}

			for (Element element : artElements) {
				if (element.hasClass("item")) {
					result.add(parseArticleElement(element.children()));
				}
			}
		}

		return result;
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
						String clazz = element.attr("class");
						if ("target".equals(clazz)) {
							myElements.add(new ArticleElement(text, style, "p"));
						} else {
							myElements.add(new ArticleElement(text));
						}
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
