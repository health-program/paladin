package com.paladin.framework.utils;

public class LogContentUtil {

	public static String createComponent(Class<?> _interface, Class<?> _extend) {
		return "------>Create Component[" + _interface.getName() + "] = [" + _extend.getName() + "]<------";
	}

}
