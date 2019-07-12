package com.paladin.health.controller.publicity.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.health.service.publicity.dto.PublicitySendMessageRecordQuery;

public class PublicitySendMessageRecordExportCondition extends ExportCondition {

	private PublicitySendMessageRecordQuery query;

	public PublicitySendMessageRecordQuery getQuery() {
		return query;
	}

	public void setQuery(PublicitySendMessageRecordQuery query) {
		this.query = query;
	}

}