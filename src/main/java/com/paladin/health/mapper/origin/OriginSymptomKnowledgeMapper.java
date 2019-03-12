package com.paladin.health.mapper.origin;

import com.paladin.health.model.origin.OriginSymptomKnowledge;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface OriginSymptomKnowledgeMapper extends CustomMapper<OriginSymptomKnowledge>{
	
	public Integer getMaxId(@Param("id") int underId);	
	public Integer getContentMaxId(@Param("id") int underId);	

	public int deleteSymptomKnowledge(@Param("symptom") String symptom);	
	public int deleteSymptomContent(@Param("symptom") String symptom);	
}