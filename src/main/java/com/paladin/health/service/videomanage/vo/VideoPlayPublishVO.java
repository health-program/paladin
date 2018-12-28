package com.paladin.health.service.videomanage.vo;

import java.util.Date;

public class VideoPlayPublishVO {

	// videoid
	private String videoId;

	// videoiName
	private String videoName;
	// 日期
	private Date date;

	// 年
	private Integer year;

	// 月
	private Integer month;

	// 日
	private Integer day;

	// 播放次数
	private Integer count;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

}