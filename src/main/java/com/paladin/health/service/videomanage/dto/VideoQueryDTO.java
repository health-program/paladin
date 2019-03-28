package com.paladin.health.service.videomanage.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.utils.StringUtil;

public class VideoQueryDTO extends OffsetPage {

	private String name;

	private String label;

	private String createUser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtil.isEmpty(name) ? null : name.trim();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}