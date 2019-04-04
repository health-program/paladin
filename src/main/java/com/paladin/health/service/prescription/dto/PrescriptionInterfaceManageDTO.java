package com.paladin.health.service.prescription.dto;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class PrescriptionInterfaceManageDTO {

	// 授权关键字
	private String appKey;

	@Length(min = 1,max = 5,message = "应输入0~50个字符")
	private String name;

	// 
	private Date createTime;

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

}