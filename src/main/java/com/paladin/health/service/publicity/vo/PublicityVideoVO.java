package com.paladin.health.service.publicity.vo;

import java.util.Date;

public class PublicityVideoVO {

	// 音像id
	private String id;

	// 工作周期
	private String workCycle;

	// 所属机构
	private String agencyId;

	// 音像名称
	private String name;

	// 制作单位
	private String makeUnit;

	// 数量
	private Integer count;

	// 备注
	private String remarks;

	// 创建时间
	private Date createTime;

	// 创建人
	private String createUserId;

	// 更新时间
	private Date updateTime;

	// 更新人
	private String updateUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkCycle() {
		return workCycle;
	}

	public void setWorkCycle(String workCycle) {
		this.workCycle = workCycle;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMakeUnit() {
		return makeUnit;
	}

	public void setMakeUnit(String makeUnit) {
		this.makeUnit = makeUnit;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

}