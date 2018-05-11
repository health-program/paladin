package com.paladin.health.controller.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.origin.OriginDiseaseNameService;
import com.paladin.health.service.origin.OriginDiseaseSummaryService;

@Controller
@RequestMapping("/health/disease")
public class DiseaseNameController {

	@Autowired
	OriginDiseaseNameService diseaseNameService;
	
	@Autowired
	OriginDiseaseSummaryService diseaseSummaryService;

	@RequestMapping("/index")
	public String index() {
		return "/health/origin/disease_name_index";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object diseaseList() {
		return CommonResponse.getSuccessResponse(diseaseNameService.findAllDiseaseName());
	}

	@RequestMapping("/pagelist")
	@ResponseBody
	public Object diseasePageList(OffsetPage page) {
		return CommonResponse.getSuccessResponse(new PageResult(diseaseNameService.findPageDiseaseName(page)));
	}

	@RequestMapping("/summary")
	@ResponseBody
	public Object summary(@RequestParam String disease) {
		return CommonResponse.getSuccessResponse(diseaseSummaryService.getDiseaseSummary(disease));
	}
	
	
}
