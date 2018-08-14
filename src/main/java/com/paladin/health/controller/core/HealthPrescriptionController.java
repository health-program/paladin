package com.paladin.health.controller.core;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.service.core.HealthPrescriptionService;
import com.paladin.health.service.prescription.PrescriptionFactorItemService;
import com.paladin.health.service.prescription.PrescriptionFactorService;
import com.paladin.health.service.prescription.PrescriptionItemService;

@Controller
@RequestMapping("/health/prescription")
public class HealthPrescriptionController {

	@Autowired
	PrescriptionFactorItemService prescriptionFactorItemService;
	@Autowired
	PrescriptionItemService prescriptionItemService;
	@Autowired
	PrescriptionFactorService prescriptionFactorService;
	@Autowired
	HealthPrescriptionService healthPrescriptionService;

	@RequestMapping("/index")
	public String index() {
		return "/health/core/health_prescription_index";
	}
	
	@RequestMapping("/index/data")
	@ResponseBody
	public Object indexData() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("factors", prescriptionFactorService.findAll());
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

	@RequestMapping("/analyze")
	public String analyze() {
		return "/health/core/health_prescription_analyze";
	}

	@RequestMapping("/factor/list")
	@ResponseBody
	public Object factorList() {
		return CommonResponse.getSuccessResponse(prescriptionFactorService.findAll());
	}

	@RequestMapping("/factor/item")
	@ResponseBody
	public Object findItemOfFactor(@RequestParam String code) {
		return CommonResponse.getSuccessResponse(prescriptionItemService.findItemOfFactor(code));
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

	// ----------------------------------------
	// 对外健康处方接口
	// ----------------------------------------

	@RequestMapping("/find/factor")
	@ResponseBody
	public Object findByFactor(@RequestParam String[] args) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.findPrescription(args));
	}

	@RequestMapping("/find/condition")
	@ResponseBody
	public Object findByCondition(@RequestBody PeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.findPrescription(condition));
	}

}
