package com.paladin.health.core.factor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainer;
import com.paladin.framework.utils.EnumUtil;
import com.paladin.health.core.IndexContainer;
import com.paladin.health.library.Relation;
import com.paladin.health.library.index.item.ItemValueDefinition;
import com.paladin.health.library.index.item.ItemValueDefinition.InputType;
import com.paladin.health.library.index.item.ItemValueDefinition.ValueType;
import com.paladin.health.library.index.item.StandardItem;
import com.paladin.health.model.prescription.PrescriptionFactorCondition;
import com.paladin.health.service.prescription.PrescriptionFactorConditionService;

@Component
public class HealthFactorAnalyzer implements SpringContainer {

	@Autowired
	private PrescriptionFactorConditionService prescriptionFactorConditionService;

	@Autowired
	private IndexContainer indexContainer;

	private List<FactorAnalyzer> factorAnalyzers = new ArrayList<>();

	@Override
	public boolean initialize() {

		List<PrescriptionFactorCondition> conditions = prescriptionFactorConditionService.findAll();

		HashMap<String, List<PrescriptionFactorCondition>> map = new HashMap<>();

		for (PrescriptionFactorCondition condition : conditions) {

			String arrayId = condition.getConditionArrayId();
			if (arrayId == null || arrayId.length() == 0) {
				arrayId = condition.getId();
			}

			List<PrescriptionFactorCondition> array = map.get(arrayId);
			if (array == null) {
				array = new ArrayList<>();
				map.put(arrayId, array);
			}

			array.add(condition);
		}

		for (List<PrescriptionFactorCondition> array : map.values()) {
			factorAnalyzers.add(new ConditionFactorAnalyzer(array));
		}

		Map<String, FactorAnalyzer> springBeans = SpringBeanHelper.getBeansByType(FactorAnalyzer.class);

		factorAnalyzers.addAll(springBeans.values());
		return true;
	}

	public String[] analyzeFactor(PeopleCondition peopleCondition) {
		ArrayList<String> result = new ArrayList<>(factorAnalyzers.size());
		for (FactorAnalyzer analyzer : factorAnalyzers) {
			String factor = analyzer.analyseFactor(peopleCondition);
			if (factor != null) {
				result.add(factor);
			}
		}

		String[] diseases = peopleCondition.getStringArray("disease");

		if (diseases != null && diseases.length > 0) {
			for (String dis : diseases) {
				result.add(dis);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	private class ConditionFactorAnalyzer implements FactorAnalyzer {

		private String factorCode;
		private List<ConditionJudger> judgers = new ArrayList<>();

		private ConditionFactorAnalyzer(List<PrescriptionFactorCondition> conditions) {
			boolean first = true;
			for (PrescriptionFactorCondition condition : conditions) {
				if (first) {
					factorCode = condition.getFactorCode();
					first = false;
				}
				judgers.add(new ConditionJudger(condition));
			}
		}

		@Override
		public String analyseFactor(PeopleCondition peopleCondition) {

			for (ConditionJudger judger : judgers) {
				if (!judger.judge(peopleCondition)) {
					return null;
				}
			}
			return factorCode;
		}
	}

	private class ConditionJudger {

		private Relation relation;
		private String key;

		private boolean isNumber = false;
		private boolean isSingle = false;
		private String[] stringValues;
		private Double[] numberValues;

		private ConditionJudger(PrescriptionFactorCondition condition) {
			key = condition.getItemKey();
			relation = EnumUtil.getEnum(condition.getRelation(), Relation.class);
			stringValues = condition.getValue().split(",");

			StandardItem standardItem = indexContainer.getStandardItem(key);
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
		}

		private boolean judge(PeopleCondition peopleCondition) {
			if (isNumber) {
				Double[] values = peopleCondition.getDoubleArray(key);
				if (values == null) {
					return false;
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
					return false;
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
	}

	public int order() {
		return 1;
	}

}
