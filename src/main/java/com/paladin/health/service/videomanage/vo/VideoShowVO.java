package com.paladin.health.service.videomanage.vo;

import java.util.Date;

import com.paladin.common.core.AttachmentContainer;
import com.paladin.common.model.syst.SysAttachment;

public class VideoShowVO {

	private String id;

	private String url;

	// 视频名称
	private String name;

	// 展示图片(附件ID)
	private String showImage;

	// 视频简介
	private String summary;

	// 标签
	private String label;

	private Date publishTime;

	public SysAttachment getShowImageUrl() {
		if (showImage != null && showImage.length() != 0) {
			return AttachmentContainer.getAttachment(showImage);
		}
		return null;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
