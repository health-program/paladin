package com.paladin.data.generate.build;

import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.GenerateType;
import com.paladin.data.generate.GenerateUtil;
import com.paladin.framework.utils.reflect.NameUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.stereotype.Component;

@Component
public class ControllerClassBuilder extends SpringBootClassBuilder {

	private static Template template;

	static {
		Configuration templateConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		try {
			templateConfig.setClassForTemplateLoading(ControllerClassBuilder.class, "");
			template = templateConfig.getTemplate("template_controller.txt");
		} catch (IOException e) {
			throw new RuntimeException("获取Controller模板失败", e);
		}
	}

	public String buildContent(GenerateTableOption tableOption) {
		StringWriter writer = new StringWriter();

		HashMap<String, Object> params = new HashMap<>();

		HashSet<String> imports = new HashSet<>();

		imports.add(GenerateUtil.getClassFullName(tableOption, GenerateType.QUERY));
		imports.add(tableOption.getModelFullName());
		imports.add(GenerateUtil.getClassFullName(tableOption, GenerateType.SERVICE));

		params.put("imports", imports);

		params.put("upperModelName", tableOption.getModelName());
		params.put("lowerModelName", NameUtil.firstLowerCase(tableOption.getModelName()));

		params.put("baseRequestMapping", getRequestPath(tableOption));
		params.put("indexPage", getPagePath(tableOption, GenerateType.PAGE_INDEX));
		params.put("viewPage", getPagePath(tableOption, GenerateType.PAGE_VIEW));
		params.put("editPage", getPagePath(tableOption, GenerateType.PAGE_EDIT));

		try {
			template.process(params, writer);
		} catch (TemplateException | IOException e) {
			throw new RuntimeException("生成文档失败", e);
		}
		return writer.getBuffer().toString();
	}

	@Override
	public GenerateType getGenerateType() {
		return GenerateType.CONTROLLER;
	}

	private String getPagePath(GenerateTableOption tableOption, GenerateType generateType) {
		String path = getRelationPath(tableOption) + "/" + GenerateUtil.getFileName(tableOption, generateType);
		return path.substring(0, path.length() - 5);
	}

	private String getRequestPath(GenerateTableOption tableOption) {
		String path = getRelationPath(tableOption);
		String table = tableOption.getTable().getName();
		int index = table.lastIndexOf("_");
		if (index >= 0) {
			table = table.substring(index);
		}
		return path + "/" + path;
	}

	private String getRelationPath(GenerateTableOption tableOption) {

		String path = "";

		String model = tableOption.getModel();
		String subModel = tableOption.getSubModel();

		if (model != null && model.length() != 0) {
			path += "/" + model;
		}

		if (subModel != null && subModel.length() != 0) {
			String[] subModels = subModel.split("\\.");
			if (subModels.length > 0) {
				for (String sm : subModels) {
					path += "/" + sm;
				}
			}
		}

		return path.length() == 0 ? "/" : path;
	}

}
