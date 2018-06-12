package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionFactorItem {

	public static final String COLUMN_FIELD_ITEM_ID = "itemId";

	@Id
	private String factorCode;

	@Id
	private Integer itemId;

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


}