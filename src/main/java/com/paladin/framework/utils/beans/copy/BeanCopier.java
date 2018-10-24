package com.paladin.framework.utils.beans.copy;

import java.util.HashMap;
import java.util.Map;

public class BeanCopier {
	
	private Class<?> sourceClass;
	private Class<?> targetClass;
	
	
	private BeanCopier () {
		
	}
	
	private static Map<Class<?>, Map<Class<?>, BeanCopier>> copierMap = new HashMap<>();
	
	public static BeanCopier create(Class<?> sourceClass, Class<?> targetClass) {
		
		if(sourceClass == null || targetClass == null) {
			
		}
		
		BeanCopier copier = null;
		Map<Class<?>, BeanCopier> map = copierMap.get(sourceClass);
		if(map != null) {
			copier = map.get(targetClass);
		}
		
		if(copier == null) {
			synchronized(copierMap) {
				map = copierMap.get(sourceClass);
				if(map == null) {
					map = new HashMap<>();
					copier = map.get(targetClass);
				}
				
				
			}
			
			
		}
		
		
		copier = new BeanCopier();
		copier.sourceClass = sourceClass;
		copier.targetClass = targetClass;
	
		
		
		
		
		return copier;
		
	}


	public Class<?> getSourceClass() {
		return sourceClass;
	}


	public Class<?> getTargetClass() {
		return targetClass;
	}
}
