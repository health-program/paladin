package com.paladin.data.generate.build;

import org.springframework.stereotype.Component;

import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;
import com.paladin.data.generate.GenerateUtil;
import com.paladin.framework.mybatis.CustomMapper;

@Component
public class MapperClassBuilder extends SpringBootClassBuilder{

	public String buildContent(GenerateTableOption tableOption) {

		StringBuilder sb = new StringBuilder();

		sb.append("package ").append(GenerateUtil.getClassPackage(tableOption, GenerateType.MAPPER)).append(";\n\n");
		sb.append("import ").append(tableOption.getModelFullName()).append(";\n");
		sb.append("import ").append(CustomMapper.class.getName()).append(";\n\n");

		sb.append("public interface ").append(tableOption.getModelName()).append("Mapper extends ").append(CustomMapper.class.getSimpleName()).append("<")
				.append(tableOption.getModelName()).append(">{\n\n}");

		return sb.toString();
	}
	
	@Override
	public GenerateType getGenerateType() {
		return GenerateType.MAPPER;
	}
}
