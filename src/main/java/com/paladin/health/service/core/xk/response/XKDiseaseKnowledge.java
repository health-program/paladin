package com.paladin.health.service.core.xk.response;

import com.paladin.health.model.knowledge.KnowledgeBase;
import com.paladin.health.model.knowledge.KnowledgeBaseDetail;

import java.util.List;

@SuppressWarnings("rawtypes")
public class XKDiseaseKnowledge {

/*	public static final String TYPE_DISEASE = "disease";
	public static final String TYPE_INDE = "index";

	private String code;

	private String name;

	private String type;

	private List knowledge;

	public XKDiseaseKnowledge() {

	}

	public XKDiseaseKnowledge(String code, String name, String type, List knowledge) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.knowledge = knowledge;
	}*/

 	private KnowledgeBase base;

 	private List<KnowledgeBaseDetail> detail;

	public KnowledgeBase getBase() {
		return base;
	}

	public void setBase(KnowledgeBase base) {
		this.base = base;
	}

	public List<KnowledgeBaseDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<KnowledgeBaseDetail> detail) {
		this.detail = detail;
	}
}
