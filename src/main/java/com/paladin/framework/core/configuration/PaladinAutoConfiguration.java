package com.paladin.framework.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainerManager;

@Configuration
public class PaladinAutoConfiguration {	
	@Bean
	public SpringContainerManager springContainerManager() {
		return new SpringContainerManager();
	}
	
	@Bean
	public SpringBeanHelper springBeanHolder() {
		return new SpringBeanHelper();
	} 
}
