package com.paladin.common.model.syst;

import javax.persistence.Id;

public class SysConstant {
	
	public final static String  COLUMN_FIELD_ORDER_NO = "orderNo";
	public final static String  COLUMN_FIELD_TYPE = "type";

	@Id
	private String type;
	
	@Id
	private Integer code;

	private String name;

	private Integer orderNo;
	

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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


}