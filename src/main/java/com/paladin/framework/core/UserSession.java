package com.paladin.framework.core;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;

/**
 * 用户会话信息
 * 
 * @author TontoZhou
 * @since 2018年1月29日
 */
public abstract class UserSession implements Serializable {

	private static final long serialVersionUID = 7077877290125259117L;
	
	protected String userId;
	protected String userName;	
	protected String account;
	
	
	public UserSession(String userId, String userName, String account) {
		this.userId = userId;
		this.userName = userName;
		this.account = account;
	}
	
	/**
	 * 是否拥有权限
	 * @param code
	 * @return
	 */
	public abstract boolean ownPermission(String code);
	
	/**
	 * 是否拥有等级
	 * @param level
	 * @return
	 */
	public abstract boolean ownLevel(int level);
	
	/**
	 * 获取当前用户会话
	 * 
	 * @return
	 */
	public static UserSession getCurrentUserSession() {
		return (UserSession) SecurityUtils.getSubject().getPrincipal();
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getAccount() {
		return account;
	}
}
