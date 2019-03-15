package com.paladin.health.service.core.xk;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.exception.SystemException;

@Component
public class XKKnowledgeServlet {

	private static Logger logger = LoggerFactory.getLogger(XKKnowledgeServlet.class);

	// 熙康环境auth访问地址
	private String authUrl = "http://dlpassport.xikang.com/oauth/token";
	// 熙康环境clientId名称
	private String clientId = "xiande_ningbo";
	// 熙康环境clientSecret名称
	private String clientSecret = "Sx5do2Yetx38N";

	// token 获取参数
	private MultiValueMap<String, Object> tokenRequestParamMap;
	private RestTemplate restTemplate;

	private volatile String accessToken;
	// TODO 超时时间，用于辅助判断token是否超时，需要看熙康是否能返回
	@SuppressWarnings("unused")
	private volatile long expiresTime = 0;

	public XKKnowledgeServlet() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(15000);// 单位为ms
		factory.setConnectTimeout(15000);// 单位为ms
		restTemplate = new RestTemplate(factory);
	}

	/**
	 * 获取熙康assess token
	 * 
	 * @return
	 */
	public String getAssessToken() {
		if (accessToken == null) {
			synchronized (XKKnowledgeServlet.class) {
				if (accessToken == null) {
					updateAssessToken(null);
					if (accessToken == null) {
						throw new BusinessException("连接熙康知识库异常！");
					}
				}
			}
		}
		return accessToken;
	}

	/**
	 * 更新assess token
	 */
	@SuppressWarnings("rawtypes")
	public void updateAssessToken(String errorToken) {
		synchronized (XKKnowledgeServlet.class) {
			if (accessToken == null || accessToken.equals(errorToken)) {
				try {
					if (tokenRequestParamMap == null) {
						tokenRequestParamMap = new LinkedMultiValueMap<>();
						tokenRequestParamMap.add("grant_type", "client_credentials");
						tokenRequestParamMap.add("scope", "trust");
						tokenRequestParamMap.add("client_id", clientId);
						tokenRequestParamMap.add("client_secret", clientSecret);
					}

					ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, tokenRequestParamMap, Map.class);
					if (response.getStatusCode() == HttpStatus.OK) {
						accessToken = (String) response.getBody().get("access_token");
						if (accessToken == null) {
							logger.error("获取熙康获取token异常，token返回空");
							throw new BusinessException("连接熙康知识库异常！");
						}
					} else {
						logger.error("获取熙康获取token异常，返回状态[" + response.getStatusCode() + "]");
						throw new BusinessException("连接熙康知识库异常！");
					}
				} catch (Exception e) {
					logger.error("获取熙康获取token异常", e);
					throw new BusinessException("连接熙康知识库异常！", e);
				}
			}
		}
	}

	/**
	 * GET 请求
	 * 
	 * @param url
	 * @param params
	 * @param responseType
	 * @return
	 */
	public <T> T getRequest(String url, Map<String, Object> params, Class<T> responseType) {
		return getRequest(url, params, responseType, 5);
	}

	/**
	 * GET 请求
	 * 
	 * @param url
	 * @param params
	 * @param responseType
	 * @param times
	 *            尝试重传次数
	 * @return
	 */
	public <T> T getRequest(String url, Map<String, Object> params, Class<T> responseType, int times) {
		String accessToken = getAssessToken();
		String requestUrl = getRequestUrl(url, accessToken, params);
		ResponseEntity<T> response = null;
		try {
			response = restTemplate.getForEntity(requestUrl, responseType);
		} catch (Exception e) {
			logger.error("请求熙康知识库异常！", e);
			throw new BusinessException("连接熙康知识库异常!");
		}

		HttpStatus status = response.getStatusCode();
		if (status == HttpStatus.OK) {
			return response.getBody();
		} else if (status == HttpStatus.UNAUTHORIZED) {
			if (times > 0) {
				updateAssessToken(accessToken);
				return getRequest(url, params, responseType, --times);
			} else {
				logger.error("尝试多次连接失败，返回未验证");
				throw new BusinessException("连接熙康知识库异常!");
			}
		} else {
			logger.error("请求熙康知识库异常，返回状态[" + status + "]");
			throw new BusinessException("连接熙康知识库异常!");
		}
	}

	/**
	 * POST JSON格式参数请求
	 * 
	 * @param url
	 * @param param
	 * @param responseType
	 * @return
	 */
	public <T> T postJsonRequest(String url, Object param, Class<T> responseType) {
		return postJsonRequest(url, param, responseType, 5);
	}

	/**
	 * POST JSON格式参数请求
	 * 
	 * @param url
	 * @param param
	 * @param responseType
	 * @param times
	 * @return
	 */
	public <T> T postJsonRequest(String url, Object param, Class<T> responseType, int times) {
		String accessToken = getAssessToken();
		String requestUrl = getRequestUrl(url, accessToken, null);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(param, headers);

		ResponseEntity<T> response = null;
		try {
			response = restTemplate.postForEntity(requestUrl, entity, responseType);
		} catch (Exception e) {
			logger.error("请求熙康知识库异常！", e);
			throw new BusinessException("连接熙康知识库异常!");
		}

		HttpStatus status = response.getStatusCode();
		if (status == HttpStatus.OK) {
			return response.getBody();
		} else if (status == HttpStatus.UNAUTHORIZED) {
			if (times > 0) {
				updateAssessToken(accessToken);
				return postJsonRequest(url, param, responseType, --times);
			} else {
				logger.error("尝试多次连接失败，返回未验证");
				throw new BusinessException("连接熙康知识库异常!");
			}
		} else {
			logger.error("请求熙康知识库异常，返回状态[" + status + "]");
			throw new BusinessException("连接熙康知识库异常!");
		}
	}

	/**
	 * 加上签名，token等
	 * 
	 * @param paramsMap
	 * @return
	 */
	private String getRequestUrl(String url, String accessToken, Map<String, Object> paramsMap) {

		if (url.indexOf("?") > -1) {
			// 如果需要，则要修改下面代码
			throw new SystemException("由于加密原因url上不能传递参数");
		}

		if (paramsMap == null) {
			paramsMap = new HashMap<>();
		}

		// 加入access_token与时间戳参数
		paramsMap.put("access_token", accessToken);
		paramsMap.put("time", String.valueOf(System.currentTimeMillis()));
		paramsMap.remove("sign");

		// 先将参数以其参数名的字典序升序进行排序
		Map<String, Object> sortedParams = new TreeMap<>(paramsMap);
		Set<Entry<String, Object>> entrys = sortedParams.entrySet();
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		for (Entry<String, Object> param : entrys) {
			basestring.append(param.getKey()).append("=");
			Object value = param.getValue();
			basestring.append(value == null ? "" : value.toString());
		}
		// 将客户端密码(应用注册时得到)附加到参数最后
		basestring.append(clientSecret);
		// 使用MD5对待签名串求签
		byte[] bytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
		} catch (IOException | NoSuchAlgorithmException ex) {
			logger.error("加密熙康签名异常", ex);
			throw new BusinessException("连接熙康知识库异常!", ex);
		}

		// 将MD5输出的二进制结果转换为小写的十六进制
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex);
		}

		paramsMap.put("sign", sign.toString());

		StringBuilder sb = new StringBuilder(url);
		int i = url.indexOf("?");
		if (i < 0) {
			sb.append("?");
		} else if (i < url.length() - 1) {
			sb.append("&");
		}

		for (Entry<String, Object> entry : paramsMap.entrySet()) {
			sb.append(entry.getKey()).append("=");
			Object value = entry.getValue();
			sb.append(value == null ? "" : value.toString()).append("&");
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		// String code = "CM.0140";
		// String url =
		// "http://dlopen.xikang.com/openapi/evaluate/diseaseEncyclopedia/"+ code;
		//
		// System.out.println(new XKKnowledgeServlet().getRequest(url, null,Map.class));

		//
		// String url = "http://dlopen.xikang.com/openapi/evaluate/diseasePrediction";
		//
		// XKEvaluateCondition diseasePredictionParticipationVo = new
		// XKEvaluateCondition();
		// diseasePredictionParticipationVo.setAge("47");
		// diseasePredictionParticipationVo.setDbp("90");
		// diseasePredictionParticipationVo.setDiabetes("1");
		// diseasePredictionParticipationVo.setDiarrhea("1");
		// diseasePredictionParticipationVo.setDrinking("1");
		// diseasePredictionParticipationVo.setDyazide("1");
		// diseasePredictionParticipationVo.setFamily_cvd("1");
		// diseasePredictionParticipationVo.setFamily_diabetes("1");
		// diseasePredictionParticipationVo.setFamily_hypertension("0");
		// diseasePredictionParticipationVo.setFamily_osteoporosis("1");
		// diseasePredictionParticipationVo.setFbc("10.8");
		// diseasePredictionParticipationVo.setHdl("4.5");
		// diseasePredictionParticipationVo.setHeight("178");
		// diseasePredictionParticipationVo.setHormone("1");
		// diseasePredictionParticipationVo.setHyperglycemia("1");
		// diseasePredictionParticipationVo.setIdl("4.12");
		// diseasePredictionParticipationVo.setMenopause("1");
		// diseasePredictionParticipationVo.setPbg("16.1");
		// diseasePredictionParticipationVo.setRarelyBask("1");
		// diseasePredictionParticipationVo.setRarelysports("1");
		// diseasePredictionParticipationVo.setSbp("156");
		// diseasePredictionParticipationVo.setSex("1");
		// diseasePredictionParticipationVo.setSmoke("1");
		// diseasePredictionParticipationVo.setSports("1");
		// diseasePredictionParticipationVo.setStrokeOrTia("1");
		// diseasePredictionParticipationVo.setTc_mmol("7.8");
		// diseasePredictionParticipationVo.setVegOrFruits("1");
		// diseasePredictionParticipationVo.setWaistline("100");
		// diseasePredictionParticipationVo.setWeight("95");
		//
		// System.out.println(new XKKnowledgeServlet().postJsonRequest(url,
		// diseasePredictionParticipationVo, Map.class));

	}

}
