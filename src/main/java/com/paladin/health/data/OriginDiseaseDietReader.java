package com.paladin.health.data;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.health.data.parser.DietPageParser;
import com.paladin.health.data.parser.DietPageParser.DietElement;
import com.paladin.health.data.parser.DietPageParser.DietUnit;
import com.paladin.health.mapper.origin.OriginDiseaseDietMapper;
import com.paladin.health.model.origin.OriginDiseaseDiet;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Component
public class OriginDiseaseDietReader {

	private static Logger logger = LoggerFactory.getLogger(OriginDiseaseDietReader.class);

	static DietPageParser dietPageParser = new DietPageParser();

	@Autowired
	OriginDiseaseDietMapper diseaseDietMapper;

	@Autowired
	OriginDiseaseNameService diseaseNameService;

	volatile int threadCount;

	public void readDiseaseDiet() {

		final List<OriginDiseaseName> names = diseaseNameService
				.searchAll(new GeneralCriteriaBuilder.Condition(OriginDiseaseName.COLUMN_FIELD_TYPE, QueryType.EQUAL, 1));

		int count = names.size();
		int i = 0;
		int size = 1000;
		boolean goon = true;

		logger.info("开始读取疾病饮食建议数据");

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
			final int base = (threadCount + 1) * 10000;

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {

					logger.info("开始读取第" + s + "条到第" + e + "条饮食建议");
					int id = base;

					for (int x = s; x <= e; x++) {

						OriginDiseaseName name = names.get(x);
						String key = name.getNameKey();
						List<DietElement> diets = null;

						try {
							try {
								diets = dietPageParser.parse(key);
							} catch (IOException e1) {
								logger.error("从网络获取页面数据异常[disease_key:" + key + "]，尝试再次读取", e1);
								try {
									diets = dietPageParser.parse(key);
								} catch (IOException e2) {
									logger.error("从网络获取页面数据异常[disease_key:" + key + "]，放弃读取", e1);
								}
							}
						} catch (Exception e) {
							logger.error("解析页面数据异常[disease_key:" + key + "]", e);
						}

						if (diets == null) {
							continue;
						}

						for (DietElement diet : diets) {

							boolean b = diet.isSuitable();
							String content = diet.getContent();

							if (content != null && content.length() > 0) {
								OriginDiseaseDiet od = new OriginDiseaseDiet();
								od.setId(id++);
								od.setDiseaseKey(key);
								od.setSummary(content);
								od.setType(b ? OriginDiseaseDiet.TYPE_SUITABLE_SUMMARY : OriginDiseaseDiet.TYPE_TABOO_SUMMARY);
								diseaseDietMapper.insert(od);
							}

							for (DietUnit unit : diet.getDietUnits()) {
								OriginDiseaseDiet od = new OriginDiseaseDiet();
								od.setId(id++);
								od.setDiseaseKey(key);
								od.setType(b ? OriginDiseaseDiet.TYPE_SUITABLE : OriginDiseaseDiet.TYPE_TABOO);
								od.setFood(unit.getFood());
								od.setReason(unit.getReason());
								od.setSuggestion(unit.getSuggestion());
								diseaseDietMapper.insert(od);
							}
						}
					}

					logger.info("成功读取第" + s + "条到第" + e + "条疾病饮食建议");
					endThread();
				}
			});

			threadCount++;
			t.start();

			i = end;
		}
	}

	private void endThread() {
		if (--threadCount == 0) {
			logger.info("结束读取饮食建议数据");
		}
	}
}
