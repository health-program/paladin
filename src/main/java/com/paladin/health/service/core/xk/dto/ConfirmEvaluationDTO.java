package com.paladin.health.service.core.xk.dto;

public class ConfirmEvaluationDTO {
	
	private String code;
	
	private Integer riskLevel;
	
	private String suggest;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}
	
}
