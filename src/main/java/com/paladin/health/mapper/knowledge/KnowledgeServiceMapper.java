package com.paladin.health.mapper.knowledge;

import com.paladin.health.model.knowledge.KnowledgeService;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface KnowledgeServiceMapper extends CustomMapper<KnowledgeService>{

	public int stopService(@Param("id") String id);

	public int startService(@Param("id")  String id);

	public int stopCurrentService();

}