package com.paladin.health.core;

import org.apache.shiro.SecurityUtils;

import com.paladin.framework.core.UserSession;
import com.paladin.health.model.syst.OrgUser;

public class HealthUserSession extends UserSession{

	private boolean isSystemAdmin;	
	private boolean isUserAdmin;
	
	public HealthUserSession(String userId, String userName, String account) {
		super(userId, userName, account);
		isSystemAdmin = true;
	}

	public HealthUserSession(OrgUser orgUser, String account) {
		super(orgUser.getId(), orgUser.getName(), account);
		isSystemAdmin = false;
		isUserAdmin = orgUser.getIsAdmin() == OrgUser.BOOLEAN_YES;
	}

	private static final long serialVersionUID = 4854607722824823403L;

	/**
	 * 获取当前用户会话
	 * 
	 * @return
	 */
	public static HealthUserSession getCurrentUserSession() {
		return (HealthUserSession) SecurityUtils.getSubject().getPrincipal();
	}
	
	public boolean isSystemAdmin() {
		return isSystemAdmin;
	}
	
	public boolean isUserAdmin() {
		return isUserAdmin;
	}
	
}
