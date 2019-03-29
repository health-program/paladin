package com.paladin.health.service.diagnose.dto;

import java.util.Date;

public class MessageTargetQuery {
	private String old;
	private String af;
	private String gxb;
	private String tnb;
	private String gxy;
	private Date birthday;

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public String getAf() {
		return af;
	}

	public void setAf(String af) {
		this.af = af;
	}

	public String getGxb() {
		return gxb;
	}

	public void setGxb(String gxb) {
		this.gxb = gxb;
	}

	public String getTnb() {
		return tnb;
	}

	public void setTnb(String tnb) {
		this.tnb = tnb;
	}

	public String getGxy() {
		return gxy;
	}

	public void setGxy(String gxy) {
		this.gxy = gxy;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
