package com.paladin.health.service.knowledge.vo;


public class KnowledgeBaseVO {

	// 
	private String id;

	// 
	private String code;

	// 
	private Integer type;

	// 名称(dn)
	private String name;

	// 所在科室(ad)
	private String department;

	// 检查目的(dm)
	private String inspectionPurpose;

	// 疾病概述(dm)
	private String diseaseOverview;

	// 判定标准(dc)
	private String judgementStandard;

	// 疾病分类(dc)
	private String diseaseClassification;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInspectionPurpose() {
		return inspectionPurpose;
	}

	public void setInspectionPurpose(String inspectionPurpose) {
		this.inspectionPurpose = inspectionPurpose;
	}

	public String getDiseaseOverview() {
		return diseaseOverview;
	}

	public void setDiseaseOverview(String diseaseOverview) {
		this.diseaseOverview = diseaseOverview;
	}

	public String getJudgementStandard() {
		return judgementStandard;
	}

	public void setJudgementStandard(String judgementStandard) {
		this.judgementStandard = judgementStandard;
	}

	public String getDiseaseClassification() {
		return diseaseClassification;
	}

	public void setDiseaseClassification(String diseaseClassification) {
		this.diseaseClassification = diseaseClassification;
	}

}