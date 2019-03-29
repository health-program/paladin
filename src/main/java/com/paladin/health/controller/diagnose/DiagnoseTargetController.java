package com.paladin.health.controller.diagnose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.diagnose.DiagnoseTargetFactorService;
import com.paladin.health.service.diagnose.dto.DiagnoseTargetQuery;

@Controller
@RequestMapping("/health/diagnose/target")
public class DiagnoseTargetController {

	@Autowired
	private DiagnoseTargetFactorService diagnoseTargetFactorService;

	/**
	 * 功能描述: <br>
	 * 〈病人历史诊断记录首页〉
	 */
	@RequestMapping("/index")
	public String diagnoseRecordsIndex() {
		return "/health/diagnose/diagnose_target_index";
	}

	/**
	 * 功能描述: <br>
	 * 〈病人历史诊断记录〉
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object diagnoseRecordsFindPage(DiagnoseTargetQuery query) {
		return CommonResponse.getSuccessResponse(diagnoseTargetFactorService.searchDiagnoseTargetByQuery(query));
	}
	
	
	

}
