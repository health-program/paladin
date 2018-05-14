package com.paladin.health.controller.origin;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.origin.OriginDiseaseDietService;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeContentService;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/health/diet")
public class DiseaseDietController {

	@Autowired
	OriginDiseaseKnowledgeService diseaseKnowledgeService;

	@Autowired
	OriginDiseaseNameService diseaseNameService;

	@Autowired
	OriginDiseaseKnowledgeContentService diseaseKnowledgeContentService;

	@Autowired
	OriginDiseaseDietService diseaseDietService;

	@RequestMapping("/index")
	public String index() {
		return "/health/origin/disease_diet_index";
	}

	@RequestMapping("/list")
    @ResponseBody
	public Object list() { return CommonResponse.getSuccessResponse(diseaseNameService.findAll()); }

	@RequestMapping("/dietInfo")
    @ResponseBody
	public  Object diseaseDietInfo(@RequestParam String diseaseKey) {
        return  CommonResponse.getSuccessResponse(diseaseDietService.findDiseaseDiet(diseaseKey));
    }
}
