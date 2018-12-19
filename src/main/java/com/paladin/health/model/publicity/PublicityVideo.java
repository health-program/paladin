package com.paladin.health.model.publicity;

import com.paladin.framework.common.BaseModel;
import javax.persistence.Id;

public class PublicityVideo extends BaseModel {

	// 音像id
	@Id
	private String id;

	// 工作周期
	private String workCycle;

	// 所属机构
	private String agencyId;

	// 音像名称
	private String name;

	// 制作单位
	private String makeUnit;

	// 数量
	private Integer count;

	// 备注
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkCycle() {
		return workCycle;
	}

	public void setWorkCycle(String workCycle) {
		this.workCycle = workCycle;
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

	public String getMakeUnit() {
		return makeUnit;
	}

	public void setMakeUnit(String makeUnit) {
		this.makeUnit = makeUnit;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}