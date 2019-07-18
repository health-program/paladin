package com.paladin.health.controller.xk;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基于熙康知识库
 * 
 * @author TontoZhou
 * @since 2019/03/14
 */
@Api("熙康疾病百科")
@Controller
@RequestMapping("/xk/")
public class XKKnowledgeController {

	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;

/*	@RequestMapping("/evaluate/input")
	public Object input() {
		return "/health/xk/evaluate_input";
	}

	@ApiOperation(value = "熙康健康评估接口")
	@ApiImplicitParam(value = "评估条件", required = true)
	@RequestMapping("/evaluate")
	@ResponseBody
	public Object getEvaluation(XKEvaluateCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getEvaluation(condition));
	}*/

	@GetMapping("/symptom/index")
	public Object index() {
		return "/health/xk/symptom_index";
	}

	@ApiOperation(value = "熙康体检百科接口")
	@ApiImplicitParam(value = "熙康疾病或指标编码", required = true)
	@PostMapping("/symptom")
	@ResponseBody
	public Object symptom(String code) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledge(code));
	}

	@ApiOperation(value = "疾病和指标数据接口")
	@GetMapping("/symptom/code")
	@ResponseBody
	public Object dict() {
		return CommonResponse.getSuccessResponse(
				ConstantsContainer.getTypeChildren(ConstantsContainer.CONSTANT_INDEX_TYPE, ConstantsContainer.CONSTANT_DISEASE_TYPE));
	}

/*	@RequestMapping("/diagnose/input")
	public Object diagnoseInput() {
		return "/health/xk/diagnose_input";
	}

	@PostMapping("/diagnose/disease")
	@ResponseBody
	public Object diagnoseDisease(@RequestParam("code[]") String[] codes) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnoseDiseases(Arrays.asList(codes)));
	}

	@PostMapping("/diagnose/index")
	@ResponseBody
	public Object diagnoseIndex(@RequestParam("code[]") String[] codes) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnoseDiseases(Arrays.asList(codes)));
	}

	@PostMapping("/diagnose/evaluation")
	@ResponseBody
	public Object diagnoseEvaluation(@RequestBody XKPeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnoseEvaluation(condition, "demo"));
	}

	@GetMapping("/tips/output")
	public Object tips() {
		return "/health/xk/tip_output";
	}

	@GetMapping("/tips/{typeCode}")
	@ResponseBody
	public Object getTips(@PathVariable String typeCode) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getTips(typeCode));
	}*/

}
