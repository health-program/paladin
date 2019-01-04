package com.paladin.health.service.videomanage.vo;

public class VideoPlayCountVO {
	
	private String id;
	
	private Integer workCycle;
	
	private Integer duration;
	
	private Integer visitorCount;
	
	private String agencyName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getWorkCycle() {
		return workCycle;
	}

	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getVisitorCount() {
		return visitorCount;
	}

	public void setVisitorCount(Integer visitorCount) {
		this.visitorCount = visitorCount;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
}
