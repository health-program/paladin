package com.paladin.health.service.origin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.health.model.origin.OriginDiseaseSummary;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OriginDiseaseSummaryService extends ServiceSupport<OriginDiseaseSummary> {

	public OriginDiseaseSummary getDiseaseSummary(String disease) {
		List<OriginDiseaseSummary> result = searchAll(
				new GeneralCriteriaBuilder.Condition(OriginDiseaseSummary.COLUMN_FIELD_DISEASE_KEY, QueryType.EQUAL, disease));
		return result != null && result.size() > 0 ? result.get(0) : null;
	}

}