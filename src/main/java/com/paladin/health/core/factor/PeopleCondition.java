package com.paladin.health.core.factor;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.time.DateTimeUtil;
import com.paladin.health.core.factor.FactorAnalyzer.FactorResult;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.model.prescription.PrescriptionFactorCondition;

/**
 * 线程非安全
 * 
 * <p>该类包含部分硬编码，请参考数据库表index_item</p>
 * 
 * @author TontoZhou
 * @since 2018年6月21日
 */
public class PeopleCondition extends HashMap<String, Object> {

	private static final long serialVersionUID = 1829527723448405253L;
		
	/**
	 * 初始化计算一些指标数值，并且分析得到有用的危险因素
	 */
	public void initialize() {

		// 年龄
		String csrq = getString("csrq");
		if (csrq != null && csrq.length() > 0) {
			try {
				Integer age = DateTimeUtil.getAge(formatter.parse(csrq));
				put("dqnl", age);
			} catch (ParseException e) {
				throw new BusinessException("出生日期格式不正确，格式应如1988.06.11");
			}
		}

		// BMI
		if (!has("bmi")) {
			Double sg = getDouble("sg");
			Double tz = getDouble("tz");
			if (sg != null && tz != null) {
				if (sg > 5) {
					// 化作米单位
					sg = sg / 100;
				}
				Double b = tz / sg / sg;
				put("bmi", b);
			}
		}

		// 血压值
		if (!has("szy")) {
			Double szy = getDouble("zcszy");
			Double ycszy = getDouble("ycszy");

			if (szy == null || (ycszy != null && ycszy.compareTo(szy) > 0)) {
				szy = ycszy;
			}

			if (szy != null) {
				put("szy", szy);
			}
		}

		if (!has("ssy")) {
			Double ssy = getDouble("zcssy");
			Double ycssy = getDouble("ycssy");

			if (ssy == null || (ycssy != null && ycssy.compareTo(ssy) > 0)) {
				ssy = ycssy;
			}

			if (ssy != null) {
				put("ssy", ssy);
			}
		}

		factors = new HashSet<>();

		String[] disease = getStringArray("disease");
		String[] factor = getStringArray("factor");

		if (disease != null) {
			for (String d : disease) {
				factors.add(d);
			}
		}

		if (factor != null) {
			for (String f : factor) {
				factors.add(f);
			}
		}

		speculate_factors = new ArrayList<>();

	}
	
	private DiagnoseTarget target;
	
	@JsonIgnore
	public DiagnoseTarget getDiagnoseTarget() {
		if(target == null) {
			target = new DiagnoseTarget();
			
			target.setId(getString("sfzhm"));
			target.setName(getString("xm"));
			target.setSex(getInteger("xb"));
			target.setCellphone(getString("sjhm"));		
			target.setName(getString("csrq"));
			target.setCreateTime(new Date());	
		}
		return target;
	}

	private Collection<String> factors;
	private List<FactorResult> speculate_factors;

	@JsonIgnore
	public Collection<String> getFactors() {
		return factors;
	}

	/**
	 * 获取可能的因素，需要去除已经确定的因素
	 * 
	 * @return
	 */
	@JsonIgnore
	public List<FactorResult> getSpeculateFactors() {
		if (speculate_factors.size() == 0) {
			return null;
		}

		List<FactorResult> list = new ArrayList<>(speculate_factors.size());
		for (FactorResult fr : speculate_factors) {
			if (!factors.contains(fr.getFactor())) {
				list.add(fr);
			}
		}
		return list;
	}

	/**
	 * 添加一个因素
	 * @param factor
	 */
	public void addFactor(String factor) {
		factors.add(factor);
	}

	/**
	 * 添加一个分析因素结果
	 * @param result
	 */
	public void addFactor(FactorResult result) {
		Integer type = result.getType();
		if (type == PrescriptionFactorCondition.TYPE_DEFAULT) {
			factors.add(result.getFactor());
		} else if (type == PrescriptionFactorCondition.TYPE_SPECULATE_DISEASE) {
			speculate_factors.add(result);
		}
	}

	/**
	 * 是否存在因素
	 * @param factor
	 * @return
	 */
	public boolean hasFactor(String factor) {
		return factors.contains(factor);
	}

	/**
	 * 是否存在属性
	 * @param key	属性关键字
	 * @return
	 */
	public boolean has(String key) {
		Object value = get(key);
		if (value != null) {
			if (value instanceof String) {
				return ((String) value).length() != 0;
			}
			return true;
		}
		return false;
	}

	/**
	 * 获取属性值（字符串形式）
	 * @param key
	 * @return
	 */
	@JsonIgnore
	public String getString(String key) {
		Object value = get(key);
		if (value != null ) {
			return value.toString();
		}
		return null;
	}

	/**
	 * 获取属性值（含小数形式）
	 * @param key
	 * @return
	 */
	@JsonIgnore
	public Double getDouble(String key) {
		Object value = get(key);
		if (value != null) {
			try {
				if (value instanceof String) {
					return Double.valueOf((String) value);
				} else if (value instanceof Number) {
					return ((Number) value).doubleValue();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 获取属性值（整数形式）
	 * @param key
	 * @return
	 */
	@JsonIgnore
	public Integer getInteger(String key) {
		Object value = get(key);
		if (value != null) {
			try {
				if (value instanceof String) {
					return Integer.valueOf((String) value);
				} else if (value instanceof Number) {
					return ((Number) value).intValue();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 获取属性值（字符串数组形式）
	 * @param key
	 * @return
	 */
	@JsonIgnore
	public String[] getStringArray(String key) {
		Object value = this.get(key);

		if (value == null) {
			return null;
		}

		String[] values = null;
		int nullCount = 0;

		if (value instanceof Collection) {
			Collection<?> coll = ((Collection<?>) value);
			int length = coll.size();
			if (length == 0) {
				return null;
			}
			int i = 0;
			values = new String[length];
			for (Object v : coll) {
				if (v != null) {
					values[i++] = v.toString();
				} else {
					values[i++] = null;
					nullCount++;
				}
			}
		} else if (value.getClass().isArray()) {
			int length = Array.getLength(values);
			if (length == 0) {
				return null;
			}
			values = new String[length];
			for (int i = 0; i < length; i++) {
				Object v = Array.get(value, i);
				if (v != null) {
					values[i] = v.toString();
				} else {
					values[i] = null;
					nullCount++;
				}
			}
		} else {
			values = new String[] { value.toString() };
		}

		// 去除NULL
		if (nullCount > 0) {
			if (nullCount == values.length) {
				return null;
			}
			String[] newvalues = new String[values.length - nullCount];
			for (int i = 0, j = 0; i < values.length; i++) {
				String v = values[i];
				if (v != null) {
					newvalues[j++] = v;
				}
			}
			values = newvalues;
		}

		return values;
	}

	/**
	 * 获取属性值（小数数组形式）
	 * @param key
	 * @return
	 */
	@JsonIgnore
	public Double[] getDoubleArray(String key) {
		Object value = this.get(key);

		if (value == null) {
			return null;
		}

		Double[] values = null;
		int nullCount = 0;

		if (value instanceof Collection) {
			Collection<?> coll = ((Collection<?>) value);
			int length = coll.size();
			if (length == 0) {
				return null;
			}
			int i = 0;
			values = new Double[length];
			for (Object v : coll) {
				if (v != null) {
					if (v instanceof String) {
						values[i++] = Double.valueOf((String) v);
					} else if (v instanceof Number) {
						values[i++] = ((Number) v).doubleValue();
					} else {
						values[i++] = null;
						nullCount++;
					}
				} else {
					values[i++] = null;
					nullCount++;
				}

			}
		} else if (value.getClass().isArray()) {
			int length = Array.getLength(values);
			if (length == 0) {
				return null;
			}
			values = new Double[length];
			for (int i = 0; i < length; i++) {
				Object v = Array.get(value, i);
				if (v != null) {
					if (v instanceof String) {
						values[i++] = Double.valueOf((String) v);
					} else if (v instanceof Number) {
						values[i++] = ((Number) v).doubleValue();
					} else {
						values[i++] = null;
						nullCount++;
					}
				} else {
					values[i++] = null;
					nullCount++;
				}
			}
		} else {
			values = new Double[1];
			if (value instanceof String) {
				values[0] = Double.valueOf((String) value);
			} else if (value instanceof Number) {
				values[0] = ((Number) value).doubleValue();
			} else {
				return null;
			}
		}

		// 去除NULL
		if (nullCount > 0) {
			if (nullCount == values.length) {
				return null;
			}
			Double[] newvalues = new Double[values.length - nullCount];
			for (int i = 0, j = 0; i < values.length; i++) {
				Double v = values[i];
				if (v != null) {
					newvalues[j++] = v;
				}
			}
			values = newvalues;
		}

		return values;
	}

	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");


}
