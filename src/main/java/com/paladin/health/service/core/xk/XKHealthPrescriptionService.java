package com.paladin.health.service.core.xk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.common.core.ConstantsContainer.KeyValue;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.core.xk.response.XKDiseaseKnowledge;
import com.paladin.health.service.core.xk.response.XKEvaluation;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.DiagnoseTargetFactorService;
import com.paladin.health.service.diagnose.DiagnoseTargetService;

@Service
@SuppressWarnings("rawtypes")
public class XKHealthPrescriptionService {

	private static Logger logger = LoggerFactory.getLogger(XKHealthPrescriptionService.class);

	@Autowired
	private DiagnoseTargetService diagnoseTargetService;
	@Autowired
	private DiagnoseRecordService diagnoseRecordService;
	@Autowired
	private DiagnoseTargetFactorService diagnoseRecordFactorService;

	@Autowired
	private XKKnowledgeServlet knowledgeServlet;

	/** 熙康疾病类型 */
	public static String CONSTANT_DISEASE_TYPE = "xk-disease-type";
	/** 熙康指标类型 */
	public static String CONSTANT_INDEX_TYPE = "xk-index-type";
	/** 熙康评估类型 */
	public static String CONSTANT_EVALUATE_TYPE = "xk-evaluate-type";

	/**
	 * 健康处方获取服务方法
	 * 
	 * @param condition
	 * @return
	 */
	@Transactional
	public XKHealthPrescription diagnose(XKPeopleCondition condition) {
		XKHealthPrescription result = new XKHealthPrescription();

		Map<String, XKDiseaseKnowledge> diseaseKnowledge = null;
		List<XKEvaluation> evaluationResultList = null;

		// 疾病或指标知识获取
		List<String> diseases = condition.getDiseases();
		if (diseases != null && diseases.size() > 0) {
			Set<String> diseaseSet = new HashSet<>(diseases);
			diseaseKnowledge = new HashMap<>();
			for (String disease : diseaseSet) {
				String diseaseName = ConstantsContainer.getTypeValue(CONSTANT_DISEASE_TYPE, disease);
				if (diseaseName != null && diseaseName.length() > 0) {
					Map knowledge = getKnowledgeOfDisease(disease);
					if (knowledge != null) {
						diseaseKnowledge.put(disease, new XKDiseaseKnowledge(disease, diseaseName, XKDiseaseKnowledge.TYPE_DISEASE, knowledge));
					}
				} else {
					diseaseName = ConstantsContainer.getTypeValue(CONSTANT_INDEX_TYPE, disease);
					Map knowledge = getKnowledgeOfDisease(disease);
					if (knowledge != null) {
						diseaseKnowledge.put(disease, new XKDiseaseKnowledge(disease, diseaseName, XKDiseaseKnowledge.TYPE_INDE, knowledge));
					}
				}
			}

			result.setKnowledge(diseaseKnowledge);
		}

		// 进行评估预测
		XKEvaluateCondition evaluateCondition = condition.getCondition();
		if (evaluateCondition != null) {
			Map evaluationResult = getEvaluation(evaluateCondition);
			if (evaluationResult != null) {
				evaluationResultList = new ArrayList<>(10);
				for (KeyValue kv : ConstantsContainer.getType(CONSTANT_EVALUATE_TYPE)) {

					String code = kv.getKey();
					String name = kv.getValue();

					Map single = (Map) evaluationResult.get(code);
					if (single == null)
						continue;
					String status = (String) single.get("status");

					// 如果不是状态10000则评估出错，可能是参数不全
					if (!"0000".equals(status)) {
						continue;
					}

					String riskLevelName = (String) single.get("riskLevel");
					int riskLevel = 0;

					// 熙康返回的是中文危险等级名称，这里对其归纳总结给出等级
					if ("极低风险".equals(riskLevelName)) {
						riskLevel = XKEvaluation.LEVEL_VERY_LOW;
					} else if ("低风险".equals(riskLevelName)) {
						riskLevel = XKEvaluation.LEVEL_LOW;
					} else if ("中风险".equals(riskLevelName) || "中等风险".equals(riskLevelName)) {
						riskLevel = XKEvaluation.LEVEL_MIDDLE;
					} else if ("高风险".equals(riskLevelName)) {
						riskLevel = XKEvaluation.LEVEL_HIGH;
					} else if ("极高风险".equals(riskLevelName)) {
						riskLevel = XKEvaluation.LEVEL_VERY_HIGH;
					} else {
						logger.error("评估[" + name + "]时出现未知风险等级：" + riskLevelName);
						continue;
					}

					String suggest = (String) single.get("suggest");
					evaluationResultList.add(new XKEvaluation(code, name, riskLevel, riskLevelName, suggest));
				}

				// 按危险等级排序
				if (evaluationResultList.size() > 0) {
					evaluationResultList.sort(new Comparator<XKEvaluation>() {
						@Override
						public int compare(XKEvaluation o1, XKEvaluation o2) {
							return o2.getRiskLevel() - o1.getRiskLevel();
						}
					});
					result.setEvaluation(evaluationResultList);
				}
			}
		}

		String identificationId = condition.getIdentificationId();
		// 保存数据，没有身份证则不进行数据持久化
		if (identificationId != null && identificationId.length() > 0) {
			String name = condition.getName();
			String cellphone = condition.getCellphone();
			Date birthday = condition.getBirthday();
			Date now = new Date();

			// 性别可以从评估那去获取
			Integer sex = condition.getSex();
			if (sex == null) {
				if (evaluateCondition != null) {
					String sexStr = evaluateCondition.getSex();
					if (sexStr != null && sexStr.length() > 0) {
						try {
							sex = Integer.valueOf(sexStr);
						} catch (Exception e) {

						}
					}
				}
			}

			// 保存或更新目标病人的信息（如果是空则不会更新掉原先数据）
			DiagnoseTarget target = diagnoseTargetService.get(identificationId);

			if (target == null) {
				target = new DiagnoseTarget();
				target.setId(identificationId);
				target.setName(name);
				target.setBirthday(birthday);
				target.setCellphone(cellphone);
				target.setSex(sex);
				target.setCreateTime(now);
				target.setUpdateTime(now);
				diagnoseTargetService.save(target);
			} else {
				target.setId(identificationId);

				if (name != null && name.length() > 0)
					target.setName(name);
				if (birthday != null)
					target.setBirthday(birthday);
				if (cellphone != null && cellphone.length() > 0)
					target.setCellphone(cellphone);
				if (sex != null)
					target.setSex(sex);
				target.setUpdateTime(now);
				diagnoseTargetService.update(target);
			}

			// 替换目标病人存在的危险因素（疾病，风险）
			List<DiagnoseTargetFactor> targetFactors = new ArrayList<>();

			if (diseaseKnowledge != null && diseaseKnowledge.size() > 0) {
				for (XKDiseaseKnowledge knowledge : diseaseKnowledge.values()) {
					String type = knowledge.getType();
					int factorType = XKDiseaseKnowledge.TYPE_DISEASE.equals(type) ? DiagnoseTargetFactor.FACTOR_TYPE_DISEASE
							: DiagnoseTargetFactor.FACTOR_TYPE_INDEX;

					targetFactors.add(
							new DiagnoseTargetFactor(identificationId, knowledge.getCode(), factorType, DiagnoseTargetFactor.DEFAULT_FACTOR_LEVEL_HAVE, now));
				}
			}

			if (evaluationResultList != null && evaluationResultList.size() > 0) {
				for (XKEvaluation evaluation : evaluationResultList) {
					targetFactors.add(new DiagnoseTargetFactor(identificationId, evaluation.getCode(), DiagnoseTargetFactor.FACTOR_TYPE_RISK,
							evaluation.getRiskLevel(), now));
				}
			}

			diagnoseRecordFactorService.removeByTarget(identificationId);
			if (targetFactors.size() > 0) {
				diagnoseRecordFactorService.batchSave(targetFactors);
			}

			// 发送短信
			sendPrescriptionMessage(result, condition);

			// 保存目标病人本次请求和返回数据
			DiagnoseRecord record = new DiagnoseRecord();
			String diagnoseId = UUIDUtil.createUUID();
			record.setId(diagnoseId);
			record.setTargetId(identificationId);
			record.setTargetCondition(JsonUtil.getJson(condition));
			record.setPrescription(JsonUtil.getJson(result));
			record.setType(DiagnoseRecord.TYPE_XK);
			record.setCreateTime(now);
			
			diagnoseRecordService.save(record);			
			result.setId(diagnoseId);
		}

		return result;
	}

	/**
	 * 发送短信
	 * 
	 * @param prescription
	 * @param condition
	 */
	private void sendPrescriptionMessage(XKHealthPrescription prescription, XKPeopleCondition condition) {
		String cellphone = condition.getCellphone();
		Boolean doSend = condition.getSendMessage();
		String senderName = condition.getSenderName();

		if (cellphone != null && cellphone.length() > 0 && doSend != null && doSend && senderName != null && senderName.length() > 0) {
			// TODO 发送短信
			System.out.println("发送短信啦！！！！！！！！！");
			prescription.setHasSended(true);
		} else {
			prescription.setHasSended(false);
		}
	}

	public Map getKnowledgeOfDisease(String code) {
		String url = "http://dlopen.xikang.com/openapi/evaluate/diseaseEncyclopedia/" + code;
		return knowledgeServlet.getRequest(url, null, Map.class);
	}

	public Map getEvaluation(XKEvaluateCondition condition) {
		String url = "http://dlopen.xikang.com/openapi/evaluate/diseasePrediction";
		return knowledgeServlet.postJsonRequest(url, condition, Map.class);
	}

}
