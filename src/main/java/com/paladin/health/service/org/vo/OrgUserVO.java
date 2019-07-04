package com.paladin.health.service.org.vo;

import com.paladin.common.core.permission.PermissionContainer;
import com.paladin.common.core.permission.Role;
import com.paladin.health.core.AgencyContainer;

public class OrgUserVO {

	// id
	private String id;

	// 姓名
	private String name;

	// 角色id
	private String roleId;

	// 机构ID
	private String agencyId;

	// 账号
	private String account;

	// 身份ID
	private String identificationId;

	// 描述
	private String description;

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

	public String getAgencyName() {
		return agencyId != null ? AgencyContainer.getAgencyName(agencyId) : null;
	}

	public String getRoleName() {
		if (roleId == null)
			return null;
		Role role = PermissionContainer.getInstance().getRole(roleId);
		return role != null ? role.getRoleName() : null;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}
}