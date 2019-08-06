package com.paladin.health.service.core.xk.dto;

import java.util.List;

import com.paladin.health.service.core.xk.response.XKPrescription;

public class ConfirmPrescriptionDTO {
	
	private String sendMessage;
	private String cellphone;
	private List<XKPrescription> prescriptions;
	
	public String getSendMessage() {
		return sendMessage;
	}
	
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}
	
	public String getCellphone() {
		return cellphone;
	}
	
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public List<XKPrescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<XKPrescription> prescriptions) {
		this.prescriptions = prescriptions;
	}


}
