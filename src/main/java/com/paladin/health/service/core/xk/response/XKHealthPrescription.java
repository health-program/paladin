package com.paladin.health.service.core.xk.response;

import java.util.List;

public class XKHealthPrescription {
	
	private String id;
	
	private boolean hasSended;
	
	private List<XKDiseaseKnowledge> knowledge;
	
	private List<XKEvaluation> evaluation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isHasSended() {
		return hasSended;
	}

	public void setHasSended(boolean hasSended) {
		this.hasSended = hasSended;
	}

	public List<XKEvaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(List<XKEvaluation> evaluation) {
		this.evaluation = evaluation;
	}

	public List<XKDiseaseKnowledge> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(List<XKDiseaseKnowledge> knowledge) {
		this.knowledge = knowledge;
	}
	
	
	
}
