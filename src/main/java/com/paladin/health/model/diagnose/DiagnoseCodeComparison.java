package com.paladin.health.model.diagnose;

public class DiagnoseCodeComparison {

	// 熙康编码ID
	private String xkCode;

	// 熙康名称
	private String xkName;

	// 昆山ICD10编码
	private String icd10Code;

	// ICD10对应名
	private String icd10Name;

	public String getXkCode() {
		return xkCode;
	}

	public void setXkCode(String xkCode) {
		this.xkCode = xkCode;
	}

	public String getXkName() {
		return xkName;
	}

	public void setXkName(String xkName) {
		this.xkName = xkName;
	}

	public String getIcd10Code() {
		return icd10Code;
	}

	public void setIcd10Code(String icd10Code) {
		this.icd10Code = icd10Code;
	}

	public String getIcd10Name() {
		return icd10Name;
	}

	public void setIcd10Name(String icd10Name) {
		this.icd10Name = icd10Name;
	}

}