package com.paladin.health.service.core.xk;

import java.util.Date;
import java.util.List;

import com.paladin.health.service.core.xk.request.XKEvaluateCondition;

public class XKPeopleCondition {
	
	private String name;
	
	private String identificationId;
	
	private String cellphone;
	
	private Integer sex;

	private Date birthday;
		
	private Integer sendMessage;
	
	private String senderName;
	
	private List<String> diseases;
	
	private XKEvaluateCondition condition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public List<String> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<String> diseases) {
		this.diseases = diseases;
	}

	public XKEvaluateCondition getCondition() {
		return condition;
	}

	public void setCondition(XKEvaluateCondition condition) {
		this.condition = condition;
	}

	public Integer getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(Integer sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	
	
}
