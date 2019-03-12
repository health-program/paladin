package com.paladin.framework.core.configuration.web;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.paladin.framework.core.SpringHandlerInterceptor;
import com.paladin.framework.core.format.DateFormatter;

@Configuration
@ConditionalOnProperty(name = "paladin.configuration.auto.web", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(MyWebProperties.class)
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private MyWebProperties webProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String filePath = webProperties.getFilePath();
		String staticPath = webProperties.getStaticPath();
		String faviconPath = webProperties.getFaviconPath();

		registry.addResourceHandler("/static/**").addResourceLocations(staticPath);
		registry.addResourceHandler("/file/**").addResourceLocations(filePath);
		registry.addResourceHandler("/favicon.ico").addResourceLocations(faviconPath);

		logger.info("文件资源存放地址：" + filePath);
		logger.info("静态资源存放地址：" + staticPath);
		logger.info("favicon存放地址：" + faviconPath);

		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(webProperties.getRootView());
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SpringHandlerInterceptor());
	}

	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatterForFieldType(Date.class, new DateFormatter());
	}

	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/static/html/error_404.html"));
		factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/static/html/error_401.html"));
		factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/html/error_500.html"));
		return factory;
	}

}
