package com.paladin.health.controller.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/health/prescription")
public class HeathPrescriptionController {
	
	@RequestMapping("/index")
	public String index() {
		return "/health/core/health_prescription";
	}
	
}
