package com.paladin.health.data.parser.knowledge;

public class ArticleElement {
	
	ElementType type;
	ArticleTitle title;

	String content;
	String style;
	String tag;
	int fontSize = 999;
	boolean isLink;

	ArticleElement(ArticleTitle title) {
		this.type = ElementType.TYPE_TITLE;
		this.title = title;
		this.content = title.title;
	}

	ArticleElement(String content) {
		this.type = ElementType.TYPE_CONTENT;
		this.content = content;
	}

	ArticleElement(String title, String style, String tag) {
		this.type = ElementType.TYPE_CATEGORY;
		this.content = title;
		this.tag = tag;
		this.style = style;

		try {
			int s = style.indexOf("font-size:");
			int e = style.indexOf("px;");

			String d = style.substring(s + 10, e);
			d = d.trim();
			fontSize = Integer.valueOf(d);
		} catch (Exception e) {
			e.printStackTrace();
			fontSize = 14;
		}

		this.isLink = false;
	}

	ArticleElement(String title, boolean isLink) {
		this.type = ElementType.TYPE_CATEGORY;
		this.content = title;
		this.isLink = true;
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

	public static enum ElementType {
		TYPE_TITLE, TYPE_CATEGORY, TYPE_CONTENT;
	}

	public String getStyle() {
		return style;
	}

	public String getTag() {
		return tag;
	}

	public boolean isLink() {
		return isLink;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public void setTitle(ArticleTitle title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setLink(boolean isLink) {
		this.isLink = isLink;
	}
}
