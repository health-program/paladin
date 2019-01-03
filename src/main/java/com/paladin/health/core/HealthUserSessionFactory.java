package com.paladin.health.core;

import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.core.permission.PermissionContainer;
import com.paladin.common.core.permission.Role;
import com.paladin.framework.core.UserSession;
import com.paladin.health.model.org.OrgUser;
import com.paladin.health.model.syst.SysUser;
import com.paladin.health.service.org.OrgUserService;

@Component
public class HealthUserSessionFactory {

	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private PermissionContainer permissionContainer;

	public UserSession createSession(SysUser sysUser) {
		int type = sysUser.getType();
		HealthUserSession userSession = null;
		if (type == SysUser.TYPE_ADMIN) {
			userSession = new HealthUserSession("admin", "系统管理员", sysUser.getAccount(), HealthUserSession.DATA_LEVEL_ADMIN, true);
		} else if (type == SysUser.TYPE_ORG_USER) {
			OrgUser orgUser = orgUserService.get(sysUser.getUserId());
			if (orgUser == null) {
				throw new DisabledAccountException();
			}
			Role role = permissionContainer.getRole(orgUser.getRoleId());
			userSession = new HealthUserSession(orgUser, role);
		}
		return userSession;
	}

}
