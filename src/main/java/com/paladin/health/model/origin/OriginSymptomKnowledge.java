package com.paladin.health.model.origin;

import javax.persistence.Id;

public class OriginSymptomKnowledge {

	public static final String COLUMN_FIELD_SYMPTOM_KEY = "symptomKey";

	@Id
	private Integer id;

	private String symptomKey;

	private String categoryKey;

	private String name;

	private Integer parentId;

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

	public String getCategoryKey() {
		return categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}