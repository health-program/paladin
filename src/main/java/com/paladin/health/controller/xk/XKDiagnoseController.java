package com.paladin.health.controller.xk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;

/**
 * 基于熙康知识库
 * @author TontoZhou
 * @since 2019年3月13日
 */
@Controller
@RequestMapping("/xk/")
public class XKDiagnoseController {
	
	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;
	
	@RequestMapping("/diagnose/input")
	public Object input() {
		return "/health/xk/diagnose_input";
	}
	
	@RequestMapping("/diagnose")
	@ResponseBody
	public Object diagnose(@RequestBody XKPeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnose(condition));
	}
	
	
}
