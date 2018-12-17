package com.paladin.health.service.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sms.properties")
public class SmsConfig {
	
	/**
	 * 参数说明见sms.properties
	 */
	@Value("${Url}")
	private String url;
	@Value("${ApplicationID}")
	private String applicationID;
	@Value("${ExtendCode}")
	private String extendCode;
	@Value("${MessageFormat}")
	private String messageFormat;
	@Value("${SendMethod}")
	private String sendMethod;
	@Value("${DeliveryResultRequest}")
	private String deliveryResultRequest;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getExtendCode() {
		return extendCode;
	}
	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
	public String getMessageFormat() {
		return messageFormat;
	}
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}
	public String getSendMethod() {
		return sendMethod;
	}
	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}
	public String getDeliveryResultRequest() {
		return deliveryResultRequest;
	}
	public void setDeliveryResultRequest(String deliveryResultRequest) {
		this.deliveryResultRequest = deliveryResultRequest;
	}
	
	

}
