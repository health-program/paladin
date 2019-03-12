package com.paladin.health.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paladin.common.core.permission.MenuPermission;
import com.paladin.common.core.permission.PermissionContainer;
import com.paladin.common.core.permission.Role;
import com.paladin.framework.core.session.UserSession;
import com.paladin.health.model.org.OrgUser;

public class HealthUserSession extends UserSession implements AuthorizationInfo {

	private static final long serialVersionUID = -4164990537271107241L;

	public HealthUserSession(String userId, String userName, String account) {
		super(userId, userName, account);
	}

	public HealthUserSession(String userId, String userName, String account, boolean isSystemAdmin) {
		super(userId, userName, account);
		this.isSystemAdmin = isSystemAdmin;
	}
	
	public HealthUserSession(OrgUser orgUser, Role role) {
		super(orgUser.getId(),orgUser.getName(),orgUser.getAccount());
		this.agencyId = orgUser.getAgencyId();
		this.roleLevel = role.getRoleLevel();
	}


	/**
	 * 获取当前用户会话
	 * 
	 * @return
	 */
	public static HealthUserSession getCurrentUserSession() {
		return (HealthUserSession) SecurityUtils.getSubject().getPrincipal();
	}

	/** 默认最低级别 */
	public static int ROLE_LEVEL_DEFAULT = 1;
	/** 机构级别 */
	public static int ROLE_LEVEL_AGENCY = 2;
	/** 管理级别 */
	public static int ROLE_LEVEL_ADMIN = 9;

	private List<String> roleIds;
	private int roleLevel;
	private String agencyId;
	private boolean isSystemAdmin = false;

	/**
	 * 设置角色ID
	 * 
	 * @param roleIds
	 */
	public void setRoleId(String... roleIds) {
		int roleLevel = 0;
		for (int i = 0; i < roleIds.length; i++) {
			Role role = PermissionContainer.getInstance().getRole(roleIds[i]);
			roleIds[i] = role.getId();
			roleLevel = Math.max(roleLevel, role.getRoleLevel());
		}
		this.roleLevel = roleLevel;
		this.roleIds = Arrays.asList(roleIds);
	}

	/**
	 * 获取角色拥有的数据等级
	 * 
	 * @return
	 */
	public int getRoleLevel() {
		return roleLevel;
	}

	/**
	 * 是否管理系统级别
	 * 
	 * @return
	 */
	public boolean isAdminRoleLevel() {
		return ROLE_LEVEL_ADMIN == roleLevel || isSystemAdmin;
	}

	/**
	 * 获取所属机构ID
	 * 
	 * @return
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * 菜单资源
	 * 
	 * @return
	 */
	public Collection<MenuPermission> getMenuResources() {
		PermissionContainer container = PermissionContainer.getInstance();
		if (isSystemAdmin) {
			return container.getSystemAdminRole().getMenuPermissions();
		}

		if (roleIds.size() == 1) {
			return container.getRole(roleIds.get(0)).getMenuPermissions();
		}

		ArrayList<Role> roles = new ArrayList<>(roleIds.size());
		for (String rid : roleIds) {
			Role role = container.getRole(rid);
			if (role != null) {
				roles.add(role);
			}
		}
		return Role.getMultiRoleMenuPermission(roles);
	}

	@Override
	@JsonIgnore
	public Collection<String> getRoles() {
		return roleIds;
	}

	@Override
	@JsonIgnore
	public Collection<String> getStringPermissions() {
		return null;
	}

	@Override
	@JsonIgnore
	public Collection<Permission> getObjectPermissions() {
		PermissionContainer container = PermissionContainer.getInstance();
		if (isSystemAdmin) {
			return container.getSystemAdminRole().getPermissionObjects();
		}

		if (roleIds.size() == 1) {
			return container.getRole(roleIds.get(0)).getPermissionObjects();
		}

		ArrayList<Role> roles = new ArrayList<>(roleIds.size());
		for (String rid : roleIds) {
			Role role = container.getRole(rid);
			if (role != null) {
				roles.add(role);
			}
		}
		return Role.getMultiRolePermissionObject(roles);
	}

	@Override
	@JsonIgnore
	public Object getUserForView() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("username", getUserName());
		map.put("account", getAccount());
		return map;
	}

}
