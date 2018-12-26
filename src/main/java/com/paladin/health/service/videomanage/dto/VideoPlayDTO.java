package com.paladin.health.service.videomanage.dto;

import java.util.Date;

public class VideoPlayDTO {

	// 
	private String id;

	// 音像id
	private String videoId;

	// 播放地点
	private String playAddress;

	// 播放开始时间
	private Date playStartTime;

	// 播放结束时间
	private Date playEndTime;

	// 观看人数
	private Integer visitorCount;

	// 负责人
	private String principal;

	// 机构id
	private String agencyId;

	// 备注
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getPlayAddress() {
		return playAddress;
	}

	public void setPlayAddress(String playAddress) {
		this.playAddress = playAddress;
	}

	public Date getPlayStartTime() {
		return playStartTime;
	}

	public void setPlayStartTime(Date playStartTime) {
		this.playStartTime = playStartTime;
	}

	public Date getPlayEndTime() {
		return playEndTime;
	}

	public void setPlayEndTime(Date playEndTime) {
		this.playEndTime = playEndTime;
	}

	public Integer getVisitorCount() {
		return visitorCount;
	}

	public void setVisitorCount(Integer visitorCount) {
		this.visitorCount = visitorCount;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}