package com.paladin.health.model.diagnose;

import java.util.Date;
import javax.persistence.Id;

public class DiagnoseRecord {

	@Id
	private String id;

	private String targetId;
	
	private String condition;

	private String prescription;

	private String correctPrescription;

	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

}