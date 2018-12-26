package com.paladin.health.model.publicity;

import javax.persistence.Id;

public class PublicityMessagePush {

	public final static int STATUS_WAITING = 0;
	public final static int STATUS_SENDING = 1;
	public final static int STATUS_SEND_SUCCESS = 2;
	public final static int STATUS_SEND_FAIL = 9;

	public final static int CHANNEL_WEB = 1;
	public final static int CHANNEL_CELLPHONE = 2;

	@Id
	private String messageId;

	@Id
	private Integer channel;

	private Integer status;

	private Integer tryTimes;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTryTimes() {
		return tryTimes;
	}

	public void setTryTimes(Integer tryTimes) {
		this.tryTimes = tryTimes;
	}

}