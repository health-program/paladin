package com.paladin.health.mapper.prescription;

import com.paladin.health.model.prescription.PrescriptionFactorItem;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;

public interface PrescriptionFactorItemMapper extends CustomMapper<PrescriptionFactorItem>{

	int removeFactorItems(@Param("code") String factor);
	
}