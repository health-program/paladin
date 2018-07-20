package com.paladin.health.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.service.core.HealthPrescriptionService;

@Controller
@RequestMapping("/open")
public class DiagnoseController {
	
	@Autowired
	HealthPrescriptionService healthPrescriptionService;
	
	@RequestMapping("/diagnose")
	@ResponseBody
	public Object diagnose(@RequestBody PeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnose(condition));
	}
	
}
