package com.paladin.health.controller.videomanage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.core.HealthUserSession;

@Controller
@RequestMapping("/health/video/upload")
public class VideoUploadController {

	@Value("${paladin.uploader.access-key}")
	private String accessKey;
	@Value("${paladin.uploader.url}")
	private String uploaderUrl;

	@RequestMapping("/index")
	public String index() {
		if (!HealthUserSession.getCurrentUserSession().isAdminRoleLevel()) {
			throw new BusinessException("没有权限访问");
		}
		return "redirect:" + uploaderUrl + "?accessKey=" + accessKey;
	}

}
