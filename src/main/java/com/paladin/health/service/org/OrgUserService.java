package com.paladin.health.service.org;

import com.paladin.common.model.org.OrgRole;
import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.core.AgencyContainer;
import com.paladin.health.core.AgencyContainer.Agency;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.org.OrgUser;
import com.paladin.health.service.org.dto.OrgUserDTO;
import com.paladin.health.service.org.dto.OrgUserQueryDTO;
import com.paladin.health.service.org.vo.OrgUserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgUserService extends ServiceSupport<OrgUser> {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private OrgRoleService orgRoleService;

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

	public List<Agency> findOwnedAgencies() {
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {
			return AgencyContainer.getRoots();
		} else if (session.getRoleLevel() == HealthUserSession.ROLE_LEVEL_AGENCY) {
			String agencyId = session.getAgencyId();
			if (agencyId != null) {
				List<Agency> result = new ArrayList<>();
				Agency agency = AgencyContainer.getAgency(agencyId);
				if (agency != null) {
					result.add(agency);
				}
				return result;
			}
		}
		return null;
	}

	public List<OrgUser> findOwnUsers(OrgUserQueryDTO query) {

		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {

		} else if (session.getRoleLevel() == HealthUserSession.ROLE_LEVEL_AGENCY) {
			List<String> ids = AgencyContainer.getAgencyAndChildrenIds(session.getAgencyId());
			if (ids == null || ids.size() == 0) {
				return new ArrayList<>();
			}

			if (ids.size() == 1) {
				query.setAgencyId(ids.get(0));
			} else {
				query.setAgencyIds(ids);
			}
		} else {
			return new ArrayList<>();
		}

		return searchAll(query);
	}

	@SuppressWarnings("unchecked")
	public PageResult<OrgUserVO> findOwnUsersPage(OrgUserQueryDTO query) {
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {

		} else if (session.getRoleLevel() == HealthUserSession.ROLE_LEVEL_AGENCY) {
			List<String> ids = AgencyContainer.getAgencyAndChildrenIds(session.getAgencyId());
			if (ids == null || ids.size() == 0) {
				return getEmptyPageResult(query);
			}
			if (ids.size() == 1) {
				query.setAgencyId(ids.get(0));
			} else {
				query.setAgencyIds(ids);
			}
		} else {
			return getEmptyPageResult(query);
		}

		return searchPage(query).convert(OrgUserVO.class);
	}

	/**
	 * 功能描述: <br>
	 * 〈查询所能操作的角色〉
	 * 
	 * @param
	 * @return java.util.List<com.paladin.common.model.org.OrgRole>
	 * @author Huangguochen
	 * @date 2019/3/15
	 */
	public List<OrgRole> searchOwnedRoles() {
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		int roleLevel = session.getRoleLevel();
		return orgRoleService.getOwnGrantRoles(roleLevel);
	}

	public int deleteUserById(String id) {
		List<SysUser> sysUsers = sysUserService.searchAll(new Condition(SysUser.COLUMN_FIELD_USER_ID, QueryType.EQUAL, id));
		int i = 0;
		if (sysUsers != null && sysUsers.size() > 0) {
			i = sysUserService.removeByPrimaryKey(sysUsers.get(0).getId());
		}
		if (i > 0) {
			i += removeByPrimaryKey(id);
		}
		return i;
	}

	public OrgUser geUserByIdCard(String idcard) {
		return searchOne(new Condition(OrgUser.COLUMN_FIELD_IDENTIFICATION_ID, QueryType.EQUAL, idcard));
	}
}