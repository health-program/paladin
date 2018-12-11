package com.paladin.common.model.syst;

import javax.persistence.Id;

public class SysAttachment {
	
	public static final String COLUMN_FIELD_ID = "id";

	@Id
	private String id;

	private String type;

	private String name;

	private String suffix;

	private Long size;

	private String pelativePath;

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

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public String getPelativePath() {
		return pelativePath;
	}

	public void setPelativePath(String pelativePath) {
		this.pelativePath = pelativePath;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}