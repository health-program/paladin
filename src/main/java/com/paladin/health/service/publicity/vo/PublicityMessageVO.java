package com.paladin.health.service.publicity.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;
import com.paladin.health.core.HealthPrescriptionContainer;
import com.paladin.health.model.prescription.PrescriptionFactor;

public class PublicityMessageVO {

	private String id;

	private Integer type;

	private String title;
	
	private String thumbnail;
	
	private String subtitle;

	private String summary;

	private String content;

	private String examineUserId;

	private String examineUserName;

	private Integer status;

	private String label;
	
	private String attachments;
	
	private Date publishTime;

	private String publishTarget;

	private String createUserId;

	private String createUserName;

	public List<PrescriptionFactor> getPublishTargetName() {
		if (publishTarget != null && publishTarget.length() != 0) {
			String[] targets = publishTarget.split(",");
			HealthPrescriptionContainer container = HealthPrescriptionContainer.getInstance();
			List<PrescriptionFactor> factors = new ArrayList<>(targets.length);
			for (String target : targets) {
				PrescriptionFactor factor = container.getFactorByCode(target);
				if (factor != null) {
					factors.add(factor);
				}
			}
			return factors;
		}
		return null;
	}
	
	 // 获取附件文件
    public List<SysAttachment> getAttachmentFiles() {
        if (attachments != null && attachments.length() != 0) {
            return AttachmentContainer.getAttachments(attachments.split(","));
        }
        return null;
    }

	 // 获取缩略图
    public List<SysAttachment> getThumbnailImage() {
        if (thumbnail != null && thumbnail.length() != 0) {
            return AttachmentContainer.getAttachments(thumbnail.split(","));
        }
        return null;
    }
    
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

	public String getExamineUserId() {
		return examineUserId;
	}

	public void setExamineUserId(String examineUserId) {
		this.examineUserId = examineUserId;
	}

	public String getExamineUserName() {
		return examineUserName;
	}

	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
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

	public String getPublishTarget() {
		return publishTarget;
	}

	public void setPublishTarget(String publishTarget) {
		this.publishTarget = publishTarget;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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
