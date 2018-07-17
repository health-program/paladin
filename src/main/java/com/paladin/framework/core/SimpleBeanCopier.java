package com.paladin.framework.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paladin.framework.utils.reflect.NameUtil;
import com.paladin.framework.utils.reflect.ReflectUtil;
import com.paladin.framework.utils.structure.SecMap;

/**
 * 简单BEAN拷贝器
 * 
 * @author TontoZhou
 * @since 2018年7月5日
 */
public class SimpleBeanCopier {

	private static SecMap<Class<?>, Class<?>, List<CopyUnit>> convertMap = new SecMap<>();

	/**
	 * 简单拷贝多个对象
	 * 
	 * @param sourceList
	 * @param targetType
	 * @return
	 */
	public <T> List<T> simpleCopyList(List<?> sourceList, Class<T> targetType) {
		return simpleCopyList(sourceList, targetType, false);
	}

	/**
	 * 简单拷贝多个对象
	 * 
	 * @param sourceList
	 * @param targetType
	 * @param ignore
	 *            是否忽略某些属性（被注释了{@link IgnoredIfNeed}）
	 * @return
	 */
	public <T> List<T> simpleCopyList(List<?> sourceList, Class<T> targetType, boolean ignore) {
		if (sourceList.size() > 0) {

			Class<?> sourceType = sourceList.get(0).getClass();
			List<CopyUnit> copyUnits = getCopyUnit(sourceType, targetType);

			List<T> targetList = new ArrayList<>(sourceList.size());

			for (Object source : sourceList) {
				try {
					T target = targetType.newInstance();

					for (CopyUnit copyUnit : copyUnits) {
						setValue(copyUnit, target, source, ignore);
					}

					targetList.add(target);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return targetList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * 简单拷贝对象
	 * 
	 * @param source
	 * @param targetType
	 * @return
	 */
	public Object simpleCopy(Object source, Class<?> targetType) {
		return simpleCopy(source, targetType, false);
	}

	/**
	 * 简单拷贝对象
	 * 
	 * @param source
	 * @param targetType
	 * @param ignore
	 *            是否忽略某些属性（被注释了{@link IgnoredIfNeed}）
	 * @return
	 */
	public Object simpleCopy(Object source, Class<?> targetType, boolean ignore) {

		Object target = null;
		try {
			target = targetType.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		simpleCopy(source, target, ignore);
		return target;
	}

	/**
	 * 简单拷贝对象
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public void simpleCopy(Object source, Object target) {
		simpleCopy(source, target, false);
	}

	/**
	 * 简单拷贝对象
	 * 
	 * @param source
	 * @param target
	 * @param ignore
	 *            是否忽略某些属性（被注释了{@link IgnoredIfNeed}）
	 * @return
	 */
	public void simpleCopy(Object source, Object target, boolean ignore) {

		if (source == null || target == null) {
			return;
		}

		Class<?> sourceType = source.getClass();
		Class<?> targetType = target.getClass();

		List<CopyUnit> copyUnits = getCopyUnit(sourceType, targetType);
		for (CopyUnit copyUnit : copyUnits) {
			try {
				setValue(copyUnit, target, source, ignore);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void setValue(CopyUnit copyUnit, Object target, Object source, boolean ignore) throws Exception {
		if (!ignore || !copyUnit.ignoredIfNeed) {
			Object value = copyUnit.getMethod.invoke(source);
			copyUnit.setMethod.invoke(target, value);
		}
	}

	private List<CopyUnit> getCopyUnit(Class<?> sourceType, Class<?> targetType) {

		List<CopyUnit> copyUnits = convertMap.get(sourceType, targetType);

		if (copyUnits == null) {
			synchronized (convertMap) {
				copyUnits = convertMap.get(sourceType, targetType);
				if (copyUnits == null) {
					Method[] sourceMethods = sourceType.getMethods();
					Method[] targetMethods = targetType.getMethods();

					HashMap<String, Method> sourceGetMethodMap = new HashMap<>();

					for (Method method : sourceMethods) {
						if (ReflectUtil.isGetMethod(method)) {
							String methodName = method.getName();
							String name = NameUtil.removeGetOrSet(methodName);
							sourceGetMethodMap.put(name, method);

							if (methodName.startsWith("i")) {
								sourceGetMethodMap.put(methodName, method);
							}
						}
					}

					copyUnits = new ArrayList<>(sourceGetMethodMap.size());

					for (Method method : targetMethods) {
						if (ReflectUtil.isSetMethod(method)) {
							String name = NameUtil.removeGetOrSet(method.getName());

							Method getMethod = sourceGetMethodMap.get(name);
							if (getMethod != null) {
								Class<?> returnType = getMethod.getReturnType();
								if (returnType.isPrimitive()) {
									returnType = ReflectUtil.getPackagePrimitive(returnType);
								}

								Class<?> setType = method.getParameterTypes()[0];
								if (setType.isPrimitive()) {
									setType = ReflectUtil.getPackagePrimitive(setType);
								}

								if (returnType == setType || setType.isAssignableFrom(returnType)) {
									CopyUnit copyUnit = new CopyUnit();
									copyUnit.getMethod = getMethod;
									copyUnit.setMethod = method;
									copyUnit.name = name;
									copyUnit.ignoredIfNeed = getMethod.getAnnotation(IgnoredIfNeed.class) != null;
									copyUnits.add(copyUnit);
								}
							}
						}
					}
				}
			}
		}

		return copyUnits;
	}

	public static class CopyUnit {
		Method getMethod;
		Method setMethod;
		String name;
		boolean ignoredIfNeed;
	}

	/**
	 * 注释在拷贝源对象的GET方法上
	 * 
	 * @author TontoZhou
	 * @since 2018年7月5日
	 */
	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface IgnoredIfNeed {

	}

	public static class SimpleBeanCopyUtil {

		private static SimpleBeanCopier copier = new SimpleBeanCopier();

		/**
		 * 简单拷贝多个对象
		 * 
		 * @param sourceList
		 * @param targetType
		 * @return
		 */
		public static <T> List<T> simpleCopyList(List<?> sourceList, Class<T> targetType) {
			return copier.simpleCopyList(sourceList, targetType);
		}

		/**
		 * 简单拷贝多个对象
		 * 
		 * @param sourceList
		 * @param targetType
		 * @param ignore
		 *            是否忽略某些属性（被注释了{@link IgnoredIfNeed}）
		 * @return
		 */
		public static <T> List<T> simpleCopyList(List<?> sourceList, Class<T> targetType, boolean ignore) {
			return copier.simpleCopyList(sourceList, targetType, ignore);
		}

		/**
		 * 简单拷贝对象
		 * 
		 * @param source
		 * @param targetType
		 * @return
		 */
		public static Object simpleCopy(Object source, Class<?> targetType) {
			return copier.simpleCopy(source, targetType);
		}

		/**
		 * 简单拷贝对象
		 * 
		 * @param source
		 * @param targetType
		 * @param ignore
		 *            是否忽略某些属性（被注释了{@link IgnoredIfNeed}）
		 * @return
		 */
		public static Object simpleCopy(Object source, Class<?> targetType, boolean ignore) {
			return copier.simpleCopy(source, targetType, ignore);
		}

		/**
		 * 简单拷贝对象
		 * 
		 * @param source
		 * @param target
		 * @return
		 */
		public static void simpleCopy(Object source, Object target) {
			copier.simpleCopy(source, target);
		}

		/**
		 * 简单拷贝对象
		 * 
		 * @param source
		 * @param target
		 * @param ignore
		 *            是否忽略某些属性（被注释了{@link IgnoredIfNeed}）
		 * @return
		 */
		public static void simpleCopy(Object source, Object target, boolean ignore) {
			copier.simpleCopy(source, target, ignore);
		}

	}

}
