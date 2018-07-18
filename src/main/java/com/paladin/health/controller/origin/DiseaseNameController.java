package com.paladin.health.controller.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.common.OffsetPage;
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

	@RequestMapping("/list")
	@ResponseBody
	public Object diseaseList() {
		return CommonResponse.getSuccessResponse(diseaseNameService.findAllDiseaseName());
	}
	
	@RequestMapping("/symptom/list")
	@ResponseBody
	public Object symptomList() {
		return CommonResponse.getSuccessResponse(diseaseNameService.findAllSymptomName());
	}

	@RequestMapping("/pagelist")
	@ResponseBody
	public Object diseasePageList(OffsetPage page) {
		return CommonResponse.getSuccessResponse(diseaseNameService.findPageDiseaseName(page));
	}

	@RequestMapping("/summary")
	@ResponseBody
	public Object summary(@RequestParam String disease) {
		return CommonResponse.getSuccessResponse(diseaseSummaryService.getDiseaseSummary(disease));
	}
	
	@RequestMapping("/key")
	@ResponseBody
	public Object getDiseaseKeyByName(@RequestParam String diseaseName) {
		return CommonResponse.getSuccessResponse(diseaseNameService.getDiseaseByName(diseaseName));
	}
	
	@RequestMapping("/symptom/key")
	@ResponseBody
	public Object getSymptomKeyByName(@RequestParam String symptomName) {
		return CommonResponse.getSuccessResponse(diseaseNameService.getSymptomByName(symptomName));
	}
}
