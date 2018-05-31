package com.paladin.data.generate.build;

import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;

public interface FileBuilder {
	
	public String buildContent(GenerateTableOption tableOption);
	
	public GenerateType getGenerateType();
	
	public void buildFile(GenerateTableOption tableOption, String projectPath);
}
