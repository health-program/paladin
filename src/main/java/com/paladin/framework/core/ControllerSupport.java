package com.paladin.framework.core;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
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

	protected <T> T beanCopy(Object source, T target) {
		SimpleBeanCopyUtil.simpleCopy(source, target, true);
		return target;
	}
	
	public <T> List<T> beanCopyList(List<?> sourceList, Class<T> targetType) {
		return SimpleBeanCopyUtil.simpleCopyList(sourceList, targetType);
	}

	public <T> List<T> beanCopyList(List<?> sourceList, List<T> targeList) {
		return SimpleBeanCopyUtil.simpleCopyList(sourceList, targeList);
	}
	

}
