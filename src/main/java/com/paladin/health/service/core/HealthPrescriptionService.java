package com.paladin.health.service.core;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.core.HealthPrescriptionContainer;
import com.paladin.health.core.HealthPrescriptionContainer.Prescription;
import com.paladin.health.core.HealthPrescriptionContainer.PrescriptionResult;
import com.paladin.health.core.factor.FactorAnalyzer.Basis;
import com.paladin.health.core.factor.HealthFactorAnalyzer;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.model.prescription.PrescriptionFactor;
import com.paladin.health.model.prescription.PrescriptionTerminology;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.DiagnoseTargetService;

@Service
public class HealthPrescriptionService {

	@Autowired
	private HealthPrescriptionContainer healthPrescriptionContainer;

	@Autowired
	private HealthFactorAnalyzer healthFactorAnalyzer;

	@Autowired
	private DiagnoseTargetService diagnoseTargetService;

	@Autowired
	private DiagnoseRecordService diagnoseRecordService;

	public PrescriptionResult findPrescription(String... args) {
		return healthPrescriptionContainer.search(args);
	}

	/**
	 * 简单获取健康处方
	 * 
	 * @param peopleCondition
	 * @return
	 */
	public PrescriptionResult findPrescription(PeopleCondition peopleCondition) {
		peopleCondition.initialize();
		healthFactorAnalyzer.analyzeFactor(peopleCondition);
		PrescriptionResult result = healthPrescriptionContainer.search(peopleCondition);
		return result;
	}

	public List<Basis> getAnalyzeBasises() {
		return healthFactorAnalyzer.getAnalyzeBasises();
	}

	/**
	 * 诊断获取健康处方，并做记录
	 * 
	 * @param condition
	 * @return
	 */
	@Transactional
	public DiagnosePrescription diagnose(PeopleCondition peopleCondition) {
		peopleCondition.initialize();
		healthFactorAnalyzer.analyzeFactor(peopleCondition);
		PrescriptionResult result = healthPrescriptionContainer.search(peopleCondition);

		DiagnoseTarget target = peopleCondition.getDiagnoseTarget();
		if (target == null) {
			throw new BusinessException("没有诊断目标人的基本信息");
		}

		String targetId = target.getId();

		if (targetId == null || targetId.length() == 0) {
			throw new BusinessException("没有诊断目标人的身份证号码");
		}

		Date now = new Date();
		target.setUpdateTime(now);
		target.setFactors(splitFactor(result.getFactors()));

		// 诊断目标档案维护，有则更新（只更新有的数据），无则新增
		if (diagnoseTargetService.updateSelective(target) <= 0) {
			target.setCreateTime(now);
			diagnoseTargetService.save(target);
		}

		DiagnoseRecord record = new DiagnoseRecord();
		String diagnoseId = UUIDUtil.createUUID();
		record.setId(diagnoseId);
		record.setTargetId(targetId);
		record.setTargetCondition(JsonUtil.getJson(peopleCondition));
		record.setPrescription(JsonUtil.getJson(result));
		record.setCreateTime(now);

		diagnoseRecordService.save(record);

		return new DiagnosePrescription(diagnoseId, result);
	}

	/**
	 * 拼接危险因素
	 * 
	 * @param factors
	 * @return
	 */
	private String splitFactor(List<PrescriptionFactor> factors) {
		// 如果无，则需要空字符串进行填充
		if (factors == null || factors.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (PrescriptionFactor factor : factors) {
			sb.append(factor.getCode()).append(",");
		}
		return sb.toString();
	}

	public static class DiagnosePrescription {

		private String diagnoseId;
		private List<PrescriptionFactor> factors;
		private List<Prescription> prescriptions;
		private List<PrescriptionTerminology> terminologies;

		public DiagnosePrescription(String diagnoseId, PrescriptionResult result) {
			this.diagnoseId = diagnoseId;
			if (result != null) {
				this.factors = result.getFactors();
				this.prescriptions = result.getPrescriptions();
				this.terminologies = result.getTerminologies();
			}
		}

		public List<PrescriptionFactor> getFactors() {
			return factors;
		}

		public List<Prescription> getPrescriptions() {
			return prescriptions;
		}

		public String getDiagnoseId() {
			return diagnoseId;
		}

		public List<PrescriptionTerminology> getTerminologies() {
			return terminologies;
		}
	}

}
