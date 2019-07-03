package com.paladin.health.service.core.xk.dto;

import java.util.List;

public class ConfirmEvaluationDTO {
	
	private String sendMessage;
	private String cellphone;
	private List<ConfirmEvaluationItemDTO> evaluationItems;
	
	public String getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}
	public List<ConfirmEvaluationItemDTO> getEvaluationItems() {
		return evaluationItems;
	}
	public void setEvaluationItems(List<ConfirmEvaluationItemDTO> evaluationItems) {
		this.evaluationItems = evaluationItems;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	
}
