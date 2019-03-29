package com.paladin.health.model.diagnose;

import java.util.Date;
import javax.persistence.Id;

import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

public class DiagnoseRecord {

	public final static String TYPE_XK = "XK";
	public final static String COLUMN_FIELD_TARGET_ID= "targetId";
	
	@Id
	private String id;

	private String targetId;
	
	private String factors;

	@IgnoreInMultipleResult
	private String targetCondition;
	@IgnoreInMultipleResult
	private String prescription;
	@IgnoreInMultipleResult
	private String correctPrescription;
	
	private String type;

	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getCorrectPrescription() {
		return correctPrescription;
	}

	public void setCorrectPrescription(String correctPrescription) {
		this.correctPrescription = correctPrescription;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetCondition() {
		return targetCondition;
	}

	public void setTargetCondition(String targetCondition) {
		this.targetCondition = targetCondition;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFactors() {
		return factors;
	}

	public void setFactors(String factors) {
		this.factors = factors;
	}

}