package com.paladin.health.controller.core;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.HealthPrescriptionContainer;
import com.paladin.health.service.core.HealthPrescriptionServiceImpl;
import com.paladin.health.service.prescription.PrescriptionFactorItemService;
import com.paladin.health.service.prescription.PrescriptionItemService;

@Controller
@RequestMapping("/health/prescription")
public class HealthPrescriptionController {

	@Autowired
	private PrescriptionFactorItemService prescriptionFactorItemService;
	@Autowired
	private PrescriptionItemService prescriptionItemService;
	@Autowired
	private HealthPrescriptionServiceImpl healthPrescriptionService;
	@Autowired
	private HealthPrescriptionContainer healthPrescriptionContainer;

	@RequestMapping("/index")
	public String index() {
		return "/health/core/health_prescription_index";
	}

	@RequestMapping("/index/data")
	@ResponseBody
	public Object indexData() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("factors", healthPrescriptionContainer.getPrescriptionFactor());
		result.put("items", prescriptionItemService.findAll());
		return CommonResponse.getSuccessResponse(result);
	}

	@RequestMapping("/factor/item/save")
	@ResponseBody
	public Object saveFactorItem(@RequestParam String factorCode, @RequestParam(required = false) String itemId) {
		Integer[] ids = null;
		if (itemId != null && itemId.length() > 0) {
			String[] itemIds = itemId.split(",");
			ids = new Integer[itemIds.length];
			for (int i = 0; i < itemIds.length; i++) {
				ids[i] = Integer.valueOf(itemIds[i]);
			}
		}
		return CommonResponse.getResponse(prescriptionFactorItemService.saveFactorItemRelation(factorCode, ids));
	}

	@RequestMapping("/factor/list")
	@ResponseBody
	public Object factorList() {
		return CommonResponse.getSuccessResponse(healthPrescriptionContainer.getPrescriptionFactor());
	}

	@RequestMapping("/factor/item")
	@ResponseBody
	public Object findItemOfFactor(@RequestParam String code) {
		return CommonResponse.getSuccessResponse(prescriptionItemService.findItemOfFactor(code));
	}
	
	@RequestMapping("/analyze/trial/index")
	public Object analyzeTrialIndex() {
		return "/health/open/open_prescription_analyze_trial";
	}
	
	@RequestMapping("/basis")
	public Object basis() {
		return "/health/core/health_prescription_basis";
	}

	@RequestMapping("/basis/data")
	@ResponseBody
	public Object basisList() {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getAnalyzeBasises());
	}
}
