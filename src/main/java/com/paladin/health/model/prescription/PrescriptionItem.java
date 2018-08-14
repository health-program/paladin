package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionItem {
	
	public static final String COLUMN_FIELD_TYPE = "type";

	@Id
	private Integer id;

	private String content;
	
	private String detail;

	private Integer type;

	private String mutex;

	private Integer mutexPriority;
	
	private String demand;
	
	private String terminology;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMutex() {
		return mutex;
	}

	public void setMutex(String mutex) {
		this.mutex = mutex;
	}

	public Integer getMutexPriority() {
		return mutexPriority;
	}

	public void setMutexPriority(Integer mutexPriority) {
		this.mutexPriority = mutexPriority;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getTerminology() {
		return terminology;
	}

	public void setTerminology(String terminology) {
		this.terminology = terminology;
	}

}