package com.paladin.health.controller.publicity.pojo;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class MessageQuery extends OffsetPage{
	
	private String title;	
	private Integer type;
	private Integer status;
	private String createUserId;

	@QueryCondition(type = QueryType.LIKE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
}
