package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionFactorItem {

	public static final String COLUMN_FIELD_ITEM_ID = "itemId";

	@Id
	private String factorCode;

	@Id
	private String itemId;

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}