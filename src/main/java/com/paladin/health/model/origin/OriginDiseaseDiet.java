package com.paladin.health.model.origin;

import javax.persistence.Id;

public class OriginDiseaseDiet {

    public static final String COLUMN_FIELD_DISEASE_KEY = "diseaseKey";

	public static final int TYPE_SUITABLE = 1;
	public static final int TYPE_TABOO = 2;
	public static final int TYPE_SUITABLE_SUMMARY = 3;
	public static final int TYPE_TABOO_SUMMARY = 4;
	
	@Id
	private Integer id;

	private String diseaseKey;

	private Integer type;
	
	private String summary;

	private String food;

	private String reason;

	private String suggestion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiseaseKey() {
		return diseaseKey;
	}

	public void setDiseaseKey(String diseaseKey) {
		this.diseaseKey = diseaseKey;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}