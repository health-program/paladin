package com.paladin.health.service.core.xk.response;

public class XKMessage {

	private String source;
	private String message;

	public XKMessage() {
	}

	public XKMessage(String source, String message) {
		this.source = source;
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
