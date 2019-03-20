package com.paladin.health.service.core.xk.response;

public class XKEvaluation {

	public final static int LEVEL_VERY_LOW = 1;
	public final static int LEVEL_LOW = 2;
	public final static int LEVEL_MIDDLE = 3;
	public final static int LEVEL_HIGH = 4;
	public final static int LEVEL_VERY_HIGH = 5;

	private String code;
	private String name;
	private int riskLevel;
	private String riskLevelName;
	private String suggest;
	
	public XKEvaluation() {
		
	}

	public XKEvaluation(String code, String name, int riskLevel, String riskLevelName, String suggest) {
		this.code = code;
		this.name = name;
		this.suggest = suggest;
		this.riskLevel = riskLevel;
		this.riskLevelName = riskLevelName;
	}

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

	public int getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskLevelName() {
		return riskLevelName;
	}

	public void setRiskLevelName(String riskLevelName) {
		this.riskLevelName = riskLevelName;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

}
