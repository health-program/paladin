package com.paladin.health.controller.diagnose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQuery;

@Controller
@RequestMapping("/health/diagnose/record")
public class DiagnoseRecordController {

	@Autowired
	private DiagnoseRecordService diagnoseRecordService;

	/**
	 * 功能描述: <br>
	 * 〈病人历史诊断记录首页〉
	 */
	@RequestMapping("/index")
	public String diagnoseRecordsIndex(String targetId, String targetName, Model model) {
		model.addAttribute("targetId", targetId);
		model.addAttribute("targetName", targetName);
		return "/health/diagnose/diagnose_record_index";
	}

	/**
	 * 功能描述: <br>
	 * 〈病人历史诊断记录〉
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object diagnoseRecordsFindPage(DiagnoseRecordQuery query) {
		return CommonResponse.getSuccessResponse(diagnoseRecordService.findRecordByTarget(query));
	}
	
	@RequestMapping("/detail")
	public String detail(String targetId, String targetName, Model model) {
		model.addAttribute("targetId", targetId);
		model.addAttribute("targetName", targetName);
		return "/health/diagnose/diagnose_record_detail";
	}
	
	/**
	 * 功能描述: <br>
	 * 〈病人历史诊断记录〉
	 */
	@RequestMapping("/get")
	@ResponseBody
	public Object getRecord(String id) {
		return CommonResponse.getSuccessResponse(diagnoseRecordService.get(id));
	}
	

}
