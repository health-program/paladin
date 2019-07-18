package com.paladin.health.core.knowledge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.core.VersionContainer;
import com.paladin.framework.core.VersionContainerManager;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.health.model.knowledge.KnowledgeEvaluate;
import com.paladin.health.model.knowledge.KnowledgeService;
import com.paladin.health.service.core.HealthPrescriptionService;
import com.paladin.health.service.core.xk.XKPeopleCondition;
import com.paladin.health.service.core.xk.dto.ConfirmEvaluationDTO;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.core.xk.response.XKDiseaseKnowledge;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;
import com.paladin.health.service.knowledge.KnowledgeEvaluateService;
import com.paladin.health.service.knowledge.KnowledgeServiceService;

@Component
public class KnowledgeManageContainer implements VersionContainer {

	@Autowired
	private KnowledgeServiceService knowledgeServiceService;
	@Autowired
	private KnowledgeEvaluateService knowledgeEvaluateService;

	private Map<String, KnowledgeServiceBean> serviceMap = new HashMap<>();
	private List<KnowledgeServiceBean> serviceList = new ArrayList<>();

	private Map<String, HealthPrescriptionService> serviceImplMap;
	private KnowledgeServiceBean currentBean;

	private static KnowledgeManageContainer container;
	/**
	 * 熙康服务code，该为缺省code
	 */
	public final static String SERVICE_CODE_XK = "XK";

	public void init() {
		if (serviceImplMap == null) {
			synchronized (KnowledgeManageContainer.class) {
				if (serviceImplMap == null) {
					Map<String, HealthPrescriptionService> serviceImpls = SpringBeanHelper.getBeansByType(HealthPrescriptionService.class);

					Map<String, HealthPrescriptionService> serviceImplMap = new HashMap<>();
					for (HealthPrescriptionService serviceImpl : serviceImpls.values()) {
						serviceImplMap.put(serviceImpl.getKnowledgeServiceCode(), serviceImpl);
					}
					this.serviceImplMap = serviceImplMap;
				}
			}
		}

		Map<String, KnowledgeServiceBean> serviceMap = new HashMap<>();
		List<KnowledgeServiceBean> serviceList = new ArrayList<>();

		List<KnowledgeService> services = knowledgeServiceService.findAll();
		KnowledgeServiceBean currentBean = null;
		for (KnowledgeService service : services) {
			KnowledgeServiceBean bean = new KnowledgeServiceBean();
			bean.id = service.getServiceCode();
			bean.service = service;
			bean.enabled = int2boolean(service.getEnabled());
			bean.serviceImpl = serviceImplMap.get(bean.id);

			if (bean.enabled) {
				currentBean = bean;
			}

			serviceMap.put(bean.id, bean);
			serviceList.add(bean);
		}

		List<KnowledgeEvaluate> evaluates = knowledgeEvaluateService.findAll();
		for (KnowledgeEvaluate evaluate : evaluates) {
			String serviceId = evaluate.getServiceId();
			KnowledgeServiceBean bean = serviceMap.get(serviceId);
			if (bean != null) {
				EvaluateConfig config = new EvaluateConfig();
				config.code = evaluate.getCode();
				config.name = evaluate.getName();
				config.evaluate = evaluate;
				config.enabled = int2boolean(evaluate.getEnabled());

				bean.evaluates.add(config);
			}
		}

		for (KnowledgeServiceBean bean : serviceList) {
			if (bean.serviceImpl != null) {
				bean.serviceImpl.setKnowledgeServiceBean(bean);
			}
		}

		this.serviceList = Collections.unmodifiableList(serviceList);
		this.serviceMap = Collections.unmodifiableMap(serviceMap);
		this.currentBean = currentBean;

		container = this;
	}

	private boolean int2boolean(Integer i) {
		return i != null && i == 1;
	}

	public static HealthPrescriptionService getCurrentHealthPrescriptionService() {
		if (container.currentBean != null && container.currentBean.serviceImpl != null) {
			return container.currentBean.serviceImpl;
		}
		return emptyServiceImpl;
	}

	public static List<KnowledgeServiceBean> getServiceBeans() {
		return container.serviceList;
	}

	public static KnowledgeServiceBean getServiceBean(String serviceCode) {
		return container.serviceMap.get(serviceCode);
	}

	@Override
	public String getId() {
		return "knowledge_manage_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		init();
		return true;
	}

	public static void updateData() {
		VersionContainerManager.versionChanged(container.getId());
	}

	private static HealthPrescriptionService emptyServiceImpl = new HealthPrescriptionService() {
		@Override
		public String getKnowledgeServiceCode() {
			return null;
		}

		@Override
		public XKHealthPrescription doSimpleEvaluation(XKPeopleCondition condition, String accessKey) {
			return null;
		}

		public void confirmSimpleEvaluation(ConfirmEvaluationDTO confirmEvaluation, String searchId, String accessKey) {
			throw new BusinessException("无法调取相关服务");
		}

		@Override
		public String confirmSimpleEvaluationAndCreatePDF(ConfirmEvaluationDTO confirmEvaluation, String searchId, String accessKey, boolean createPDF) {
			return null;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Map getEvaluation(XKEvaluateCondition condition) {
			return null;
		}

		@Override
		public List<String> getTips(String typeCode) {
			return null;
		}

		@Override
		public XKDiseaseKnowledge getKnowledge(String code) {
			return null;
		}

		@Override
		public void setKnowledgeServiceBean(KnowledgeServiceBean bean) {
		}
	};

	public static class KnowledgeServiceBean {
		private String id;
		private boolean enabled;
		private KnowledgeService service;
		private List<EvaluateConfig> evaluates = new ArrayList<>();
		private HealthPrescriptionService serviceImpl;

		public String getId() {
			return id;
		}

		public KnowledgeService getService() {
			return service;
		}

		public List<EvaluateConfig> getEvaluates() {
			return evaluates;
		}

		public HealthPrescriptionService getServiceImpl() {
			return serviceImpl;
		}

		public boolean isEnabled() {
			return enabled;
		}
	}

	public static class EvaluateConfig {
		private KnowledgeEvaluate evaluate;
		private String code;
		private String name;
		private boolean enabled;

		public KnowledgeEvaluate getEvaluate() {
			return evaluate;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public boolean isEnabled() {
			return enabled;
		}

	}

}
