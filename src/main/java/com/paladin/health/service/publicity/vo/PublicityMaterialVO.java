package com.paladin.health.service.publicity.vo;

import java.util.Date;

public class PublicityMaterialVO {

	private String id;

	// 工作周期
	private Integer workCycle;
	
	// 所属机构
	private String agencyId;
	
	// 所属机构
	private String agencyName;

	// 健康教育宣传资料名称
	private String name;

	// 健康教育宣传资料类型
	private Integer type;

	// 编发单位
	private String compileIssueUnit;

	// 数量
	private Integer count;

	// 日期
	private Date date;

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

	public String getCompileIssueUnit() {
		return compileIssueUnit;
	}

	public void setCompileIssueUnit(String compileIssueUnit) {
		this.compileIssueUnit = compileIssueUnit;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Integer getWorkCycle() {
		return workCycle;
	}

	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}