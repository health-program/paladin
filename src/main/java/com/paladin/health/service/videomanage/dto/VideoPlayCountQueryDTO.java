package com.paladin.health.service.videomanage.dto;

import com.paladin.framework.common.OffsetPage;

public class VideoPlayCountQueryDTO extends OffsetPage {
	private  String id;
	private Integer workCycle;
	private String agencyName;
	public Integer getWorkCycle() {
		return workCycle;
	}
	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
