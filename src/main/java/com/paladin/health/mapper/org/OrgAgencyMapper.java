package com.paladin.health.mapper.org;

import com.paladin.health.model.org.OrgAgency;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface OrgAgencyMapper extends CustomMapper<OrgAgency>{

	public int removeAgency(@Param("id") String id);
	
}