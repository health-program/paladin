package com.paladin.health.service.videomanage.vo;

import java.util.Date;

import com.paladin.common.core.AttachmentContainer;
import com.paladin.common.model.syst.SysAttachment;

public class VideoVO {

	//
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
	
	// 发布时间
	private Date publishTime;
	
	private String createUserName;
	
	private String examineUserName;

	// 获取附件文件
	public SysAttachment getShowImageFile() {
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getExamineUserName() {
		return examineUserName;
	}

	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
	}

}