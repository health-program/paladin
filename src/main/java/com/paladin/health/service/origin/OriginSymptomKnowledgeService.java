package com.paladin.health.service.origin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.health.model.origin.OriginSymptomKnowledge;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OriginSymptomKnowledgeService extends ServiceSupport<OriginSymptomKnowledge>{
	
	public List<OriginSymptomKnowledge> findAllSymptomKnowledge(String symptomKey) {
		return searchAll(new GeneralCriteriaBuilder.Condition(OriginSymptomKnowledge.COLUMN_FIELD_SYMPTOM_KEY, QueryType.EQUAL, symptomKey));
	}
	
}