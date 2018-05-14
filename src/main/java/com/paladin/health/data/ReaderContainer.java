package com.paladin.health.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringContainer;

@Component
public class ReaderContainer implements SpringContainer {

	@Autowired
	OriginDiseaseNameReader diseaseNameReader;
	@Autowired
	OriginDiseaseSummaryReader diseaseSummaryReader;
	@Autowired
	OriginDiseaseKnowledgeReader diseaseKnowledgeReader;
	@Autowired
	OriginDiseaseDietReader diseaseDietReader;
	@Autowired
	OriginDiseaseKnowledgeRepairer diseaseKnowledgeRepairer;
	@Autowired
	OriginSymptomKnowledgeReader symptomKnowledgeReader;
	@Autowired
	OriginSymptomKnowledgeRepairer symptomKnowledgeRepairer;
	
	@Override
	public boolean initialize() {		
		return true;
	}

	@Override
	public boolean afterInitialize() {
		return true;
	}

	@Override
	public int order() {
		return 0;
	}

}
