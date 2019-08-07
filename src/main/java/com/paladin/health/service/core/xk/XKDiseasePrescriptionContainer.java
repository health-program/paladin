package com.paladin.health.service.core.xk;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.VersionContainer;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.health.model.knowledge.KnowledgeBase;
import com.paladin.health.model.knowledge.KnowledgeBaseDetail;
import com.paladin.health.service.core.xk.response.XKMessage;
import com.paladin.health.service.core.xk.response.XKPrescription;
import com.paladin.health.service.knowledge.KnowledgeBaseDetailService;
import com.paladin.health.service.knowledge.KnowledgeBaseService;

@Component
public class XKDiseasePrescriptionContainer implements VersionContainer {

	@Autowired
	private KnowledgeBaseService knowledgeBaseService;
	@Autowired
	private KnowledgeBaseDetailService knowledgeBaseDetailService;

	private static Map<String, DiseasePrescriptionPackage> nameMap = new HashMap<>();
	private static Map<String, DiseasePrescriptionPackage> codeMap = new HashMap<>();

	public void init() {
		Map<String, DiseasePrescriptionPackage> nameMap = new HashMap<>();
		Map<String, DiseasePrescriptionPackage> codeMap = new HashMap<>();

		List<KnowledgeBase> kbs = knowledgeBaseService.findAll();
		for (KnowledgeBase kb : kbs) {

			String name = kb.getName();
			String code = kb.getCode();
			List<KnowledgeBaseDetail> details = knowledgeBaseDetailService
					.searchAll(new Condition(KnowledgeBaseDetail.COLUMN_KNOWLEDGE_ID, QueryType.EQUAL, kb.getId()));

			if (details != null && details.size() == 1) {
				KnowledgeBaseDetail detail = details.get(0);

				String lifestyle = detail.getLifestyle(); // 生活方式
				String dietaryAdvice = detail.getDietaryAdvice(); // 饮食建议
				String dietaryShouldEat = detail.getDietaryShouldEat(); // 饮食宜吃
				String dietaryAvoidEat = detail.getDietaryAvoidEat(); // 饮食忌吃
				String sportsAdvice = detail.getSportsAdvice(); // 运动建议
				String sportsShouldDo = detail.getSportsShouldDo(); // 运动宜做
				String sportsAvoidDo = detail.getSportsAvoidDo(); // 运动忌做

				List<Item> life = parseJson(lifestyle);
				List<Item> dietary = parseJson(dietaryAdvice);
				List<Item> dietaryShould = parseJson(dietaryShouldEat);
				List<Item> dietaryAvoid = parseJson(dietaryAvoidEat);
				List<Item> sports = parseJson(sportsAdvice);
				List<Item> sportsShould = parseJson(sportsShouldDo);
				List<Item> sportsAvoid = parseJson(sportsAvoidDo);

				String lifeOverview = getOverview(life);
				String dietaryOverview = getOverview(dietary);
				String sportsOverview = getOverview(sports);

				String overview = lifeOverview + dietaryOverview + sportsOverview;

				StringBuilder sb = new StringBuilder();

				int index = 1;
				index = contactSuggestion(sb, "生活方式", index, life, lifeOverview);
				index = contactSuggestion(sb, "饮食建议", index, dietary, dietaryOverview);
				index = contactSuggestion(sb, "饮食宜吃", index, dietaryShould, "");
				index = contactSuggestion(sb, "饮食忌吃", index, dietaryAvoid, "");
				index = contactSuggestion(sb, "运动建议", index, sports, sportsOverview);
				index = contactSuggestion(sb, "运动宜做", index, sportsShould, "");
				index = contactSuggestion(sb, "运动忌做", index, sportsAvoid, "");

				String suggestion = sb.toString();
				if (suggestion != null && suggestion.length() > 0) {
					XKMessage message = new XKMessage(kb.getName(), overview);
					XKPrescription prescription = new XKPrescription(name, code, XKPrescription.TYPE_DISEASE, XKPrescription.LEVEL_HAS, suggestion);

					DiseasePrescriptionPackage packagz = new DiseasePrescriptionPackage();
					packagz.setMessage(message);
					packagz.setPrescription(prescription);

					nameMap.put(name, packagz);
					codeMap.put(code, packagz);
				}
			}
		}

		XKDiseasePrescriptionContainer.nameMap = nameMap;
		XKDiseasePrescriptionContainer.codeMap = codeMap;
	}

	private int contactSuggestion(StringBuilder sb, String title, int index, List<Item> items, String overview) {
		String content = getContent(items);
		if (overview.length() > 0 || content.length() > 0) {
			sb.append(index).append(".").append(title).append("：").append(overview).append(content).append("@#");
			return index + 1;
		}
		return index;
	}

	private String getContent(List<Item> items) {
		if (items != null && items.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int index = 1;
			for (Item item : items) {
				String key = item.getKey();
				if (key != null) {
					if (!"综述".equals(item.getKey())) {
						String val = item.getValue();
						if (val != null) {
							val = val.trim();
							if (val.length() > 0) {
								sb.append(index).append("）").append(key).append("：").append(val);
								index++;
							}
						}
					}
				}
			}
			return sb.toString();
		}
		return "";
	}

	private String getOverview(List<Item> items) {
		if (items != null) {
			for (Item item: items) {
					if ("综述".equals(item.getKey())) {
						String val = item.getValue();
						if (val != null) {
							val = val.trim();
							if (val.length() > 0) {
								return val;
							}
						}
					}
				
			}
		}
		return "";
	}

	private List<Item> parseJson(String json) {
		if (json != null && json.length() > 0) {
			try {
				return JsonUtil.parseJsonList(json, Item.class);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static DiseasePrescriptionPackage getPrescriptionPackage(String name, String code) {
		DiseasePrescriptionPackage packagz = nameMap.get(name);
		return packagz == null ?codeMap.get(code): packagz;
	}

	public static class Item {
		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public static class DiseasePrescriptionPackage {
		private XKPrescription prescription;
		private XKMessage message;

		public XKPrescription getPrescription() {
			return prescription;
		}

		public void setPrescription(XKPrescription prescription) {
			this.prescription = prescription;
		}

		public XKMessage getMessage() {
			return message;
		}

		public void setMessage(XKMessage message) {
			this.message = message;
		}
	}

	@Override
	public String getId() {
		return "disease_prescription_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		init();
		return true;
	}

}
