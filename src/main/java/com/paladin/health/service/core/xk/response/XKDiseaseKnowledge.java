package com.paladin.health.service.core.xk.response;

import java.util.List;

@SuppressWarnings("rawtypes")
public class XKDiseaseKnowledge {

	public static final String TYPE_DISEASE = "disease";
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
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(List knowledge) {
		this.knowledge = knowledge;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
