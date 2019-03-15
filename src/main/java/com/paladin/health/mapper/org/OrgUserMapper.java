package com.paladin.health.mapper.org;

import com.paladin.health.model.org.OrgUser;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.service.org.dto.OrgUserQueryDTO;
import com.paladin.health.service.org.vo.OrgUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgUserMapper extends CustomMapper<OrgUser>{

    List<OrgUserVO> orsearchAllUsersByQuery(@Param("query") OrgUserQueryDTO query, @Param("agencyId") String agencyId);
}