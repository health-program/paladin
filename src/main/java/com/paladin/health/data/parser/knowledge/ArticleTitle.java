package com.paladin.health.data.parser.knowledge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArticleTitle {

	String title;
	Integer index;

	int prefix = 0;
	static int PREFIX_KUOHAO = 1;

	int suffix = 0;
	static int SUFFIX_KUOHAO = 1;
	static int SUFFIX_OTHER = 2;

	int type;
	static int TYPE_CHINESE = 1;
	static int TYPE_NUMBER = 2;
	static int TYPE_SPECIAL = 3;

	private ArticleTitle() {
	}

	public String getTitle() {
		return title;
	}

	public Integer getIndex() {
		return index;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public boolean isSpecial() {
		return type == TYPE_SPECIAL;
	}

	public boolean isNext(ArticleTitle at) {
		if (at.index == index + 1) {
			if (at.type == type && at.prefix == prefix && at.suffix == suffix) {
				return true;
			}
		}

		if (at.type == TYPE_SPECIAL && type == TYPE_SPECIAL) {
			return true;
		}

		return false;
	}

	public static Map<String, Integer> indexMap = new HashMap<>();
	public static Set<String> startMap = new HashSet<>();
	public static Set<String> kuohaoEndMap = new HashSet<>();
	public static Set<String> otherEndMap = new HashSet<>();

	public static ArticleTitle getTitle(String text) {

		if (text.length() < 3) {
			return null;
		}

		if (text.startsWith("( ")) {
			text = "(" + text.substring(2);
		} else if (text.startsWith("（ ")) {
			text = "（" + text.substring(2);
		}

		String s1 = text.substring(0, 1);
		String s2 = text.substring(1, 2);

		ArticleTitle at = new ArticleTitle();

		Integer index = indexMap.get(s1);
		int start = 0;

		if (index == null) {
			if (startMap.contains(s1)) {
				at.prefix = PREFIX_KUOHAO;
				index = indexMap.get(s2);
				if (index != null) {
					start = 1;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}

		char c = text.charAt(start);

		if (c >= '①' && c <= '⑲') {
			at.type = TYPE_SPECIAL;
			at.suffix = SUFFIX_OTHER;
		} else if (c >= '㈠' && c <= '㈩') {
			at.type = TYPE_CHINESE;
			at.prefix = PREFIX_KUOHAO;
			at.suffix = SUFFIX_KUOHAO;
		} else if (c >= 0x4E00 && c <= 0x9FA5) {
			at.type = TYPE_CHINESE;
		} else if (c >= '⑴' && c <= '⑼') {
			at.type = TYPE_NUMBER;
			at.prefix = PREFIX_KUOHAO;
			at.suffix = SUFFIX_KUOHAO;
		} else if (c >= '⒈' && c <= '⒐') {
			at.type = TYPE_NUMBER;
			at.suffix = SUFFIX_OTHER;
		} else if (c >= '１' && c <= '９') {
			at.type = TYPE_NUMBER;
		} else if ((c >= '1' && c <= '9') || c == 'l') {
			at.type = TYPE_NUMBER;
		} else {
			throw new RuntimeException("??????");
		}

		if (at.suffix == 0) {
			Integer _index = indexMap.get(text.substring(start, start + 2));
			if (_index != null) {
				index = _index;
				start += 2;
			} else {
				start += 1;
			}

			String s = text.substring(start, start + 1);
			if (kuohaoEndMap.contains(s)) {
				at.suffix = SUFFIX_KUOHAO;
			} else if (otherEndMap.contains(s)) {
				at.suffix = SUFFIX_OTHER;
			} else {
				return null;
			}
		}

		start += 1;

		at.index = index;
		at.title = text.substring(start);
		return at;
	}

	static {

		indexMap.put("l", 1);

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

		indexMap.put("㈠", 1);
		indexMap.put("㈡", 2);
		indexMap.put("㈢", 3);
		indexMap.put("㈣", 4);
		indexMap.put("㈤", 5);
		indexMap.put("㈥", 6);
		indexMap.put("㈦", 7);
		indexMap.put("㈧", 8);
		indexMap.put("㈨", 9);
		indexMap.put("㈩", 10);

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

		indexMap.put("⒈", 1);
		indexMap.put("⒉", 2);
		indexMap.put("⒊", 3);
		indexMap.put("⒋", 4);
		indexMap.put("⒌", 5);
		indexMap.put("⒍", 6);
		indexMap.put("⒎", 7);
		indexMap.put("⒏", 8);
		indexMap.put("⒐", 9);

		indexMap.put("⑴", 1);
		indexMap.put("⑵", 2);
		indexMap.put("⑶", 3);
		indexMap.put("⑷", 4);
		indexMap.put("⑸", 5);
		indexMap.put("⑹", 6);
		indexMap.put("⑺", 7);
		indexMap.put("⑻", 8);
		indexMap.put("⑼", 9);

		indexMap.put("１", 1);
		indexMap.put("２", 2);
		indexMap.put("３", 3);
		indexMap.put("４", 4);
		indexMap.put("５", 5);
		indexMap.put("６", 6);
		indexMap.put("７", 7);
		indexMap.put("８", 8);
		indexMap.put("９", 9);
		indexMap.put("１０", 10);
		indexMap.put("１１", 11);
		indexMap.put("１２", 12);
		indexMap.put("１３", 13);
		indexMap.put("１４", 14);

		startMap.add("(");
		startMap.add("（");
		startMap.add("【");

		kuohaoEndMap.add(")");
		kuohaoEndMap.add("）");
		kuohaoEndMap.add("】");
		kuohaoEndMap.add("〗");
		kuohaoEndMap.add("]");

		otherEndMap.add(",");
		otherEndMap.add("，");
		otherEndMap.add(".");
		otherEndMap.add("、");
		otherEndMap.add("。");
		otherEndMap.add("．");
		otherEndMap.add(":");
		otherEndMap.add("：");
		otherEndMap.add(" ");
		otherEndMap.add("　");
	}

}
