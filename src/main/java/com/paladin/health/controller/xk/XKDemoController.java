package com.paladin.health.controller.xk;

import com.paladin.framework.spring.DevCondition;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基于熙康知识库
 * 
 * @author TontoZhou
 * @since 2019/03/14
 */
@Controller
@RequestMapping("/xk/demo")
@Conditional(DevCondition.class)
public class XKDemoController {

	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;

	@RequestMapping("/evaluate/input")
	public Object input() {
		return "/health/xk/evaluate_input";
	}

	@ApiOperation(value = "熙康健康评估接口")
	@ApiImplicitParam(value = "评估条件", required = true)
	@RequestMapping("/evaluate")
	@ResponseBody
	public Object getEvaluation(XKEvaluateCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getEvaluation(condition));
	}

	@RequestMapping("/diagnose/input")
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
		return CommonResponse.getSuccessResponse(healthPrescriptionService.doSimpleEvaluation(condition, "demo"));
	}

	@GetMapping("/tips/output")
	public Object tips() {
		return "/health/xk/tip_output";
	}

	@GetMapping("/tips/{typeCode}")
	@ResponseBody
	public Object getTips(@PathVariable String typeCode) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getTips(typeCode));
	}

}
