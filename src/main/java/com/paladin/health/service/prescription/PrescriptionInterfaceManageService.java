package com.paladin.health.service.prescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.prescription.PrescriptionInterfaceManageMapper;
import com.paladin.health.model.prescription.PrescriptionInterfaceManage;
import com.paladin.framework.core.ServiceSupport;

@Service
public class PrescriptionInterfaceManageService extends ServiceSupport<PrescriptionInterfaceManage> {

	@Autowired
	private PrescriptionInterfaceManageMapper prescriptionInterfaceManageMapper;
	
	public boolean start(String id) {
		return prescriptionInterfaceManageMapper.start(id) >0;
	}

	public boolean stop(String id) {
		return prescriptionInterfaceManageMapper.stop(id) >0;
	}

}