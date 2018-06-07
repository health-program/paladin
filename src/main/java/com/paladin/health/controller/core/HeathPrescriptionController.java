package com.paladin.health.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.HealthPrescriptionContainer;

@Controller
@RequestMapping("/health/prescription")
public class HeathPrescriptionController {
	
	@Autowired
	HealthPrescriptionContainer healthPrescriptionContainer;
	
	@RequestMapping("/index")
	public String index() {
		return "/health/core/health_prescription";
	}
	
	@RequestMapping("/factor/list")
	@ResponseBody
	public Object factorList() {
		return CommonResponse.getSuccessResponse(healthPrescriptionContainer.getPrescriptionFactor());
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public Object search(@RequestParam String[] args) {
		return CommonResponse.getSuccessResponse(healthPrescriptionContainer.search(args));
	}
	
	
}
