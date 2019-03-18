package com.paladin.health.controller.open;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.common.core.ConstantsContainer;
import com.paladin.common.core.ConstantsContainer.KeyValue;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKEvaluateCondition;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;

@Controller
@RequestMapping("/open/xk")
public class XKAPPDiagnoseController {
	
	private final static String TYPES ="xk-index-type";
	
	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;
	
	/**
	 * 加载所有体检指标参数
	 * @return
	 */
	@GetMapping("/dict")
	@ResponseBody
	public Object dict() {
		Map<String, List<KeyValue>>typeList= ConstantsContainer.getTypeChildren(TYPES);
		List<KeyValue>list=typeList.get(TYPES);
		return CommonResponse.getSuccessResponse(list);
	}
	
	/**
	 * 返回指标对应的结果内容
	 * @return
	 */
	@RequestMapping("/symptom")
	@ResponseBody
	public Object symptom(String code) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledgeOfDisease(code));
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

}
