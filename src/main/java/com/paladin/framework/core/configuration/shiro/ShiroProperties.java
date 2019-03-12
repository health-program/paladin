package com.paladin.framework.core.configuration.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paladin.shiro")
public class ShiroProperties {

	/**
	 * 是否分布式集群
	 */
	private boolean cluster;

	/**
	 * token field，如果NULL则不用
	 */
	private String tokenField;

	/**
	 * session 保存在redis中key的前缀
	 */
	private String sessionPrefix = "shiro-session";

	/**
	 * session 在redis中缓存时间
	 */
	private int sessionTime = 30;

	/**
	 * session lastAccessTime 更新间隔
	 */
	private int accessTimeUpdateInterval = 60 * 1000;
	
	/**
	 * 静态资源前缀，多个可用逗号分隔，如果没有则为空
	 */
	private String staticResourcePrefix;

	/**
	 * 需要验证资源前缀，如果为空，表示除静态资源外所有
	 */
	private String authResourcePrefix = "/health/";
	
	/**
	 * 登录URL
	 */
	private String loginUrl = "/health/login";
	
	/**
	 * 登出URL
	 */
	private String logoutUrl = "/health/logout";
	
	/**
	 * 登录成功跳转URL
	 */
	private String successUrl = "/health/index";
	
	/**
	 * 未验证跳转页面
	 */
	private String unauthorizedUrl = "/static/html/error_401.html";
	
	/**
	 * APP请求标识
	 */
	private String appHeaderField = "isApp";
	
	public boolean isCluster() {
		return cluster;
	}

	public void setCluster(boolean cluster) {
		this.cluster = cluster;
	}

	public String getTokenField() {
		return tokenField;
	}

	public void setTokenField(String tokenField) {
		this.tokenField = tokenField;
	}

	public String getSessionPrefix() {
		return sessionPrefix;
	}

	public void setSessionPrefix(String sessionPrefix) {
		this.sessionPrefix = sessionPrefix;
	}

	public int getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(int sessionTime) {
		this.sessionTime = sessionTime;
	}

	public int getAccessTimeUpdateInterval() {
		return accessTimeUpdateInterval;
	}

	public void setAccessTimeUpdateInterval(int accessTimeUpdateInterval) {
		this.accessTimeUpdateInterval = accessTimeUpdateInterval;
	}

	public String getStaticResourcePrefix() {
		return staticResourcePrefix;
	}

	public void setStaticResourcePrefix(String staticResourcePrefix) {
		this.staticResourcePrefix = staticResourcePrefix;
	}

	public String getAuthResourcePrefix() {
		return authResourcePrefix;
	}

	public void setAuthResourcePrefix(String authResourcePrefix) {
		this.authResourcePrefix = authResourcePrefix;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public String getAppHeaderField() {
		return appHeaderField;
	}

	public void setAppHeaderField(String appHeaderField) {
		this.appHeaderField = appHeaderField;
	}
}
