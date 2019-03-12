package com.paladin.framework.core.configuration.mybatis;

import com.github.pagehelper.PageInterceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.util.Properties;

/**
 * MyBatis基础配置
 */
@Configuration
@ConditionalOnProperty(name = "paladin.configuration.auto.mybatis", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(MyBatisProperties.class)
@Import(MyBatisMapperScannerConfig.class)
public class MyBatisConfiguration implements TransactionManagementConfigurer {

	private Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

	@Resource
	public DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(MyBatisProperties myBatisProperties) {

		logger.info("初始化MyBatis插件");

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(myBatisProperties.getTypeAliasesPackage());

		// 分页插件
		PageInterceptor pageHelper = new PageInterceptor();
		Properties pageProperties = new Properties();
		pageProperties.setProperty("reasonable", "true");
		pageProperties.setProperty("supportMethodsArguments", "true");
		pageProperties.setProperty("returnPageInfo", "check");
		pageProperties.setProperty("params", "count=countSql");
		pageHelper.setProperties(pageProperties);

		/*
		 * 添加插件，如果需要可以查看QueryInterceptor等插件
		 * 
		 */
		bean.setPlugins(new Interceptor[] { pageHelper });

		// 添加XML目录
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		try {
			bean.setMapperLocations(resolver.getResources(myBatisProperties.getMapperLocations()));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建SqlSessionFactory失败");
		}

	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

}
