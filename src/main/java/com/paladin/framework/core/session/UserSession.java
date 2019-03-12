package com.paladin.framework.core.session;

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
	
	private String userId;
	private String userName;	
	private String account;
	
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

	/**
	 * 提供用户部分数据给视图展示
	 * @return
	 */
	public abstract Object getUserForView();
		
}
