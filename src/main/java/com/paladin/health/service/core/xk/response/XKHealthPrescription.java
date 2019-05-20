package com.paladin.health.service.core.xk.response;

import java.util.List;

public class XKHealthPrescription {
	
	private String id;
			
	private List<XKEvaluation> evaluation;
	
	private List<String> message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<XKEvaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(List<XKEvaluation> evaluation) {
		this.evaluation = evaluation;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}


}
