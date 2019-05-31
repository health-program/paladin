package com.paladin.health.service.core.xk.dto;

import java.util.List;

public class ConfirmEvaluationDTO {
	
	private String sendMessage;
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
	
	
	
}
