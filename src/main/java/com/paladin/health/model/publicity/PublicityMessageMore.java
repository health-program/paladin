package com.paladin.health.model.publicity;

import com.paladin.health.model.sys.OrgUser;

import tk.mybatis.mapper.annotation.JoinColumn;

public class PublicityMessageMore extends PublicityMessage {

	@JoinColumn(joinProperty = "name", joinClass = OrgUser.class, foreignProperty = "createUserId")
	private String createUserName;

	@JoinColumn(joinProperty = "name", joinClass = OrgUser.class, foreignProperty = "examineUserId")
	private String examineUserName;
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getExamineUserName() {
		return examineUserName;
	}

	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
	}

}
