package com.paladin.health.model.videomanage;

import com.paladin.framework.common.BaseModel;

import java.util.Date;

import javax.persistence.Id;

public class Video extends BaseModel {

	public final static String COLUMN_FIELD_TOP_ORDER_NO = "topOrderNo";

	public static final Integer TOP_NUMBER = 7;
	public static final Integer TOP_NOT_SORT_NUMBER = 6;

	public final static int STATUS_TEMP = 0;
	public final static int STATUS_SUBMIT_EXAMINE = 1;
	public final static int STATUS_EXAMINE_FAIL = 2;
	public final static int STATUS_EXAMINE_SUCCESS = 9;

	@Id
	private String id;

	// 视频名称
	private String name;

	// 视频地址
	private String url;

	// 展示图片(附件ID)
	private String showImage;

	// 视频简介
	private String summary;

	// 标签
	private String label;

	// 是否置顶（boolean）
	private Integer top;

	// 置顶排序号
	private Integer topOrderNo;

	// 状态
	private Integer status;

	// 审核人
	private String examineUserId;

	// 发布时间
	private Date publishTime;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
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

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getTopOrderNo() {
		return topOrderNo;
	}

	public void setTopOrderNo(Integer topOrderNo) {
		this.topOrderNo = topOrderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExamineUserId() {
		return examineUserId;
	}

	public void setExamineUserId(String examineUserId) {
		this.examineUserId = examineUserId;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

}