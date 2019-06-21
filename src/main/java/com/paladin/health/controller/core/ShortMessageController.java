package com.paladin.health.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.schedule.MessageScheduleService;

@Controller
@RequestMapping("/health/sms")
public class ShortMessageController {
	
	@Autowired
	private MessageScheduleService messageScheduleService;
	
	@RequestMapping("/send")
	@ResponseBody
	public Object search() {
		if(HealthUserSession.getCurrentUserSession().isSystemAdmin()) {
			messageScheduleService.sendMessage();
			return CommonResponse.getSuccessResponse();	
		}
		return CommonResponse.getNoPermissionResponse();
	}
	
}
