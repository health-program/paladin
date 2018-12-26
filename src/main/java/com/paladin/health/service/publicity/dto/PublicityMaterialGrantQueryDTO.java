package com.paladin.health.service.publicity.dto;

import java.util.Date;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class PublicityMaterialGrantQueryDTO extends OffsetPage{
	//年度
    private Integer workCycle;
    //发放机构的Id
    private String materialName;
    //发放机构的Id
    private String grantorAgency;
    //发放人
    private String grantor;
    //接收人
    private String receiver;
    //发放日期开始
    private Date startTime;
    //发放日期结束
    private Date endTime;
    
    @QueryCondition(type = QueryType.EQUAL)
	public Integer getWorkCycle() {
		return workCycle;
	}
	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}
	@QueryCondition(type = QueryType.EQUAL)
	public String getGrantorAgency() {
		return grantorAgency;
	}
	public void setGrantorAgency(String grantorAgency) {
		this.grantorAgency = grantorAgency;
	}
	@QueryCondition(type = QueryType.EQUAL)
	public String getGrantor() {
		return grantor;
	}
	@QueryCondition(type = QueryType.LIKE)
	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}
	@QueryCondition(type = QueryType.LIKE)
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
    
}
