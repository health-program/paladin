package com.paladin.health.mapper.prescription;

import com.paladin.health.model.prescription.PrescriptionItem;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface PrescriptionItemMapper extends CustomMapper<PrescriptionItem>{

	List<PrescriptionItem> findItemOfFactor(@Param("code") String code);

}