package com.paladin.health.controller.xk;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@ApiOperation(value = "熙康健康评估接口")
	@PostMapping("/evaluate/simple")
	@ResponseBody
	public Object diagnoseEvaluation(@RequestBody XKPeopleCondition condition, @RequestParam(required = false, name = "appkey") String accessKey) {
		if (accessKey == null || accessKey.length() == 0) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}

		return CommonResponse.getSuccessResponse(healthPrescriptionService.diagnoseEvaluation(condition, accessKey));
	}

	@ApiOperation(value = "熙康体检百科接口")
	@ApiImplicitParam(value = "熙康疾病或指标编码", required = true)
	@GetMapping("/knowledge")
	@ResponseBody
	public Object knowledge(@RequestParam String code, @RequestParam(required = false, name = "appkey") String accessKey) {
		if (accessKey == null || accessKey.length() == 0) {
			return CommonResponse.getNoPermissionResponse("请传入AccessKey");
		}
		return CommonResponse.getSuccessResponse(healthPrescriptionService.getKnowledge(code));
	}

	@ApiOperation(value = "疾病和指标数据接口")
	@GetMapping("/knowledge/code")
	@ResponseBody
	public Object dict() {
		return CommonResponse.getSuccessResponse(
				ConstantsContainer.getTypeChildren(XKHealthPrescriptionService.CONSTANT_INDEX_TYPE, XKHealthPrescriptionService.CONSTANT_DISEASE_TYPE));
	}

}
