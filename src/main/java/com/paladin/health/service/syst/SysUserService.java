package com.paladin.health.service.syst;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.secure.SecureUtil;
import com.paladin.health.config.BusinessConfig;
import com.paladin.health.model.syst.SysUser;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysUserService extends ServiceSupport<SysUser> {

	/**
	 * 创建一个账号
	 * 
	 * @param account
	 * @param userId
	 * @param type
	 * @return
	 */
	public int createUserAccount(String account, String userId) {

		if (account == null || !validateAccount(account))
			throw new BusinessException("账号不符合规则或者已经存在该账号");

		String salt = SecureUtil.createSalte();
		String password = BusinessConfig.getDefaultPassword();
		password = SecureUtil.createPassword(password, salt);

		SysUser user = new SysUser();
		user.setAccount(account);
		user.setPassword(password);
		user.setSalt(salt);
		user.setUserId(userId);
		user.setState(SysUser.STATE_ENABLED);
		user.setType(SysUser.TYPE_ORG_USER);

		return save(user);

	}

	private final static Pattern accountPattern = Pattern.compile("^\\w{5,30}$");
	private final static Pattern passwordPattern = Pattern.compile("^\\w{6,30}$");

	/**
	 * 验证账号
	 * 
	 * @param account
	 * @return true 可用/false 不可用
	 */
	public boolean validateAccount(String account) {
		if (!accountPattern.matcher(account).matches())
			return false;
		Example example = GeneralCriteriaBuilder.buildAnd(SysUser.class,
				new GeneralCriteriaBuilder.Condition(SysUser.COLUMN_FIELD_ACCOUNT, QueryType.EQUAL, account));
		return getSqlMapper().selectCountByExample(example) == 0;
	}

	/**
	 * 获取系统用户
	 * @param account
	 * @return
	 */
	public SysUser getUser(String account) {
		List<SysUser> users = searchAll(new GeneralCriteriaBuilder.Condition(SysUser.COLUMN_FIELD_ACCOUNT, QueryType.EQUAL, account));
		return (users != null && users.size() > 0) ? users.get(0) : null;
	}

	/**
	 * 更新登录人密码
	 * 
	 * @param password
	 * @return
	 */
	public int updateSelfPassword(String newPassword, String oldPassword) {

		if (newPassword == null || !passwordPattern.matcher(newPassword).matches()) {
			throw new BusinessException("密码不符合规则");
		}

		UserSession session = UserSession.getCurrentUserSession();
		String account = session.getAccount();
		SysUser user = getUser(account);
		if (user == null) {
			throw new BusinessException("账号异常");
		}

		oldPassword = SecureUtil.createPassword(oldPassword, user.getSalt());
		if (!oldPassword.equals(user.getPassword())) {
			throw new BusinessException("原密码不正确");
		}

		String salt = SecureUtil.createSalte();
		newPassword = SecureUtil.createPassword(newPassword, salt);

		SysUser updateUser = new SysUser();
		updateUser.setId(user.getId());
		updateUser.setSalt(salt);
		updateUser.setPassword(newPassword);

		int effect = updateSelective(updateUser);

		if (effect > 0) {
			SecurityUtils.getSubject().logout();
		}

		return effect;
	}

	/**
	 * 重置密码
	 * @param account
	 * @return
	 */
	public int resetPassword(String account) {
		
		SysUser sysUser = getUser(account);
		if (sysUser == null) {
			throw new BusinessException("账号异常");
		}

		String salt = SecureUtil.createSalte();
		String password = BusinessConfig.getDefaultPassword();
		password = SecureUtil.createPassword(password, salt);

		SysUser updateUser = new SysUser();
		updateUser.setId(sysUser.getId());
		updateUser.setSalt(salt);
		updateUser.setPassword(password);

		return updateSelective(updateUser);
	}



}
