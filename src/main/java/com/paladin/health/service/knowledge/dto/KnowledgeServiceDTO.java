package com.paladin.health.service.knowledge.dto;


public class KnowledgeServiceDTO {

	// 
	private String serviceCode;

	// 服务名称
	private String serviceName;

	// 备注
	private String note;

	// 是否启用
	private Integer enabled;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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