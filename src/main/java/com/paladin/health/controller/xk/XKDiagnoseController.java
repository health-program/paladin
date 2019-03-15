package com.paladin.health.controller.xk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.common.core.ConstantsContainer;
import com.paladin.common.core.ConstantsContainer.KeyValue;
import com.paladin.common.service.syst.SysConstantService;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;

/**
 * 基于熙康知识库
 * @author TontoZhou
 * @since 2019年3月13日
 */
@Api("熙康疾病百科")
@Controller
@RequestMapping("/xk/")
public class XKDiagnoseController {
	private final static String TYPES ="xk-index-type";
	
	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;
	
	@ApiOperation(value = "疾病评估输入信息页面跳转")
	@GetMapping("/diagnose/input")
	public Object input() {
		return "/health/xk/diagnose_input";
	}
	
	@ApiOperation(value = "")
	//@ApiImplicitParam(name = "code", value = "CODE", required = true, dataType = "String", allowMultiple = true)
	@GetMapping("/diagnose")
	@ResponseBody
	public Object diagnose(@RequestBody XKPeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnose(condition));
	}
	
	@ApiOperation(value = "疾病百科搜索展示页面")
	@GetMapping("/symptom/index")
	public Object index() {
		return "/health/xk/symptom_index";
	}
	
	@ApiOperation(value = "通过code获取疾病详情")
	@ApiImplicitParam(name = "code", value = "熙康疾病代码", required = true, dataType = "String")
	@PostMapping("/symptom")
	@ResponseBody
	public Object symptom(String code) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledgeOfDisease(code));
	}
	
	@ApiOperation(value = "为搜索加载疾病字典", response = List.class, responseContainer = "List")
	@GetMapping("/dict")
	@ResponseBody
	public Object dict() {
		Map<String, List<KeyValue>>typeList= ConstantsContainer.getTypeChildren(TYPES);
		List<KeyValue>list=typeList.get(TYPES);
		return CommonResponse.getSuccessResponse(list);
	}
	
}
