package com.paladin.health.service.videomanage.vo;

import java.util.Date;

/**
 * @author 蒋恒 
 *  已发布视频展示
 */
public class PublishedVideoVO {
	// id
	private String id;

	// 视频名称
	private String name;

	// 视频简介
	private String summary;

	// 标签
	private String label;
	
	private Date updateTime;

	// 播放次数
	private Integer count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
