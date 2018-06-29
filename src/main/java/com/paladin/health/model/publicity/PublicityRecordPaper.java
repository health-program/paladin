package com.paladin.health.model.publicity;

import com.paladin.framework.common.BaseModel;
import java.sql.Date;
import javax.persistence.Id;

public class PublicityRecordPaper extends BaseModel {

	@Id
	private String id;

	private String name;

	private String type;

	private String compileIssueUnit;

	private String remarks;

	private Integer count;

	private Date date;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompileIssueUnit() {
		return compileIssueUnit;
	}

	public void setCompileIssueUnit(String compileIssueUnit) {
		this.compileIssueUnit = compileIssueUnit;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}