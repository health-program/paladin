package com.paladin.health.controller.xk;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.AuthKeyContainer;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import com.paladin.health.service.core.xk.dto.ConfirmEvaluationDTO;
import com.paladin.health.service.diagnose.DiagnoseRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 基于熙康知识库
 * 
 * @author TontoZhou
 * @since 2019/03/14
 */
@Api("熙康疾病百科")
@Controller
@RequestMapping("/")
public class XKDiagnoseController {

	@Autowired
	private XKHealthPrescriptionService healthPrescriptionService;
	@Autowired
	private DiagnoseRecordService diagnoseRecordService;
	@Autowired
	private AuthKeyContainer authKeyContainer;

	@ApiOperation(value = "熙康健康评估接口")
	@PostMapping("/evaluate/simple/data")
	@ResponseBody
	public Object diagnoseEvaluation(@RequestBody XKPeopleCondition condition, @RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		return CommonResponse.getSuccessResponse(healthPrescriptionService.doSimpleEvaluation(condition, accessKey));
	}

	@ApiOperation(value = "熙康健康评估接口")
	@PostMapping("/evaluate/simple")
	@ResponseBody
	public Object diagnoseEvaluationPage(@RequestBody XKPeopleCondition condition, @RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		healthPrescriptionService.doSimpleEvaluation(condition, accessKey);
		return CommonResponse.getSuccessResponse();
	}

	@ApiOperation(value = "熙康健康评估记录接口")
	@GetMapping("/evaluate/simple/record")
	@ResponseBody
	public Object getEvaluationRecord(@RequestParam("searchId") String searchId, @RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		return CommonResponse.getSuccessResponse(diagnoseRecordService.getRecordBySearchId(searchId, accessKey));
	}

	@ApiOperation(value = "熙康健康评估记录接口")
	@GetMapping("/evaluate/simple/record/page")
	public Object getEvaluationRecordPage(@RequestParam("searchId") String searchId, @RequestParam String accessKey, Model model) {
		if (!validAccessKey(accessKey)) {
			model.addAttribute("errorMessage", "未授权访问，请传入AccessKey");
			return "/health/xk/error";
		}

		DiagnoseRecord record = diagnoseRecordService.getRecordBySearchId(searchId, accessKey);
		if (record == null) {
			model.addAttribute("errorMessage", "未找到健康处方对应记录");
			return "/health/xk/error";
		}
		model.addAttribute("searchId", searchId);
		model.addAttribute("accessKey", accessKey);
		model.addAttribute("record", JsonUtil.getJson(record));
		return "/health/xk/prescription";
	}

	@ApiOperation(value = "熙康健康评估记录接口")
	@PostMapping("/evaluate/simple/confirm")
	@ResponseBody
	public Object confirmSimpleEvaluation(@RequestBody List<ConfirmEvaluationDTO> confirmEvaluations, @RequestParam("searchId") String searchId,
			@RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		healthPrescriptionService.confirmSimpleEvaluation(confirmEvaluations, searchId, accessKey);
		return CommonResponse.getSuccessResponse();
	}

	@ApiOperation(value = "熙康体检百科接口")
	@ApiImplicitParam(value = "熙康疾病或指标编码", required = true)
	@GetMapping("/knowledge")
	@ResponseBody
	public Object knowledge(@RequestParam String code, @RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledge(code));
	}

	@ApiOperation(value = "疾病和指标数据接口")
	@GetMapping("/knowledge/code")
	@ResponseBody
	public Object dict(@RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		return CommonResponse.getSuccessResponse(
				ConstantsContainer.getTypeChildren(XKHealthPrescriptionService.CONSTANT_INDEX_TYPE, XKHealthPrescriptionService.CONSTANT_DISEASE_TYPE));
	}
	
	private boolean validAccessKey(String accessKey) {
		return authKeyContainer.hasAccessKey(accessKey);
	}
	
}
