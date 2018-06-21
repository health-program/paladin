package com.paladin.health.core.factor;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;

import com.paladin.framework.utils.time.DateTimeUtil;

public class PeopleCondition extends HashMap<String, Object> {

	private static final long serialVersionUID = 1829527723448405253L;

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

	/**
	 * 初始化计算一些指标数值，并且分析得到有用的危险因素
	 */
	public void initialize() {

		// 年龄
		if (!has("dqnl")) {
			String csrq = getString("csrq");
			if (csrq != null && csrq.length() > 0) {
				try {
					Integer age = DateTimeUtil.getAge(formatter.parse(csrq));
					put("dqnl", age);
				} catch (ParseException e) {
					throw new RuntimeException("出生日期格式不正确，格式应如1988.06.11");
				}
			}
		}

		// BMI
		if (!has("bmi")) {
			Double sg = getDouble("sg");
			Double tz = getDouble("tz");
			if (sg != null && tz != null) {				
				if(sg >5) {
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

	}

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

	public String getString(String key) {
		Object value = get(key);
		if (value != null && value instanceof String) {
			return (String) value;
		}
		return null;
	}

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
}
