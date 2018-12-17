package com.paladin.health.service.sms;

import java.net.URL;

import org.apache.axis.client.Service;
import org.csapi.www.schema.sms.MessageFormat;
import org.csapi.www.schema.sms.SendMethodType;
import org.csapi.www.schema.sms.SendSmsRequest;
import org.csapi.www.service.Cmcc_mas_wbsSoapBindingStub;
import org.springframework.stereotype.Component;

@Component
public class ShortMessageSender {

	public boolean sendMessage(String message, String... cellphones) {
		URL url;
		try {
			url = new URL("http://223.105.0.175:8804/ry4/services/cmcc_mas_wbs");
			Service service = new Service();
			Cmcc_mas_wbsSoapBindingStub stub = new Cmcc_mas_wbsSoapBindingStub(url, service);
			SendSmsRequest reqeuest = new SendSmsRequest();
			reqeuest.setApplicationID("P000000000000055");
			reqeuest.setDeliveryResultRequest(true);
			reqeuest.setMessage(message);
			reqeuest.setMessageFormat(MessageFormat.GB2312);
			reqeuest.setSendMethod(SendMethodType.Normal);

			org.apache.axis.types.URI[] ary = new org.apache.axis.types.URI[cellphones.length];
			for (int i = 0; i < cellphones.length; i++) {
				ary[i] = new org.apache.axis.types.URI("tel:" + cellphones[i]);
			}
			reqeuest.setDestinationAddresses(ary);
			stub.sendSms(reqeuest);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
