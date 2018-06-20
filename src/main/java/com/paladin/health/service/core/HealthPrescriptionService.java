package com.paladin.health.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.core.HealthPrescriptionContainer;
import com.paladin.health.core.HealthPrescriptionContainer.PrescriptionResult;
import com.paladin.health.core.factor.HealthFactorAnalyzer;
import com.paladin.health.core.factor.PeopleCondition;

@Service
public class HealthPrescriptionService {

	@Autowired
	private HealthPrescriptionContainer healthPrescriptionContainer;
	@Autowired
	private HealthFactorAnalyzer healthFactorAnalyzer;

	public PrescriptionResult findPrescription(String... args) {
		return healthPrescriptionContainer.search(args);
	}

	public PrescriptionResult findPrescription(PeopleCondition peopleCondition) {
		peopleCondition.initialize();
		String[] result = healthFactorAnalyzer.analyzeFactor(peopleCondition);
		return healthPrescriptionContainer.search(result);
	}
}
