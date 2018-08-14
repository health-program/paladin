package com.paladin.health.core.factor.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paladin.health.core.factor.FactorAnalyzer;
import com.paladin.health.core.factor.PeopleCondition;

/**
 *  腹型肥胖因素分析器
 * <p>该类包含部分硬编码，请参考数据库表index_item</p>
 * @author TontoZhou
 * @since 2018年7月20日
 */
@Component
public class AbdominalObesityFactorAnalyzer implements FactorAnalyzer {

	private final static String factor_fxfp = "fxfp";

	@Override
	public FactorResult analyseFactor(PeopleCondition peopleCondition) {
		
		boolean isGirl = peopleCondition.getDiagnoseTarget().isGirl();		

		// Double bmi = peopleCondition.getDouble("bmi");
		Double yw = peopleCondition.getDouble("yw");
		
		if (yw != null) {
			if(yw >=90 || (isGirl && yw >=85)) {
				return new FactorResult(factor_fxfp);
			} 
//			暂时不考虑细化标准			
//			else if(bmi != null &&bmi <24 && (yw >=85 || (isGirl && yw >=80))) {
//				return new FactorResult(factor_fxfp);
//			}				
		}

		return null;
	}

	@Override
	public List<Basis> getBasis() {	
		Basis basis = new Basis("腹型肥胖","腰围>=90cm或女性腰围>=85cm");
		return Arrays.asList(basis);
	}

	@Override
	public int order() {
		return -1;
	}
}
