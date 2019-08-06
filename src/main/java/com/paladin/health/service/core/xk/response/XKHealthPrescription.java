package com.paladin.health.service.core.xk.response;

import java.util.List;

public class XKHealthPrescription {
	
	private String id;
			
	private List<XKPrescription> prescription;
		
	private List<XKMessage> message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<XKMessage> getMessage() {
		return message;
	}

	public void setMessage(List<XKMessage> message) {
		this.message = message;
	}

	public List<XKPrescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<XKPrescription> prescription) {
		this.prescription = prescription;
	}


}
