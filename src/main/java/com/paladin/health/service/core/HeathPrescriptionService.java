package com.paladin.health.service.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.core.HealthPrescriptionContainer;
import com.paladin.health.core.HealthPrescriptionContainer.PrescriptionResult;

@Service
public class HeathPrescriptionService {
	
	@Autowired
	private HealthPrescriptionContainer healthPrescriptionContainer;
	
	public PrescriptionResult search(String... args) {	
		return healthPrescriptionContainer.search(args);		
	}
	
	
}
