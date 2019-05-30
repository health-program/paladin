package com.paladin.framework.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		// 判断是不是APP,是的话返回状态码，不返回登录页
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	public static String getServletPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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

	public static void sendJsonByCors(HttpServletResponse response, Object obj, String allowOrigin) {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", allowOrigin);

		try {
			JsonUtil.writeJson(response.getWriter(), obj);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void sendJsonByCors(HttpServletResponse response, Object obj) {
		sendJsonByCors(response, obj, "*");
	}
}
