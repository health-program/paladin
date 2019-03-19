package com.paladin.health.service.core.xk.response;

import java.util.List;
import java.util.Map;

public class XKHealthPrescription {
	
	private String id;
	
	private boolean hasSended;
	
	private Map<String, XKDiseaseKnowledge> knowledge;
	
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

	public Map<String, XKDiseaseKnowledge> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Map<String, XKDiseaseKnowledge> knowledge) {
		this.knowledge = knowledge;
	}

	public List<XKEvaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(List<XKEvaluation> evaluation) {
		this.evaluation = evaluation;
	}
	
	
	
}
