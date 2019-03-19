package com.paladin.health.model.diagnose;

import java.util.Date;

import javax.persistence.Id;

public class DiagnoseTargetFactor {

	public static final int FACTOR_TYPE_DISEASE = 1;
	public static final int FACTOR_TYPE_INDEX = 2;
	public static final int FACTOR_TYPE_RISK = 3;
	
	public static final int DEFAULT_FACTOR_LEVEL_HAVE = 1;
		
	@Id
	private String targetId;
	@Id
	private String factorCode;
	private Integer factorType;
	private Integer factorLevel;
	private Date createTime;

	public DiagnoseTargetFactor() {

	}

	public DiagnoseTargetFactor(String targetId, String code, Integer type, Integer level, Date createTime) {
		this.targetId = targetId;
		this.factorCode = code;
		this.factorType = type;
		this.factorLevel = level;
		this.createTime = createTime;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public Integer getFactorType() {
		return factorType;
	}

	public void setFactorType(Integer factorType) {
		this.factorType = factorType;
	}

	public Integer getFactorLevel() {
		return factorLevel;
	}

	public void setFactorLevel(Integer factorLevel) {
		this.factorLevel = factorLevel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
