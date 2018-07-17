package com.paladin.framework.core;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;

/**
 * 用户会话信息
 * 
 * @author TontoZhou
 * @since 2018年1月29日
 */
public class UserSession implements Serializable {

	private static final long serialVersionUID = 7077877290125259117L;
	
	String userId;
	String userName;	
	String account;
	
	public UserSession(String userId, String userName, String account) {
		this.userId = userId;
		this.userName = userName;
		this.account = account;
	}
	
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
