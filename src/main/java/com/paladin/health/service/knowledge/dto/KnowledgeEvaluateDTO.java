package com.paladin.health.service.knowledge.dto;


public class KnowledgeEvaluateDTO {

	// 
	private String code;

	// 
	private String serviceId;

	// 名称
	private String name;

	// 备注
	private String note;

	// 是否启用
	private Integer enabled;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}