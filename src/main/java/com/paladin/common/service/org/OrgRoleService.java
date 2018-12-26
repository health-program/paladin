package com.paladin.common.service.org;

import org.springframework.stereotype.Service;

import com.paladin.common.model.org.OrgRole;
import com.paladin.framework.common.BaseModel;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class OrgRoleService extends ServiceSupport<OrgRole> {

	@Override
	public int removeByPrimaryKey(Object id) {
		OrgRole role = get(id);
		if (role.getIsDefault() == BaseModel.BOOLEAN_YES) {
			throw new BusinessException("不能删除默认角色");
		}

		return super.removeByPrimaryKey(id);
	}

}