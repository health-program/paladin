package com.paladin.framework.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.WebUtil;
import com.paladin.framework.web.response.CommonResponse;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	private String defaultErrorView;

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	private void sendJson(HttpServletResponse response, Object obj) {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		try {
			JsonUtil.writeJson(response.getOutputStream(), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// 需要注释？

		String page = null;
		CommonResponse responseObject = null;

		String message = ex.getMessage();

		if (ex instanceof BusinessException) {
			responseObject = CommonResponse.getFailResponse(message == null ? "业务异常" : message);
			page = "/error_business";
		} else if (ex instanceof BadRequestException) {
			responseObject = CommonResponse.getErrorResponse(message == null ? "请求参数异常" : message);
			page = "/error_bad_request";
		} else if (ex instanceof SystemException) {
			responseObject = CommonResponse.getErrorResponse(message == null ? "系统异常" : message);
			page = "/error_system";
			logger.error("系统异常", ex);
		} else {
			responseObject = CommonResponse.getErrorResponse("系统未知异常");
			page = "/error";
			logger.error("系统未知异常", ex);
		}

		if (logger.isDebugEnabled()) {
			ex.printStackTrace();
		}

		if (WebUtil.isAjaxRequest(request)) {
			sendJson(response, responseObject);
		} else {
			return new ModelAndView(page, "error", responseObject.getResult());
		}

		return new ModelAndView();
	}

}
