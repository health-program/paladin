package com.paladin.health.controller.knowledge.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.health.service.knowledge.dto.KnowledgeEvaluateQuery;

public class KnowledgeEvaluateExportCondition extends ExportCondition {

	private KnowledgeEvaluateQuery query;

	public KnowledgeEvaluateQuery getQuery() {
		return query;
	}

	public void setQuery(KnowledgeEvaluateQuery query) {
		this.query = query;
	}

}