package com.paladin.health.model.org;

import com.paladin.framework.common.BaseModel;
import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

import javax.persistence.Id;
import java.util.Date;

public class OrgAnnualWork extends BaseModel {

	// 
	@Id
	private String id;

	// 
	private String agencyId;

	// 类型
	private Integer type;

	// 名称
	private String name;

	// 负责人
	private String principal;

	// 内容
	@IgnoreInMultipleResult
	private String content;

	// 开始时间
	private Date beginTime;

	// 结束时间
	private Date endTime;

	// 备注
	private String remarks;

	// 
	private String attachments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

}