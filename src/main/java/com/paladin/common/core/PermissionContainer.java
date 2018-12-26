package com.paladin.common.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paladin.common.model.org.OrgRole;
import com.paladin.common.service.org.OrgPermissionService;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.framework.core.VersionContainer;


@Component
public class PermissionContainer implements VersionContainer {

	private static Logger logger = LoggerFactory.getLogger(PermissionContainer.class);

	@Autowired
	private OrgPermissionService permissionService;

	@Autowired
	private OrgRoleService orgRoleService;


	// 锁
	Object lock = new Object();

//	private static volatile Map<String, Role> roleMap;
//	private static volatile Map<String, MenuResource> menuMap;
//
//	private static volatile Set<String> adminPermissionCodes;
//	private static volatile List<MenuResource> adminMenuResources;

	public boolean initData() {

//		synchronized (lock) {
//			initPermission();
//			initRole();
//			
//			return true;
//		}
//
//		for (Role role : roleMap.values()) {
//			logger.info("===> 加载角色[" + role.getOrgRole().getRoleName() + "]<===");
//		}

		return true;
	}
	
	

	@Override
	public String getId() {
		return "permission_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		return initData();
	}

	

}
