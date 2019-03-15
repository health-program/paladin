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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.paladin.framework.core.exception.BusinessException;

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
	private RestTemplate restTemplate = new RestTemplate();

	private volatile String accessToken;
	// TODO 超时时间，用于辅助判断token是否超时，需要看熙康是否能返回
	@SuppressWarnings("unused")
	private volatile long expiresTime = 0;

	/**
	 * 获取熙康assess token
	 * 
	 * @return
	 */
	public String getAssessToken() {
		if (accessToken == null) {
			synchronized (XKKnowledgeServlet.class) {
				if (accessToken == null) {
					updateAssessToken();
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
	public void updateAssessToken() {
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
		url = getRequestUrl(url, params);
		ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
		HttpStatus status = response.getStatusCode();

		System.out.println(response);
		if (status == HttpStatus.OK) {
			return response.getBody();
		} else if (status == HttpStatus.UNAUTHORIZED) {
			if (times > 0) {
				// logger.info("token过期");
				updateAssessToken();
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
	 * 加上签名，token等
	 * 
	 * @param paramsMap
	 * @return
	 */
	private String getRequestUrl(String url, Map<String, Object> paramsMap) {

		if (paramsMap == null) {
			paramsMap = new HashMap<>();
		}

		String accessToken = getAssessToken();

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

}
