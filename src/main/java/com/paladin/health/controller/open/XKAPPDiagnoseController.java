package com.paladin.health.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.diagnose.DiagnoseRecordService;

@Controller
@RequestMapping("/open/xk")
public class XKAPPDiagnoseController {
	
	
	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;
	
	@Autowired
	private DiagnoseRecordService diagnoseRecordService;
	
	/**
	 * 加载所有体检指标参数
	 * @return
	 */
	@GetMapping("/symptom/code")
	@ResponseBody
	public Object dict() {
		return CommonResponse.getSuccessResponse(ConstantsContainer.getTypeChildren(ConstantsContainer.CONSTANT_INDEX_TYPE,ConstantsContainer.CONSTANT_DISEASE_TYPE));
	}
	
	/**
	 * 返回指标对应的结果内容
	 * @return
	 */
	@RequestMapping("/symptom")
	@ResponseBody
	public Object symptom(String code) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledge(code));
	}
	
	
	/**
	 * 根据评估指标返回评估结果
	 * @return
	 */
	@RequestMapping("/evaluate")
	@ResponseBody
	public Object getEvaluation(@RequestBody XKEvaluateCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getEvaluation(condition));
	}
	
	/*
	 * 健康风险评估
	 * @return
	 * 
	 * */
	@RequestMapping("/evaluate/simple/record")
	@ResponseBody
	public Object getHealthRecord(String targetId) {
		return CommonResponse.getSuccessResponse(diagnoseRecordService.getHealthRecord(targetId));
	}

}
