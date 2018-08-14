package com.paladin.health.core.factor;

import java.util.Arrays;
import java.util.List;

import com.paladin.health.model.prescription.PrescriptionFactorCondition;

public interface FactorAnalyzer {

	public FactorResult analyseFactor(PeopleCondition peopleCondition);

	public List<Basis> getBasis();
	
	/**
	 * 顺序
	 * @return
	 */
	public int order();
	
	public static class FactorResult {

		private String factor;

		/** {@link PrescriptionFactorCondition}中的类型 */
		private Integer type;

		private String illustration;

		public FactorResult(String factor, Integer type, String illustration) {
			this.factor = factor;
			this.type = type;
			this.illustration = illustration;
		}

		public FactorResult(String factor, Integer type) {
			this(factor, type, null);
		}

		public FactorResult(String factor) {
			this(factor, PrescriptionFactorCondition.TYPE_DEFAULT, null);
		}

		public String getFactor() {
			return factor;
		}

		public void setFactor(String factor) {
			this.factor = factor;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getIllustration() {
			return illustration;
		}

		public void setIllustration(String illustration) {
			this.illustration = illustration;
		}

	}

	public static class Basis{
		
		private String factorName;
		
		private List<String> basises;
		
		public Basis(String factorName, List<String> basises) {
			this.factorName = factorName;
			this.basises = basises;
		}
		
		public Basis(String factorName, String... basises) {
			this.factorName = factorName;
			this.basises = Arrays.asList(basises);
		}

		public String getFactorName() {
			return factorName;
		}

		public void setFactorName(String factorName) {
			this.factorName = factorName;
		}

		public List<String> getBasises() {
			return basises;
		}

		public void setBasises(List<String> basises) {
			this.basises = basises;
		}
		
	}

}
