package com.paladin.health.service.org.dto;

import java.util.List;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class OrgUserQueryDTO extends OffsetPage {

	// 姓名
	private String name;
	private String agencyId;
	private List<String> agencyIds;

	@QueryCondition(type = QueryType.LIKE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@QueryCondition(type = QueryType.IN, name = "agencyId")
	public List<String> getAgencyIds() {
		return agencyIds;
	}

	public void setAgencyIds(List<String> agencyIds) {
		this.agencyIds = agencyIds;
	}

	@QueryCondition(type = QueryType.EQUAL, name = "agencyId")
	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}