package com.paladin.health.model.prescription;

import javax.persistence.Id;

public class PrescriptionItem {

	@Id
	private Integer id;

	private String content;

	private Integer type;

	private String mutex;

	private Integer mutexPriority;

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

}