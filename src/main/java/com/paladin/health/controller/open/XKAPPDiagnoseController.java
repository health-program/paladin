package com.paladin.health.controller.open;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.common.core.ConstantsContainer;
import com.paladin.common.core.ConstantsContainer.KeyValue;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;

@Controller
@RequestMapping("/open/xk")
public class XKAPPDiagnoseController {
	
	private final static String TYPES ="xk-index-type";
	
	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;
	
	@GetMapping("/dict")
	@ResponseBody
	public Object dict() {
		Map<String, List<KeyValue>>typeList= ConstantsContainer.getTypeChildren(TYPES);
		List<KeyValue>list=typeList.get(TYPES);
		return CommonResponse.getSuccessResponse(list);
	}
	
	@RequestMapping("/symptom")
	@ResponseBody
	public Object symptom(String code) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledgeOfDisease(code));
	}

}
