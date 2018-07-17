package com.paladin.framework.common;

import java.util.Date;

import javax.persistence.OrderBy;

import com.paladin.health.model.sys.OrgUser;

import tk.mybatis.mapper.annotation.ColumnType;

public abstract class BaseModel {
	
	/**
	 * 是
	 */
	public final static int BOOLEAN_YES = 1;
	/**
	 * 否
	 */
	public final static int BOOLEAN_NO = 0;
	
	
	public final static String COLUMN_FIELD_CREATE_TIME = "createTime";

	@OrderBy("DESC")
	private Date createTime;

	@ColumnType(foreignClass = OrgUser.class)
    private String createUserId;

    private Date updateTime;
    
	@ColumnType(foreignClass = OrgUser.class)
    private String updateUserId;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
    
}
