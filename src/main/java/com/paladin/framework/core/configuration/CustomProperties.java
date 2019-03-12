package com.paladin.framework.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paladin.common")
public class CustomProperties {

	/**
	 * 版本容器是否运行，在分布式下可实现弱实时性同步，非分布式下不需要运行
	 */
	private boolean containerVersionRun = false;


	public boolean isContainerVersionRun() {
		return containerVersionRun;
	}

	public void setContainerVersionRun(boolean containerVersionRun) {
		this.containerVersionRun = containerVersionRun;
	}

}
