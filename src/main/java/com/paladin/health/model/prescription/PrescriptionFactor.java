package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionFactor {

	@Id
	private String code;

	private String name;

	private String type;
	
	private String parentFactor;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentFactor() {
		return parentFactor;
	}

	public void setParentFactor(String parentFactor) {
		this.parentFactor = parentFactor;
	}

}