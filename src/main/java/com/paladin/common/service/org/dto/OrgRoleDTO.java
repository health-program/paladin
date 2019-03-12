package com.paladin.common.service.org.dto;

import javax.validation.constraints.NotEmpty;

public class OrgRoleDTO {

	// id
	private String id;

	// 角色名称
	@NotEmpty(message = "角色名称不能为空")
	private String roleName;

	// 角色等级（考核权限等级）
	private Integer roleLevel;

	// 角色说明
	private String roleDesc;

	// 是否启用 1是0否
	private Integer enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

}