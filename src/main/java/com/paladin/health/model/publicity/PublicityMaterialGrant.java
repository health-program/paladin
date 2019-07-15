package com.paladin.health.model.publicity;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;
import java.util.Date;

public class PublicityMaterialGrant extends BaseModel{
	@Id
    private String id;
	//年度
    private Integer workCycle;
    //名称
    private String materialId;
    //类型
    private Integer materialType;
    //数量
    private Integer count;
    //机构名称
    private Integer grantTargetType;
    //发放对象
    private String grantTarget;
	//发放对象名称
    private  String grantTargetName;
    //发放人
    private String grantor;
    //发放机构的Id
    private String grantorAgency;
    //发放日期
    private Date grantDate;
    //接收人
    private String receiver;
    //备注
    private String remarks;

	private String attachments;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getWorkCycle() {
		return workCycle;
	}
	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Integer getMaterialType() {
		return materialType;
	}
	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getGrantTargetType() {
		return grantTargetType;
	}
	public void setGrantTargetType(Integer grantTargetType) {
		this.grantTargetType = grantTargetType;
	}
	public String getGrantTarget() {
		return grantTarget;
	}
	public void setGrantTarget(String grantTarget) {
		this.grantTarget = grantTarget;
	}
	public String getGrantor() {
		return grantor;
	}
	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}
	public String getGrantorAgency() {
		return grantorAgency;
	}
	public void setGrantorAgency(String grantorAgency) {
		this.grantorAgency = grantorAgency;
	}
	public Date getGrantDate() {
		return grantDate;
	}
	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getGrantTargetName() {
		return grantTargetName;
	}

	public void setGrantTargetName(String grantTargetName) {
		this.grantTargetName = grantTargetName;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
}
