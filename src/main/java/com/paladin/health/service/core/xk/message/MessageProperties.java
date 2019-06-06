package com.paladin.health.service.core.xk.message;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("health.message")
public class MessageProperties {

	private List<Festival> festival;
	private List<Festival> lfestival;
	private List<Festival> solar;
	private List<Festival> season;

	public List<Festival> getFestival() {
		return festival;
	}

	public void setFestival(List<Festival> festival) {
		this.festival = festival;
	}

	public List<Festival> getSolar() {
		return solar;
	}

	public void setSolar(List<Festival> solar) {
		this.solar = solar;
	}

	public List<Festival> getSeason() {
		return season;
	}

	public void setSeason(List<Festival> season) {
		this.season = season;
	}

	public List<Festival> getLfestival() {
		return lfestival;
	}

	public void setLfestival(List<Festival> lfestival) {
		this.lfestival = lfestival;
	}

}
