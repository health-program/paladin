package com.paladin.health.mapper.diagnose;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.service.diagnose.dto.MessageTargetQuery;
import com.paladin.health.service.diagnose.vo.DiagnoseTargetSimpleVO;

public interface DiagnoseTargetMapper extends CustomMapper<DiagnoseTarget>{

	public List<DiagnoseTargetSimpleVO> findOldTarget(@Param("birthday") Date birthday);
	public List<DiagnoseTargetSimpleVO> findHypertensionTarget();
	public List<DiagnoseTargetSimpleVO> findDiabetesTarget();
	public List<DiagnoseTargetSimpleVO> findCHDTarget();
	public List<DiagnoseTargetSimpleVO> findAFTarget();
	
	public List<DiagnoseTargetSimpleVO> findTargetForMessage(MessageTargetQuery query);
	public List<DiagnoseTargetSimpleVO> findAllTargetForMessage();
	
	
}