package com.paladin.health.controller.open;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.utils.WebUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.IndexContainer;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.service.core.HealthPrescriptionService;

@Controller
@RequestMapping("/open")
public class DiagnoseController {
	
	@Autowired
	private HealthPrescriptionService healthPrescriptionService;
	
	@Autowired
	private IndexContainer indexContainer;
	
	@RequestMapping("/diagnose/index")
	public Object diagnoseIndex () {
		return "/health/open/open_prescription_analyze";
	}
	
	@RequestMapping("/interface")
	public Object interfaceIndex (Model model, HttpServletRequest request) {
		model.addAttribute("baseUrl", WebUtil.getServletPath(request));
		return "/health/open/open_interface";
	}
	
	@RequestMapping("/interface/data")
	@ResponseBody
	public Object interfaceData() {		
		return CommonResponse.getSuccessResponse(indexContainer.getStandardItems());
	}
	
	@RequestMapping("/diagnose")
	@ResponseBody
	public Object diagnose(@RequestBody PeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnose(condition));
	}
	
}
