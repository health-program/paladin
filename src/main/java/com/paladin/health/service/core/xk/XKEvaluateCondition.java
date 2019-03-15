package com.paladin.health.service.core.xk;

public class XKEvaluateCondition {

	// 性别 值域：男(1)、女(0)
	private String sex;
	// 年龄 单位：岁
	private String age;
	// 体重 单位：KG
	private String weight;
	// 身高 单位：CM
	private String height;
	// 高压（收缩压）
	private String sbp;
	// 低压（舒张压）
	private String dbp;
	// 餐后血糖 单位：mmol/l
	private String pbg;
	// 空腹血糖 单位：mmol/l
	private String fbc;
	// 腰围 单位：CM
	private String waistline;
	// 是否患糖尿病 值域：1:是 0:否
	private String diabetes;
	// 是否发作过中风或脑缺血 值域：1:是 0:否
	private String strokeOrTia;
	// 空腹血清总胆固醇 单位：mmol/l
	private String tc_mmol;

	// 是否吸烟 值域：1:是 0:否
	private String smoke;
	// 低密度脂蛋白 单位：mmol/l
	private String idl;
	// 高密度脂蛋白 单位：mmol/l
	private String hdl;
	// CVD家族史 值域：1:是 0:否
	private String family_cvd;
	// 每天是否锻炼30分钟以上 值域：1:是 0:否
	private String sports;
	// 每天是否吃水果或蔬菜 值域：1:是 0:否
	private String vegOrFruits;
	// 是否服用降压药 值域：1:是 0:否
	private String dyazide;
	// 是否高血糖 值域：1:是 0:否
	private String hyperglycemia;
	// 是否有糖尿病家族史 值域：1:是 0:否
	private String family_diabetes;
	// 是否连续6个月以上服用激素类药品 值域：1:是 0:否
	private String hormone;
	// 45岁前是否绝经 值域：1:是 0:否
	private String menopause;
	// 是否饮酒 值域：1:是 0:否
	private String drinking;

	// 腹泻、腹痛或大便习惯 值域：1:是 0:否
	private String diarrhea;
	// 不经常晒太阳 值域：1:是 0:否
	private String rarelyBask;
	// 不经常参加运动锻炼 值域：1:是 0:否
	private String rarelysports;
	// 是否有质疏松家族史 值域：1:是 0:否
	private String family_osteoporosis;
	// 是否有高血压家族史 值域：0:父母皆无；1:父或母有 ；2:父母皆有
	private String family_hypertension;
	
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getSbp() {
		return sbp;
	}
	public void setSbp(String sbp) {
		this.sbp = sbp;
	}
	public String getDbp() {
		return dbp;
	}
	public void setDbp(String dbp) {
		this.dbp = dbp;
	}
	public String getPbg() {
		return pbg;
	}
	public void setPbg(String pbg) {
		this.pbg = pbg;
	}
	public String getFbc() {
		return fbc;
	}
	public void setFbc(String fbc) {
		this.fbc = fbc;
	}
	public String getWaistline() {
		return waistline;
	}
	public void setWaistline(String waistline) {
		this.waistline = waistline;
	}
	public String getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}
	public String getStrokeOrTia() {
		return strokeOrTia;
	}
	public void setStrokeOrTia(String strokeOrTia) {
		this.strokeOrTia = strokeOrTia;
	}
	public String getTc_mmol() {
		return tc_mmol;
	}
	public void setTc_mmol(String tc_mmol) {
		this.tc_mmol = tc_mmol;
	}
	public String getSmoke() {
		return smoke;
	}
	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}
	public String getIdl() {
		return idl;
	}
	public void setIdl(String idl) {
		this.idl = idl;
	}
	public String getHdl() {
		return hdl;
	}
	public void setHdl(String hdl) {
		this.hdl = hdl;
	}
	public String getFamily_cvd() {
		return family_cvd;
	}
	public void setFamily_cvd(String family_cvd) {
		this.family_cvd = family_cvd;
	}
	public String getSports() {
		return sports;
	}
	public void setSports(String sports) {
		this.sports = sports;
	}
	public String getVegOrFruits() {
		return vegOrFruits;
	}
	public void setVegOrFruits(String vegOrFruits) {
		this.vegOrFruits = vegOrFruits;
	}
	public String getDyazide() {
		return dyazide;
	}
	public void setDyazide(String dyazide) {
		this.dyazide = dyazide;
	}
	public String getHyperglycemia() {
		return hyperglycemia;
	}
	public void setHyperglycemia(String hyperglycemia) {
		this.hyperglycemia = hyperglycemia;
	}
	public String getFamily_diabetes() {
		return family_diabetes;
	}
	public void setFamily_diabetes(String family_diabetes) {
		this.family_diabetes = family_diabetes;
	}
	public String getHormone() {
		return hormone;
	}
	public void setHormone(String hormone) {
		this.hormone = hormone;
	}
	public String getMenopause() {
		return menopause;
	}
	public void setMenopause(String menopause) {
		this.menopause = menopause;
	}
	public String getDrinking() {
		return drinking;
	}
	public void setDrinking(String drinking) {
		this.drinking = drinking;
	}
	public String getDiarrhea() {
		return diarrhea;
	}
	public void setDiarrhea(String diarrhea) {
		this.diarrhea = diarrhea;
	}
	public String getRarelyBask() {
		return rarelyBask;
	}
	public void setRarelyBask(String rarelyBask) {
		this.rarelyBask = rarelyBask;
	}
	public String getRarelysports() {
		return rarelysports;
	}
	public void setRarelysports(String rarelysports) {
		this.rarelysports = rarelysports;
	}
	public String getFamily_osteoporosis() {
		return family_osteoporosis;
	}
	public void setFamily_osteoporosis(String family_osteoporosis) {
		this.family_osteoporosis = family_osteoporosis;
	}
	public String getFamily_hypertension() {
		return family_hypertension;
	}
	public void setFamily_hypertension(String family_hypertension) {
		this.family_hypertension = family_hypertension;
	}

}
