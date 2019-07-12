package com.paladin.health.service.publicity.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class PublicityShortMessageQueryDTO extends OffsetPage{

	private Integer type;
	
	private Integer status;
	
	private String title;

	@QueryCondition(type = QueryType.EQUAL)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@QueryCondition(type = QueryType.EQUAL)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@QueryCondition(type = QueryType.LIKE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
