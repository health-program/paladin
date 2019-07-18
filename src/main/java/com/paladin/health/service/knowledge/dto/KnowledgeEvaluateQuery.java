package com.paladin.health.service.knowledge.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class KnowledgeEvaluateQuery extends OffsetPage {

	private String serviceId;

	@QueryCondition(type = QueryType.EQUAL)
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}