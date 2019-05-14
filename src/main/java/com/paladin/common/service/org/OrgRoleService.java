package com.paladin.common.service.org;

import com.paladin.common.core.permission.PermissionContainer;
import com.paladin.common.model.org.OrgRole;
import com.paladin.common.service.org.dto.OrgRoleDTO;
import com.paladin.framework.common.BaseModel;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class OrgRoleService extends ServiceSupport<OrgRole> {

	public List<OrgRole> getOwnGrantRoles(int roleLevel) {
		/**
		 * 只能获取数据等级小于等于自己的角色
		 */
		return searchAll(
				new Condition(OrgRole.COLUMN_FIELD_ROLE_LEVEL, QueryType.LESS_EQUAL, roleLevel),
				new Condition(OrgRole.COLUMN_FIELD_ENABLE,QueryType.EQUAL,1));
	}

	@Transactional
	public boolean updateRole(@Valid OrgRoleDTO orgRoleDTO) {
		String id = orgRoleDTO.getId();
		if(id == null||id.length() ==0) {
			throw new BusinessException("找不到更新角色");
		}
		
		OrgRole model = get(id);
		if(model == null) {
			throw new BusinessException("找不到更新角色");
		}
		
		SimpleBeanCopyUtil.simpleCopy(orgRoleDTO, model);
		update(model);
		PermissionContainer.updateData();	
		return true;
	}

	@Transactional
	public boolean saveRole(@Valid OrgRoleDTO orgRoleDTO) {
		OrgRole model = new OrgRole();
		SimpleBeanCopyUtil.simpleCopy(orgRoleDTO, model);
		model.setIsDefault(BaseModel.BOOLEAN_NO);
		save(model);
		PermissionContainer.updateData();	
		return true;		
	}

}