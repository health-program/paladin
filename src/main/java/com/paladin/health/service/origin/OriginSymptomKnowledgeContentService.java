package com.paladin.health.service.origin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.health.model.origin.OriginSymptomKnowledgeContent;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OriginSymptomKnowledgeContentService extends ServiceSupport<OriginSymptomKnowledgeContent>{
	
	public List<OriginSymptomKnowledgeContent> findKnowledgeContent(String knowledgeId) {
		return searchAll(new GeneralCriteriaBuilder.Condition(OriginSymptomKnowledgeContent.COLUMN_FIELD_KNOWLEDGE_ID, QueryType.EQUAL, knowledgeId));
	}

	public List<OriginSymptomKnowledgeContent> findSymptomContent(String symptomKey) {
		return searchAll(new GeneralCriteriaBuilder.Condition(OriginSymptomKnowledgeContent.COLUMN_FIELD_SYMPTOM_KEY, QueryType.EQUAL, symptomKey));
	}
	
}