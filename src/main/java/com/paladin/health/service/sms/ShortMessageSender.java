package com.paladin.health.service.sms;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientCallback;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.message.Message;
import org.springframework.stereotype.Component;

import com.paladin.framework.utils.reflect.ReflectUtil;
import com.paladin.health.service.sms.URI.MalformedURIException;

@Component
public class ShortMessageSender {

	@Resource
	private SmsConfig smsConfig;

	private static final String wsdlUrl = "http://223.105.0.175:8804/ry4/services/cmcc_mas_wbs?wsdl";
	private static final ClientImpl client;
	
	
	

	static {
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		// client = factory.createClient(wsdlUrl);
		client = (ClientImpl) factory.createClient(wsdlUrl);
		
		
		try {
			Class<?> type = Thread.currentThread().getContextClassLoader().loadClass("org.csapi.schema.sms.SendSmsRequest");
			
			Method[] methods = type.getMethods();
			
			for(Method method:methods) {
				
				String name = method.getName();
				if(name.startsWith("getMessageFormat")) {			
					Class<?> clazz = method.getReturnType();
					
					while(clazz!=Object.class) {
						
						clazz = clazz.getSuperclass();
						System.out.println(clazz.getName());
					}
					
					
				}
//				if(name.startsWith("get")) {					
//					Class<?> clazz = method.getReturnType();
//					System.out.println(name+":" + clazz.getName());
//					if(clazz != String.class) {
//						Method[] ms = clazz.getMethods();
//						for(Method m : ms) {
//							System.out.print(m.getName()+",");
//						}
//					}
//					
//					
//				}
					
				System.out.println(method.getName());
				
			}			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ShortMessageSender().sendMessage();
	}

	public void sendMessage() {

		try {			
			SendSmsRequest request = new SendSmsRequest();
			request.setApplicationID("P000000000000055");
			request.setMessageFormat(SendSmsRequest.MESSAGE_FORMAT_GB2312);
			request.setExtendCode("123456");
			request.setSendMethod(SendSmsRequest.SEND_METHOD_NORMAL);
			request.setDeliveryResultRequest(true);	
			
			List<String> addresses = new ArrayList<>();
			addresses.add("tel:13584950680");
			
			request.setDestinationAddresses(addresses);
			request.setMessage("您好我是周旭武");
				
			
			Object[] response = client.invoke( "sendSms", request);
			
			System.out.println(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	

}
