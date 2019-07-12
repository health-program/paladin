package com.paladin.health.controller.diagnose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQuery;

import java.util.Date;

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
	public String detail(String recordId, String targetId, String targetName, String time, Model model) {
		model.addAttribute("recordId", recordId);
		model.addAttribute("targetName", targetName);
		model.addAttribute("targetId", targetId);
		model.addAttribute("time", time);
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

	@RequestMapping("/count/index")
	public String countIndex() {
		return "/health/diagnose/diagnose_record_count";
	}

	@RequestMapping("/count/name")
	@ResponseBody
	public Object countByName(@RequestParam(required = false) String hospitalName, @RequestParam(required = false) Date bgTime,
			@RequestParam(required = false) Date endTime, @RequestParam(required = false) String sendMessage) {
		boolean isSend = "1".equals(sendMessage);
		return CommonResponse.getSuccessResponse(diagnoseRecordService.countRecordByHospitalName(hospitalName, bgTime, endTime, isSend));
	}

}
