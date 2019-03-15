package com.paladin.health.service.core.xk;

import java.util.List;

public class XKPeopleCondition {
	
	private String name;
	
	private String identificationId;
	
	private String cellphone;
	
	private List<String> diseases;
	
	private XKEvaluateCondition condition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public List<String> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<String> diseases) {
		this.diseases = diseases;
	}

	public XKEvaluateCondition getCondition() {
		return condition;
	}

	public void setCondition(XKEvaluateCondition condition) {
		this.condition = condition;
	}

	
	
	
}
