package com.paladin.health.service.publicity.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class PublicitySendMessageRecordQuery extends OffsetPage {
	
	private String messageId;
	
	private String targetCellphone;
	
	private String targetName;
	
	private Integer status;

	@QueryCondition(type = QueryType.EQUAL)
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@QueryCondition(type = QueryType.EQUAL)
	public String getTargetCellphone() {
		return targetCellphone;
	}

	public void setTargetCellphone(String targetCellphone) {
		this.targetCellphone = targetCellphone;
	}

	@QueryCondition(type = QueryType.LIKE)
	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	@QueryCondition(type = QueryType.EQUAL)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}