package com.paladin.health.controller.xk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;

/**
 * 基于熙康知识库
 * 
 * @author TontoZhou
 * @since 2019/03/14
 */
@Api("熙康疾病百科")
@Controller
@RequestMapping("/xk/")
public class XKDiagnoseController {

	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;

	// TODO 后期删除
	@RequestMapping("/evaluate/get/demo")
	@ResponseBody
	public Object getEvaluationDemo() {
		XKEvaluateCondition demo = new XKEvaluateCondition();
		demo.setAge("47");
		demo.setDbp("90");
		demo.setDiabetes("1");
		demo.setDiarrhea("1");
		demo.setDrinking("1");
		demo.setDyazide("1");
		demo.setFamily_cvd("1");
		demo.setFamily_diabetes("1");
		demo.setFamily_hypertension("0");
		demo.setFamily_osteoporosis("1");
		demo.setFbc("10.8");
		demo.setHdl("4.5");
		demo.setHeight("178");
		demo.setHormone("1");
		demo.setHyperglycemia("1");
		demo.setIdl("4.12");
		demo.setMenopause("1");
		demo.setPbg("16.1");
		demo.setRarelyBask("1");
		demo.setRarelysports("1");
		demo.setSbp("156");
		demo.setSex("1");
		demo.setSmoke("1");
		demo.setSports("1");
		demo.setStrokeOrTia("1");
		demo.setTc_mmol("7.8");
		demo.setVegOrFruits("1");
		demo.setWaistline("100");
		demo.setWeight("95");
		return CommonResponse.getSuccessResponse(demo);
	}

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
		return CommonResponse.getSuccessResponse(ConstantsContainer.getTypeChildren(XKHealthPrescriptionService.CONSTANT_INDEX_TYPE,XKHealthPrescriptionService.CONSTANT_DISEASE_TYPE));
	}
	
	@RequestMapping("/diagnose/input")
	public Object diagnoseInput() {
		return "/health/xk/diagnose_input";
	}
	
	@PostMapping("/diagnose")
	@ResponseBody
	public Object diagnose(@RequestBody XKPeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnose(condition));
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
