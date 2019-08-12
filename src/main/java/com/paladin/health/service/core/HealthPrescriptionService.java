package com.paladin.health.service.core;

import com.paladin.health.core.knowledge.KnowledgeManageContainer.KnowledgeServiceBean;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import com.paladin.health.service.core.xk.dto.ConfirmPrescriptionDTO;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.core.xk.response.XKDiseaseKnowledge;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;

import java.util.List;
import java.util.Map;

/**
 * 健教处方接口，按照熙康标准规则制定（熙康提供的接口不是很标准，如果有其他知识库服务，并不能按照一样的标准接入，这里只是应付）
 * @author TontoZhou
 * @since 2019年7月17日
 */
public interface HealthPrescriptionService {

	/**
	 * 获取知识库服务CODE
	 * 
	 * @return
	 */
	public String getKnowledgeServiceCode();

	/**
	 * 获取知识
	 * 
	 * @param code
	 * @return
	 */
	public XKDiseaseKnowledge getKnowledge(String code);

	/**
	 * 简单评估
	 * 
	 * @param condition
	 * @param accessKey
	 * @return
	 */
	public XKHealthPrescription doSimpleEvaluation(XKPeopleCondition condition, String accessKey);

	/**
	 * 确认评估，并更新确认后评估内容
	 * 
	 * @param confirmEvaluations
	 * @param searchId
	 * @param accessKey
	 */
	public default void confirmSimpleEvaluation(ConfirmPrescriptionDTO confirmEvaluation, String searchId, String accessKey) {
		confirmSimpleEvaluationAndCreatePDFOrDoc(confirmEvaluation, searchId, accessKey, XKHealthPrescriptionService.CREATE_DO_NOTHING);
	}

	/**
	 * 确认评估，并更新确认后评估内容，如果需要创建PDF则创建PDF文件
	 * 
	 * @param confirmEvaluation
	 * @param searchId
	 * @param accessKey
	 * @param createPDF
	 */
	public String confirmSimpleEvaluationAndCreatePDFOrDoc(ConfirmPrescriptionDTO confirmEvaluation, String searchId, String accessKey, int createType);

	/**
	 * 获取评估
	 * 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getEvaluation(XKEvaluateCondition condition);

	/**
	 * 获取TIPS
	 * 
	 * @param typeCode
	 * @return
	 */
	public List<String> getTips(String typeCode);
	
	/**
	 * 设置bean
	 */
	public void setKnowledgeServiceBean(KnowledgeServiceBean bean);
	
	

}
