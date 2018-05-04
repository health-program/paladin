package com.paladin.data.generate;

public class GenerateMapperXMLClassUtil {

	public static String createXMLContent(GenerateTableOption tableOption) {

		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n")
			.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n")
			.append("<mapper namespace=\"").append(tableOption.getModelFullName()).append("\">\r\n\r\n")
			.append("</mapper>\r\n");

		return sb.toString();
	}

}
