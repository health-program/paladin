package com.paladin.health.core;

import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.core.permission.PermissionContainer;
import com.paladin.common.core.permission.Role;
import com.paladin.common.model.syst.SysUser;
import com.paladin.framework.core.session.UserSession;
import com.paladin.health.model.org.OrgUser;
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
			userSession = new HealthUserSession(sysUser.getId(), "系统管理员", sysUser.getAccount(), true);
		} else if (type == SysUser.TYPE_ORG_USER) {
			OrgUser orgUser = orgUserService.get(sysUser.getUserId());
			if (orgUser == null) {
				throw new DisabledAccountException();
			}
			Role role = permissionContainer.getRole(orgUser.getRoleId());
			if (role == null) {
				throw new DisabledAccountException();
			}
			userSession = new HealthUserSession(orgUser, role);
		}
		return userSession;
	}

}
