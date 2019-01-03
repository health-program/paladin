package com.paladin.common.core.permission;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.paladin.common.model.org.OrgPermission;
import com.paladin.common.model.org.OrgRole;
import com.paladin.framework.common.BaseModel;

public class Role {

	private String id;

	// 角色名称
	private String roleName;

	// 角色等级
	private int roleLevel;

	// 是否默认角色
	private boolean isDefault;

	// 是否启用
	private boolean enable;

	// 角色说明
	private String roleDesc;

	private HashMap<String, MenuPermission> menuPermissionMap;
	private Set<String> permissionCodeSet;

	public Role(OrgRole orgRole) {
		this.id = orgRole.getId();
		this.roleName = orgRole.getRoleName();
		this.roleLevel = orgRole.getRoleLevel();
		this.roleDesc = orgRole.getRoleDesc();
		this.isDefault = orgRole.getIsDefault() == BaseModel.BOOLEAN_YES;
		this.enable = orgRole.getEnable() == BaseModel.BOOLEAN_YES;

		this.menuPermissionMap = new HashMap<>();
		this.permissionCodeSet = new HashSet<>();
	}

	public void addPermission(OrgPermission orgPermission, Map<String, OrgPermission> allPermissionMap) {
		String code = orgPermission.getExpressionContent();
		if (code != null && code.length() > 0) {
			permissionCodeSet.add(code);
		}

		if (orgPermission.getIsAdmin() == BaseModel.BOOLEAN_YES) {
			String id = orgPermission.getId();
			MenuPermission menuPermission = menuPermissionMap.get(id);
			if (menuPermission == null) {
				menuPermissionMap.put(id, new MenuPermission(orgPermission, true));
			} else {
				menuPermission.setOwned(true);
			}

			String parentId = orgPermission.getParentId();
			while (parentId != null && parentId.length() > 0) {
				menuPermission = menuPermissionMap.get(parentId);
				if (menuPermission == null) {
					orgPermission = allPermissionMap.get(parentId);
					if (orgPermission != null) {
						menuPermissionMap.put(parentId, new MenuPermission(orgPermission, false));
						parentId = orgPermission.getParentId();
						continue;
					}
				}
				break;
			}
		}

	}

	public Collection<MenuPermission> getMenuPermissions() {
		return Collections.unmodifiableCollection(menuPermissionMap.values());
	}

	public MenuPermission getMenuPermission(String id) {
		return menuPermissionMap.get(id);
	}

	public boolean ownPermission(String code) {
		return permissionCodeSet.contains(code);
	}

	public boolean ownLevel(int roleLevel) {
		return this.roleLevel >= roleLevel;
	}

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

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
