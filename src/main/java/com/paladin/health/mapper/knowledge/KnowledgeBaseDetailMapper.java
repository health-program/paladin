package com.paladin.health.mapper.knowledge;

import com.paladin.health.model.knowledge.KnowledgeBaseDetail;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDetailQuery;
import com.paladin.health.service.knowledge.vo.KnowledgeBaseSimpleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KnowledgeBaseDetailMapper extends CustomMapper<KnowledgeBaseDetail> {

    List<KnowledgeBaseSimpleVO> searchknowledgeSimpleInfo(@Param("query") KnowledgeBaseDetailQuery query);
}