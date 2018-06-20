package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionFactorCondition {

	@Id
	private String id;

	private String itemKey;

	private String relation;

	private String value;

	private String factorCode;
	
	private String conditionArrayId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getConditionArrayId() {
		return conditionArrayId;
	}

	public void setConditionArrayId(String conditionArrayId) {
		this.conditionArrayId = conditionArrayId;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

}