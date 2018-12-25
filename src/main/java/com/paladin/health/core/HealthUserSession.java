package com.paladin.health.core;

import org.apache.shiro.SecurityUtils;
import com.paladin.framework.core.UserSession;
import com.paladin.health.model.org.OrgUser;

public class HealthUserSession extends UserSession {

	private static final long serialVersionUID = 4854607722824823403L;
	public final static int DATA_LEVEL_ADMIN = 1;
	public final static int DATA_LEVEL_AGENCY = 2;

	public HealthUserSession(String userId, String userName, String account, int dataLevel, boolean isSystemAdmin) {
		super(userId, userName, account);
		this.dataLevel = dataLevel;
		this.isSystemAdmin = isSystemAdmin;
	}

	public HealthUserSession(OrgUser orgUser) {
		this(orgUser.getId(), orgUser.getName(), orgUser.getAccount(), 2, false);
		this.agencyId = orgUser.getAgencyId();
	}

	private boolean isSystemAdmin;
	private int dataLevel;
	private String agencyId;

	/**
	 * 是否系统管理员
	 * @return
	 */
	public boolean isSystemAdmin() {
		return isSystemAdmin;
	}

	/**
	 * 是否管理数据等级
	 * @return
	 */
	public boolean isAdminDataLevel() {
		return dataLevel == DATA_LEVEL_ADMIN;
	}

	/**
	 * 是否机构数据等级
	 * @return
	 */
	public boolean isAgencyDataLevel() {
		return dataLevel == DATA_LEVEL_AGENCY;
	}
	
	/**
	 * 获取机构（简单处理只考虑一个机构情况）
	 * @return
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * 获取用户类型
	 * @return
	 */
	public boolean getIsSystemAdmin() {
		return isSystemAdmin;
	}

	/**
	 * 获取当前用户会话
	 * 
	 * @return
	 */
	public static HealthUserSession getCurrentUserSession() {
		return (HealthUserSession) SecurityUtils.getSubject().getPrincipal();
	}



}
