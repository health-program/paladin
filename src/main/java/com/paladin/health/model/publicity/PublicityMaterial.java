package com.paladin.health.model.publicity;

import com.paladin.framework.common.BaseModel;
import java.util.Date;
import javax.persistence.Id;

public class PublicityMaterial extends BaseModel {

	// 
	@Id
	private String id;

	// 工作周期
	private String workCycle;

	// 所属机构
	private String agencyId;

	// 健康教育宣传资料名称
	private String name;

	// 健康教育宣传资料类型
	private String type;

	// 编发单位
	private String compileIssueUnit;

	// 数量
	private Integer count;

	// 日期
	private Date date;

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

}