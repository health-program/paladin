package com.paladin.health.service.org;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.model.org.OrgUser;
import com.paladin.health.service.org.dto.OrgUserDTO;
import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class OrgUserService extends ServiceSupport<OrgUser> {

	@Autowired
	private SysUserService sysUserService;

	public OrgUser createUser(OrgUserDTO orgUserDTO) {
		OrgUser model = new OrgUser();
		SimpleBeanCopyUtil.simpleCopy(orgUserDTO, model);
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (sysUserService.createUserAccount(orgUserDTO.getAccount(), id, SysUser.TYPE_ORG_USER) > 0) {
			if (save(model) > 0) {
				return model;
			}
		}
		return null;
	}

	public OrgUser updateUser(@Valid OrgUserDTO orgUserDTO) {
		String id = orgUserDTO.getId();
		if (id == null || id.length() == 0) {
			throw new BusinessException("没有找到需要更新的用户");
		}
		OrgUser model = get(id);
		String account = model.getAccount();
		SimpleBeanCopyUtil.simpleCopy(orgUserDTO, model);

		String newAccount = model.getAccount();
		if (newAccount == null || newAccount.length() == 0) {
			throw new BusinessException("账号不能为空");
		}

		if (!account.equals(newAccount)) {
			sysUserService.updateAccount(id, account, newAccount);
		}

		if (update(model) > 0) {
			return model;
		}

		return null;
	}

}