package com.paladin.health.model.org;

import javax.persistence.Id;

import com.paladin.framework.common.UnDeleteBaseModel;

public class OrgAgency extends UnDeleteBaseModel{

	// 
	@Id
	private String id;

	// 机构名称
	private String name;
	
	// 父机构
	private String parentId;

	// 备注
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}