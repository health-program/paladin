
package com.paladin.health.service.sms;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface SmsService {

	@WebMethod(operationName = "submit", action = "submit")
	@WebResult(name = "return", targetNamespace = "http://webservice.service.netmatch.com/")
	public String submit(
			@WebParam(name = "arg0", targetNamespace = "http://webservice.service.netmatch.com/")
			String arg0);

	@WebMethod(operationName = "balance", action = "balance")
	@WebResult(name = "return", targetNamespace = "http://webservice.service.netmatch.com/")
	public String balance(
			@WebParam(name = "arg0", targetNamespace = "http://webservice.service.netmatch.com/")
			String arg0);
	
	@WebMethod(operationName = "receive", action = "receive")
	@WebResult(name = "return", targetNamespace = "http://webservice.service.netmatch.com/")
	public String receive(
			@WebParam(name = "arg0", targetNamespace = "http://webservice.service.netmatch.com/")
			String arg0);

}
