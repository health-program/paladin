package com.paladin.health.model.knowledge;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class KnowledgeBaseDetail extends BaseModel {
	public static String COLUMN_KNOWLEDGE_ID="knowledgeId";

	@Id
	private String id;

	private String knowledgeId;

	// 名称(cr)
	private String title;

	// 原因(dd_c)
	private String cause;

	// 症状(dd_s)
	private String symptom;

	// 风险(dd_r)
	private String risk;

	// 生活方式(dd_l)
	private String lifestyle;

	// 饮食建议(dd_d)
	private String dietaryAdvice;

	// 饮食宜吃(dd_d_s)
	private String dietaryShouldEat;

	// 饮食忌吃(dd_d_a)
	private String dietaryAvoidEat;

	// 运动建议(dd_sa)
	private String sportsAdvice;

	// 运动宜做(dd_sa_s)
	private String sportsShouldDo;

	// 运动忌做(dd_sa_a)
	private String sportsAvoidDo;

	// 医疗保健(dd_m)
	private String medicalInsurance;

	// 就医复查指南(dd_g)
	private String medicalReviewGuide;

	// 生活常识(dd_n)
	private String lifeCommonSense;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getLifestyle() {
		return lifestyle;
	}

	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}

	public String getDietaryAdvice() {
		return dietaryAdvice;
	}

	public void setDietaryAdvice(String dietaryAdvice) {
		this.dietaryAdvice = dietaryAdvice;
	}

	public String getDietaryShouldEat() {
		return dietaryShouldEat;
	}

	public void setDietaryShouldEat(String dietaryShouldEat) {
		this.dietaryShouldEat = dietaryShouldEat;
	}

	public String getDietaryAvoidEat() {
		return dietaryAvoidEat;
	}

	public void setDietaryAvoidEat(String dietaryAvoidEat) {
		this.dietaryAvoidEat = dietaryAvoidEat;
	}

	public String getSportsAdvice() {
		return sportsAdvice;
	}

	public void setSportsAdvice(String sportsAdvice) {
		this.sportsAdvice = sportsAdvice;
	}

	public String getSportsShouldDo() {
		return sportsShouldDo;
	}

	public void setSportsShouldDo(String sportsShouldDo) {
		this.sportsShouldDo = sportsShouldDo;
	}

	public String getSportsAvoidDo() {
		return sportsAvoidDo;
	}

	public void setSportsAvoidDo(String sportsAvoidDo) {
		this.sportsAvoidDo = sportsAvoidDo;
	}

	public String getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public String getMedicalReviewGuide() {
		return medicalReviewGuide;
	}

	public void setMedicalReviewGuide(String medicalReviewGuide) {
		this.medicalReviewGuide = medicalReviewGuide;
	}

	public String getLifeCommonSense() {
		return lifeCommonSense;
	}

	public void setLifeCommonSense(String lifeCommonSense) {
		this.lifeCommonSense = lifeCommonSense;
	}

}