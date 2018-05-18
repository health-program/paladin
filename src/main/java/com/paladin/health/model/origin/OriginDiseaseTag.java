package com.paladin.health.model.origin;

import javax.persistence.Id;

public class OriginDiseaseTag {
	
	/**是否属于医保*/
	public final static String TYPE_SFSYYB = "SFSYYB";
	/**别名*/
	public final static String TYPE_BM = "BM";
	/**发病部位*/
	public final static String TYPE_FBBW = "FBBW";
	/**传染性*/
	public final static String TYPE_CRX = "CRX";
	/**相关症状*/
	public final static String TYPE_XGZZ = "XGZZ";
	/**并发疾病*/
	public final static String TYPE_BFJB = "BFJB";
	/**是否遗传*/
	public final static String TYPE_SFYC = "SFYC";
	/**传染病种类*/
	public final static String TYPE_CRBZL = "CRBZL";
	/**传播途径*/
	public final static String TYPE_CBTJ = "CBTJ";
	
	public static final String COLUMN_FIELD_DISEASE_KEY = "diseaseKey";

	@Id
	private String name;

	@Id
	private String diseaseKey;

	@Id
	private String type;
	
	private String diseaseName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiseaseKey() {
		return diseaseKey;
	}

	public void setDiseaseKey(String diseaseKey) {
		this.diseaseKey = diseaseKey;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}


}