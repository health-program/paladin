package com.paladin.health.mapper.diagnose;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;

public interface DiagnoseTargetFactorMapper extends CustomMapper<DiagnoseTargetFactor> {

	public int deleteByTarget(@Param("targetId") String targetId);

	public int insertByBatch(@Param("coll") List<DiagnoseTargetFactor> coll);

}