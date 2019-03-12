package com.paladin.framework.core.configuration.mybatis;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer，如果你不使用通用Mapper
 */
@Configuration
@AutoConfigureAfter(MyBatisConfiguration.class)
@EnableConfigurationProperties(MyBatisProperties.class)
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(MyBatisProperties myBatisProperties) {

		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage(myBatisProperties.getBasePackage());

		Properties mapperProperties = new Properties();
		mapperProperties.setProperty("mappers", CustomMapper.class.getName() + "," + CustomJoinMapper.class.getName());
		mapperProperties.setProperty("notEmpty", "false");
		mapperProperties.setProperty("IDENTITY", "MYSQL");
		mapperScannerConfigurer.setProperties(mapperProperties);

		return mapperScannerConfigurer;
	}

}
