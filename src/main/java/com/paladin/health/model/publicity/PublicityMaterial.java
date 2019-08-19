package com.paladin.health.model.publicity;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;
import java.util.Date;

public class PublicityMaterial extends BaseModel {

	// 
	@Id
	private String id;

	// 工作周期
	private Integer workCycle;

	// 所属机构
	private String agencyId;

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

	private String attachments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getWorkCycle() {
		return workCycle;
	}

	public void setWorkCycle(Integer workCycle) {
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
}