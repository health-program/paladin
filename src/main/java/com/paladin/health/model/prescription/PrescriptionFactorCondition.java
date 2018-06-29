package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionFactorCondition {
	
	/** 默认*/
	public static final Integer TYPE_DEFAULT = 1;
	
	/** 推测是疾病 */
	public static final Integer TYPE_SPECULATE_DISEASE = 2;


	@Id
	private Integer id;

	private String itemKey;

	private String relation;

	private String value;

	private String factorCode;
	
	private String disease;
	
	private Integer type;
	
	private String illustration;
	
	private Integer conditionArrayId;


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

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConditionArrayId() {
		return conditionArrayId;
	}

	public void setConditionArrayId(Integer conditionArrayId) {
		this.conditionArrayId = conditionArrayId;
	}

}