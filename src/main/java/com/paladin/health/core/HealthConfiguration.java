package com.paladin.health.core;

import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.paladin.common.core.CommonHandlerExceptionResolver;
import com.paladin.common.core.TontoDialect;
import com.paladin.framework.core.configuration.shiro.ShiroCasProperties;

import io.buji.pac4j.realm.Pac4jRealm;

@Configuration
public class HealthConfiguration {

	@Bean
	public TontoDialect getTontoDialect() {
		return new TontoDialect();
	}

	@Bean
	public HandlerExceptionResolver getHandlerExceptionResolver() {
		return new CommonHandlerExceptionResolver();
	}
	
	@Bean("casRealm")
	@ConditionalOnProperty(prefix = "paladin", value = "cas-enabled", havingValue = "true", matchIfMissing = false)
	public Pac4jRealm getCasRealm(ShiroCasProperties shiroCasProperties) {
		return new HealthCasUserRealm(shiroCasProperties);
	}

	@Bean("localRealm")
	public AuthorizingRealm getLocalRealm() {
		return new HealthUserRealm();
	}
}
