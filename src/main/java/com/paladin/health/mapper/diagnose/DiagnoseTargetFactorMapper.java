package com.paladin.health.mapper.diagnose;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQueryDTO;
import com.paladin.health.service.diagnose.vo.DiagnoseRecordSimpleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiagnoseTargetFactorMapper extends CustomMapper<DiagnoseTargetFactor> {

	public int deleteByTarget(@Param("targetId") String targetId);

	public int insertByBatch(@Param("coll") List<DiagnoseTargetFactor> coll);

    List<DiagnoseRecordSimpleVO> searchDiagnoseTargetFactor(@Param("query") DiagnoseRecordQueryDTO query);

}