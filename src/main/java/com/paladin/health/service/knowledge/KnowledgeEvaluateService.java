package com.paladin.health.service.knowledge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.health.core.knowledge.KnowledgeManageContainer;
import com.paladin.health.mapper.knowledge.KnowledgeEvaluateMapper;
import com.paladin.health.model.knowledge.KnowledgeEvaluate;
import com.paladin.framework.core.ServiceSupport;

@Service
public class KnowledgeEvaluateService extends ServiceSupport<KnowledgeEvaluate> {

	@Autowired
	private KnowledgeEvaluateMapper knowledgeEvaluateMapper;

	@Transactional
	public boolean startEvaluate(String id) {
		if (knowledgeEvaluateMapper.startEvaluate(id) > 0) {
			KnowledgeManageContainer.updateData();
			return true;
		}
		return false;
	}

	@Transactional
	public boolean stopEvaluate(String id) {
		if (knowledgeEvaluateMapper.stopEvaluate(id) > 0) {
			KnowledgeManageContainer.updateData();
			return true;
		}
		return false;
	}
}