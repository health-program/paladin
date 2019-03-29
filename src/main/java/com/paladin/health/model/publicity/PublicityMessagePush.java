package com.paladin.health.model.publicity;

import javax.persistence.Id;

public class PublicityMessagePush {

	public final static int STATUS_SEND_SUCCESS = 1;
	public final static int STATUS_SEND_FAIL = 0;

	@Id
	private String messageId;

	@Id
	private Integer targetId;

	private Integer status;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}