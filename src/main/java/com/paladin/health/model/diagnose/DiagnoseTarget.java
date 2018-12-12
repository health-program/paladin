package com.paladin.health.model.diagnose;

import java.util.Date;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DiagnoseTarget {
	
	public static final int SEX_MAN = 1;
	public static final int SEX_WOMAN = 2;
	
	
	@Id
	private String id;

	private String name;

	private Integer sex;

	private Date birthday;

	private String cellphone;
		
	private String factors;

	private Date createTime;
	
	private Date updateTime;

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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonIgnore
	public boolean isGirl() {
		return sex != null && sex == DiagnoseTarget.SEX_WOMAN;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFactors() {
		return factors;
	}

	public void setFactors(String factors) {
		this.factors = factors;
	}

	
}