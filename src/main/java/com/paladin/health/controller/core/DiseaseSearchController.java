package com.paladin.health.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.DiseaseSearchContainer;

@Controller
@RequestMapping("/health/search")
public class DiseaseSearchController {

	@Autowired
	DiseaseSearchContainer diseaseSearchContainer;
	
	@RequestMapping("/index")
	public String index() {
		return "/health/core/search";
	}
	
	@RequestMapping("/")
	@ResponseBody
	public Object search(@RequestParam String[] args) {
		return CommonResponse.getSuccessResponse(diseaseSearchContainer.search(args));
	}

}
