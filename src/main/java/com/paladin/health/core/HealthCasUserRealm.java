package com.paladin.health.core;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.core.configuration.shiro.ShiroCasProperties;
import com.paladin.framework.core.session.UserSession;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;

public class HealthCasUserRealm extends Pac4jRealm {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private HealthUserSessionFactory userSessionFactory;
	
	private String idCardField;

	public HealthCasUserRealm(ShiroCasProperties shiroCasProperties) {	
		this.setAuthenticationTokenClass(Pac4jToken.class);
		this.idCardField = shiroCasProperties.getCasIdCardField();
	}

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

		logger.debug("后台登录：SysUserRealm.doGetAuthenticationInfo()");
		
		final Pac4jToken token = (Pac4jToken) authenticationToken;
        final List<CommonProfile> profiles = token.getProfiles();
        final Pac4jPrincipal principal = new Pac4jPrincipal(profiles, getPrincipalNameAttribute());
        
        String idcard = (String) principal.getProfile().getAttribute(idCardField);
        if(idcard == null || idcard.length() == 0) {
        	throw new UnknownAccountException();
        }
        
		String username = principal.getName();
		SysUser sysUser = sysUserService.getUserByAccount(username);

		if (sysUser == null) {
			throw new UnknownAccountException();
		}

		if (sysUser.getState() != SysUser.STATE_ENABLED) {
			throw new LockedAccountException(); // 帐号锁定
		}

		/*
		 * 获取权限信息:这里没有进行实现， 请自行根据UserInfo,Role,Permission进行实现； 获取之后可以在前端for循环显示所有链接;
		 */
		UserSession userSession = userSessionFactory.createSession(sysUser);

		// 加密方式;
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现

		// usersession 对象会存入session属性中，与session保持同步，并在session中实现权限控制
		List<Object> principals = Arrays.asList(userSession,principal);
        PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());       
        SimpleAuthenticationInfo authenticationInfo=  new SimpleAuthenticationInfo(principalCollection, token.getCredentials());

		logger.info("===>用户[" + username + ":" + userSession.getUserName() + "]登录系统<===");

		// 登录日志与更新最近登录时间
		sysUserService.updateLastTime(username);

		return authenticationInfo;
	}

	/**
	 * 此方法调用 hasRole,hasPermission的时候才会进行回调.
	 *
	 * 权限信息.(授权): 1、如果用户正常退出，缓存自动清空； 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 * 
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws AuthenticationException {
		// 废弃shiro缓存验证信息策略
		return null;
	}

	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			return null;
		}
		return (AuthorizationInfo) principals.getPrimaryPrincipal();
	}

}
