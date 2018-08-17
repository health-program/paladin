package com.paladin.health.model.publicity;

import com.paladin.framework.common.UnDeleteBaseModel;
import com.paladin.health.model.sys.OrgUser;

import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PublicityMessage extends UnDeleteBaseModel {

	public final static int STATUS_TEMP =0;
	public final static int STATUS_SUBMIT_EXAMINE =1;
	public final static int STATUS_EXAMINE_FAIL =2;
	public final static int STATUS_EXAMINE_SUCCESS =9;
	public final static int STATUS_TO_SEND =10;
	
	public final static String COLUMN_FIELD_STATUS = "status";
	
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private Integer type;

	private String title;

	private String subtitle;

	private String summary;

	@IgnoreInMultipleResult
	private String content;
	
	@ColumnType(foreignClass = OrgUser.class)
	private String examineUserId;

	private Integer status;
	
	private Date publishTime;
	
	private Integer toApp;
	
	private Integer toWeixin;
	
	private Integer toCellphone;
	
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

	public Integer getToApp() {
		return toApp;
	}

	public void setToApp(Integer toApp) {
		this.toApp = toApp;
	}

	public Integer getToWeixin() {
		return toWeixin;
	}

	public void setToWeixin(Integer toWeixin) {
		this.toWeixin = toWeixin;
	}

	public Integer getToCellphone() {
		return toCellphone;
	}

	public void setToCellphone(Integer toCellphone) {
		this.toCellphone = toCellphone;
	}

}