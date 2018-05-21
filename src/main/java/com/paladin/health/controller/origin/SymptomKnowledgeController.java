package com.paladin.health.controller.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginDiseaseNameService;
import com.paladin.health.service.origin.OriginSymptomKnowledgeContentService;
import com.paladin.health.service.origin.OriginSymptomKnowledgeService;

@Controller
@RequestMapping("/health/symptom")
public class SymptomKnowledgeController {

	@Autowired
	OriginSymptomKnowledgeService symptomKnowledgeService;
	
	@Autowired
	OriginDiseaseNameService diseaseNameService;
	
	@Autowired
	OriginSymptomKnowledgeContentService symptomKnowledgeContentService;

	@RequestMapping("/index")
	public String index(@RequestParam(required = false) String symptomKey, Model model) {	
		if(symptomKey != null && symptomKey.length() !=0) {
			OriginDiseaseName name = diseaseNameService.getSymptomName(symptomKey);
			model.addAttribute("symptomKey", symptomKey);
			model.addAttribute("symptomName", name.getName());
		}		
		return "/health/origin/symptom_knowledge_index";
	}

	@RequestMapping("/list/symptom")
	@ResponseBody
	public Object knowledgeList(@RequestParam String symptomKey) {
		return CommonResponse.getSuccessResponse(symptomKnowledgeService.findAllSymptomKnowledge(symptomKey));
	}

	@RequestMapping("/content")
	@ResponseBody
	public Object knowledgeContent(@RequestParam String knowledgeId) {
		return CommonResponse.getSuccessResponse(symptomKnowledgeContentService.findKnowledgeContent(knowledgeId));
	}

	@RequestMapping("/content/symptom")
	@ResponseBody
	public Object symptomContent(@RequestParam String symptomKey) {
		return CommonResponse.getSuccessResponse(symptomKnowledgeContentService.findSymptomContent(symptomKey));
	}
	
}
