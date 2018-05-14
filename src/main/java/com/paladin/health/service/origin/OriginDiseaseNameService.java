package com.paladin.health.service.origin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.health.model.origin.OriginDiseaseName;
import com.github.pagehelper.Page;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OriginDiseaseNameService extends ServiceSupport<OriginDiseaseName> {

	public OriginDiseaseName getDiseaseName(String diseaseKey) {

		List<OriginDiseaseName> names = searchAll(new GeneralCriteriaBuilder.Condition[] {
				new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, OriginDiseaseName.TYPE_DISEASE),
				new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_NAME_KEY, QueryType.EQUAL, diseaseKey) });

		if (names == null || names.size() == 0) {
			return null;
		}

		return names.get(0);
	}
	
	public OriginDiseaseName getSymptomName(String symptomKey) {

		List<OriginDiseaseName> names = searchAll(new GeneralCriteriaBuilder.Condition[] {
				new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, OriginDiseaseName.TYPE_SYMPTOM),
				new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_NAME_KEY, QueryType.EQUAL, symptomKey) });

		if (names == null || names.size() == 0) {
			return null;
		}

		return names.get(0);
	}

	public List<OriginDiseaseName> findAllDiseaseName() {
		return searchAll(new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, OriginDiseaseName.TYPE_DISEASE));
	}

	public Page<OriginDiseaseName> findPageDiseaseName(OffsetPage page) {
		return searchPage(new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, OriginDiseaseName.TYPE_DISEASE), page);
	}

	public Page<OriginDiseaseName> findPageSymptomName(OffsetPage page) {
		return searchPage(new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, OriginDiseaseName.TYPE_SYMPTOM), page);
	}
	
	public List<OriginDiseaseName> findAllSymptomName() {
		return searchAll(new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, OriginDiseaseName.TYPE_SYMPTOM));
	}

}