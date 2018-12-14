package com.paladin.data.generate.build;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paladin.data.generate.GenerateBuilderContainer;
import com.paladin.data.generate.GenerateColumnOption;
import com.paladin.data.generate.GenerateTableOption;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class PageDetailBuilder extends SpringBootPageBuilder {

	private static Template template = FreemarkerUtil.getTemplate("/template_page_detail.temp");

	public String buildContent(GenerateTableOption tableOption) {
		HashMap<String, Object> data = new HashMap<>();

		String enumcodes = "";
		StringBuilder sb = new StringBuilder();
		List<GenerateColumnOption> columnOptions = tableOption.getColumnOptions();
		sb.append("[\n");
		for (GenerateColumnOption columnOption : columnOptions) {
			if (!columnOption.isEditable()) {
				continue;
			}

			sb.append("\t\t\t\t{ title: \"").append(columnOption.getTitle()).append("\"");
			sb.append(", name: \"").append(columnOption.getFieldName()).append("\"");

			String inputType = "TEXT";

			if (columnOption.isConstant()) {
				sb.append(", enum: \"").append(columnOption.getConstantType()).append("\"");
				enumcodes += columnOption.getConstantType() + ",";
				inputType = "SELECT";
			}

			if (Date.class.isAssignableFrom(columnOption.getFieldType())) {
				inputType = "DATE";
			}

			if (columnOption.isPrimary()) {
				inputType = "ID";
			}

			sb.append(", inputType: \"").append(inputType).append("\"");
			sb.append(" },\n");
		}

		sb.deleteCharAt(sb.length() - 2);
		sb.append("\t\t\t\t]");

		if (enumcodes.length() > 0) {
			data.put("enumcodes", "<tt:constant enumcode=\"" + enumcodes + "\"/>");
		} else {
			data.put("enumcodes", "");
		}

		data.put("columns", sb.toString());
		data.put("title", "详情");
		data.put("mainTitle", tableOption.getTitle());

		ControllerClassBuilder controllerBuilder = (ControllerClassBuilder) GenerateBuilderContainer.getFileContentBuilder(BuilderType.CONTROLLER);
		data.put("getDataUrl", "\"" + controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getGetDetailRequestMapping(tableOption)
				+ "?id=\"+$(\"#id\").val()");
		data.put("updateUrl", "\"" + controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getUpdateRequestMapping(tableOption)
				+ "?id=\"+$(\"#id\").val()");
		data.put("indexUrl", controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getIndexRequestMapping(tableOption));

		StringWriter writer = new StringWriter();
		try {
			template.process(data, writer);
		} catch (TemplateException | IOException e) {
			throw new RuntimeException("自动生成page_detail失败", e);
		}
		return writer.toString();
	}

	@Override
	public BuilderType getBuilderType() {
		return BuilderType.PAGE_DETAIL;
	}

	@Override
	public String getFileName(GenerateTableOption tableOption) {
		return tableOption.getTable().getName() + "_detail.html";
	}
}
