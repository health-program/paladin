package com.paladin.health.model.prescription;

import javax.persistence.Id;
import java.util.Date;

public class PrescriptionInterfaceManage {

	// 授权关键字
	@Id
	private String appKey;

	// 第三方名称，例如某某社区
	private String name;

	// 
	private Date createTime;
	
	
	private Integer enabled;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}