package com.paladin.health.core.factor.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paladin.health.core.factor.FactorAnalyzer;
import com.paladin.health.core.factor.PeopleCondition;

/**
 * 肥胖因素分析器
 * <p>
 * 该类包含部分硬编码，请参考数据库表index_item
 * </p>
 * 
 * @author TontoZhou
 * @since 2018年7月20日
 */
@Component
public class ObesityFactorAnalyzer implements FactorAnalyzer {

	private final static String factor_fp = "fp";
	private final static String factor_xs = "xs";
	private final static String factor_cz = "cz";

	// private static Map<Integer, Double> boyAgeBmiMap = new HashMap<>();
	// private static Map<Integer, Double> girlAgeBmiMap = new HashMap<>();
	//
	// static {
	// boyAgeBmiMap.put(6, 17.7);
	// boyAgeBmiMap.put(7, 18.7);
	// boyAgeBmiMap.put(8, 19.7);
	// boyAgeBmiMap.put(9, 20.8);
	// boyAgeBmiMap.put(10, 21.9);
	// boyAgeBmiMap.put(11, 23.0);
	// boyAgeBmiMap.put(12, 24.1);
	// boyAgeBmiMap.put(13, 25.2);
	// boyAgeBmiMap.put(14, 26.1);
	// boyAgeBmiMap.put(15, 26.6);
	// boyAgeBmiMap.put(16, 27.1);
	// boyAgeBmiMap.put(17, 27.6);
	// boyAgeBmiMap.put(18, 28.0);
	//
	// girlAgeBmiMap.put(6, 17.5);
	// girlAgeBmiMap.put(7, 18.5);
	// girlAgeBmiMap.put(8, 19.4);
	// girlAgeBmiMap.put(9, 20.4);
	// girlAgeBmiMap.put(10, 21.5);
	// girlAgeBmiMap.put(11, 22.7);
	// girlAgeBmiMap.put(12, 23.9);
	// girlAgeBmiMap.put(13, 24.5);
	// girlAgeBmiMap.put(14, 25.9);
	// girlAgeBmiMap.put(15, 26.6);
	// girlAgeBmiMap.put(16, 27.1);
	// girlAgeBmiMap.put(17, 27.6);
	// girlAgeBmiMap.put(18, 28.0);
	// }

	@Override
	public FactorResult analyseFactor(PeopleCondition peopleCondition) {

		Double fpBmi = 28.0;
		Double czBmi = 24.0;
		Double xsBmi = 18.5;
		// 由于青少年的BMI变化很大，去除该标准

		// Integer age = peopleCondition.getInteger("dcnl");
		// boolean isGirl = peopleCondition.getDiagnoseTarget().isGirl();

		// if (age != null) {
		// if (age < 6) {
		// return null;
		// }
		// if (age <= 18) {
		// if (isGirl) {
		// fpBmi = girlAgeBmiMap.get(age);
		// } else {
		// fpBmi = boyAgeBmiMap.get(age);
		// }
		// }
		// }

		Double bmi = peopleCondition.getDouble("bmi");

		if (bmi != null) {
			if (bmi >= fpBmi) {
				return new FactorResult(factor_fp);
			} else if (bmi >= czBmi) {
				return new FactorResult(factor_cz);
			} else if (bmi < xsBmi) {
				return new FactorResult(factor_xs);
			}
		}

		return null;
	}

	@Override
	public List<Basis> getBasis() {
		Basis basis1 = new Basis("肥胖", "BMI大于等于28.0");
		Basis basis2 = new Basis("超重", "BMI大于等于24.0");
		Basis basis3 = new Basis("消瘦", "BMI小于18.5");
		return Arrays.asList(basis1, basis2, basis3);
	}

	@Override
	public int order() {
		return -1;
	}

}
