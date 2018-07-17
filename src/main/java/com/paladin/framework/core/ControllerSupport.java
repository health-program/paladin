package com.paladin.framework.core;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.paladin.framework.web.response.CommonResponse;

public class ControllerSupport {

	/**
	 * 验证异常处理
	 * 
	 * @param bindingResult
	 * @return
	 */
	public Object validErrorHandler(BindingResult bindingResult) {

		List<FieldError> errors = bindingResult.getFieldErrors();

		String[][] result = new String[errors.size()][3];

		int i = 0;

		for (FieldError error : bindingResult.getFieldErrors()) {
			result[i++] = new String[] { error.getCode(), error.getField(), error.getDefaultMessage() };
		}

		return CommonResponse.getValidFailResponse(result);
	}

	protected <T> T beanCompleteCopy(Object source, T target) {
		copier.simpleCopy(source, target);
		return target;
	}

	protected <T> T beanIncompleteCopy(Object source, T target) {
		copier.simpleCopy(source, target, true);
		return target;
	}

	public <T> List<T> beanCopyList(List<?> sourceList, Class<T> targetType) {
		return copier.simpleCopyList(sourceList, targetType);
	}

	private static SimpleBeanCopier copier = new SimpleBeanCopier() {
		protected void setValue(CopyUnit copyUnit, Object target, Object source, boolean ignore) throws Exception {
			if (!ignore || !copyUnit.ignoredIfNeed) {
				Object value = copyUnit.getMethod.invoke(source);
				if (value != null && value instanceof String && ((String) value).length() == 0) {
					value = null;
				}
				copyUnit.setMethod.invoke(target, value);
			}
		}
	};

}
