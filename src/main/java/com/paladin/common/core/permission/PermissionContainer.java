package com.paladin.common.core.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.model.org.OrgPermission;
import com.paladin.common.model.org.OrgRole;
import com.paladin.common.model.org.OrgRolePermission;
import com.paladin.common.service.org.OrgPermissionService;
import com.paladin.common.service.org.OrgRolePermissionService;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.framework.core.VersionContainer;

@Component
public class PermissionContainer implements VersionContainer {

	private static Logger logger = LoggerFactory.getLogger(PermissionContainer.class);

	@Autowired
	private OrgPermissionService orgPermissionService;

	@Autowired
	private OrgRoleService orgRoleService;

	@Autowired
	private OrgRolePermissionService orgRolePermissionService;

	private volatile Map<String, Role> roleMap;

	private volatile Map<String, OrgPermission> permissionMap;

	/**
	 * 初始化权限
	 */
	public void initPermission() {
		logger.info("------------初始化权限开始------------");
		
		Map<String, OrgPermission> permissionMap = new HashMap<>();
		List<OrgPermission> orgPermissions = orgPermissionService.findAll();
		for (OrgPermission orgPermission : orgPermissions) {
			permissionMap.put(orgPermission.getId(), orgPermission);
		}
		this.permissionMap = permissionMap;
		initRole();
		logger.info("------------初始化权限结束------------");
	}

	/**
	 * 初始化角色和角色授予的权限
	 */
	public void initRole() {
		logger.info("------------初始化角色开始------------");
		List<OrgRole> orgRoles = orgRoleService.findAll();
		Map<String, Role> roleMap = new HashMap<>();
		for (OrgRole orgRole : orgRoles) {
			Role role = new Role(orgRole);
			roleMap.put(role.getId(), role);
		}

		List<OrgRolePermission> orgRolePermissions = orgRolePermissionService.findAll();
		for (OrgRolePermission orgRolePermission : orgRolePermissions) {
			OrgPermission permission = permissionMap.get(orgRolePermission.getPermissionId());
			Role role = roleMap.get(orgRolePermission.getRoleId());
			role.addPermission(permission, permissionMap);
		}

		this.roleMap = roleMap;
		logger.info("------------初始化权限结束------------");
	}

	/**
	 * 获取角色
	 * @param id
	 * @return
	 */
	public Role getRole(String id) {
		return roleMap.get(id);
	}
	
	
	@Override
	public String getId() {
		return "permission_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		initPermission();
		return true;
	}

}
