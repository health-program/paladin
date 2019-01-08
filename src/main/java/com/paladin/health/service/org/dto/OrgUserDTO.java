package com.paladin.health.service.org.dto;

import javax.validation.constraints.NotEmpty;

public class OrgUserDTO {

    // id
    private String id;

	// 姓名
    @NotEmpty
	private String name;

	// 角色id
    @NotEmpty
	private String roleId;

	// 机构ID
	private String agencyId;

	// 账号
    @NotEmpty
	private String account;

	// 描述
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}