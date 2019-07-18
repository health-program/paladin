package com.paladin.health.controller.knowledge.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.health.service.knowledge.dto.KnowledgeServiceQuery;

public class KnowledgeServiceExportCondition extends ExportCondition {

	private KnowledgeServiceQuery query;

	public KnowledgeServiceQuery getQuery() {
		return query;
	}

	public void setQuery(KnowledgeServiceQuery query) {
		this.query = query;
	}

}