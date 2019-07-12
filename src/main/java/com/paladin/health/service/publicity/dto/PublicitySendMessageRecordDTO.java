package com.paladin.health.service.publicity.dto;


public class PublicitySendMessageRecordDTO {

	// 
	private String id;

	// 短信ID
	private String messageId;

	// 目标手机
	private String targetCellphone;

	// 目标姓名
	private String targetName;

	// 状态
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTargetCellphone() {
		return targetCellphone;
	}

	public void setTargetCellphone(String targetCellphone) {
		this.targetCellphone = targetCellphone;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}