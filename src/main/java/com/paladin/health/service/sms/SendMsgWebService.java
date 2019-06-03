package com.paladin.health.service.sms;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paladin.framework.utils.MessageConvert;
import com.paladin.health.model.sms.SmsSendResponse;

@Service
public class SendMsgWebService {
	// ############################此部分参数需要修改#######################
	@Value("${sms.webservice.account}")
	public String account; // 接口账号
	@Value("${sms.webservice.key}")
	public String key; // 密码
//	public static String phone = "18017580238"; // 发送手机，如：13888888888
//	public static String content = "123456 短信内容"; // 短信内容
	@Value("${sms.webservice.url}")
	public String url; // 卫计委短信平台地址

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgWebService.class);
	
	// 发送短信方法
	public SmsSendResponse sendSms(String phone,String content) {
		SmsImplServiceClient client = new SmsImplServiceClient(url);
		 try{
//			//create a default service endpoint
	        	SmsService service = client.getSmsService4XMLImplPort();
		//------------------------
//		SmsService4XMLImplServiceClient smsservice = new SmsService4XMLImplServiceClient();
//		try {
//			ISmsService4XML sms = service.getSmsService4XMLImplPort(url);
			LOGGER.info("**********发送短信***Begin************");
			String message=convertToXmlForSubmit(phone, content); // 使用document 对象封装XML
			LOGGER.info("**短信**请求内容:"+message);
			String res = service.submit(message);
			LOGGER.info("**********发送短信***End************");
			LOGGER.info("**短信**响应内容:"+res);
			return MessageConvert.xmlToBean(res, SmsSendResponse.class);
		} catch (Exception e) {
			LOGGER.error("短信发送异常:"+e.getMessage());
		}
		return null;
	}

		// 查询余额方法
		public SmsSendResponse getBalance() {
			//-------------------------------------------------------
			SmsImplServiceClient client = new SmsImplServiceClient(url);
	        try{
//			//create a default service endpoint
	        	SmsService service = client.getSmsService4XMLImplPort();
			
	        	String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><message><account>"
						+ account
						+ "</account><password>"
						+ key
						+ "</password></message>";
				LOGGER.info("******查询余额***Begin******请求:"+message);
				String res = service.balance(message);
				LOGGER.info("******查询余额***End******响应:"+res);
				return MessageConvert.xmlToBean(res, SmsSendResponse.class);  
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	            throw new RuntimeException(e);
	        }  
		}
		
		/**
		 * 使用document 对象封装XML
		 * @param account
		 * @param key
		 * @param phones
		 * @param content
		 * @return
		 */
		public String convertToXmlForSubmit(String phones,String content) {
			//封装XML
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element message = doc.addElement("message");
			Element accountEl = message.addElement("account");
			accountEl.setText(account);
			Element password = message.addElement("key");
			password.setText(key);
			Element phonesEl = message.addElement("phones");
			phonesEl.setText(phones);
			Element contentEL = message.addElement("content");
			contentEL.setText(content);
			return doc.asXML();
		}
		
}
