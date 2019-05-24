package com.paladin.health.service.core.xk;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.time.DateTimeUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.service.core.xk.dto.ConfirmEvaluationDTO;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.core.xk.response.XKDiseaseKnowledge;
import com.paladin.health.service.core.xk.response.XKEvaluation;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.DiagnoseTargetFactorService;
import com.paladin.health.service.diagnose.DiagnoseTargetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

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

	@Value("${xk.knowledge.url}")
	private String knowledgeUrl;

	@Value("${xk.evaluation.url}")
	private String evaluationUrl;

	@Value("${xk.tips.url}")
	private String tipsUrl;

	/** 熙康疾病类型 */
	public static String CONSTANT_DISEASE_TYPE = "xk-disease-type";
	/** 熙康指标类型 */
	public static String CONSTANT_INDEX_TYPE = "xk-index-type";
	/** 熙康评估类型 */
	public static String CONSTANT_EVALUATE_TYPE = "xk-evaluate-type";

	@Transactional
	public List<XKDiseaseKnowledge> diagnoseDiseases(List<String> codes) {
		List<XKDiseaseKnowledge> diseaseKnowledge = null;
		if (codes != null && codes.size() > 0) {
			Set<String> diseaseSet = new HashSet<>(codes);
			diseaseKnowledge = new ArrayList<>();
			for (String disease : diseaseSet) {
				XKDiseaseKnowledge k = getKnowledge(disease);
				if (k != null) {
					diseaseKnowledge.add(k);
				}
			}
		}
		return diseaseKnowledge;
	}

	/**
	 * 简单评估
	 * 
	 * @param condition
	 * @param accessKey
	 * @return
	 */
	@Transactional
	public XKHealthPrescription doSimpleEvaluation(XKPeopleCondition condition, String accessKey) {

		List<XKEvaluation> evaluationResultList = null;
		XKEvaluateCondition evaluateCondition = condition.getCondition();

		// 进行评估预测
		if (evaluateCondition != null) {
			Map evaluationResult = getEvaluation(evaluateCondition);
			if (evaluationResult != null) {
				evaluationResultList = new ArrayList<>(10);
				for (ConstantsContainer.KeyValue kv : ConstantsContainer.getType(CONSTANT_EVALUATE_TYPE)) {

					String code = kv.getKey();
					String name = kv.getValue();

					Map single = (Map) evaluationResult.get(code);
					if (single == null)
						continue;
					String status = (String) single.get("status");

					// 如果不是状态0000则评估出错，可能是参数不全
					if (!"0000".equals(status)) {
						continue;
					}

					String riskResultStr = (String) single.get("result");
					if (riskResultStr == null || riskResultStr.length() == 0) {
						continue;
					}

					Map riskResult = null;
					try {
						riskResult = JsonUtil.parseJson(riskResultStr, Map.class);
					} catch (IOException e) {
						logger.error("评估[" + name + "]时非法解析json字符串格式：" + riskResultStr);
						continue;
					}

					if (riskResult == null) {
						continue;
					}

					String riskLevelName = (String) riskResult.get("riskLevel");

					// 熙康返回的是中文危险等级名称，这里对其归纳总结给出等级
					Integer riskLevel = XKEvaluation.nameLevelMap.get(riskLevelName);
					if (riskLevel == null) {
						logger.error("评估[" + name + "]时出现未知风险等级：" + riskLevelName);
						continue;
					}

					String suggest = (String) riskResult.get("suggest");
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

				}
			}
		}

		// 数据记录
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
				target.setUpdateBy(accessKey);
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
				target.setUpdateBy(accessKey);
				diagnoseTargetService.update(target);
			}

			// 更新评估得到的因素
			updateEvaluationFactor(evaluationResultList, identificationId, accessKey);

			String searchId = condition.getSearchId();
			List<String> messages = getMessage(target, evaluationResultList);

			// 保存目标病人本次请求和返回数据
			DiagnoseRecord record = new DiagnoseRecord();
			String diagnoseId = UUIDUtil.createUUID();
			record.setId(diagnoseId);
			record.setTargetId(identificationId);
			record.setTargetCondition(JsonUtil.getJson(condition));
			record.setPrescription(JsonUtil.getJson(evaluationResultList));
			record.setType(DiagnoseRecord.TYPE_XK);
			record.setCreateTime(now);
			record.setCreateBy(accessKey);
			record.setSearchId(searchId);
			record.setMessage(JsonUtil.getJson(messages));

			diagnoseRecordService.save(record);

			XKHealthPrescription result = new XKHealthPrescription();
			result.setId(diagnoseId);
			result.setEvaluation(evaluationResultList);
			result.setMessage(messages);

			return result;
		}

		return null;
	}

	/**
	 * 替换目标病人存在的危险因素（疾病，风险）
	 * 
	 * @param evaluationResultList
	 * @param identificationId
	 * @param accessKey
	 */
	private void updateEvaluationFactor(List<XKEvaluation> evaluationResultList, String identificationId, String accessKey) {
		Date now = new Date();
		List<DiagnoseTargetFactor> targetFactors = new ArrayList<>();

		if (evaluationResultList != null && evaluationResultList.size() > 0) {
			for (XKEvaluation evaluation : evaluationResultList) {
				targetFactors.add(new DiagnoseTargetFactor(identificationId, evaluation.getCode(), DiagnoseTargetFactor.FACTOR_TYPE_RISK,
						evaluation.getRiskLevel(), now, accessKey));
			}
		}

		diagnoseRecordFactorService.removeByTarget(identificationId);
		if (targetFactors.size() > 0) {
			diagnoseRecordFactorService.batchSave(targetFactors);
		}

	}

	/**
	 * 确认评估，并更新确认后评估内容
	 * 
	 * @param confirmEvaluations
	 * @param searchId
	 * @param accessKey
	 */
	public void confirmSimpleEvaluation(List<ConfirmEvaluationDTO> confirmEvaluations, String searchId, String accessKey) {
		DiagnoseRecord record = diagnoseRecordService.getRecordBySearchId(searchId, accessKey);
		String id = record.getId();
		String identificationId = record.getTargetId();

		List<XKEvaluation> evaluationResultList = null;
		if (confirmEvaluations != null && confirmEvaluations.size() > 0) {
			evaluationResultList = new ArrayList<>(confirmEvaluations.size());
			for (ConfirmEvaluationDTO confirmEvaluation : confirmEvaluations) {
				String code = confirmEvaluation.getCode();
				String name = XKEvaluation.codeNameMap.get(code);
				if (name == null) {
					throw new BusinessException("评估数据错误！");
				}

				Integer level = confirmEvaluation.getRiskLevel();
				String levelName = XKEvaluation.levelNameMap.get(level);
				if (levelName == null) {
					throw new BusinessException("评估数据错误！");
				}

				String suggest = confirmEvaluation.getSuggest();
				evaluationResultList.add(new XKEvaluation(code, name, level, levelName, suggest));
			}
		}

		updateEvaluationFactor(evaluationResultList, identificationId, accessKey);

		String correctPrescription = "";
		if (evaluationResultList != null) {
			correctPrescription = JsonUtil.getJson(evaluationResultList);
		}

		diagnoseRecordService.updateCorrectPrescription(id, correctPrescription);
	}
	
	
	/**
	 * 根据居民情况获取短信
	 * 
	 * @param target
	 * @param evaluationResultList
	 * @return
	 */
	private List<String> getMessage(DiagnoseTarget target, List<XKEvaluation> evaluationResultList) {
		if (evaluationResultList != null) {
			for (XKEvaluation eval : evaluationResultList) {
				String code = eval.getCode();
				int level = eval.getRiskLevel();

				if (level > XKEvaluation.LEVEL_MIDDLE) {
					if (XKEvaluation.CODE_DIABETES.equals(code)) {
						return getTips("diabetes");
					} else if (XKEvaluation.CODE_HYPERTENSION.equals(code)) {
						return getTips("hypertension");
					}
				}
			}
		}

		if (target != null) {
			Date birthday = target.getBirthday();
			if (birthday != null) {
				if (DateTimeUtil.getAge(birthday) > 60) {
					return getTips("aged");
				}
			}
		}

		return getTips("festival");
	}

	/**
	 * 获取知识
	 * 
	 * @param code
	 * @return
	 */
	public XKDiseaseKnowledge getKnowledge(String code) {
		String url = knowledgeUrl + code;
		String diseaseName = ConstantsContainer.getTypeValue(CONSTANT_DISEASE_TYPE, code);
		if (diseaseName != null && diseaseName.length() > 0) {
			List knowledge = knowledgeServlet.getRequest(url, null, List.class);
			if (knowledge != null) {
				return new XKDiseaseKnowledge(code, diseaseName, XKDiseaseKnowledge.TYPE_DISEASE, knowledge);
			}
		} else {
			diseaseName = ConstantsContainer.getTypeValue(CONSTANT_INDEX_TYPE, code);
			if (diseaseName != null && diseaseName.length() > 0) {
				List knowledge = knowledgeServlet.getRequest(url, null, List.class);
				if (knowledge != null) {
					return new XKDiseaseKnowledge(code, diseaseName, XKDiseaseKnowledge.TYPE_INDE, knowledge);
				}
			}
		}

		return null;
	}

	/**
	 * 获取评估
	 * 
	 * @param condition
	 * @return
	 */
	public Map getEvaluation(XKEvaluateCondition condition) {
		return knowledgeServlet.postJsonRequest(evaluationUrl, condition, Map.class);
	}

	/**
	 * 获取TIPS
	 * 
	 * @param typeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getTips(String typeCode) {
		String url = tipsUrl + typeCode;
		Map result = knowledgeServlet.getRequest(url, null, Map.class);

		String status = (String) result.get("status");
		if (!"0000".equals(status)) {
			return null;
		}
		return (List<String>) result.get("result");
	}

}
