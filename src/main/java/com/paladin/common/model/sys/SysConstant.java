package com.paladin.common.model.sys;

import javax.persistence.Id;

public class SysConstant {
	
	public final static String  COLUMN_FIELD_ORDER_NO = "orderNo";
	public final static String  COLUMN_FIELD_TYPE = "type";

	@Id
	private String code;

	private String name;

	@Id
	private String type;

	private Integer orderNo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}



}