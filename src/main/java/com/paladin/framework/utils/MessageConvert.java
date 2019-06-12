package com.paladin.framework.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageConvert<T>{

	@SuppressWarnings("unused")
	private static  Logger logger = LoggerFactory.getLogger(MessageConvert.class);
	
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, Class<T> clazz) {
        JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			Unmarshaller um = context.createUnmarshaller();
			return (T) um.unmarshal(new StringReader(xml));    
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
        return null;
    }
	
	public static <T> String beanToXml(T t) {
        JAXBContext context;
        StringWriter sw = new StringWriter();
		try {
			context = JAXBContext.newInstance(t.getClass());
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化
			mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
			mar.setProperty(Marshaller.JAXB_FRAGMENT, false); 
			mar.marshal(t, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
        return sw.toString();
    }
	
}
