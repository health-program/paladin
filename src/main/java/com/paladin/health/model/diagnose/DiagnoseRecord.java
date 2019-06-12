package com.paladin.health.model.diagnose;

import java.util.Date;
import javax.persistence.Id;

import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

public class DiagnoseRecord {

	public final static int SEND_STATUS_SUCCESS = 1;
	public final static int SEND_STATUS_FAIL = 2;
	public final static int SEND_STATUS_DEFAULT = -1;

	public final static String TYPE_XK = "XK";
	public final static String COLUMN_FIELD_TARGET_ID = "targetId";
	public final static String COLUMN_FIELD_SEARCH_ID = "searchId";
	public final static String COLUMN_FIELD_CREATE_BY = "createBy";

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

	private Date updateTime;

	private String createBy;

	private String searchId;

	private String message;

	private String sendMessage;

	private Integer sendStatus;

	private String sendError;
	
	private String sendCellphone;
	
	private String doctorName;
	
	private String hospitalName;
	
	private String confirmer;

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendError() {
		return sendError;
	}

	public void setSendError(String sendError) {
		this.sendError = sendError;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}

	public String getSendCellphone() {
		return sendCellphone;
	}

	public void setSendCellphone(String sendCellphone) {
		this.sendCellphone = sendCellphone;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}