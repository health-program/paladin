package com.paladin.health.service.publicity.dto;

import java.util.Date;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class PublicityMaterialQueryDTO extends OffsetPage {
	
	private Integer workCycle;
	
	private String agencyId;
	
	private String name;
	
	private String type;
	
	private Date startTime;
	
	private Date endTime;
	
	@QueryCondition(type = QueryType.EQUAL)
	public Integer getWorkCycle() {
		return workCycle;
	}
	
	public void setWorkCycle(Integer workCycle) {
		this.workCycle = workCycle;
	}
	
	public String getAgencyId() {
		return agencyId;
	}
	@QueryCondition(type = QueryType.EQUAL)
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	@QueryCondition(type = QueryType.LIKE)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@QueryCondition(type = QueryType.EQUAL)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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

}