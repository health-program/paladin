package com.paladin.data.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.service.cache.SysVisitCacheService;
import com.paladin.data.controller.dto.ColumnDTO;
import com.paladin.data.controller.dto.GenerateTableOptionDTO;
import com.paladin.data.controller.dto.GenerateTableOptionDTO.GenerateColumnOptionDTO;
import com.paladin.data.database.DataBaseSource;
import com.paladin.data.database.model.Column;
import com.paladin.data.database.model.DataBase;
import com.paladin.data.database.model.Table;
import com.paladin.data.generate.GenerateColumnOption;
import com.paladin.data.generate.GenerateTableOption;
import com.paladin.data.generate.build.BuilderType;
import com.paladin.data.model.DBConnection;
import com.paladin.data.service.DBConnectionService;
import com.paladin.data.service.GenerateService;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;

@Controller
@RequestMapping("/data/connection")
public class DBConnectionController extends ControllerSupport {

	@Autowired
	private DBConnectionService connectionService;

	@Autowired
	private GenerateService generateService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "/data/connection/index";
	}

	@RequestMapping("/search")
	@ResponseBody
	public Object searchAll(OffsetPage query) {
		return CommonResponse.getSuccessResponse(connectionService.searchPage(query));
	}

	@RequestMapping("/view")
	public String view(@RequestParam String name, Model model) {
		DBConnection connection = connectionService.get(name);

		if (connection == null) {
			connection = new DBConnection();
		}

		model.addAttribute("connection", connection);
		return "data/connection/view";
	}

	@RequestMapping("/add/input")
	public String addInput(Model model) {
		model.addAttribute("connection", new DBConnection());
		return "data/connection/form";
	}

	@RequestMapping("/edit/input")
	public String editInput(@RequestParam String name, Model model) {
		DBConnection connection = connectionService.get(name);

		if (connection == null) {
			connection = new DBConnection();
		}

		model.addAttribute("connection", connection);
		return "data/connection/form";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(@Valid DBConnection connection, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		return CommonResponse.getResponse(connectionService.saveOrUpdate(connection));
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam String name) {
		return CommonResponse.getResponse(connectionService.removeByPrimaryKey(name), "删除失败");
	}

	@RequestMapping("/connect")
	public String connect(DBConnection connection, Model model) {
		connectionService.connect(connection);
		try {
			return "redirect:/data/connection/db/index?dbName=" + URLEncoder.encode(connection.getName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new BusinessException("非法数据库名称：" + connection.getName());
		}
	}

	@RequestMapping("/db/index")
	public String dbIndex(@RequestParam String dbName, Model model) {
		model.addAttribute("dbName", dbName);
		return "/data/connection/db_index";
	}

	@RequestMapping("/db/table")
	@ResponseBody
	public Object tableList(@RequestParam String dbName) {

		Table[] tables = connectionService.getDBTables(dbName);
		String[] tableNames = new String[tables.length];
		for (int i = 0; i < tables.length; i++) {
			tableNames[i] = tables[i].getName();
		}

		return CommonResponse.getSuccessResponse(tableNames);
	}

	@RequestMapping("/db/column")
	@ResponseBody
	public Object tableList(@RequestParam String dbName, @RequestParam String tableName) {

		Column[] columns = connectionService.getDBTableColumns(dbName, tableName);
		List<ColumnDTO> columnVOs = beanCopyList(Arrays.asList(columns), ColumnDTO.class);

		return CommonResponse.getSuccessResponse(columnVOs);
	}

	@RequestMapping("/db/build/input")
	public Object toBuild(@RequestParam String dbName, @RequestParam String tableName, HttpServletRequest request, Model model) {

		model.addAttribute("dbName", dbName);
		model.addAttribute("tableName", tableName);
		model.addAttribute("projectPath", visitCacheService.getCache(request, CACHE_PROJECT_PATH));

		return "/data/connection/build";
	}

	@RequestMapping("/db/build")
	@ResponseBody
	public Object build(@RequestBody GenerateTableOptionDTO option) {

		String dbName = option.getDbName();
		String tableName = option.getTableName();

		DataBaseSource dataBaseSource = connectionService.getDataBaseSource(dbName);

		if (dataBaseSource == null) {
			throw new BusinessException("不存在数据库：" + dbName);
		}

		DataBase dataBase = dataBaseSource.getDataBase(false);
		Table table = dataBase.getChild(tableName);

		if (table == null) {
			throw new BusinessException("不存在表：" + tableName);
		}

		GenerateTableOption tableOption = new GenerateTableOption(table, dataBaseSource.getDataBaseConfig().getType());

		beanCopy(option, tableOption);

		HashMap<String, String> contentMap = new HashMap<>();
		contentMap.put("model", generateService.buildFileContent(tableOption, BuilderType.MODEL));
		contentMap.put("mapper", generateService.buildFileContent(tableOption, BuilderType.MAPPER));
		contentMap.put("service", generateService.buildFileContent(tableOption, BuilderType.SERVICE));

		return CommonResponse.getSuccessResponse(null, contentMap);
	}

	@Autowired
	SysVisitCacheService visitCacheService;

	private final static String CACHE_PROJECT_PATH = "data_project_path";

	@RequestMapping("/db/build/boot")
	@ResponseBody
	public Object buildBoot(HttpServletRequest request, @RequestBody GenerateTableOptionDTO option) {

		String dbName = option.getDbName();
		String tableName = option.getTableName();

		DataBaseSource dataBaseSource = connectionService.getDataBaseSource(dbName);

		if (dataBaseSource == null) {
			throw new BusinessException("不存在数据库：" + dbName);
		}

		DataBase dataBase = dataBaseSource.getDataBase(false);
		Table table = dataBase.getChild(tableName);

		if (table == null) {
			throw new BusinessException("不存在表：" + tableName);
		}

		GenerateTableOption tableOption = new GenerateTableOption(table, dataBaseSource.getDataBaseConfig().getType());

		beanCopy(option, tableOption);
		
		List<GenerateColumnOptionDTO>  columnOptionDTOs = option.getColumnOptions();
		for(GenerateColumnOptionDTO columnOptionDTO : columnOptionDTOs) {
			GenerateColumnOption columnOption = tableOption.getColumnOption(columnOptionDTO.getColumnName());
			beanCopy(columnOptionDTO, columnOption);
		}

		String projectPath = option.getProjectPath();

		if (projectPath == null || projectPath.length() == 0) {
			throw new BusinessException("项目路径不能为空");
		}

		generateService.buildProjectFile(tableOption, BuilderType.MODEL, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.MODEL_VO, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.MODEL_DTO, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.QUERY_DTO, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.MAPPER, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.SERVICE, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.CONTROLLER, projectPath);

		generateService.buildProjectFile(tableOption, BuilderType.SQLMAPPER, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.JAVASCRIPT, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.PAGE_INDEX, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.PAGE_ADD, projectPath);
		generateService.buildProjectFile(tableOption, BuilderType.PAGE_DETAIL, projectPath);

		visitCacheService.putCache(request, CACHE_PROJECT_PATH, projectPath);

		return CommonResponse.getSuccessResponse();
	}

}
