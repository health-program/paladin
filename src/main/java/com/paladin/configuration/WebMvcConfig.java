package com.paladin.configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.paladin.framework.core.SpringHandlerInterceptor;
import com.paladin.framework.core.exception.ExceptionHandler;
import com.paladin.framework.core.format.DateFormatter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
															
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${attachment.path}")
	private String attachmentPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String fielPath = "file:" + attachmentPath;
		String staticPath = "classpath:/static/";

		registry.addResourceHandler("/static/**").addResourceLocations(staticPath);
		registry.addResourceHandler("/file/**").addResourceLocations(fielPath);
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:favicon.ico");
		
		logger.info("文件资源存放地址：" + fielPath);
		logger.info("静态资源存放地址：" + staticPath);

	}

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/hrms/main");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);       
    } 
    
    @Bean(name = "handlerExceptionResolver")
    public HandlerExceptionResolver exceptionHandler(){
        logger.debug("WebMvcConfig.exceptionHandler()");
        return new ExceptionHandler();
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
