package com.paladin.health.service.publicity.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class PublicityMaterialGrantDTO {
	private String id;
	//年度
	@NotNull(message = "工作周期不能为空！")
	private Integer workCycle;
	//名称
	private String materialId;
	//类型
	private Integer materialType;
	//数量
	@NotNull(message = "数量不能为空！")
	private Integer count;
	//发放对象
	private Integer grantTargetType;
	//发放对象
	private String grantTarget;
	//发放人
	@NotEmpty(message = "发放人不能为空！")
	private String grantor;
	//发放机构的Id
	private String grantorAgency;
	//发放日期
	@NotNull(message = "发放日期不能为空！")
	private Date grantDate;
	//接收人
	@NotEmpty(message = "接收人不能为空！")
	private String receiver;
	//备注
	private String remarks;
	
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
	
}
