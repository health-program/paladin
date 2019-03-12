package com.paladin.health.service.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.prescription.PrescriptionItemMapper;
import com.paladin.health.model.prescription.PrescriptionItem;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class PrescriptionItemService extends ServiceSupport<PrescriptionItem>{
	
	@Autowired
	PrescriptionItemMapper prescriptionItemMapper;
	
	public List<PrescriptionItem> findItemOfFactor(String code) {		
		return prescriptionItemMapper.findItemOfFactor(code);		
	}
	
	public List<PrescriptionItem> findItemByType(Integer type) {		
        return searchAll(new Condition(PrescriptionItem.COLUMN_FIELD_TYPE, QueryType.EQUAL, type));
	}
	
	
}