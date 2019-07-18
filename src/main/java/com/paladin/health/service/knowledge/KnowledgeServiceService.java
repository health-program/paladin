package com.paladin.health.service.knowledge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.health.core.knowledge.KnowledgeManageContainer;
import com.paladin.health.mapper.knowledge.KnowledgeServiceMapper;
import com.paladin.health.model.knowledge.KnowledgeService;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class KnowledgeServiceService extends ServiceSupport<KnowledgeService> {

	@Autowired
	private KnowledgeServiceMapper knowledgeServiceMapper;

	@Transactional
	public boolean startService(String id) {
		knowledgeServiceMapper.stopCurrentService();
		if (knowledgeServiceMapper.startService(id) > 0) {
			KnowledgeManageContainer.updateData();
			return true;
		} else {
			throw new BusinessException("启动服务失败");
		}
	}

	@Transactional
	public boolean stopService(String id) {
		if (knowledgeServiceMapper.stopService(id) > 0) {
			KnowledgeManageContainer.updateData();
			return true;
		}
		return false;
	}

}