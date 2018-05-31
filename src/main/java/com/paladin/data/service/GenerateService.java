package com.paladin.data.service;

import org.springframework.stereotype.Service;

import com.paladin.data.generate.GenerateBuilderContainer;
import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;
import com.paladin.data.generate.build.FileBuilder;

@Service
public class GenerateService {
	
	
	/**
	 * 创建文件内容
	 * 
	 * @param tableOption
	 * @param generateType
	 * @return 如果有文件内容构建器则调用构建，无则返回空字符串
	 */
	public String buildFileContent(GenerateTableOption tableOption, GenerateType generateType) {

		FileBuilder builder = GenerateBuilderContainer.getFileContentBuilder(generateType);
		if(builder != null) {
			return builder.buildContent(tableOption);
		}
		
		return "";
	}

	/**
	 * 创建spring boot项目文件
	 * 
	 * @param tableOption
	 * @param generateType
	 * @param content
	 */
	public void buildProjectFile(GenerateTableOption tableOption, GenerateType generateType, String projectPath) {
		FileBuilder builder = GenerateBuilderContainer.getFileContentBuilder(generateType);
		if(builder != null) {
			builder.buildFile(tableOption, projectPath);
		}
	}
}
