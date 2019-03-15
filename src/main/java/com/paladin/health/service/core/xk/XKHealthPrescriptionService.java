package com.paladin.health.service.core.xk;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.DiagnoseTargetService;

@Service
public class XKHealthPrescriptionService {

	@Autowired
	private DiagnoseTargetService diagnoseTargetService;

	@Autowired
	private DiagnoseRecordService diagnoseRecordService;

	@Autowired
	private XKKnowledgeServlet knowledgeServlet;

	public Object diagnose(XKPeopleCondition condition) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Map getKnowledgeOfDisease(String code) {
		String url = "http://dlopen.xikang.com/openapi/evaluate/diseaseEncyclopedia/" + code;
		return knowledgeServlet.getRequest(url, null, Map.class);
	}

	@SuppressWarnings("rawtypes")
	public Map getEvaluation(XKEvaluateCondition condition) {
		String  url = "http://dlopen.xikang.com/openapi/evaluate/diseasePrediction";
		return knowledgeServlet.postJsonRequest(url, condition, Map.class);
	}

}
