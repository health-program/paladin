package com.paladin.health.service.core.xk;

import com.github.pagehelper.util.StringUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.paladin.common.core.ConstantsContainer;
import com.paladin.common.core.TemporaryFileHelper;
import com.paladin.common.core.TemporaryFileHelper.TemporaryFileOutputStream;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.time.DateFormatUtil;
import com.paladin.framework.utils.time.DateTimeUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.model.sms.SmsSendResponse;
import com.paladin.health.service.core.xk.dto.ConfirmEvaluationDTO;
import com.paladin.health.service.core.xk.dto.ConfirmEvaluationItemDTO;
import com.paladin.health.service.core.xk.message.MessageContainer;
import com.paladin.health.service.core.xk.request.XKEvaluateCondition;
import com.paladin.health.service.core.xk.response.XKDiseaseKnowledge;
import com.paladin.health.service.core.xk.response.XKEvaluation;
import com.paladin.health.service.core.xk.response.XKHealthPrescription;
import com.paladin.health.service.diagnose.DiagnoseRecordService;
import com.paladin.health.service.diagnose.DiagnoseTargetFactorService;
import com.paladin.health.service.diagnose.DiagnoseTargetService;
import com.paladin.health.service.sms.SendMsgWebService;

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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@SuppressWarnings("rawtypes")
public class XKHealthPrescriptionService {

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

	@Value("${xk.knowledge.url}")
	private String knowledgeUrl;

	@Value("${xk.evaluation.url}")
	private String evaluationUrl;

	@Value("${xk.tips.url}")
	private String tipsUrl;

	/** 熙康疾病类型 */
	public static String CONSTANT_DISEASE_TYPE = "xk-disease-type";
	/** 熙康指标类型 */
	public static String CONSTANT_INDEX_TYPE = "xk-index-type";
	/** 熙康评估类型 */
	public static String CONSTANT_EVALUATE_TYPE = "xk-evaluate-type";

	@Transactional
	public List<XKDiseaseKnowledge> diagnoseDiseases(List<String> codes) {
		List<XKDiseaseKnowledge> diseaseKnowledge = null;
		if (codes != null && codes.size() > 0) {
			Set<String> diseaseSet = new HashSet<>(codes);
			diseaseKnowledge = new ArrayList<>();
			for (String disease : diseaseSet) {
				XKDiseaseKnowledge k = getKnowledge(disease);
				if (k != null) {
					diseaseKnowledge.add(k);
				}
			}
		}
		return diseaseKnowledge;
	}

	/**
	 * 简单评估
	 * 
	 * @param condition
	 * @param accessKey
	 * @return
	 */
	@Transactional
	public XKHealthPrescription doSimpleEvaluation(XKPeopleCondition condition, String accessKey) {

		List<XKEvaluation> evaluationResultList = null;
		XKEvaluateCondition evaluateCondition = condition.getCondition();

		// 进行评估预测
		if (evaluateCondition != null) {
			Map evaluationResult = getEvaluation(evaluateCondition);
			if (evaluationResult != null) {
				evaluationResultList = new ArrayList<>(8);
				for (ConstantsContainer.KeyValue kv : ConstantsContainer.getType(CONSTANT_EVALUATE_TYPE)) {

					String code = kv.getKey();
					String name = kv.getValue();

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
					Integer riskLevel = XKEvaluation.nameLevelMap.get(riskLevelName);
					if (riskLevel == null) {
						logger.error("评估[" + name + "]时出现未知风险等级：" + riskLevelName);
						continue;
					}

					String suggest = (String) riskResult.get("suggest");
					evaluationResultList.add(new XKEvaluation(code, name, riskLevel, riskLevelName, suggest));
				}

				// 按危险等级排序
				if (evaluationResultList.size() > 0) {
					evaluationResultList.sort(new Comparator<XKEvaluation>() {
						@Override
						public int compare(XKEvaluation o1, XKEvaluation o2) {
							return o2.getRiskLevel() - o1.getRiskLevel();
						}
					});

				}
			}
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
			updateEvaluationFactor(evaluationResultList, identificationId, accessKey);

			String searchId = condition.getSearchId();
			List<String> messages = getMessage(target, evaluationResultList);

			// 保存目标病人本次请求和返回数据
			DiagnoseRecord record = new DiagnoseRecord();
			String diagnoseId = UUIDUtil.createUUID();
			record.setId(diagnoseId);
			record.setTargetId(identificationId);
			record.setTargetCondition(JsonUtil.getJson(condition));
			record.setPrescription(JsonUtil.getJson(evaluationResultList));
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
			result.setEvaluation(evaluationResultList);
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
	private void updateEvaluationFactor(List<XKEvaluation> evaluationResultList, String identificationId, String accessKey) {
		Date now = new Date();
		List<DiagnoseTargetFactor> targetFactors = new ArrayList<>();

		if (evaluationResultList != null && evaluationResultList.size() > 0) {
			for (XKEvaluation evaluation : evaluationResultList) {
				targetFactors.add(new DiagnoseTargetFactor(identificationId, evaluation.getCode(), DiagnoseTargetFactor.FACTOR_TYPE_RISK,
						evaluation.getRiskLevel(), now, accessKey));
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
	public void confirmSimpleEvaluation(ConfirmEvaluationDTO confirmEvaluation, String searchId, String accessKey) {
		confirmSimpleEvaluationAndCreatePDF(confirmEvaluation, searchId, accessKey, false);
	}

	/**
	 * 确认评估，并更新确认后评估内容，如果需要创建PDF则创建PDF文件
	 * 
	 * @param confirmEvaluation
	 * @param searchId
	 * @param accessKey
	 * @param createPDF
	 */
	public String confirmSimpleEvaluationAndCreatePDF(ConfirmEvaluationDTO confirmEvaluation, String searchId, String accessKey, boolean createPDF) {
		DiagnoseRecord record = diagnoseRecordService.getRecordBySearchId(searchId, accessKey);
		String id = record.getId();
		String identificationId = record.getTargetId();
		String hospitalName = record.getHospitalName();
		String confirmer = record.getDoctorName();

		List<ConfirmEvaluationItemDTO> evaluationItems = confirmEvaluation.getEvaluationItems();

		List<XKEvaluation> evaluationResultList = null;
		if (evaluationItems != null && evaluationItems.size() > 0) {
			evaluationResultList = new ArrayList<>(evaluationItems.size());
			for (ConfirmEvaluationItemDTO evaluationItem : evaluationItems) {
				String code = evaluationItem.getCode();
				String name = XKEvaluation.codeNameMap.get(code);
				if (name == null) {
					throw new BusinessException("评估数据错误！");
				}

				Integer level = evaluationItem.getRiskLevel();
				String levelName = XKEvaluation.levelNameMap.get(level);
				if (levelName == null) {
					throw new BusinessException("评估数据错误！");
				}

				String suggest = evaluationItem.getSuggest();
				evaluationResultList.add(new XKEvaluation(code, name, level, levelName, suggest));
			}
		}

		updateEvaluationFactor(evaluationResultList, identificationId, accessKey);

		String correctPrescription = "";
		if (evaluationResultList != null) {
			correctPrescription = JsonUtil.getJson(evaluationResultList);
		}

		DiagnoseTarget target = diagnoseTargetService.get(record.getTargetId());

		String sendMessage = confirmEvaluation.getSendMessage();
		int sendStatus = DiagnoseRecord.SEND_STATUS_DEFAULT;
		String sendError = null;
		String cellphone = null;

		if (sendMessage != null) {
			sendMessage = sendMessage.trim();
			if (sendMessage.length() != 0) {
				cellphone = confirmEvaluation.getCellphone();
				if (cellphone == null || cellphone.length() == 0) {
					cellphone = target.getCellphone();
				}

				if (cellphone != null && cellphone.length() > 0) {
					sendMessage += "——" + hospitalName + "·" + confirmer;
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

		if (createPDF) {
			TemporaryFileOutputStream output = TemporaryFileHelper.getFileOutputStream(null, ".pdf");
			try {
				createPDF(evaluationResultList, target, output, new Date(), confirmer);
				return output.getFileRelativeUrl();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("创建PDF失败");
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
	private List<String> getMessage(DiagnoseTarget target, List<XKEvaluation> evaluationResultList) {
		if (evaluationResultList != null) {
			for (XKEvaluation eval : evaluationResultList) {
				String code = eval.getCode();
				int level = eval.getRiskLevel();

				if (level > XKEvaluation.LEVEL_MIDDLE) {
					if (XKEvaluation.CODE_DIABETES.equals(code)) {
						return getTips("diabetes");
					} else if (XKEvaluation.CODE_HYPERTENSION.equals(code)) {
						return getTips("hypertension");
					}
				}
			}
		}

		if (target != null) {
			Date birthday = target.getBirthday();
			if (birthday != null) {
				if (DateTimeUtil.getAge(birthday) > 60) {
					return getTips("aged");
				}
			}
		}

		String message = messageContainer.getOneFestivalMessage();
		List<String> msgs = new ArrayList<>();
		msgs.add(message);
		return msgs;
	}

	/**
	 * 获取知识
	 * 
	 * @param code
	 * @return
	 */
	public XKDiseaseKnowledge getKnowledge(String code) {
		String url = knowledgeUrl + code;
		String diseaseName = ConstantsContainer.getTypeValue(CONSTANT_DISEASE_TYPE, code);
		if (diseaseName != null && diseaseName.length() > 0) {
			List knowledge = knowledgeServlet.getRequest(url, null, List.class);
			if (knowledge != null) {
				return new XKDiseaseKnowledge(code, diseaseName, XKDiseaseKnowledge.TYPE_DISEASE, knowledge);
			}
		} else {
			diseaseName = ConstantsContainer.getTypeValue(CONSTANT_INDEX_TYPE, code);
			if (diseaseName != null && diseaseName.length() > 0) {
				List knowledge = knowledgeServlet.getRequest(url, null, List.class);
				if (knowledge != null) {
					return new XKDiseaseKnowledge(code, diseaseName, XKDiseaseKnowledge.TYPE_INDE, knowledge);
				}
			}
		}

		return null;
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
		referenceMap.put(XKEvaluation.CODE_AF, "基于Wang, Massaro, Levy et al于2003年在JAMA上发表的文章《通过社区新发作的AF预测中风或者死亡的风险分数》");
		referenceMap.put(XKEvaluation.CODE_CHD, "基于D Agostino Russe Mw. Huse DM等人在2000年美国心脏期刊发表的《原发性和继发性冠心病评估:从 Framingham研究中获得的新结果》");
		referenceMap.put(XKEvaluation.CODE_CVD, "基于2型糖尿病患者未来5年内发生CVD事件的评分模型");
		referenceMap.put(XKEvaluation.CODE_DIABETES, "基于芬兰Undstrom高危糖尿病人群预测模型");
		referenceMap.put(XKEvaluation.CODE_HYPERTENSION, "基于中国35-64岁人群15年高血压发生风险预测研究");
		referenceMap.put(XKEvaluation.CODE_ICVD, "基于国家“十五“攻关课题‘冠心病、脑卒中综合危险度评估及干预方案的研究《课题组在此开发的用于评估个体10年ICVD发病危险的工具》");
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

	public void createPDF(List<XKEvaluation> evaluationResultList, DiagnoseTarget target, OutputStream output, Date createTime, String confirmer)
			throws Exception {

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

			Paragraph info = new Paragraph();
			info.add(Chunk.NEWLINE);
			info.add(new Phrase("姓名：", fontt));
			info.add(new Phrase(name != null ? name : "", font));
			info.add("          ");
			info.add(new Phrase("性别：", fontt));
			info.add(new Phrase(sex != null ? (sex == 1 ? "男" : "女") : "", font));
			info.add("          ");
			info.add(new Phrase("确认医生：", fontt));
			info.add(new Phrase(confirmer != null ? confirmer : "", font));
			info.add("          ");
			info.add(new Phrase("评估时间：", fontt));
			info.add(new Phrase(createTime != null ? formatter.format(createTime) : "", font));
			info.add(Chunk.NEWLINE);
			info.add(Chunk.NEWLINE);
			info.setAlignment(Element.ALIGN_CENTER);
			document.add(info);
			// int num = 0;
			for (XKEvaluation evaluation : evaluationResultList) {
				BaseFont sfTTF1 = BaseFont.createFont("/ttf/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				BaseColor color = null;
				int riskLevel = evaluation.getRiskLevel();
				if (riskLevel == 4)
					color = new BaseColor(255, 0, 0);
				if (riskLevel == 3)
					color = new BaseColor(255, 165, 0);
				if (riskLevel == 2)
					color = new BaseColor(0, 166, 90);
				Font fontColor = new Font(sfTTF1, 11, Font.NORMAL, color);
				Font font1 = new Font(sfTTF1, 12);
				Font font111 = new Font(sfTTF1, 11);
				Paragraph info1 = new Paragraph();
				info1.setIndentationLeft(24);
				// info1.add(Chunk.NEWLINE);
				info1.add(new Phrase("评估内容：", font1));
				info1.add(new Phrase(evaluation.getName(), font111));
				info1.add(Chunk.NEWLINE);
				info1.add(new Phrase("风险等级：", font1));
				info1.add(new Phrase(evaluation.getRiskLevelName(), fontColor));
				info1.add(Chunk.NEWLINE);
				info1.add(new Phrase("分析建议：", font1));
				document.add(info1);
				BaseFont sfTTF11 = BaseFont.createFont("/ttf/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				Font font11 = new Font(sfTTF11, 11);
				String suggest = evaluation.getSuggest();
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
						info11.setSpacingAfter(12f);
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
