package com.paladin.health.mapper.prescription;

import com.paladin.health.model.prescription.PrescriptionInterfaceManage;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface PrescriptionInterfaceManageMapper extends CustomMapper<PrescriptionInterfaceManage>{

	int stop(@Param("id") String id);

	int start(@Param("id") String id);

}