package com.paladin.health.service.publicity.vo;

import com.paladin.common.core.AttachmentContainer;
import com.paladin.common.model.syst.SysAttachment;

import java.util.Date;
import java.util.List;

public class PublicityMaterialVO {

	private String id;

	// 工作周期
	private Integer workCycle;
	
	// 所属机构
	private String agencyId;
	
	// 所属机构
	private String agencyName;

	// 健康教育宣传资料名称
	private String name;

	// 健康教育宣传资料类型
	private Integer type;

	// 编发单位
	private String compileIssueUnit;

	// 数量
	private Integer count;

	// 日期
	private Date date;

	// 备注
	private String remarks;

	private String attachments;

	// 获取附件文件
	public List<SysAttachment> getAttachmentFiles() {
		if (attachments != null && attachments.length() != 0) {
			return AttachmentContainer.getAttachments(attachments.split(","));
		}
		return null;
	}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompileIssueUnit() {
		return compileIssueUnit;
	}

	public void setCompileIssueUnit(String compileIssueUnit) {
		this.compileIssueUnit = compileIssueUnit;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Integer getWorkCycle() {
		return workCycle;
	}

	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
}