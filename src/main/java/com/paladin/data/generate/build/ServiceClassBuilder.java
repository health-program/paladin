package com.paladin.data.generate.build;

import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;
import com.paladin.data.generate.GenerateUtil;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.ServiceSupport;

@Component
public class ServiceClassBuilder extends SpringBootClassBuilder{

	public String buildContent(GenerateTableOption tableOption) {

		StringBuilder sb = new StringBuilder();

		sb.append("package ").append(GenerateUtil.getClassPackage(tableOption, GenerateType.SERVICE)).append(";\n\n");
		
		sb.append("import ").append(Service.class.getName()).append(";\n\n");

		sb.append("import ").append(tableOption.getModelFullName()).append(";\n");
		sb.append("import ").append(ServiceSupport.class.getName()).append(";\n\n");
		
		sb.append("@Service\n");
		sb.append("public class ").append(tableOption.getModelName()).append("Service extends ").append(ServiceSupport.class.getSimpleName()).append("<")
				.append(tableOption.getModelName()).append(">{\n\n}");

		return sb.toString();
	}
	
	@Override
	public GenerateType getGenerateType() {
		return GenerateType.SERVICE;
	}
}
