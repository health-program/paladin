package com.paladin.health.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringContainer;

@Component
public class ReaderContainer implements SpringContainer{

	@Autowired
	OriginDiseaseNameReader diseaseNameReader;
	@Autowired
	OriginDiseaseSummaryReader diseaseSummaryReader;
	@Autowired
	OriginDiseaseKnowledgeReader diseaseKnowledgeReader;
	
	@Override
	public boolean initialize() {
		//diseaseSummaryReader.readDiseaseSummary();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				//diseaseKnowledgeReader.readDiseaseKnowledgeSingle(0);
			}		
		}).start();
		
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
