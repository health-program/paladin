package com.paladin.health.controller.xk;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.AuthKeyContainer;
import com.paladin.health.core.knowledge.KnowledgeManageContainer;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import com.paladin.health.service.core.xk.dto.ConfirmEvaluationDTO;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;
import com.paladin.health.service.diagnose.DiagnoseRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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
		XKHealthPrescription result = KnowledgeManageContainer.getCurrentHealthPrescriptionService().doSimpleEvaluation(condition, accessKey);
		if (result == null) {
			return CommonResponse.getFailResponse("无法调取相关服务");
		}
		return CommonResponse.getSuccessResponse(result);
	}

	@ApiOperation(value = "熙康健康评估接口")
	@PostMapping("/evaluate/simple")
	@ResponseBody
	public Object diagnoseEvaluationPage(@RequestBody XKPeopleCondition condition, @RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}

		XKHealthPrescription result = KnowledgeManageContainer.getCurrentHealthPrescriptionService().doSimpleEvaluation(condition, accessKey);
		if (result == null) {
			return CommonResponse.getFailResponse("无法调取相关服务");
		}
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
	public Object getEvaluationRecordConfirmPage(@RequestParam("searchId") String searchId, @RequestParam String accessKey, Model model) {
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
		return "/health/xk/prescription_confirm";
	}

	@ApiOperation(value = "熙康健康评估记录接口")
	@GetMapping("/evaluate/simple/record/view")
	public Object getEvaluationRecordPage(@RequestParam("identificationId") String identificationId, @RequestParam String accessKey, Model model) {
		if (!validAccessKey(accessKey)) {
			model.addAttribute("errorMessage", "未授权访问，请传入AccessKey");
			return "/health/xk/error";
		}

		DiagnoseRecord record = diagnoseRecordService.getLastRecordByIdentificationId(identificationId, accessKey);
		if (record == null) {
			model.addAttribute("errorMessage", "您没有创建过健康处方");
			return "/health/xk/error";
		}

		model.addAttribute("record", JsonUtil.getJson(record));
		return "/health/xk/prescription_view";
	}

	@ApiOperation(value = "熙康健康评估记录接口")
	@PostMapping("/evaluate/simple/confirm")
	@ResponseBody
	public Object confirmSimpleEvaluation(@RequestBody ConfirmEvaluationDTO confirmEvaluation, @RequestParam("searchId") String searchId,
			@RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		KnowledgeManageContainer.getCurrentHealthPrescriptionService().confirmSimpleEvaluation(confirmEvaluation, searchId, accessKey);
		return CommonResponse.getSuccessResponse();
	}

	@ApiOperation(value = "熙康健康评估记录接口")
	@PostMapping("/evaluate/simple/confirm2pdf")
	@ResponseBody
	public Object confirmSimpleEvaluation2pdf(@RequestBody ConfirmEvaluationDTO confirmEvaluation, @RequestParam("searchId") String searchId,
			@RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}

		String path = KnowledgeManageContainer.getCurrentHealthPrescriptionService().confirmSimpleEvaluationAndCreatePDF(confirmEvaluation, searchId, accessKey,
				true);
		if (path == null || path.length() == 0) {
			return CommonResponse.getFailResponse("无法调取相关服务");
		} else {
			return CommonResponse.getSuccessResponse("success", path);
		}
	}

	@ApiOperation(value = "熙康体检百科接口")
	@ApiImplicitParam(value = "熙康疾病或指标编码", required = true)
	@GetMapping("/knowledge")
	@ResponseBody
	public Object knowledge(@RequestParam String code, @RequestParam String accessKey) {
		if (!validAccessKey(accessKey)) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		return CommonResponse.getSuccessResponse(KnowledgeManageContainer.getCurrentHealthPrescriptionService().getKnowledge(code));
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
