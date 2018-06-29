package com.paladin.health.core.factor.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.paladin.health.core.factor.FactorAnalyzer;
import com.paladin.health.core.factor.PeopleCondition;

@Component
public class ObesityFactorAnalyzer implements FactorAnalyzer {

	private final static String factor = "fp";

	private static Map<Integer, Double> boyAgeBmiMap = new HashMap<>();
	private static Map<Integer, Double> girlAgeBmiMap = new HashMap<>();

	static {
		boyAgeBmiMap.put(6, 17.7);
		boyAgeBmiMap.put(7, 18.7);
		boyAgeBmiMap.put(8, 19.7);
		boyAgeBmiMap.put(9, 20.8);
		boyAgeBmiMap.put(10, 21.9);
		boyAgeBmiMap.put(11, 23.0);
		boyAgeBmiMap.put(12, 24.1);
		boyAgeBmiMap.put(13, 25.2);
		boyAgeBmiMap.put(14, 26.1);
		boyAgeBmiMap.put(15, 26.6);
		boyAgeBmiMap.put(16, 27.1);
		boyAgeBmiMap.put(17, 27.6);
		boyAgeBmiMap.put(18, 28.0);

		girlAgeBmiMap.put(6, 17.5);
		girlAgeBmiMap.put(7, 18.5);
		girlAgeBmiMap.put(8, 19.4);
		girlAgeBmiMap.put(9, 20.4);
		girlAgeBmiMap.put(10, 21.5);
		girlAgeBmiMap.put(11, 22.7);
		girlAgeBmiMap.put(12, 23.9);
		girlAgeBmiMap.put(13, 24.5);
		girlAgeBmiMap.put(14, 25.9);
		girlAgeBmiMap.put(15, 26.6);
		girlAgeBmiMap.put(16, 27.1);
		girlAgeBmiMap.put(17, 27.6);
		girlAgeBmiMap.put(18, 28.0);

	}

	@Override
	public FactorResult analyseFactor(PeopleCondition peopleCondition) {

		Double limitBmi = 28.0;
 
		Integer age = peopleCondition.getInteger("dcnl");
		String sex = peopleCondition.getString("xb");
		boolean isGirl = "f".equals(sex);

		if (age != null) {
			if (age < 6) {
				return null;
			}
			if (age <= 18) {
				if (isGirl) {
					limitBmi = girlAgeBmiMap.get(age);
				} else {
					limitBmi = boyAgeBmiMap.get(age);
				}
			}
		}

		Double bmi = peopleCondition.getDouble("bmi");
		Double yw = peopleCondition.getDouble("yw");
		Double sg = peopleCondition.getDouble("sg");

		if (bmi != null && bmi >= limitBmi) {
			return new FactorResult(factor);
		}

		if (yw != null) {
			if (sg != null) {
				double x = yw / sg;
				if (x > 0.53 || (isGirl && x > 0.49)) {
					return new FactorResult(factor);
				}
			 } else if (bmi != null) {				
				if(yw >=90 && (isGirl && yw >=85)) {
					return new FactorResult(factor);
				} else if(bmi <24 && yw >=85 && (isGirl && yw >=80)) {
					return new FactorResult(factor);
				}				
			}
		}

		return null;
	}

	@Override
	public String getFactor() {
		return factor;
	}

}
