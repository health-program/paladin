package com.paladin.health.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringContainer;
import com.paladin.health.data.parser.knowledge.ErrorKnowledgePageGetter;
import com.paladin.health.mapper.origin.OriginDiseaseKnowledgeContentMapper;
import com.paladin.health.mapper.origin.OriginDiseaseKnowledgeMapper;
import com.paladin.health.service.origin.OriginDiseaseNameService;

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
	@Autowired
	OriginDiseaseNameService diseaseNameService;
	@Autowired
	OriginDiseaseKnowledgeMapper diseaseKnowledgeMapper;
	@Autowired
	OriginDiseaseKnowledgeContentMapper diseaseKnowledgeContentMapper;

	@Autowired
	DiseaseBaseAnalysis diseaseBaseAnalysis;

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

	protected void readKnow() {
		List<String[]> ignoreList = ErrorKnowledgePageGetter.getErrorDisease2("d:/temp/error.txt");
		Map<String, Set<String>> ignoreMap = new HashMap<>();

		List<String[]> list = new ArrayList<>();
		for (String[] ignore : ignoreList) {
			String s1 = ignore[0];
			String s2 = ignore[1];

			Set<String> set = ignoreMap.get(s1);
			if (set == null) {
				set = new HashSet<String>();
				ignoreMap.put(s1, set);
			}
			set.add(s2);

			list.add(new String[] { s1, s2, "http://localhost:8080/error/" + s1 + "-" + s2 + ".html" });

		}
		diseaseKnowledgeReader.readDiseaseKnowledge(ignoreMap);
	}

	protected void readKnow(String[][] params) {

		List<String[]> list = new ArrayList<>();
		for (String[] param : params) {
			String s1 = param[0];
			String s2 = param[1];
			list.add(new String[] { s1, s2, "http://localhost:8080/error/" + s1 + "-" + s2 + ".html" });
		}

		diseaseKnowledgeReader.readModifiedDiseaseKnowledge(0, list);
	}

}
