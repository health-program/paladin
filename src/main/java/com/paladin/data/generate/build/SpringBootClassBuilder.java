package com.paladin.data.generate.build;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;
import com.paladin.data.generate.GenerateUtil;
import com.paladin.framework.exception.BusinessException;

public abstract class SpringBootClassBuilder implements FileBuilder {

	@Override
	public void buildFile(GenerateTableOption tableOption, String projectPath) {
		GenerateType generateType = this.getGenerateType();
		try {
			Path path = GenerateUtil.getBootProjectJavaPath(projectPath, tableOption, generateType);
			String content = this.buildContent(tableOption);
			Files.write(path, content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("创建文件[" + generateType + "]失败");
		}
	}

}
