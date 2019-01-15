package com.paladin.health.service.videomanage.vo;

public class VideoPlayCountShowVO {
	
	private String id;
	
	private Integer workCycle;
	
	private Long duration;
	
	private Double durations;
	
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
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

	public Double getDurations() {
		return durations;
	}

	public void setDurations(Double durations) {
		this.durations = durations;
	}
	
}
