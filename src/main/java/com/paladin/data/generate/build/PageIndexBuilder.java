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
public class PageIndexBuilder extends SpringBootPageBuilder {

	private static Template template = FreemarkerUtil.getTemplate("/template_page_index.temp");

	public String buildContent(GenerateTableOption tableOption) {
		HashMap<String, Object> data = new HashMap<>();

		String enumcodes = "";
		StringBuilder sb = new StringBuilder();
		List<GenerateColumnOption> columnOptions = tableOption.getColumnOptions();
		for (GenerateColumnOption columnOption : columnOptions) {
			if (columnOption.isPrimary() || !columnOption.isTableable()) {
				continue;
			}

			sb.append("\t\t\t\t\t{ title: \"").append(columnOption.getTitle()).append("\", align: \"center\", field: \"").append(columnOption.getFieldName()).append("\"");

			if (columnOption.isConstant()) {
				sb.append(" ,enumcode: \"").append(columnOption.getConstantType()).append("\"");
				enumcodes += columnOption.getConstantType() + ",";
			}

			if (Date.class.isAssignableFrom(columnOption.getFieldType())) {
				sb.append(" ,formatter: \"date\"");
			}
			sb.append(" },\n");
		}
		
		sb.deleteCharAt(sb.length() - 1);

		if (enumcodes.length() > 0) {
			data.put("enumcodes", "<tt:constant enumcode=\"" + enumcodes + "\"/>\n\t");
		} else {
			data.put("enumcodes", " ");
		}

		data.put("tableColumns", sb.toString());
		data.put("mainTitle", tableOption.getTitle());
		
		ControllerClassBuilder controllerBuilder = (ControllerClassBuilder) GenerateBuilderContainer.getFileContentBuilder(BuilderType.CONTROLLER);
		data.put("searchUrl", controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getFindPageRequestMapping(tableOption));
		data.put("addUrl", controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getAddRequestMapping(tableOption));
		data.put("detailUrl", controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getDetailRequestMapping(tableOption));
		data.put("removeUrl", controllerBuilder.getBaseRequestMapping(tableOption) + controllerBuilder.getDeleteRequestMapping(tableOption));

		StringWriter writer = new StringWriter();
		try {
			template.process(data, writer);
		} catch (TemplateException | IOException e) {
			throw new RuntimeException("自动生成page_index失败", e);
		}
		return writer.toString();
	}

	@Override
	public BuilderType getBuilderType() {
		return BuilderType.PAGE_INDEX;
	}

	@Override
	public String getFileName(GenerateTableOption tableOption) {
		return tableOption.getTable().getName() + "_index.html";
	}
}
