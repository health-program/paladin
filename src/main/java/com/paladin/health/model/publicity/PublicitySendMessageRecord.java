package com.paladin.health.model.publicity;

import java.util.Date;

import javax.persistence.Id;

public class PublicitySendMessageRecord {

	public final static int SEND_STATUS_SUCCESS = 1;
	public final static int SEND_STATUS_ERROR = 2;
	
	public final static String COLUMN_FIELD_CREATE_TIME = "createTime";

	// 
	@Id
	private String id;

	// 短信ID
	private String messageId;

	// 目标手机
	private String targetCellphone;

	// 目标姓名
	private String targetName;

	// 状态
	private Integer status;
	
	private Date createTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}