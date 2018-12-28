package com.paladin.health.model.videomanage;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class Video extends BaseModel {

    public final static String COLUMN_FIELD_TOP_ORDER_NO = "topOrderNo";

    public static final Integer topNumber = 6;
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

}