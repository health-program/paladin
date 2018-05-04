package com.paladin.health.controller.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paladin.health.service.origin.OriginDiseaseKnowledgeContentService;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Controller
@RequestMapping("/health/diet")
public class DiseaseDietController {

	@Autowired
	OriginDiseaseKnowledgeService diseaseKnowledgeService;

	@Autowired
	OriginDiseaseNameService diseaseNameService;

	@Autowired
	OriginDiseaseKnowledgeContentService diseaseKnowledgeContentService;

	@RequestMapping("/index")
	public String index() {
		return "/health/origin/disease_diet_index";
	}

	

}
