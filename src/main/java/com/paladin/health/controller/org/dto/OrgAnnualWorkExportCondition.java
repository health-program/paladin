package com.paladin.health.controller.org.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.health.service.org.dto.OrgAnnualWorkQuery;

public class OrgAnnualWorkExportCondition extends ExportCondition {

	private OrgAnnualWorkQuery query;

	public OrgAnnualWorkQuery getQuery() {
		return query;
	}

	public void setQuery(OrgAnnualWorkQuery query) {
		this.query = query;
	}

}