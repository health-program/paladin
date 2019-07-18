package com.paladin.health.service.knowledge.vo;

import com.paladin.health.service.knowledge.dto.KnowledgeDetailInfo;

import java.util.List;

public class KnowledgeBaseDetailVO {
	private String id;

	//
	private String knowledgeId;

	private String title;

	// 原因(dd_c)
	private List<KnowledgeDetailInfo> cause;

	// 症状(dd_s)
	private List<KnowledgeDetailInfo> symptom;

	// 风险(dd_r)
	private List<KnowledgeDetailInfo> risk;

	// 生活方式(dd_l)
	private List<KnowledgeDetailInfo> lifestyle;

	// 饮食建议(dd_d)
	private List<KnowledgeDetailInfo> dietaryAdvice;

	// 饮食宜吃(dd_d_s)
	private List<KnowledgeDetailInfo> dietaryShouldEat;

	// 饮食忌吃(dd_d_a)
	private List<KnowledgeDetailInfo> dietaryAvoidEat;

	// 运动建议(dd_sa)
	private List<KnowledgeDetailInfo> sportsAdvice;

	// 运动宜做(dd_sa_s)
	private List<KnowledgeDetailInfo> sportsShouldDo;

	// 运动忌做(dd_sa_a)
	private List<KnowledgeDetailInfo> sportsAvoidDo;

	// 医疗保健(dd_m)
	private List<KnowledgeDetailInfo> medicalInsurance;

	// 就医复查指南(dd_g)
	private List<KnowledgeDetailInfo> medicalReviewGuide;

	// 生活常识(dd_n)
	private List<KnowledgeDetailInfo> lifeCommonSense;

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

	public List<KnowledgeDetailInfo> getCause() {
		return cause;
	}

	public void setCause(List<KnowledgeDetailInfo> cause) {
		this.cause = cause;
	}

	public List<KnowledgeDetailInfo> getSymptom() {
		return symptom;
	}

	public void setSymptom(List<KnowledgeDetailInfo> symptom) {
		this.symptom = symptom;
	}

	public List<KnowledgeDetailInfo> getRisk() {
		return risk;
	}

	public void setRisk(List<KnowledgeDetailInfo> risk) {
		this.risk = risk;
	}

	public List<KnowledgeDetailInfo> getLifestyle() {
		return lifestyle;
	}

	public void setLifestyle(List<KnowledgeDetailInfo> lifestyle) {
		this.lifestyle = lifestyle;
	}

	public List<KnowledgeDetailInfo> getDietaryAdvice() {
		return dietaryAdvice;
	}

	public void setDietaryAdvice(List<KnowledgeDetailInfo> dietaryAdvice) {
		this.dietaryAdvice = dietaryAdvice;
	}

	public List<KnowledgeDetailInfo> getDietaryShouldEat() {
		return dietaryShouldEat;
	}

	public void setDietaryShouldEat(List<KnowledgeDetailInfo> dietaryShouldEat) {
		this.dietaryShouldEat = dietaryShouldEat;
	}

	public List<KnowledgeDetailInfo> getDietaryAvoidEat() {
		return dietaryAvoidEat;
	}

	public void setDietaryAvoidEat(List<KnowledgeDetailInfo> dietaryAvoidEat) {
		this.dietaryAvoidEat = dietaryAvoidEat;
	}

	public List<KnowledgeDetailInfo> getSportsAdvice() {
		return sportsAdvice;
	}

	public void setSportsAdvice(List<KnowledgeDetailInfo> sportsAdvice) {
		this.sportsAdvice = sportsAdvice;
	}

	public List<KnowledgeDetailInfo> getSportsShouldDo() {
		return sportsShouldDo;
	}

	public void setSportsShouldDo(List<KnowledgeDetailInfo> sportsShouldDo) {
		this.sportsShouldDo = sportsShouldDo;
	}

	public List<KnowledgeDetailInfo> getSportsAvoidDo() {
		return sportsAvoidDo;
	}

	public void setSportsAvoidDo(List<KnowledgeDetailInfo> sportsAvoidDo) {
		this.sportsAvoidDo = sportsAvoidDo;
	}

	public List<KnowledgeDetailInfo> getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(List<KnowledgeDetailInfo> medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public List<KnowledgeDetailInfo> getMedicalReviewGuide() {
		return medicalReviewGuide;
	}

	public void setMedicalReviewGuide(List<KnowledgeDetailInfo> medicalReviewGuide) {
		this.medicalReviewGuide = medicalReviewGuide;
	}

	public List<KnowledgeDetailInfo> getLifeCommonSense() {
		return lifeCommonSense;
	}

	public void setLifeCommonSense(List<KnowledgeDetailInfo> lifeCommonSense) {
		this.lifeCommonSense = lifeCommonSense;
	}
}