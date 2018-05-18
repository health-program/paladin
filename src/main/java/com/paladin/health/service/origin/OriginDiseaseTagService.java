package com.paladin.health.service.origin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.health.model.origin.OriginDiseaseTag;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OriginDiseaseTagService extends ServiceSupport<OriginDiseaseTag>{

	public List<OriginDiseaseTag> findAllDiseaseTag(String diseaseKey) {
		return searchAll(new GeneralCriteriaBuilder.Condition(OriginDiseaseTag.COLUMN_FIELD_DISEASE_KEY, QueryType.EQUAL, diseaseKey));
	}

}