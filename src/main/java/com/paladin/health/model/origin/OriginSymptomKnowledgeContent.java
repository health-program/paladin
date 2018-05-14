package com.paladin.health.model.origin;

import javax.persistence.Id;

public class OriginSymptomKnowledgeContent {

	public static final String COLUMN_FIELD_SYMPTOM_KEY = "symptomKey";
	
	public static final String COLUMN_FIELD_KNOWLEDGE_ID = "knowledgeId";
	
	@Id
	private Integer id;

	private String symptomKey;

	private String content;

	private Integer knowledgeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymptomKey() {
		return symptomKey;
	}

	public void setSymptomKey(String symptomKey) {
		this.symptomKey = symptomKey;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

}