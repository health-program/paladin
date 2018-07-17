package com.paladin.health.controller.sys.pojo;

import javax.validation.constraints.NotEmpty;

import com.paladin.framework.core.SimpleBeanCopier.IgnoredIfNeed;

public class OrgUserDTO {
	
	private String id;

	@NotEmpty(message = "名称不能为空")
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

	@IgnoredIfNeed
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
