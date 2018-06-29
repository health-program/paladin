package com.paladin.health.model.publicity;

import com.paladin.framework.common.BaseModel;
import javax.persistence.Id;

public class PublicityRecordVideo extends BaseModel {

	@Id
	private String id;

	private String name;

	private String makeUnit;

	private String remarks;

	private Integer count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}