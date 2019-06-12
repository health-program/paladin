package com.paladin.health.service.core.xk.dto;

import java.util.List;

public class ConfirmEvaluationDTO {
	
	private String sendMessage;
	private String confirmer;
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
	public String getConfirmer() {
		return confirmer;
	}
	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	
}
