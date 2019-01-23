package com.paladin.health.model.publicity;

import com.paladin.framework.common.UnDeleteBaseModel;

import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PublicityMessage extends UnDeleteBaseModel {

	public final static int STATUS_TEMP = 0;
	public final static int STATUS_SUBMIT_EXAMINE = 1;
	public final static int STATUS_EXAMINE_FAIL = 2;
	public final static int STATUS_EXAMINE_SUCCESS = 9;

	
	public final static int TYPE_NOTICE = 1;
	public final static int TYPE_ARTICLE = 2;
	public final static int TYPE_SHORT_MESSAGE = 3;
	
	public final static String COLUMN_FIELD_STATUS = "status";

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private Integer type;

	private String title;

	private String thumbnail;
	
	private String subtitle;

	private String summary;

	@IgnoreInMultipleResult
	private String content;

	private String examineUserId;

	private Integer status;
	
	private String label;

	private String attachments;
	
	private Date publishTime;

	private String publishTarget;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getPublishTarget() {
		return publishTarget;
	}

	public void setPublishTarget(String publishTarget) {
		this.publishTarget = publishTarget;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}