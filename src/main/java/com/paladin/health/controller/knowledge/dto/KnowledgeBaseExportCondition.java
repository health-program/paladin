package com.paladin.health.controller.knowledge.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseQuery;

public class KnowledgeBaseExportCondition extends ExportCondition {

	private KnowledgeBaseQuery query;

	public KnowledgeBaseQuery getQuery() {
		return query;
	}

	public void setQuery(KnowledgeBaseQuery query) {
		this.query = query;
	}

}