package com.paladin.health.core.factor.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paladin.health.core.factor.FactorAnalyzer;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.model.diagnose.DiagnoseTarget;

@Component
public class PopulationFactorAnalyzer implements FactorAnalyzer {

	@Override
	public FactorResult analyseFactor(PeopleCondition peopleCondition) {

		Integer sex = peopleCondition.getInteger("xb");
		if (sex != null) {
			if (sex == DiagnoseTarget.SEX_WOMAN) {
				peopleCondition.addFactor("woman");
			} else if (sex == DiagnoseTarget.SEX_MAN) {
				peopleCondition.addFactor("man");
			}
		}

		Double age = peopleCondition.getDouble("dqnl");
		if(age != null) {
			if (age >= 65) {
				peopleCondition.addFactor("lnr");
			}

			if (age >= 80) {
				peopleCondition.addFactor("gllnr");
			}
		}

		return null;
	}

	@Override
	public List<Basis> getBasis() {
		Basis basis1 = new Basis("老年人", "年龄>=65岁");
		Basis basis2 = new Basis("高龄老年人", "年龄>=80岁");
		return Arrays.asList(basis1, basis2);
	}

	@Override
	public int order() {
		return Integer.MIN_VALUE;
	}

}
