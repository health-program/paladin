package com.paladin.health.model.sms;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class SmsSendResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4553875952776031636L;

	private String result;
	
	private String desc;
	
	private String balance;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
