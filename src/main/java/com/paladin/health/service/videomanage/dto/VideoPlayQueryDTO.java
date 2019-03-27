package com.paladin.health.service.videomanage.dto;

import java.util.Date;

import com.paladin.framework.common.OffsetPage;

public class VideoPlayQueryDTO extends OffsetPage {
	
	private String agencyId;
	
	private String videoId;
	
	private Date startTime;
	
	private Date endTime;
	
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

}