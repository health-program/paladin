package com.paladin.health.controller.knowledge.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDetailQuery;

public class KnowledgeBaseDetailExportCondition extends ExportCondition {

	private KnowledgeBaseDetailQuery query;

	public KnowledgeBaseDetailQuery getQuery() {
		return query;
	}

	public void setQuery(KnowledgeBaseDetailQuery query) {
		this.query = query;
	}

}