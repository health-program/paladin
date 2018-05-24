package com.paladin.health.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.utils.StringUtil;
import com.paladin.framework.utils.reflect.ReflectUtil;
import com.paladin.health.data.parser.DiseaseNameParser;
import com.paladin.health.data.parser.DiseaseNameParser.DiseaseName;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.model.origin.OriginDiseaseSummary;
import com.paladin.health.model.origin.OriginDiseaseTag;
import com.paladin.health.service.origin.OriginDiseaseNameService;
import com.paladin.health.service.origin.OriginDiseaseSummaryService;
import com.paladin.health.service.origin.OriginDiseaseTagService;

@Component
public class DiseaseBaseAnalysis {

	private static Logger logger = LoggerFactory.getLogger(DiseaseBaseAnalysis.class);

	@Autowired
	OriginDiseaseSummaryService diseaseSummaryService;
	@Autowired
	OriginDiseaseTagService diseaseTagService;
	@Autowired
	OriginDiseaseNameService diseaseNameService;

	static DiseaseNameParser diseaseNameParser = new DiseaseNameParser();

	public void analyzeCategory() {

		Object[][] params = { { "erke", 107, "儿童" }, { "chanke", 15, "孕妇" }, { "fuke", 39, "女性" }, { "nanke", 12, "男性" }, { "laonianke", 22, "老年人" } };

		for (Object[] param : params) {
			String type = (String) param[0];
			int pageSize = (int) param[1];
			String categoryName = (String) param[2];

			List<DiseaseName> result = new ArrayList<>();
			for (int i = 0; i < pageSize; i++) {
				try {
					result.addAll(diseaseNameParser.parseDisease(type, i));
				} catch (IOException e) {
					logger.error("解析类型疾病错误[type:" + type + ",page:" + i + "]", e);
					continue;
				}
			}

			for (DiseaseName dname : result) {
				String name = dname.getName();
				String pinyin = dname.getPinyin();

				OriginDiseaseName odname = diseaseNameService.getDiseaseName(pinyin);
				if (odname != null && odname.getName().equals(name)) {

					OriginDiseaseTag tag = new OriginDiseaseTag();
					tag.setDiseaseKey(pinyin);
					tag.setType(OriginDiseaseTag.TYPE_CATEGORY);
					tag.setDiseaseName(name);
					tag.setName(categoryName);

					diseaseTagService.save(tag);
				} else {
					logger.error("找不到相应疾病[name:" + name + ",key:" + pinyin + "]");
				}
			}
		}
	}

	public void analyzeTag() {
		List<OriginDiseaseSummary> summaries = diseaseSummaryService.findAll();
		analyzeTag(summaries);
	}

	public void analyzeTag(List<OriginDiseaseSummary> summaries) {
		for (OriginDiseaseSummary summary : summaries) {
			String disease = summary.getDiseaseKey();
			String diseaseName = diseaseNameService.getDiseaseName(disease).getName();

			analyzeAndSave(disease, diseaseName, summary.getBfjb(), OriginDiseaseTag.TYPE_BFJB, true);
			analyzeAndSave(disease, diseaseName, summary.getSfsyyb(), OriginDiseaseTag.TYPE_SFSYYB, false);
			analyzeAndSave(disease, diseaseName, summary.getBm(), OriginDiseaseTag.TYPE_BM, true);
			analyzeAndSave(disease, diseaseName, summary.getFbbw(), OriginDiseaseTag.TYPE_FBBW, true);
			analyzeAndSave(disease, diseaseName, summary.getCrx(), OriginDiseaseTag.TYPE_CRX, false);
			analyzeAndSave(disease, diseaseName, summary.getXgzz(), OriginDiseaseTag.TYPE_XGZZ, true);
			analyzeAndSave(disease, diseaseName, summary.getSfyc(), OriginDiseaseTag.TYPE_SFYC, false);
			analyzeAndSave(disease, diseaseName, summary.getCbtj(), OriginDiseaseTag.TYPE_CBTJ, true);
			analyzeAndSave(disease, diseaseName, summary.getCrbzl(), OriginDiseaseTag.TYPE_CRBZL, false);
		}
	}

	private void analyzeAndSave(String disease, String diseaseName, String content, String type, boolean needSplit) {

		if (content == null || content.length() == 0) {
			return;
		}

		content = StringUtil.strongTrim(content);

		try {
			HashSet<String> names = new HashSet<>();
			int count = 1;
			if (needSplit) {
				String[] ss = content.split(",|，");
				count = ss.length;
				for (String s : ss) {
					names.add(s);
				}
			} else {
				names.add(content);
			}

			if (count != names.size()) {
				OriginDiseaseSummary summary = new OriginDiseaseSummary();
				summary.setDiseaseKey(disease);
				String param = "";
				for (String s : names) {
					param += s + ",";
				}
				param = param.substring(0, param.length() - 1);
				ReflectUtil.getSetMethod(type.toLowerCase(), OriginDiseaseSummary.class, String.class).invoke(summary, param);
				logger.info("更新疾病[" + disease + "]类型[" + type + "]内容：" + content + " ==>" + param);
				diseaseSummaryService.updateSelective(summary);
			}

			OriginDiseaseTag tag = new OriginDiseaseTag();
			tag.setDiseaseKey(disease);
			tag.setType(type);
			tag.setDiseaseName(diseaseName);

			for (String name : names) {
				if (OriginDiseaseTag.TYPE_XGZZ.equals(name)) {
					if (!checkSymptom(name)) {
						logger.error("找不到对应的症状[" + name + "]");
						continue;
					}
				} else if (OriginDiseaseTag.TYPE_BFJB.equals(name)) {
					if (!checkDisease(name)) {
						logger.error("找不到对应的并发疾病[" + name + "]");
						continue;
					}
				}

				tag.setName(name);
				diseaseTagService.save(tag);
			}
		} catch (Exception e) {
			logger.error("分析疾病[" + disease + "]类型[" + type + "]异常，数据内容：[" + content + "]", e);
		}
	}

	private boolean checkSymptom(String name) {
		return diseaseNameService.getSymptomName(name) != null;
	}

	private boolean checkDisease(String name) {
		return diseaseNameService.getDiseaseName(name) != null;
	}
}
