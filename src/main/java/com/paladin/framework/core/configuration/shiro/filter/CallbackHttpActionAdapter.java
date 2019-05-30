package com.paladin.framework.core.configuration.shiro.filter;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.http.adapter.HttpActionAdapter;

import com.paladin.framework.core.configuration.PaladinConstants;
import com.paladin.framework.core.configuration.shiro.ShiroCasProperties;

public class CallbackHttpActionAdapter implements HttpActionAdapter<Object, J2EContext>{
	
	private String loginTypeField;
	
	public CallbackHttpActionAdapter(ShiroCasProperties shiroCasProperties) {
		this.loginTypeField = shiroCasProperties.getLoginTypeField();
	}
	
	@Override
	public Object adapt(int code, J2EContext context) {
		if(code == 302) {
			context.getRequest().getSession().setAttribute(loginTypeField, PaladinConstants.LOGIN_TYPE_CAS);
		}
		return null;
	}

}
