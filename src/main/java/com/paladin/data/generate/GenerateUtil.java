package com.paladin.data.generate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GenerateUtil {

	public static String getFileName(GenerateTableOption tableOption, GenerateType generateType) {

		if (GenerateType.SERVICE == generateType) {
			return tableOption.getModelName() + "Service.java";
		} else if (GenerateType.MAPPER == generateType) {
			return tableOption.getModelName() + "Mapper.java";
		} else if (GenerateType.MODEL == generateType) {
			return tableOption.getModelName() + ".java";
		} else if (GenerateType.CONTROLLER == generateType) {
			return tableOption.getModelName() + "Controller.java";
		} else if (GenerateType.QUERY == generateType) {
			return tableOption.getModelName() + "Query.java";
		} else if (GenerateType.SQLMAPPER == generateType) {
			return tableOption.getTable().getName() + "_mapper.xml";
		} else if (GenerateType.JAVASCRIPT == generateType) {
			return tableOption.getTable().getName() + ".js";
		} else if (GenerateType.PAGE_INDEX == generateType) {
			return tableOption.getTable().getName() + "_index.html";
		} else if (GenerateType.PAGE_VIEW == generateType) {
			return tableOption.getTable().getName() + "_view.html";
		} else if (GenerateType.PAGE_EDIT == generateType) {
			return tableOption.getTable().getName() + "_edit.html";
		}
		return null;
	}
	
	public static String getClassName(GenerateTableOption tableOption, GenerateType generateType) {

		if (GenerateType.SERVICE == generateType) {
			return tableOption.getModelName() + "Service";
		} else if (GenerateType.MAPPER == generateType) {
			return tableOption.getModelName() + "Mapper";
		} else if (GenerateType.MODEL == generateType) {
			return tableOption.getModelName() + "";
		} else if (GenerateType.CONTROLLER == generateType) {
			return tableOption.getModelName() + "Controller";
		} else if (GenerateType.QUERY == generateType) {
			return tableOption.getModelName() + "Query";
		} 
		
		return null;
	}

	private static final HashMap<GenerateType, String> packageMap = new HashMap<>();

	static {
		packageMap.put(GenerateType.SERVICE, "service");
		packageMap.put(GenerateType.MAPPER, "mapper");
		packageMap.put(GenerateType.MODEL, "model");
		packageMap.put(GenerateType.CONTROLLER, "controller");
		packageMap.put(GenerateType.QUERY, "controller");
		packageMap.put(GenerateType.SQLMAPPER, "mapper");
		packageMap.put(GenerateType.JAVASCRIPT, "static/js");
		packageMap.put(GenerateType.PAGE_INDEX, "templates");
		packageMap.put(GenerateType.PAGE_VIEW, "templates");
		packageMap.put(GenerateType.PAGE_EDIT, "templates");
	}

	public static String getClassPackage(GenerateTableOption tableOption, GenerateType generateType) {

		String basePackage = tableOption.getBasePackage();
		String model = tableOption.getModel();
		String subModel = tableOption.getSubModel();

		if (model != null && model.length() != 0) {
			basePackage += "." + model;
		}

		basePackage += "." + packageMap.get(generateType);

		if (subModel != null && subModel.length() != 0) {
			String[] subModels = subModel.split("\\.");
			if (subModels.length > 0) {
				for (String sm : subModels) {
					basePackage += "." + sm;
				}
			}
		}
		
		if(generateType == GenerateType.QUERY) {
			basePackage += ".pojo";
		}

		return basePackage;
	}
	
	public static String getClassFullName(GenerateTableOption tableOption, GenerateType generateType) {
		return getClassPackage(tableOption,generateType) + "." + getClassName(tableOption,generateType);
	}

	/**
	 * 获取spring boot项目类的路径
	 * 
	 * @param projectPath
	 * @param tableOption
	 * @param generateType
	 * @return
	 * @throws IOException
	 */
	public static Path getBootProjectJavaPath(String projectPath, GenerateTableOption tableOption, GenerateType generateType) throws IOException {
		return getBootProjectClassPath(projectPath, getClassPackage(tableOption, generateType), getFileName(tableOption, generateType));
	}

	/**
	 * 获取spring boot资源地址
	 * 
	 * @param projectPath
	 * @param tableOption
	 * @param generateType
	 * @return
	 * @throws IOException
	 */
	public static Path getBootProjectResourcesPath(String projectPath, GenerateTableOption tableOption, GenerateType generateType) throws IOException {

		if (projectPath != null && projectPath.length() > 0) {
			projectPath = projectPath.replaceAll("\\\\", "/");
			projectPath += projectPath.endsWith("/") ? "src/main/resources" : "/src/main/resources";
		} else {
			throw new IOException("项目路径不能为空");
		}

		String model = tableOption.getModel();
		String subModel = tableOption.getSubModel();

		ArrayList<String> subPaths = new ArrayList<>();
		subPaths.add(packageMap.get(generateType));

		if (model != null && model.length() != 0) {
			subPaths.add(model);
		}

		if (subModel != null && subModel.length() != 0) {
			String[] subModels = subModel.split("\\.");
			if (subModels.length > 0) {
				for (String sm : subModels) {
					subPaths.add(sm);
				}
			}
		}

		subPaths.add(getFileName(tableOption, generateType));

		String[] subPathArray = new String[subPaths.size()];
		subPathArray = subPaths.toArray(subPathArray);

		String[] dirPath = Arrays.copyOf(subPathArray, subPathArray.length - 1);
		Files.createDirectories(Paths.get(projectPath, dirPath));
		return Paths.get(projectPath, subPathArray);
	}

	/**
	 * 获取spring boot项目类的路径
	 * 
	 * @param projectPath
	 * @param classPackage
	 * @param className
	 * @return
	 * @throws IOException
	 */
	public static Path getBootProjectClassPath(String projectPath, String classPackage, String fileName) throws IOException {

		if (projectPath != null && projectPath.length() > 0) {
			projectPath = projectPath.replaceAll("\\\\", "/");
			projectPath += projectPath.endsWith("/") ? "src/main/java" : "/src/main/java";
		}

		String[] more = null;
		if (classPackage == null || classPackage.equals("")) {
			more = new String[] { fileName };
		} else {
			more = classPackage.split("\\.");
			Files.createDirectories(Paths.get(projectPath, more));
			more = Arrays.copyOf(more, more.length + 1);
			more[more.length - 1] = fileName;
		}

		Path path = Paths.get(projectPath, more);
		return path;
	}
}
