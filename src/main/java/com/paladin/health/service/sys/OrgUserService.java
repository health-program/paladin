package com.paladin.health.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BadRequestException;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.sys.OrgUser;

@Service
public class OrgUserService extends ServiceSupport<OrgUser> {

	@Autowired
	private SysUserService sysUserService;

	public int updateUser(OrgUser user) {
		
		String id = user.getId();
		if (id == null || id.length() == 0) {
			throw new BadRequestException();
		} 
		
		OrgUser oldUser = get(id);
		
		if (oldUser == null) {
			throw new BusinessException("编辑的用户不存在");
		}
		
		HealthUserSession session = HealthUserSession.getCurrentUserSession();		
		boolean isSelf = session.getUserId().equals(id);
		
		Integer oldIsAdmin = oldUser.getIsAdmin();
		Integer isAdmin = user.getIsAdmin();
		
		if(!oldIsAdmin.equals(isAdmin))
		{
			if(isSelf) {
				throw new BusinessException("您不能修改自己的管理权限");
			}

			if (isAdmin == 1) {
				if (!session.isSystemAdmin()) {
					throw new BusinessException("您没有权限创建管理人员账号");
				}
			} else if (isAdmin == 0) {
				if (!session.isSystemAdmin() && !session.isUserAdmin()) {
					throw new BusinessException("您没有权限创建人员账号");
				}
			} else {
				throw new BadRequestException();
			}
		}	
		return update(user);
	}

	@Transactional
	public int addUser(OrgUser user) {
		int effect = 0;
		
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		Integer isAdmin = user.getIsAdmin();
		if (isAdmin == 1) {
			if (!session.isSystemAdmin()) {
				throw new BusinessException("您没有权限创建管理人员账号");
			}
		} else if (isAdmin == 0) {
			if (!session.isSystemAdmin() && !session.isUserAdmin()) {
				throw new BusinessException("您没有权限创建人员账号");
			}
		} else {
			throw new BadRequestException();
		}

		// 创建用户账号并保存
		String userId = UUIDUtil.createUUID();
		user.setId(userId);
		if (sysUserService.createUserAccount(user.getAccount(), userId) > 0) {
			effect = save(user);
		}
		return effect;
	}
		

	public int removeUser(String id) {
		OrgUser user = get(id);
		if (user == null) {
			throw new BusinessException("删除的用户不存在");
		}
		
		HealthUserSession session = HealthUserSession.getCurrentUserSession();		
		if(session.getUserId().equals(id)) {
			throw new BusinessException("您不能删除自己");
		}
		
		Integer isAdmin = user.getIsAdmin();
		if (isAdmin == 1) {
			if (!session.isSystemAdmin()) {
				throw new BusinessException("您没有权限删除该用户");
			}
		} else if (isAdmin == 0) {
			if (!session.isSystemAdmin() && !session.isUserAdmin()) {
				throw new BusinessException("您没有权限删除该用户");
			}
		} 
		
		return removeByPrimaryKey(id);
	}


}