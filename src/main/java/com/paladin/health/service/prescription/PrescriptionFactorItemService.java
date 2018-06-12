package com.paladin.health.service.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.mapper.prescription.PrescriptionFactorItemMapper;
import com.paladin.health.model.prescription.PrescriptionFactorItem;

@Service
public class PrescriptionFactorItemService extends ServiceSupport<PrescriptionFactorItem>{

	@Autowired
	PrescriptionFactorItemMapper prescriptionFactorItemMapper;
	
	public List<PrescriptionFactorItem> findFactorByItem(Integer id) {
        return searchAll(new GeneralCriteriaBuilder.Condition(PrescriptionFactorItem.COLUMN_FIELD_ITEM_ID, QueryType.EQUAL, id));
	}
		
	public int saveFactorItemRelation(String factorCode, Integer[] itemIds) {		
		prescriptionFactorItemMapper.removeFactorItems(factorCode);		
		PrescriptionFactorItem factorItem = new PrescriptionFactorItem();
		factorItem.setFactorCode(factorCode);
		
		if(itemIds != null) {
			for(Integer itemId: itemIds) {
				factorItem.setItemId(itemId);
				 save(factorItem);
			}
		}
		
		return 1;
	}
	
}