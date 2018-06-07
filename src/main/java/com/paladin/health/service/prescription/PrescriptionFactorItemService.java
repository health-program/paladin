package com.paladin.health.service.prescription;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.health.model.prescription.PrescriptionFactorItem;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class PrescriptionFactorItemService extends ServiceSupport<PrescriptionFactorItem>{

	public List<PrescriptionFactorItem> findFactorByItem(Integer id) {
        return searchAll(new GeneralCriteriaBuilder.Condition(PrescriptionFactorItem.COLUMN_FIELD_ITEM_ID, QueryType.EQUAL, id));
	}

}