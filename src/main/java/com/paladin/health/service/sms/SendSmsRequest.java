package com.paladin.health.service.sms;

import java.util.List;

public class SendSmsRequest {
	
	public static final java.lang.String MESSAGE_FORMAT_ASCII = "ASCII";
    public static final java.lang.String MESSAGE_FORMAT_UCS2 = "UCS2";
    public static final java.lang.String MESSAGE_FORMAT_GB18030 = "GB18030";
    public static final java.lang.String MESSAGE_FORMAT_GB2312 = "GB2312";
    public static final java.lang.String MESSAGE_FORMAT_BINARY = "Binary";
	
    public static final String SEND_METHOD_NORMAL = "Normal";
    public static final String SEND_METHOD_INSTANT = "Instant";
    public static final String SEND_METHOD_LONG = "Long";
    public static final String SEND_METHOD_STRUCTURED = "Structured";
	
	private String ApplicationID;
	private List<String> DestinationAddresses;
	private String ExtendCode;
	private String MessageFormat;
	private String SendMethod;
	private String Message;
	private Boolean DeliveryResultRequest;
		
	public String getApplicationID() {
		return ApplicationID;
	}
	public void setApplicationID(String applicationID) {
		ApplicationID = applicationID;
	}
	public String getExtendCode() {
		return ExtendCode;
	}
	public void setExtendCode(String extendCode) {
		ExtendCode = extendCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Boolean getDeliveryResultRequest() {
		return DeliveryResultRequest;
	}
	public void setDeliveryResultRequest(Boolean deliveryResultRequest) {
		DeliveryResultRequest = deliveryResultRequest;
	}
	public String getMessageFormat() {
		return MessageFormat;
	}
	public void setMessageFormat(String messageFormat) {
		MessageFormat = messageFormat;
	}
	public String getSendMethod() {
		return SendMethod;
	}
	public void setSendMethod(String sendMethod) {
		SendMethod = sendMethod;
	}
	public List<String> getDestinationAddresses() {
		return DestinationAddresses;
	}
	public void setDestinationAddresses(List<String> destinationAddresses) {
		DestinationAddresses = destinationAddresses;
	}
	
	
}
