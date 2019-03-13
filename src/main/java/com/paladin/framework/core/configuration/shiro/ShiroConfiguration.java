package com.paladin.framework.core.configuration.shiro;

import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <h2>shiro配置</h2>
 * <p>
 * 修改了部分shiro的代码，从而提高效率，减少session的重复读取
 * </p>
 * 
 * @author TontoZhou
 * @since 2018年3月21日
 */
@Configuration
@ConditionalOnProperty(name = "paladin.configuration.auto.shiro", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ShiroProperties.class)
@ConditionalOnBean(Realm.class)
public class ShiroConfiguration {

	private static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	@Bean(name = "redisSessionDAO")
	public ShiroRedisSessionDAO getRedisSessionDAO(ShiroProperties shiroProperties, RedisTemplate<String, Object> jdkRedisTemplate) {
		logger.debug("ShiroConfiguration.redisSessionDAO()");
		ShiroRedisSessionDAO sessionDao = new ShiroRedisSessionDAO(shiroProperties, jdkRedisTemplate);
		return sessionDao;
	}

	/**
	 * @see DefaultWebSessionManager
	 * @return
	 */
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager(ShiroProperties shiroProperties, ShiroRedisSessionDAO redisSessionDAO) {
		logger.debug("ShiroConfiguration.defaultWebSessionManager()");

		DefaultWebSessionManager sessionManager = null;

		if (shiroProperties.isCluster()) {

			sessionManager = new CommonWebSessionManager(shiroProperties);

			/**
			 * 如果设置集群共享session，需要redis来存放session
			 */
			sessionManager.setSessionDAO(redisSessionDAO);

			// 用户权限，认证等缓存设置
			// 因为验证权限部分用其他方式实现，所以不需要缓存
			// sessionManager.setCacheManager(new RedisCacheManager());
			sessionManager.setSessionFactory(new SessionFactory() {

				/*
				 * 使用 {@link com.paladin.configuration.ShiroRedisSessionDAO.ControlledSession}
				 * 控制session update次数
				 */
				@Override
				public Session createSession(SessionContext initData) {
					if (initData != null) {
						String host = initData.getHost();
						if (host != null) {

							if (logger.isDebugEnabled()) {
								logger.debug("创建ControlledSession[HOST:" + host + "]");
							}

							return new ShiroRedisSessionDAO.ControlledSession(host);
						}
					}

					if (logger.isDebugEnabled()) {
						logger.info("创建ControlledSession[HOST:无]");
					}
					return new ShiroRedisSessionDAO.ControlledSession();
				}

			});
		} else {
			sessionManager = new CommonWebSessionManager(shiroProperties);
		}

		// session 监听
		// Collection<SessionListener> sessionListeners = new ArrayList<>();
		// sessionListeners.add(new CustomSessionListener());
		// sessionManager.setSessionListeners(sessionListeners);

		// 单位为毫秒（1秒=1000毫秒） 3600000毫秒为1个小时
		sessionManager.setSessionValidationInterval(3600000);
		// 3600000 milliseconds = 1 hour
		sessionManager.setGlobalSessionTimeout(shiroProperties.getSessionTime() * 60 * 1000);
		// 是否删除无效的，默认也是开启
		sessionManager.setDeleteInvalidSessions(true);
		// 是否开启 检测，默认开启
		sessionManager.setSessionValidationSchedulerEnabled(true);
		// 是否在url上显示检索得到的sessionid
		sessionManager.setSessionIdUrlRewritingEnabled(false);

		return sessionManager;
	}

	/**
	 * @see org.apache.shiro.mgt.SecurityManager
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManage(DefaultWebSessionManager defaultWebSessionManager, Realm realm) {
		logger.debug("ShiroConfiguration.getDefaultWebSecurityManage()");

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);

		// 注入缓存管理器;
		// securityManager.setCacheManager(redisCacheManager());
		securityManager.setSessionManager(defaultWebSessionManager);
		return securityManager;
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager Filter Chain定义说明
	 * 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean(name = "shiroFilter")
	@ConditionalOnMissingBean(ShiroFilterFactoryBean.class)
	public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager, ShiroProperties shiroProperties) {
		logger.debug("ShiroConfiguration.shirFilter()");

		// ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 如果有shiro过滤有明确前缀，而不是默认的"\*"，则不需要改写shiro去手动排除某些请求 ShiroFilterFactoryBean
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean() {
			/*
			 * 在请求进入shiro流程前，判断请求是否为静态资源，如果是则跳出shiro流程
			 */
			protected AbstractShiroFilter createInstance() throws Exception {

				SecurityManager securityManager = getSecurityManager();
				if (securityManager == null) {
					String msg = "SecurityManager property must be set.";
					throw new BeanInitializationException(msg);
				}

				if (!(securityManager instanceof WebSecurityManager)) {
					String msg = "The security manager does not implement the WebSecurityManager interface.";
					throw new BeanInitializationException(msg);
				}

				FilterChainManager manager = createFilterChainManager();

				PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
				chainResolver.setFilterChainManager(manager);

				return new UnStaticShiroFilter((WebSecurityManager) securityManager, chainResolver, shiroProperties);
			}

			class UnStaticShiroFilter extends AbstractShiroFilter {

				private String[] staticPrefixs;
				private String[] authPrefixs;

				protected UnStaticShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver, ShiroProperties shiroProperties) {
					super();
					if (webSecurityManager == null) {
						throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
					}
					setSecurityManager(webSecurityManager);
					if (resolver != null) {
						setFilterChainResolver(resolver);
					}

					String staticRsPrefix = shiroProperties.getStaticResourcePrefix();
					if (staticRsPrefix != null && staticRsPrefix.length() > 0) {
						staticPrefixs = staticRsPrefix.split(",");
					}

					String authRsPrefix = shiroProperties.getAuthResourcePrefix();
					if (authRsPrefix != null && authRsPrefix.length() > 0) {
						authPrefixs = authRsPrefix.split(",");
					}
				}

				protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, final FilterChain chain)
						throws ServletException, IOException {

					// -------------- add by TontoZhou-----------------
					if (servletRequest instanceof HttpServletRequest) {
						HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
						String requestUrl = httpRequest.getRequestURI();

						// 过滤静态资源，防止静态资源读取session等操作
						if (authPrefixs != null) {
							for (String ap : authPrefixs) {
								if (requestUrl.startsWith(ap)) {
									super.doFilterInternal(servletRequest, servletResponse, chain);
									return;
								}
							}

							chain.doFilter(servletRequest, servletResponse);
							return;
						}

						if (staticPrefixs != null) {
							for (String sp : staticPrefixs) {
								if (requestUrl.startsWith(sp)) {
									chain.doFilter(servletRequest, servletResponse);
									return;
								}
							}
							super.doFilterInternal(servletRequest, servletResponse, chain);
							return;
						}
					}					
					// -------------- add by TontoZhou -----------------					
				}
			}

		};

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());

		// 增加自定义过滤
		//Map<String, Filter> filters = new HashMap<>();
		//filters.put("authc", new AjaxFormAuthenticationFilter(shiroProperties));

		//shiroFilterFactoryBean.setFilters(filters);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		/**
		 * anon（匿名） org.apache.shiro.web.filter.authc.AnonymousFilter authc（身份验证）
		 * org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		 * authcBasic（http基本验证）org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
		 * org.apache.shiro.web.filter.authc.LogoutFilter logout（退出）
		 * org.apache.shiro.web.filter.session.NoSessionCreationFilter
		 * noSessionCreation（不创建session）
		 * org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter perms(许可验证)
		 * org.apache.shiro.web.filter.authz.PortFilter port（端口验证）
		 * org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter rest (rest方面)
		 * org.apache.shiro.web.filter.authz.RolesAuthorizationFilter roles（权限验证）
		 * org.apache.shiro.web.filter.authz.SslFilter ssl （ssl方面）
		 * org.apache.shiro.web.filter.authc.UserFilter user member（用户方面）
		 * 表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
		 */

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

		filterChainDefinitionMap.put(shiroProperties.getLogoutUrl(), "logout");
		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	// 该方法可以手动设置filter，从而修改shiro的urlpatterns，但是并没实现，还需要研究
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// @Bean
	// public FilterRegistrationBean filterRegistrationBean() {
	// FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
	// filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
	// // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
	// // filterRegistration.addInitParameter("targetFilterLifecycle", "true");
	// filterRegistration.setEnabled(true);
	// filterRegistration.addUrlPatterns("/manage/*");//
	// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
	// return filterRegistration;
	// }

	/**
	 * Shiro默认提供了三种 AuthenticationStrategy 实现： AtLeastOneSuccessfulStrategy
	 * ：其中一个通过则成功。 FirstSuccessfulStrategy ：其中一个通过则成功，但只返回第一个通过的Realm提供的验证信息。
	 * AllSuccessfulStrategy ：凡是配置到应用中的Realm都必须全部通过。 authenticationStrategy
	 * 
	 * @return
	 */
	@Bean(name = "authenticationStrategy")
	public AuthenticationStrategy authenticationStrategy() {
		logger.debug("ShiroConfiguration.authenticationStrategy()");
		return new FirstSuccessfulStrategy();
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "authorizationAttributeSourceAdvisor")
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		logger.debug("ShiroConfiguration.authorizationAttributeSourceAdvisor()");

		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 注入LifecycleBeanPostProcessor
	 * 
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		logger.debug("ShiroConfiguration.lifecycleBeanPostProcessor()");
		return new LifecycleBeanPostProcessor();
	}

	@ConditionalOnMissingBean
	@Bean(name = "defaultAdvisorAutoProxyCreator")
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		logger.debug("ShiroConfiguration.getDefaultAdvisorAutoProxyCreator()");
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

}
