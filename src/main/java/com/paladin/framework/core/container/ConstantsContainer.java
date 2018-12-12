package com.paladin.framework.core.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.model.syst.SysConstant;
import com.paladin.common.service.syst.SysConstantService;
import com.paladin.framework.core.VersionContainer;

@Component
public class ConstantsContainer implements VersionContainer {

	@Autowired
	private SysConstantService constantService;

	private static Map<String, List<KeyValue>> constantMap = new HashMap<>();
	private static Map<String, Map<String, Integer>> constantValueMap = new HashMap<>();
	private static Map<String, Map<Integer, String>> constantKeyMap = new HashMap<>();

	public boolean initialize() {
		Map<String, List<KeyValue>> enumConstantMap = new HashMap<>();
		List<SysConstant> constants = constantService.findAll();
		for (SysConstant constant : constants) {
			String type = constant.getType();
			List<KeyValue> kvList = enumConstantMap.get(type);
			if (kvList == null) {
				kvList = new ArrayList<>();
				enumConstantMap.put(type, kvList);
			}

			Integer code = constant.getCode();
			String name = constant.getName();

			kvList.add(new KeyValue(code, name));

			Map<String, Integer> valueMap = constantValueMap.get(type);
			if (valueMap == null) {
				valueMap = new HashMap<>();
				constantValueMap.put(type, valueMap);
			}

			valueMap.put(name, code);

			Map<Integer, String> keyMap = constantKeyMap.get(type);
			if (keyMap == null) {
				keyMap = new HashMap<>();
				constantKeyMap.put(type, keyMap);
			}

			keyMap.put(code, name);
		}

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

	/**
	 * 根据类型和名称得到常量KEY
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public static Integer getTypeKey(String type, String name) {
		if (name == null || name.length() == 0) {
			return null;
		}
		Map<String, Integer> valueMap = constantValueMap.get(type);
		if (valueMap != null) {
			return valueMap.get(name);
		}
		return null;
	}

	/**
	 * 根据类型和编码得到常量值
	 * 
	 * @param type
	 * @param key
	 * @return
	 */
	public static String getTypeValue(String type, Integer key) {
		if (key == null) {
			return null;
		}
		Map<Integer, String> keyMap = constantKeyMap.get(type);
		if (keyMap != null) {
			return keyMap.get(key);
		}
		return null;
	}

	public final static class KeyValue {
		private Integer key;
		private String value;

		public KeyValue(String key, String value) {
			this.key = Integer.valueOf(key);
			this.value = value;
		}

		public KeyValue(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public Integer getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
}
