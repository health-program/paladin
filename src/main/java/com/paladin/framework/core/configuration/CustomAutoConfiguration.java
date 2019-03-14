package com.paladin.framework.core.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainerManager;

@Configuration
@EnableConfigurationProperties(CustomProperties.class)
public class CustomAutoConfiguration {	
	@Bean
	public SpringContainerManager springContainerManager() {
		return new SpringContainerManager();
	}
	
	@Bean
	public SpringBeanHelper springBeanHolder() {
		return new SpringBeanHelper();
	} 
}