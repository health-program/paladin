package com.paladin.framework.core.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.paladin.framework.core.VersionContainer;

@Component
public class ConstantsContainer implements VersionContainer{
		
	private static Map<String, List<KeyValue>> constantMap = new HashMap<>();

	public boolean initialize() {
		
		Map<String, List<KeyValue>> enumConstantMap = new HashMap<>();
		
		List<KeyValue> sexTypes = new ArrayList<>();
		sexTypes.add(new KeyValue("1", "男"));
		sexTypes.add(new KeyValue("2", "女"));
		enumConstantMap.put("sex-type", sexTypes);
		
		List<KeyValue> booleanTypes = new ArrayList<>();
		booleanTypes.add(new KeyValue("1", "是"));
		booleanTypes.add(new KeyValue("0", "否"));
		enumConstantMap.put("boolean-type", booleanTypes);
		
		
		List<KeyValue> prescriptionItemTypes = new ArrayList<>();
		prescriptionItemTypes.add(new KeyValue("1", "日常生活"));
		prescriptionItemTypes.add(new KeyValue("3", "饮食适宜"));
		prescriptionItemTypes.add(new KeyValue("4", "饮食禁忌"));
		prescriptionItemTypes.add(new KeyValue("5", "饮食行为"));
		prescriptionItemTypes.add(new KeyValue("6", "心理"));
		prescriptionItemTypes.add(new KeyValue("7", "运动"));
		prescriptionItemTypes.add(new KeyValue("9", "容易引发的疾病或状态"));
		prescriptionItemTypes.add(new KeyValue("10", "容易引发的问题"));

		enumConstantMap.put("prescription-item-type", prescriptionItemTypes);
				
		constantMap = enumConstantMap;		
		return true;
	}
	
	@Override
	public String getId() {
		return "constants_container";
	}

	
	@Override
	public boolean versionChangedHandle(long version) {
		initialize();
		return true;
	}

	public static Map<String, List<KeyValue>> getTypeChildren(String... typeCodes) {
		Map<String, List<KeyValue>> result = new HashMap<>();
		for (String typeCode : typeCodes) {
			result.put(typeCode, constantMap.get(typeCode));
		}
		return result;
	}
	
	public final static class KeyValue {
		String key;
		String value;

		public KeyValue(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public KeyValue(int key, String value) {
			this.key = String.valueOf(key);
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
}
