package com.paladin.health.model.sys;

import com.paladin.framework.common.UnDeleteBaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrgUser extends UnDeleteBaseModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String name;

	private String account;

	private String description;
	
	private Integer isAdmin;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}


}