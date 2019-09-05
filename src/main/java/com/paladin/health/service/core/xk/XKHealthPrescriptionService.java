package com.paladin.health.service.core.xk;
import com.github.pagehelper.util.StringUtil;
import com.google.common.base.Strings;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.paladin.common.core.TemporaryFileHelper;
import com.paladin.common.core.TemporaryFileHelper.TemporaryFileOutputStream;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.time.DateFormatUtil;
import com.paladin.framework.utils.time.DateTimeUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.core.knowledge.KnowledgeManageContainer;
import com.paladin.health.core.knowledge.KnowledgeManageContainer.EvaluateConfig;
import com.paladin.health.core.knowledge.KnowledgeManageContainer.KnowledgeServiceBean;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.model.knowledge.KnowledgeBase;
import com.paladin.health.model.knowledge.KnowledgeBaseDetail;
import com.paladin.health.model.sms.SmsSendResponse;
import com.paladin.health.service.core.HealthPrescriptionService;
import com.paladin.health.service.core.xk.XKDiseasePrescriptionContainer.DiseasePrescriptionPackage;
import com.paladin.health.service.core.xk.dto.ConfirmPrescriptionDTO;
import com.paladin.health.service.core.xk.message.MessageContainer;
import com.paladin.health.service.core.xk.request.XKDisease;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.core.xk.response.XKDiseaseKnowledge;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;
import com.paladin.health.service.core.xk.response.XKMessage;
import com.paladin.health.service.core.xk.response.XKPrescription;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.DiagnoseTargetFactorService;
import com.paladin.health.service.diagnose.DiagnoseTargetService;
import com.paladin.health.service.knowledge.KnowledgeBaseDetailService;
import com.paladin.health.service.knowledge.KnowledgeBaseService;
import com.paladin.health.service.sms.SendMsgWebService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("rawtypes")
public class XKHealthPrescriptionService implements HealthPrescriptionService {

	private static Logger logger = LoggerFactory.getLogger(XKHealthPrescriptionService.class);

	@Autowired
	private DiagnoseTargetService diagnoseTargetService;
	@Autowired
	private DiagnoseRecordService diagnoseRecordService;
	@Autowired
	private DiagnoseTargetFactorService diagnoseRecordFactorService;
	@Autowired
	private SendMsgWebService sendMsgWebService;
	@Autowired
	private XKKnowledgeServlet knowledgeServlet;
	@Autowired
	private MessageContainer messageContainer;
	@Autowired
	private KnowledgeBaseService knowledgeBaseService;
	@Autowired
	private KnowledgeBaseDetailService knowledgeBaseDetailService;

	@Value("${xk.knowledge.url}")
	private String knowledgeUrl;

	@Value("${xk.evaluation.url}")
	private String evaluationUrl;

	@Value("${xk.tips.url}")
	private String tipsUrl;

	public static final int CREATE_DO_NOTHING = 0;
	public static final int CREATE_PDF = 1;
	public static final int CREATE_DOC = 2;

	@Override
	public String getKnowledgeServiceCode() {
		return KnowledgeManageContainer.SERVICE_CODE_XK;
	}

	private final static Configuration TEMPLATE_CONFIG;

	private final  static Template TEMPLATE;

	 static {
		TEMPLATE_CONFIG = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		TEMPLATE_CONFIG.setClassForTemplateLoading(XKHealthPrescriptionService.class, "/com/paladin/health/service/core/xk/");
		TEMPLATE_CONFIG.setDefaultEncoding("UTF-8");
		try {
			TEMPLATE = TEMPLATE_CONFIG.getTemplate("/prescription.ftl");
		} catch (IOException e) {
			throw new RuntimeException("获取DOC模板失败", e);
		}
	}

	private List<EvaluateConfig> evaluatableds = new ArrayList<>();

	@Override
	public void setKnowledgeServiceBean(KnowledgeServiceBean bean) {
		List<EvaluateConfig> evaluatableds = new ArrayList<>();
		List<EvaluateConfig> configs = bean.getEvaluates();
		if (configs != null) {
			for (EvaluateConfig config : configs) {
				if (config.isEnabled()) {
					evaluatableds.add(config);
				}
			}
		}
		this.evaluatableds = evaluatableds;
	}

	// 糖尿病ICD10匹配规则（E10-E14）
	private Pattern diabetesICD10Pattern = Pattern.compile("^E1[0-4]\\..+");
	// 高血压ICD10匹配规则（I10-I15）
	private Pattern hypertensionICD10Pattern = Pattern.compile("^I1[0-5]\\..+");

	/**
	 * 简单评估
	 * 
	 * @param condition
	 * @param accessKey
	 * @return
	 */
	@Transactional
	public XKHealthPrescription doSimpleEvaluation(XKPeopleCondition condition, String accessKey) {

		Map<String, XKPrescription> prescriptionMap = new HashMap<>();
		List<XKMessage> messages = new ArrayList<>();

		XKEvaluateCondition evaluateCondition = condition.getCondition();
		List<XKDisease> diseases = condition.getDiseases();

		boolean hasDiabetes = false;
		boolean hasHypertension = false;

		// 是否有糖尿病
		if ("1".equals(evaluateCondition.getDiabetes())) {
			if (diseases == null) {
				diseases = new ArrayList<>();
			}
			diseases.add(new XKDisease("糖尿病", "E10.00"));
			hasDiabetes = true;
		}

		// 分析是否高血压
		if (diseases != null && diseases.size() > 0) {
			// ICD10分析
			for (XKDisease disease : diseases) {
				String code = disease.getCode();
				String name = disease.getName();
				if (code == null || code.length() == 0) {
					continue;
				}

				if (!hasDiabetes) {
					// 是否有糖尿病
					if ("糖尿病".equals(name) || diabetesICD10Pattern.matcher(code).matches()) {
						hasDiabetes = true;
					}
				}

				if (!hasHypertension) {
					// 是否高血压
					if ("高血压".equals(name) || hypertensionICD10Pattern.matcher(code).matches()) {
						hasHypertension = true;
					}
				}
			}
		}

		if (hasDiabetes) {
			diseases.add(new XKDisease("糖尿病", "E10.00"));
		}

		if (hasHypertension) {
			diseases.add(new XKDisease("高血压", "I10.00"));
		}

		// 进行诊断疾病指导建议
		if (diseases != null && diseases.size() > 0) {
			for (XKDisease disease : diseases) {
				String diseaseName = disease.getName();
				if (!prescriptionMap.containsKey(diseaseName)) {
					XKPrescription prescription = getDiseasePrescriptionAndMessage(disease, messages);
					if (prescription != null) {
						prescriptionMap.put(diseaseName, prescription);
					}
				}
			}
		}

		// 如果是糖尿病，则直接设置糖尿病参数为是
		if (hasDiabetes) {
			evaluateCondition.setDiabetes("1");
		}

		// 进行评估预测
		if (evaluateCondition != null) {
			Map evaluationResult = getEvaluation(evaluateCondition);
			if (evaluationResult != null) {
				for (EvaluateConfig kv : evaluatableds) {

					String code = kv.getCode();
					String name = kv.getName();

					Map single = (Map) evaluationResult.get(code);
					if (single == null)
						continue;
					String status = (String) single.get("status");

					// 如果不是状态0000则评估出错，可能是参数不全
					if (!"0000".equals(status)) {
						continue;
					}

					String riskResultStr = (String) single.get("result");
					if (riskResultStr == null || riskResultStr.length() == 0) {
						continue;
					}

					Map riskResult = JsonUtil.parseJson(riskResultStr, Map.class);
					if (riskResult == null) {
						logger.error("评估[" + name + "]时非法解析json字符串格式：" + riskResultStr);
						continue;
					}

					String riskLevelName = (String) riskResult.get("riskLevel");

					// 熙康返回的是中文危险等级名称，这里对其归纳总结给出等级
					Integer riskLevel = XKPrescription.nameLevelMap.get(riskLevelName);
					if (riskLevel == null) {
						logger.error("评估[" + name + "]时出现未知风险等级：" + riskLevelName);
						continue;
					}

					String suggest = (String) riskResult.get("suggest");
					if (suggest == null) {
						continue;
					}

					suggest = suggest.trim();
					if (suggest.length() == 0) {
						continue;
					}

					if (hasDiabetes && XKPrescription.CODE_DIABETES.equals(code)) {
						// 已诊断糖尿病则不需要评估结果
						prescriptionMap.remove("糖尿病");
						riskLevel = XKPrescription.LEVEL_HAS;
					} else if (hasHypertension && XKPrescription.CODE_HYPERTENSION.equals(code)) {
						// 已诊断高血压则不需要评估结果
						prescriptionMap.remove("高血压");
						riskLevel = XKPrescription.LEVEL_HAS;
					}

					prescriptionMap.put(name, new XKPrescription(name, code, XKPrescription.TYPE_RISK, riskLevel, suggest));
				}
			}
		}

		List<XKPrescription> prescriptionList = new ArrayList<>(prescriptionMap.values());

		// 按危险等级排序
		if (prescriptionList.size() > 0) {
			prescriptionList.sort(new Comparator<XKPrescription>() {
				@Override
				public int compare(XKPrescription o1, XKPrescription o2) {
					return o2.getRiskLevel() - o1.getRiskLevel();
				}
			});
		}

		// 数据记录
		String identificationId = condition.getIdentificationId();
		// 保存数据，没有身份证则不进行数据持久化
		if (identificationId != null && identificationId.length() > 0) {
			String name = condition.getName();
			String cellphone = condition.getCellphone();
			Date birthday = condition.getBirthday();
			Date now = new Date();

			// 性别可以从评估那去获取
			Integer sex = condition.getSex();
			if (sex == null) {
				if (evaluateCondition != null) {
					String sexStr = evaluateCondition.getSex();
					if (sexStr != null && sexStr.length() > 0) {
						try {
							sex = Integer.valueOf(sexStr);
						} catch (Exception e) {

						}
					}
				}
			}

			// 保存或更新目标病人的信息（如果是空则不会更新掉原先数据）
			DiagnoseTarget target = diagnoseTargetService.get(identificationId);

			if (target == null) {
				target = new DiagnoseTarget();
				target.setId(identificationId);
				target.setName(name);
				target.setBirthday(birthday);
				target.setCellphone(cellphone);
				target.setSex(sex);
				target.setCreateTime(now);
				target.setUpdateTime(now);
				target.setUpdateBy(accessKey);
				diagnoseTargetService.save(target);
			} else {
				target.setId(identificationId);

				if (name != null && name.length() > 0)
					target.setName(name);
				if (birthday != null)
					target.setBirthday(birthday);
				if (cellphone != null && cellphone.length() > 0)
					target.setCellphone(cellphone);
				if (sex != null)
					target.setSex(sex);
				target.setUpdateTime(now);
				target.setUpdateBy(accessKey);
				diagnoseTargetService.update(target);
			}

			// 更新评估得到的因素
			updateTargetFactor(prescriptionList, identificationId, accessKey);

			String searchId = condition.getSearchId();

			setPrescriptionMessage(target, prescriptionList, messages);

			// 保存目标病人本次请求和返回数据
			DiagnoseRecord record = new DiagnoseRecord();
			String diagnoseId = UUIDUtil.createUUID();
			record.setId(diagnoseId);
			record.setTargetId(identificationId);
			record.setTargetCondition(JsonUtil.getJson(condition));
			record.setTargetDisease(diseases != null ? JsonUtil.getJson(diseases) : null);
			record.setPrescription(JsonUtil.getJson(prescriptionList));
			record.setType(DiagnoseRecord.TYPE_XK);
			record.setCreateTime(now);
			record.setCreateBy(accessKey);
			record.setSearchId(searchId);
			record.setDoctorName(condition.getDoctorName());
			record.setHospitalName(condition.getHospitalName());
			record.setMessage(JsonUtil.getJson(messages));

			diagnoseRecordService.save(record);

			XKHealthPrescription result = new XKHealthPrescription();
			result.setId(diagnoseId);
			result.setPrescription(prescriptionList);
			result.setMessage(messages);

			return result;
		}

		return null;
	}

	/**
	 * 替换目标病人存在的危险因素（疾病，风险）
	 * 
	 * @param evaluationResultList
	 * @param identificationId
	 * @param accessKey
	 */
	private void updateTargetFactor(List<XKPrescription> prescriptionList, String identificationId, String accessKey) {
		Date now = new Date();
		List<DiagnoseTargetFactor> targetFactors = new ArrayList<>();

		if (prescriptionList != null && prescriptionList.size() > 0) {
			for (XKPrescription prescription : prescriptionList) {
				targetFactors.add(new DiagnoseTargetFactor(identificationId, prescription.getName(), prescription.getCode(), prescription.getType(),
						prescription.getRiskLevel(), now, accessKey));
			}
		}

		diagnoseRecordFactorService.removeByTarget(identificationId);
		if (targetFactors.size() > 0) {
			diagnoseRecordFactorService.batchSave(targetFactors);
		}

	}

	/**
	 * 确认评估，并更新确认后评估内容
	 * 
	 * @param confirmEvaluations
	 * @param searchId
	 * @param accessKey
	 */
	public void confirmSimpleEvaluation(ConfirmPrescriptionDTO confirmEvaluation, String searchId, String accessKey) {
		confirmSimpleEvaluationAndCreatePDFOrDoc(confirmEvaluation, searchId, accessKey, CREATE_DO_NOTHING);
	}

	/**
	 * 确认评估，并更新确认后评估内容，如果需要创建PDF则创建PDF文件
	 * 
	 * @param confirmEvaluation
	 * @param searchId
	 * @param accessKey
	 * @param createPDF
	 */
	public String confirmSimpleEvaluationAndCreatePDFOrDoc(ConfirmPrescriptionDTO confirmPrescription, String searchId, String accessKey, int createType) {
		DiagnoseRecord record = diagnoseRecordService.getRecordBySearchId(searchId, accessKey);
		String id = record.getId();
		String identificationId = record.getTargetId();
		String hospitalName = record.getHospitalName();
		String confirmer = record.getDoctorName();

		List<XKPrescription> prescriptionItems = confirmPrescription.getPrescriptions();
		updateTargetFactor(prescriptionItems, identificationId, accessKey);

		String correctPrescription = "";
		if (prescriptionItems != null) {
			correctPrescription = JsonUtil.getJson(prescriptionItems);
		}

		DiagnoseTarget target = diagnoseTargetService.get(record.getTargetId());

		String sendMessage = confirmPrescription.getSendMessage();
		int sendStatus = DiagnoseRecord.SEND_STATUS_DEFAULT;
		String sendError = null;
		String cellphone = null;

		if (sendMessage != null) {
			sendMessage = sendMessage.trim();
			if (sendMessage.length() != 0) {
				cellphone = confirmPrescription.getCellphone();
				if (cellphone == null || cellphone.length() == 0) {
					cellphone = target.getCellphone();
				}

				if (cellphone != null && cellphone.length() > 0) {
					sendMessage += "【" + hospitalName + "·" + confirmer + "】";
					try {
						SmsSendResponse resp = sendMsgWebService.sendSms(cellphone, sendMessage);
						if (resp != null) {
							String result = resp.getResult();
							if (SmsSendResponse.RESULT_SUCCESS.equals(result)) {
								sendStatus = DiagnoseRecord.SEND_STATUS_SUCCESS;
							} else {
								sendStatus = DiagnoseRecord.SEND_STATUS_FAIL;
								sendError = resp.getDesc();
							}
						} else {
							sendStatus = DiagnoseRecord.SEND_STATUS_FAIL;
							sendError = "连接异常";
						}

					} catch (Exception e) {
						sendStatus = DiagnoseRecord.SEND_STATUS_FAIL;
						sendError = e.getMessage();
					}
				}
			}
		}

		if (sendError != null && sendError.length() > 200) {
			sendError = sendError.substring(0, 200);
		}

		diagnoseRecordService.updateCorrectPrescription(id, correctPrescription, sendMessage, sendStatus, sendError, cellphone, confirmer);

		if (createType == CREATE_PDF) {
			TemporaryFileOutputStream output = TemporaryFileHelper.getFileOutputStream(null, "pdf");
			try {
				createPDF(prescriptionItems, target, output, new Date(), confirmer, hospitalName);
				return output.getFileRelativeUrl();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("创建PDF失败");
			}
		} else if (createType == CREATE_DOC) {
			TemporaryFileOutputStream output = TemporaryFileHelper.getFileOutputStream(null, "doc");
			try {
				createDoc(prescriptionItems, target, output, new Date(), confirmer, hospitalName);
				return output.getFileRelativeUrl();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("创建DOC失败");
			}
		}

		return null;
	}

	/**
	 * 根据居民情况获取短信
	 * 
	 * @param target
	 * @param evaluationResultList
	 * @return
	 */
	private void setPrescriptionMessage(DiagnoseTarget target, List<XKPrescription> prescriptionList, List<XKMessage> xkMessages) {
		List<String> messages = null;
		String source = null;

		if (prescriptionList != null) {
			for (XKPrescription eval : prescriptionList) {
				if (eval.getType() != XKPrescription.TYPE_RISK) {
					continue;
				}

				String code = eval.getCode();
				int level = eval.getRiskLevel();

				if (level > XKPrescription.LEVEL_MIDDLE) {
					if (XKPrescription.CODE_DIABETES.equals(code)) {
						messages = getTips(XKPrescription.CODE_DIABETES);
						source = "糖尿病小贴士";
					} else if (XKPrescription.CODE_HYPERTENSION.equals(code)) {
						messages = getTips(XKPrescription.CODE_HYPERTENSION);
						source = "高血压小贴士";
					}
				}
			}
		}

		if (messages == null && target != null) {
			Date birthday = target.getBirthday();
			if (birthday != null) {
				if (DateTimeUtil.getAge(birthday) > 60) {
					messages = getTips("aged");
					source = "老年人小贴士";
				}
			}
		}

		if (messages == null) {
			String message = messageContainer.getOneFestivalMessage();
			messages = new ArrayList<>();
			messages.add(message);
			source = "节气小贴士";
		}

		List<XKMessage> xkMsgs = new ArrayList<>();

		if (messages != null && messages.size() > 0) {
			xkMsgs.add(new XKMessage(source, messages.get(0)));
		}

		xkMessages.addAll(xkMsgs);
	}

	/**
	 * 获取疾病处方和短信
	 * 
	 * @param disease
	 * @return
	 */
	private XKPrescription getDiseasePrescriptionAndMessage(XKDisease disease, List<XKMessage> messages) {
		if (disease != null) {
			String name = disease.getName();
			if (name != null) {
				name = name.trim();
				if (name.length() > 0) {
					DiseasePrescriptionPackage packg = XKDiseasePrescriptionContainer.getPrescriptionPackage(name, disease.getCode());
					if (packg != null) {
						messages.add(packg.getMessage());
						return packg.getPrescription();
					}
				}
			}
		}

		return null;
	}

	/**
	 * 获取疾病百科知识
	 */
	public XKDiseaseKnowledge getKnowledge(String code) {
		if (Strings.isNullOrEmpty(code)) {
			throw new BusinessException("请输入疾病或指标编码");
		}
		List<KnowledgeBase> bases = knowledgeBaseService.searchAll(new Condition(KnowledgeBase.COLUMN_CODE, QueryType.EQUAL, code));
		if (bases == null || bases.size() == 0) {
			throw new BusinessException("该编码的疾病或指标基本信息不存在");
		}
		if (bases.size() > 1) {
			throw new BusinessException("该编码的疾病或指标基本信息存在多条记录");
		}
		XKDiseaseKnowledge xkDiseaseKnowledge = new XKDiseaseKnowledge();
		KnowledgeBase base = bases.get(0);
		xkDiseaseKnowledge.setBase(base);
		String baseId = base.getId();
		List<KnowledgeBaseDetail> details = knowledgeBaseDetailService
				.searchAll(new Condition(KnowledgeBaseDetail.COLUMN_KNOWLEDGE_ID, QueryType.EQUAL, baseId));
		if (details != null && details.size() > 0) {
			xkDiseaseKnowledge.setDetail(details);
		}
		return xkDiseaseKnowledge;
	}

	/**
	 * 获取评估
	 * 
	 * @param condition
	 * @return
	 */
	public Map getEvaluation(XKEvaluateCondition condition) {
		// 【不经常晒太阳】和【不经常参加运动锻炼】对外输入 为【 经常晒太阳】和【经常参加运动锻炼】，所以需要转换下值给熙康
		String rarelyBask = condition.getRarelyBask();
		if ("1".equals(rarelyBask)) {
			condition.setRarelyBask("0");
		} else if ("0".equals(rarelyBask)) {
			condition.setRarelyBask("1");
		}

		String rarelysports = condition.getRarelysports();
		if ("1".equals(rarelysports)) {
			condition.setRarelysports("0");
		} else if ("0".equals(rarelysports)) {
			condition.setRarelysports("1");
		}

		return knowledgeServlet.postJsonRequest(evaluationUrl, condition, Map.class);
	}

	/**
	 * 获取TIPS
	 * 
	 * @param typeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getTips(String typeCode) {
		String url = tipsUrl + typeCode;
		Map result = knowledgeServlet.getRequest(url, null, Map.class);

		String status = (String) result.get("status");
		if (!"0000".equals(status)) {
			return null;
		}
		return (List<String>) result.get("result");
	}

	private static Map<String, String> referenceMap;

	static {
		referenceMap = new HashMap<>();
		referenceMap.put(XKPrescription.CODE_AF, "基于Wang, Massaro, Levy et al于2003年在JAMA上发表的文章《通过社区新发作的AF预测中风或者死亡的风险分数》");
		referenceMap.put(XKPrescription.CODE_CHD, "基于D Agostino Russe Mw. Huse DM等人在2000年美国心脏期刊发表的《原发性和继发性冠心病评估:从 Framingham研究中获得的新结果》");
		referenceMap.put(XKPrescription.CODE_CVD, "基于2型糖尿病患者未来5年内发生CVD事件的评分模型");
		referenceMap.put(XKPrescription.CODE_DIABETES, "基于芬兰Undstrom高危糖尿病人群预测模型");
		referenceMap.put(XKPrescription.CODE_HYPERTENSION, "基于中国35-64岁人群15年高血压发生风险预测研究");
		referenceMap.put(XKPrescription.CODE_ICVD, "基于国家“十五“攻关课题‘冠心病、脑卒中综合危险度评估及干预方案的研究《课题组在此开发的用于评估个体10年ICVD发病危险的工具》");
		// referenceMap.put(XKEvaluation.CODE_OSTEOPOROSIS, "骨质疏松症风险评估");
	}

	private static byte[] pdfImage1;
	private static byte[] pdfImage2;
	private static byte[] pdfImage3;

	static {
		try {
			pdfImage1 = FileCopyUtils.copyToByteArray(new ClassPathResource("static/image/health_code.png").getInputStream());
			pdfImage2 = FileCopyUtils.copyToByteArray(new ClassPathResource("static/image/health1.jpg").getInputStream());
			pdfImage3 = FileCopyUtils.copyToByteArray(new ClassPathResource("static/image/line.jpg").getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPDF(List<XKPrescription> prescriptionList, DiagnoseTarget target, OutputStream output, Date createTime, String confirmer,
			String hospitalName) throws Exception {

		// 1.创建PDF文件
		Document document = new Document();
		// 横向，这个可以自己根据实际情况看需不需要，我的是竖着放不下，只能横向展示
		Rectangle pageSize = new Rectangle(PageSize.A4);
		// pageSize.setBackgroundColor(new BaseColor(245,245,245));//设置背景颜色
		document.setPageSize(pageSize);
		document.setMargins(0, 0, 25, 25);// 边距
		// 2.创建书写器（Writer）对象

		PdfWriter writer = PdfWriter.getInstance(document, output);
		writer.setViewerPreferences(PdfWriter.PageModeFullScreen);
		document.open();// 3.打开文档。
		// 4.向文档中添加内容。
		// 中文字体,解决中文不能显示问题
		BaseFont titleFont = BaseFont.createFont("/ttf/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		BaseFont contentFont = BaseFont.createFont("/ttf/STKAITI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		Paragraph paragraph = new Paragraph();
		paragraph.setIndentationLeft(24);
		Image img5 = Image.getInstance(pdfImage1);
		img5.scaleToFit(110, 110);
		paragraph.add(new Chunk(img5, 0, 0, true));

		Image img4 = Image.getInstance(pdfImage2);
		img4.scaleToFit(250, 250);
		paragraph.add("                                                      ");
		paragraph.add(new Chunk(img4, 0, 0, true));
		document.add(paragraph);// 增加到文档中

		Paragraph signatureImg2 = new Paragraph();
		Image img2 = Image.getInstance(pdfImage3);
		signatureImg2.add(img2);
		document.add(signatureImg2);

		/*--------------------------------正文---------------------------------*/
		if (target != null) {
			Font font = new Font(contentFont, 13);
			Font fontt = new Font(titleFont, 14);

			String name = target.getName();
			Integer sex = target.getSex();

			SimpleDateFormat formatter = DateFormatUtil.getThreadSafeFormat("yyyy-MM-dd");
			String hospital = "";
			if (StringUtil.isNotEmpty(hospitalName)) {
				hospital = "(" + hospitalName + ")";
			}
			Paragraph info = new Paragraph();
			info.add(Chunk.NEWLINE);
			info.add(new Phrase("姓名：", fontt));
			info.add(new Phrase(name != null ? name : "", font));
			info.add("    ");
			info.add(new Phrase("性别：", fontt));
			info.add(new Phrase(sex != null ? (sex == 1 ? "男" : "女") : "", font));
			info.add("    ");
			info.add(new Phrase("医生：", fontt));
			info.add(new Phrase(confirmer != null ? confirmer + hospital : "", font));
			info.add("    ");
			info.add(new Phrase("时间：", fontt));
			info.add(new Phrase(createTime != null ? formatter.format(createTime) : "", font));
			info.add(Chunk.NEWLINE);
			info.add(Chunk.NEWLINE);
			info.setAlignment(Element.ALIGN_CENTER);
			document.add(info);
			// int num = 0;
			for (XKPrescription prescription : prescriptionList) {
				BaseFont sfTTF1 = BaseFont.createFont("/ttf/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				BaseColor color = null;

				int riskLevel = prescription.getRiskLevel();
				if (riskLevel >= 4) {
					color = new BaseColor(255, 0, 0);
				} else if (riskLevel == 3) {
					color = new BaseColor(255, 165, 0);
				} else {
					color = new BaseColor(0, 166, 90);
				}

				Font fontColor = new Font(sfTTF1, 11, Font.NORMAL, color);
				Font font1 = new Font(sfTTF1, 12);
				Font font111 = new Font(sfTTF1, 11);
				Paragraph info1 = new Paragraph();
				info1.setIndentationLeft(24);
				// info1.add(Chunk.NEWLINE);
				info1.add(new Phrase("评估内容：", font1));
				info1.add(new Phrase(prescription.getName(), font111));
				info1.add(Chunk.NEWLINE);

				if (prescription.getType() == XKPrescription.TYPE_RISK) {
					info1.add(new Phrase("风险等级：", font1));
					info1.add(new Phrase(XKPrescription.levelNameMap.get(riskLevel), fontColor));
				} else {
					info1.add(new Phrase("风险等级：", font1));
					info1.add(new Phrase("已经确诊", fontColor));
				}

				info1.add(Chunk.NEWLINE);
				info1.add(new Phrase("分析建议：", font1));
				document.add(info1);
				BaseFont sfTTF11 = BaseFont.createFont("/ttf/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				Font font11 = new Font(sfTTF11, 11);
				String suggest = prescription.getSuggest();
				String[] suggests = suggest.split("\\n");
				// 根据回车分段
				for (String each : suggests) {
					Paragraph info11 = new Paragraph();
					info11.setIndentationRight(24);
					if (StringUtil.isNotEmpty(each)) {
						info11.add(new Phrase(each, font11));
						info11.setFirstLineIndent(21);
						info11.setIndentationLeft(78);
						info11.setLeading(16f);
						info11.setSpacingAfter(3);
						info11.setSpacingBefore(0);
						document.add(info11);
					}
				}

				// num++;
				/*
				 * if(evaluationResultList.size() != num){ info1.add(Chunk.NEWLINE);
				 * LineSeparator line = new LineSeparator(1f,91,new
				 * BaseColor(32,178,170),Element.ALIGN_CENTER,-6f); document.add(line); }
				 */
			}
			writer.setPageEvent(new PdfPageHelper());
		}
		document.close();
	}


	public void createDoc(List<XKPrescription> prescriptionList, DiagnoseTarget target, OutputStream output, Date createTime, String confirmer,
						  String hospitalName) throws Exception {
		if (target != null) {
			HashMap<String, Object> content = new HashMap<>(4);
			String name = target.getName();
			Integer sex = target.getSex();
			SimpleDateFormat formatter = DateFormatUtil.getThreadSafeFormat("yyyy-MM-dd");
			String hospital = "";
			if (StringUtil.isNotEmpty(hospitalName)) {
				hospital = "(" + hospitalName + ")";
			}

			content.put("name",name != null ? name : "");
			content.put("sex",sex != null ? (sex == 1 ? "男" : "女") : "");
			content.put("docName",confirmer != null ? confirmer + hospital : "");
			content.put("time",createTime != null ? formatter.format(createTime) : "");
			List<Map<String, Object>> contents = new ArrayList<>(prescriptionList.size());
			List<String> advices;
			HashMap<String, Object> params;
			for (XKPrescription xkPrescription : prescriptionList) {
				params = new HashMap<>(3);
				params.put("evaluationContent",xkPrescription.getName());
				params.put("riskLevel", XKPrescription.levelNameMap.get(xkPrescription.getRiskLevel()));
				String suggest = xkPrescription.getSuggest();
                advices = Arrays.stream(Optional.ofNullable(suggest).orElse("暂无处方建议").split("\\n"))
                .filter(s -> !Strings.isNullOrEmpty(s))
                .map(String::trim)
                .collect(Collectors.toList());
				params.put("advices",advices);
				contents.add(params);
			}
			content.put("contents",contents);
			TEMPLATE.process(content, new OutputStreamWriter(output, StandardCharsets.UTF_8));
		}

	}


	class PdfPageHelper extends PdfPageEventHelper {
		@Override
		public void onEndPage(PdfWriter writer, Document document) {

			PdfContentByte cb = writer.getDirectContent();// 得到层
			cb.saveState();
			// 开始
			cb.beginText();
			try {
				BaseFont footer = BaseFont.createFont("/ttf/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				cb.setFontAndSize(footer, 10);
				float y = document.bottom(0);
				cb.setColorFill(new BaseColor(105, 105, 105));
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "温馨提示:为了您和您家人的健康,请到辖区内的社区卫生服务机构进行相关的健康咨询", (document.right() + document.left()) / 2, y, 0);
				cb.endText();
				cb.restoreState();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
