package com.paladin.health.data.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DietPageParser extends PageParser {

	public List<DietElement> parse(String disease) throws IOException {

		Document doc = getDocument("http://jbk.39.net/" + disease + "/ysbj/");

		Elements yinshiElements = doc.getElementsByClass("yinshi_table");
		if (yinshiElements == null || yinshiElements.size() == 0) {
			System.out.println("找不到疾病[" + disease + "]的饮食数据");
			return new ArrayList<>();
		}

		List<DietElement> dietElements = parseDietElement(yinshiElements);
		return dietElements;
	}

	private List<DietElement> parseDietElement(Elements yinshiElements) {

		List<DietElement> dietElements = new ArrayList<>();
		for (Element element : yinshiElements) {

			DietElement suitable = new DietElement(true);
			DietElement taboo = new DietElement(false);

			Elements children = element.children();
			if (children == null || children.size() == 0) {
				continue;
			}

			boolean suit = false;

			for (Element child : children) {

				String tagName = child.tag().getName();
				String text = child.text();
				text = repairHtmlText(text);

				if ("div".equals(tagName)) {
					suit = text.startsWith("饮食适宜");
					if (suit) {
						suitable.setContent(text);
					} else {
						taboo.setContent(text);
					}
				} else if ("table".equals(tagName)) {

					List<DietUnit> units = parseDietUnit(child);
					if (suit) {
						suitable.dietUnits = units;
					} else {
						taboo.dietUnits = units;
					}
				}
			}

			dietElements.add(suitable);
			dietElements.add(taboo);
		}

		return dietElements;
	}

	private List<DietUnit> parseDietUnit(Element table) {

		Elements trElements = table.child(0).children();
		List<DietUnit> units = new ArrayList<>();

		for (int i = 1; i < trElements.size(); i++) {
			Element tr = trElements.get(i);
			DietUnit unit = new DietUnit();

			unit.setFood(repairHtmlText(tr.child(0).text()));
			unit.setReason(repairHtmlText(tr.child(1).text()));
			unit.setSuggestion(repairHtmlText(tr.child(2).text()));
			units.add(unit);
		}

		return units;
	}

	public static class DietUnit {

		String food;
		String reason;
		String suggestion;

		public String getFood() {
			return food;
		}

		public void setFood(String food) {
			this.food = food;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public String getSuggestion() {
			return suggestion;
		}

		public void setSuggestion(String suggestion) {
			this.suggestion = suggestion;
		}

	}

	public static class DietElement {

		boolean suitable;
		String content;
		List<DietUnit> dietUnits = new ArrayList<>();

		public DietElement(boolean suitable) {
			this.suitable = suitable;
		}

		public boolean isTaboo() {
			return !suitable;
		}

		public boolean isSuitable() {
			return suitable;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public List<DietUnit> getDietUnits() {
			return dietUnits;
		}

		public void setDietUnits(List<DietUnit> dietUnits) {
			this.dietUnits = dietUnits;
		}
	}
}
