package com.paladin.health.core.factor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainer;
import com.paladin.health.core.HealthPrescriptionContainer;
import com.paladin.health.core.IndexContainer;
import com.paladin.health.core.factor.FactorAnalyzer.Basis;
import com.paladin.health.core.factor.FactorAnalyzer.FactorResult;
import com.paladin.health.library.Relation;
import com.paladin.health.library.index.item.ItemValueDefinition;
import com.paladin.health.library.index.item.ItemValueDefinition.InputType;
import com.paladin.health.library.index.item.ItemValueDefinition.ValueType;
import com.paladin.health.library.index.item.StandardItem;
import com.paladin.health.model.prescription.PrescriptionFactor;
import com.paladin.health.model.prescription.PrescriptionFactorCondition;
import com.paladin.health.service.prescription.PrescriptionFactorConditionService;

@Component
public class HealthFactorAnalyzer implements SpringContainer {

	@Autowired
	private PrescriptionFactorConditionService prescriptionFactorConditionService;

	@Autowired
	private IndexContainer indexContainer;

	@Autowired
	private HealthPrescriptionContainer healthPrescriptionContainer;

	private List<FactorAnalyzer> factorAnalyzers;
	private List<Basis> analyzeBasises;

	@Override
	public boolean initialize() {

		List<PrescriptionFactorCondition> conditions = prescriptionFactorConditionService.findAll();

		HashMap<Integer, List<PrescriptionFactorCondition>> map = new HashMap<>();

		for (PrescriptionFactorCondition condition : conditions) {

			Integer arrayId = condition.getConditionArrayId();
			if (arrayId == null) {
				arrayId = condition.getId();
			}

			List<PrescriptionFactorCondition> array = map.get(arrayId);
			if (array == null) {
				array = new ArrayList<>();
				map.put(arrayId, array);
			}

			array.add(condition);
		}

		List<FactorAnalyzer> factorAnalyzers = new ArrayList<>();

		for (List<PrescriptionFactorCondition> array : map.values()) {
			factorAnalyzers.add(new ConditionFactorAnalyzer(array));
		}

		Map<String, FactorAnalyzer> springBeans = SpringBeanHelper.getBeansByType(FactorAnalyzer.class);

		factorAnalyzers.addAll(springBeans.values());

		List<FactorAnalyzer> analyzers1 = new ArrayList<>(factorAnalyzers.size());
		List<FactorAnalyzer> analyzers2 = new ArrayList<>(factorAnalyzers.size());

		for (FactorAnalyzer factorAnalyzer : factorAnalyzers) {
			if (factorAnalyzer instanceof ConditionFactorAnalyzer) {
				if (((ConditionFactorAnalyzer) factorAnalyzer).hasDiseaseJudger()) {
					analyzers2.add(factorAnalyzer);
					continue;
				}
			}
			analyzers1.add(factorAnalyzer);
		}

		analyzers1.addAll(analyzers2);
		this.factorAnalyzers = analyzers1;

		// 对分析器排序
		Collections.sort(this.factorAnalyzers, new Comparator<FactorAnalyzer>() {
			@Override
			public int compare(FactorAnalyzer o1, FactorAnalyzer o2) {
				int i1 = o1.order();
				int i2 = o2.order();
				return i1 == i2 ? 0 : (i1 > i2 ? 1 : -1);
			}
		});

		// 初始化分析器判断标准的文字说明
		analyzeBasises = new ArrayList<>();
		for (FactorAnalyzer analyzer : this.factorAnalyzers) {
			List<Basis> basises = analyzer.getBasis();
			if (basises == null) {
				continue;
			}
			analyzeBasises.addAll(basises);
		}

		return true;
	}

	/**
	 * 分析因素
	 * 
	 * @param peopleCondition
	 */
	public void analyzeFactor(PeopleCondition peopleCondition) {
		for (FactorAnalyzer analyzer : factorAnalyzers) {
			FactorResult factor = analyzer.analyseFactor(peopleCondition);
			if (factor != null) {
				peopleCondition.addFactor(factor);
			}
		}
	}

	/**
	 * 获取分析准则
	 * 
	 * @return
	 */
	public List<Basis> getAnalyzeBasises() {
		return analyzeBasises;
	}

	private class ConditionFactorAnalyzer implements FactorAnalyzer {

		private String factorCode;
		private boolean isSpeculateFactor = false;
		private String illustration;
		private List<ConditionJudger> judgers = new ArrayList<>();

		private ConditionFactorAnalyzer(List<PrescriptionFactorCondition> conditions) {
			boolean first = true;
			for (PrescriptionFactorCondition condition : conditions) {
				if (first) {
					factorCode = condition.getFactorCode();
					isSpeculateFactor = condition.getType() == PrescriptionFactorCondition.TYPE_SPECULATE_DISEASE;
					illustration = condition.getIllustration();
					first = false;
				}
				judgers.add(new ConditionJudger(condition));
			}
		}

		@Override
		public FactorResult analyseFactor(PeopleCondition peopleCondition) {

			for (ConditionJudger judger : judgers) {
				if (!judger.judge(peopleCondition)) {
					return null;
				}
			}

			if (isSpeculateFactor) {
				return new FactorResult(factorCode, PrescriptionFactorCondition.TYPE_SPECULATE_DISEASE, illustration);
			}
			return new FactorResult(factorCode);
		}

		private boolean hasDiseaseJudger() {
			for (ConditionJudger judger : judgers) {
				if (judger.diseases != null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public List<Basis> getBasis() {
			PrescriptionFactor factor = healthPrescriptionContainer.getFactorByCode(factorCode);
			if (factor != null) {
				List<String> basises = new ArrayList<>(judgers.size());
				for (ConditionJudger judger : judgers) {
					basises.add(judger.toString());
				}
				Basis basis = new Basis(factor.getName(), basises);
				return Arrays.asList(basis);
			}

			return null;
		}

		@Override
		public int order() {
			return 0;
		}
	}

	private class ConditionJudger {

		private Relation relation;
		private String key;

		private boolean isNumber = false;
		private boolean isSingle = false;
		private String[] stringValues;
		private Double[] numberValues;
		private boolean nullable;

		private String[] diseases;
		private StandardItem standardItem;

		private ConditionJudger(PrescriptionFactorCondition condition) {
			key = condition.getItemKey();
			relation = Enum.valueOf(Relation.class, condition.getRelation());
			stringValues = condition.getValue().split(",");
			nullable = condition.getNullable() == 1;

			standardItem = indexContainer.getStandardItem(key);
			ItemValueDefinition valueDefinition = standardItem.getItemValueDefinition();
			InputType inputType = valueDefinition.getInputType();

			if (inputType == InputType.INPUT && valueDefinition.getValueType() == ValueType.NUMBER) {
				isNumber = true;
				numberValues = new Double[stringValues.length];
				for (int i = 0; i < stringValues.length; i++) {
					numberValues[i] = Double.valueOf(stringValues[i]);
				}
			}

			isSingle = stringValues.length == 1;

			if (relation == Relation.BETWEEN || relation == Relation.BETWEEN_EQUAL) {
				if (numberValues[0] > numberValues[1]) {
					Double a = numberValues[0];
					numberValues[0] = numberValues[1];
					numberValues[1] = a;
				}
			}

			String disease = condition.getDisease();
			if (disease != null && disease.length() != 0) {
				diseases = disease.split(",");
			}
		}

		private boolean judge(PeopleCondition peopleCondition) {

			if (diseases != null) {
				Collection<String> factors = peopleCondition.getFactors();
				for (String disease : diseases) {
					if (!factors.contains(disease)) {
						return false;
					}
				}
			}

			if (isNumber) {
				Double[] values = peopleCondition.getDoubleArray(key);
				if (values == null) {
					return nullable ? true : false;
				}

				if (isSingle) {
					try {
						if (relation == Relation.EQUAL || relation == Relation.IN) {
							return numberValues[0].equals(values[0]);
						} else if (relation == Relation.NOT_EQUAL) {
							// 空值不能做不等于的判断
							return !numberValues[0].equals(values[0]);
						} else if (relation == Relation.HAVE) {
							return isIn(values, numberValues[0]);
						} else if (relation == Relation.GREAT) {
							return numberValues[0].compareTo(values[0]) < 0;
						} else if (relation == Relation.GREAT_EQUAL) {
							return numberValues[0].compareTo(values[0]) <= 0;
						} else if (relation == Relation.LESS) {
							return numberValues[0].compareTo(values[0]) > 0;
						} else if (relation == Relation.LESS_EQUAL) {
							return numberValues[0].compareTo(values[0]) >= 0;
						}
					} catch (Exception e) {

					}
				} else {

					if (relation == Relation.EQUAL) {
						return isArrayEqual(values, numberValues);
					} else if (relation == Relation.NOT_EQUAL) {
						return !isArrayEqual(values, numberValues);
					} else if (relation == Relation.IN) {
						for (Double a : values) {
							if (!isIn(numberValues, a)) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.HAVE) {
						for (Double a : numberValues) {
							if (!isIn(values, a)) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.BETWEEN) {
						Double d1 = numberValues[0];
						Double d2 = numberValues[1];
						for (Double a : values) {
							if (a.compareTo(d1) <= 0 || a.compareTo(d2) >= 0) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.BETWEEN_EQUAL) {
						Double d1 = numberValues[0];
						Double d2 = numberValues[1];
						for (Double a : values) {
							if (a.compareTo(d1) < 0 || a.compareTo(d2) > 0) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.GREAT) {
						Double d = numberValues[0];
						for (Double a : values) {
							if (a.compareTo(d) <= 0) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.GREAT_EQUAL) {
						Double d = numberValues[0];
						for (Double a : values) {
							if (a.compareTo(d) < 0) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.LESS) {
						Double d = numberValues[0];
						for (Double a : values) {
							if (a.compareTo(d) >= 0) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.LESS_EQUAL) {
						Double d = numberValues[0];
						for (Double a : values) {
							if (a.compareTo(d) > 0) {
								return false;
							}
						}
						return true;
					}

				}
			} else {
				String[] values = peopleCondition.getStringArray(key);
				if (values == null) {
					return nullable ? true : false;
				}

				if (isSingle) {
					if (relation == Relation.EQUAL || relation == Relation.IN) {
						return stringValues[0].equals(values[0]);
					} else if (relation == Relation.NOT_EQUAL) {
						return !stringValues[0].equals(values[0]);
					} else if (relation == Relation.HAVE) {
						return isIn(values, stringValues[0]);
					}
				} else {
					if (relation == Relation.EQUAL) {
						return isArrayEqual(stringValues, values);
					} else if (relation == Relation.NOT_EQUAL) {
						return !isArrayEqual(stringValues, values);
					} else if (relation == Relation.IN) {
						for (String s : values) {
							if (!isIn(stringValues, s)) {
								return false;
							}
						}
						return true;
					} else if (relation == Relation.HAVE) {
						for (String val : stringValues) {
							if (!isIn(values, val)) {
								return false;
							}
						}
						return true;
					}
				}
			}

			return false;
		}

		private <T> boolean isIn(T[] array, T val) {
			if (val == null) {
				return false;
			}

			for (T t : array) {
				if (t.equals(val)) {
					return true;
				}
			}
			return false;
		}

		private boolean isArrayEqual(Object[] array1, Object[] array2) {
			if (array1.length != array2.length) {
				return false;
			}
			Arrays.sort(array1);
			Arrays.sort(array2);
			return Arrays.equals(array1, array2);
		}

		public String toString() {
			return standardItem.toContent(relation, stringValues);
		}
	}

	public int order() {
		return 1;
	}

}
