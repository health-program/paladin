package com.paladin.health.service.core.xk.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XKPrescription {

	public static final int TYPE_DISEASE = 1;
	public static final int TYPE_INDEX = 2;
	public static final int TYPE_RISK = 3;
	
	public final static int LEVEL_VERY_LOW = 1;
	public final static int LEVEL_LOW = 2;
	public final static int LEVEL_MIDDLE = 3;
	public final static int LEVEL_HIGH = 4;
	public final static int LEVEL_VERY_HIGH = 5;
	public final static int LEVEL_HAS = 9;

	public final static String CODE_AF = "af"; // 心房颤动后中风风险评估
	public final static String CODE_CHD = "chd";// 冠心病风险评估
	public final static String CODE_CVD = "cvd";// 五年内糖尿病患者发生心脑血管并发症风险评估
	public final static String CODE_DIABETES = "diabetes"; // 糖尿病风险评估
	public final static String CODE_HYPERTENSION = "hypertension";// 高血压风险评估
	public final static String CODE_ICVD = "icvd";// 缺血性心血管病风险评估
	public final static String CODE_OSTEOPOROSIS = "osteoporosis";// 骨质疏松症风险评估

	public final static Map<String, Integer> nameLevelMap;
	public final static Map<Integer, String> levelNameMap;

	public final static Map<String, String> codeNameMap;

	static {
		nameLevelMap = new HashMap<>();
		levelNameMap = new HashMap<>();

		nameLevelMap.put("极低风险", LEVEL_VERY_LOW);
		nameLevelMap.put("低风险", LEVEL_LOW);
		nameLevelMap.put("中风险", LEVEL_MIDDLE);
		nameLevelMap.put("中等风险", LEVEL_MIDDLE);
		nameLevelMap.put("高风险", LEVEL_HIGH);
		nameLevelMap.put("极高风险", LEVEL_VERY_HIGH);
		nameLevelMap.put("已经确诊", LEVEL_HAS);
		
		for (Entry<String, Integer> entry : nameLevelMap.entrySet()) {
			levelNameMap.put(entry.getValue(), entry.getKey());
		}

		codeNameMap = new HashMap<>();
		codeNameMap.put(CODE_AF, "心房颤动后中风风险");
		codeNameMap.put(CODE_CHD, "冠心病风险");
		codeNameMap.put(CODE_CVD, "五年内糖尿病患者发生心脑血管并发症风险");
		codeNameMap.put(CODE_DIABETES, "糖尿病风险");
		codeNameMap.put(CODE_HYPERTENSION, "高血压风险");
		codeNameMap.put(CODE_ICVD, "缺血性心血管病风险");
		codeNameMap.put(CODE_OSTEOPOROSIS, "骨质疏松症风险");

	}
	
	
	private String name;
	private String code;
	private Integer type;
	private Integer riskLevel;
	private String suggest;

	public XKPrescription() {

	}

	public XKPrescription(String name, String code, Integer type, Integer riskLevel, String suggest) {
		this.name = name;
		this.code = code;
		this.type = type;
		this.riskLevel = riskLevel;
		this.suggest = suggest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}

}
