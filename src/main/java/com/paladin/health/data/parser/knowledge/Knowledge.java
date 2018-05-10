package com.paladin.health.data.parser.knowledge;

import java.util.Date;
import java.util.List;

public class Knowledge {
	
	List<List<ArticleElement>> articles;
	Date updateTime;
	String  content;
	
	public List<List<ArticleElement>> getArticles() {
		return articles;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getContent() {
		return content;
	}
}
