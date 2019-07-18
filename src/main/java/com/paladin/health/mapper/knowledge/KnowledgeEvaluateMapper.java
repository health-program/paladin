package com.paladin.health.mapper.knowledge;

import com.paladin.health.model.knowledge.KnowledgeEvaluate;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface KnowledgeEvaluateMapper extends CustomMapper<KnowledgeEvaluate>{

	public int stopEvaluate(@Param("id") String id);

	public int startEvaluate(@Param("id") String id);

}