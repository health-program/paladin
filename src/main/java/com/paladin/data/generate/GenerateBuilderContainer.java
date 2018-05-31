package com.paladin.data.generate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.paladin.data.generate.build.FileBuilder;
import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainer;

@Component
public class GenerateBuilderContainer implements SpringContainer {

	static Map<GenerateType, FileBuilder> builderMap = new HashMap<>();

	@Override
	public boolean initialize() {
		Map<String, FileBuilder> builders = SpringBeanHelper.getBeansByType(FileBuilder.class);

		for (FileBuilder builder : builders.values()) {
			builderMap.put(builder.getGenerateType(), builder);
		}

		return true;
	}

	@Override
	public boolean afterInitialize() {
		return true;
	}

	@Override
	public int order() {
		return 0;
	}

	public static FileBuilder getFileContentBuilder(GenerateType generateType) {
		return builderMap.get(generateType);
	}

}
