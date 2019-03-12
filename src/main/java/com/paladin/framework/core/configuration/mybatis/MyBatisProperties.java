package com.paladin.framework.core.configuration.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paladin.mybatis")
public class MyBatisProperties {

	private String basePackage = "com.paladin.common.mapper,com.paladin.data.mapper,com.paladin.health.mapper";

	private String typeAliasesPackage = "com.paladin.common.model,com.paladin.data.model,com.paladin.health.model";

	private String mapperLocations = "classpath:mapper/**/*.xml";

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public String getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

}
