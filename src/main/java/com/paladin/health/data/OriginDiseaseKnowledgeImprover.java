package com.paladin.health.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.health.model.origin.OriginDiseaseKnowledge;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeContentService;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Component
public class OriginDiseaseKnowledgeImprover {

	private static Logger logger = LoggerFactory.getLogger(OriginDiseaseKnowledgeImprover.class);

	@Autowired
	OriginDiseaseNameService diseaseNameService;
	@Autowired
	OriginDiseaseKnowledgeService diseaseKnowledgeService;
	@Autowired
	OriginDiseaseKnowledgeContentService diseaseKnowledgeContentService;


	volatile int threadCount;

	public void improve() {

		final List<OriginDiseaseName> names = diseaseNameService.findAllDiseaseName();

		int count = names.size();
		int i = 0;
		int size = 1000;
		boolean goon = true;

		logger.info("开始修复疾病数据");

		while (goon) {

			if (i > count) {
				break;
			}

			int end = i + size;
			if (end > count) {
				end = count;
				goon = false;
			}

			final int s = i;
			final int e = end - 1;

			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("开始修复第" + s + "条到第" + e + "条疾病知识");

					for (int x = s; x <= e; x++) {
						OriginDiseaseName name = names.get(x);
						try {
							improveDisease(name);
						} catch (Exception e) {
							logger.error("修复疾病[" + name.getNameKey() + "]出错", e);
						}
					}

					logger.info("成功修复第" + s + "条到第" + e + "条疾病知识");
					endThread();
				}
			});

			th.start();
			threadCount++;

			i = end;
		}
	}

	private void endThread() {
		if (--threadCount == 0) {
			logger.info("结束修复疾病知识数据");
		}
	}

	String[] yyzlFirstTitles = { "辨证论治", "一般治疗", "药物治疗", "手术治疗", "其他疗法" ,"中医验方", "其他"};

	public void improveDisease(OriginDiseaseName diseaseName) {

		String disease = diseaseName.getNameKey();

		List<OriginDiseaseKnowledge> originDiseaseKnowledges = diseaseKnowledgeService.findAllDiseaseKnowledge(disease);

		int id = 0;
		for (OriginDiseaseKnowledge know : originDiseaseKnowledges) {
			if (know.getCategoryKey().equals("yyzl") && know.getParentId() == null) {
				id = know.getId();
			}
		}

		List<OriginDiseaseKnowledge> firstKnowList = new ArrayList<>();

		for (OriginDiseaseKnowledge know : originDiseaseKnowledges) {
			if (know.getParentId() != null && know.getParentId() == id) {
				firstKnowList.add(know);
			}
		}
	
		HashSet<String> errorStarts = new HashSet<>();
		for (OriginDiseaseKnowledge know : firstKnowList) {
			String name = know.getName();
			for (String t : yyzlFirstTitles) {
				if (name.length() > t.length() && name.endsWith(t)) {
					errorStarts.add(name.substring(0, name.length() - t.length()));
				}
			}
		}

		if (errorStarts.size() > 1) {
			logger.info("存在两个错误开头：" + diseaseName);
		} else if (errorStarts.size() == 1) {		
			String errorStart = errorStarts.iterator().next();
			for (OriginDiseaseKnowledge know : originDiseaseKnowledges) {
				String name = know.getName();
				if (name.length() > errorStart.length() && name.startsWith(errorStart)) {					
					know.setName(name.substring(errorStart.length()));
					diseaseKnowledgeService.update(know);
				}
			}			
		}

	}

}
