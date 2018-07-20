package com.paladin.framework.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}


	public static void sendJson(HttpServletResponse response, Object obj) {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		try {
			JsonUtil.writeJson(response.getWriter(), obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
