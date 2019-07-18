package com.paladin.health.controller.open;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.utils.WebUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.IndexContainer;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.service.core.HealthPrescriptionServiceImpl;

/**
 * 该接口方法为最初设想，已经作废
 * @author TontoZhou
 * @since 2019年3月13日
 */
//@Controller
@RequestMapping("/open")
public class DiagnoseController {

	@Autowired
	private HealthPrescriptionServiceImpl healthPrescriptionService;

	@Autowired
	private IndexContainer indexContainer;

	@RequestMapping("/diagnose/index")
	public Object diagnoseIndex() {
		return "/health/open/open_prescription_analyze";
	}
	
	@RequestMapping("/diagnose/trial/index")
	public Object diagnoseTrialIndex() {
		return "/health/open/open_prescription_analyze_trial";
	}

	@RequestMapping("/diagnose/trial")
	@ResponseBody
	public Object diagnoseTrial(@RequestBody PeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.findPrescription(condition));
	}

	@RequestMapping("/diagnose")
	@ResponseBody
	public Object diagnose(@RequestBody PeopleCondition condition) {
		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnose(condition));
	}

	@RequestMapping("/item/data")
	@ResponseBody
	public Object itemDetailList() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("item", indexContainer.getIndexItems());
		result.put("itemValueDefinition", indexContainer.getIndexItemValueDefinitions());
		result.put("itemStandard", indexContainer.getIndexItemStandards());

		return CommonResponse.getSuccessResponse(result);
	}

	@RequestMapping("/interface")
	public Object interfaceIndex(Model model, HttpServletRequest request) {
		model.addAttribute("baseUrl", WebUtil.getServletPath(request));
		return "/health/open/open_interface";
	}

	@RequestMapping("/interface/data")
	@ResponseBody
	public Object interfaceData() {
		return CommonResponse.getSuccessResponse(indexContainer.getStandardItems());
	}

}
