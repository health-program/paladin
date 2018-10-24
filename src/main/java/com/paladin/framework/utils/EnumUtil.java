package com.paladin.framework.utils;

public class EnumUtil {

	public static boolean equals(String str, Enum<?>... constants) {
		for (Enum<?> constant : constants) {
			if (constant.name().equals(str))
				return true;
		}
		return false;
	}
	
//	@SuppressWarnings("unchecked")
//	public static <T extends Enum<T>> T getEnum(String name, Class<T> enumClass) {
//		if (name != null && name.length() > 0) {
//			Enum<T>[] enums = enumClass.getEnumConstants();
//			for (Enum<T> e : enums) {
//				if (e.name().equals(name)) {
//					return (T) e;
//				}
//			}
//		}
//		return null;
//	}
	
	
}
