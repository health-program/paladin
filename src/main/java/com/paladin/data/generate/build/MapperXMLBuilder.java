package com.paladin.data.generate.build;

import org.springframework.stereotype.Component;

import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;
import com.paladin.data.generate.GenerateUtil;

@Component
public class MapperXMLBuilder extends SpringBootResourceBuilder{

	public String buildContent(GenerateTableOption tableOption) {

		StringBuilder sb = new StringBuilder();

		String mapper = GenerateUtil.getClassPackage(tableOption, GenerateType.MAPPER) + "." + tableOption.getModelName() + "Mapper";

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n")
				.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n")
				.append("<mapper namespace=\"").append(mapper).append("\">\r\n\r\n").append("</mapper>\r\n");

		return sb.toString();
	}
	
	@Override
	public GenerateType getGenerateType() {
		return GenerateType.SQLMAPPER;
	}
}
