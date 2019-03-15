package com.paladin.health.controller.xk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKEvaluateCondition;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;

/**
 * 基于熙康知识库
 * 
 * @author TontoZhou
 * @since 2019年3月13日
 */
@Controller
@RequestMapping("/xk/")
public class XKDiagnoseController {

	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;

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

	@RequestMapping("/evaluate")
	@ResponseBody
	public Object getEvaluation(XKEvaluateCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getEvaluation(condition));
	}

}
