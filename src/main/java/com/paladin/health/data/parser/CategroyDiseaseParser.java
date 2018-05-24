package com.paladin.health.data.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CategroyDiseaseParser extends PageParser{
	
	public Map<String,List<String>> parse() throws IOException {

		Document doc = getDocument("https://www.quyiyuan.com/diseases/");	
		Map<String,List<String>> result = new HashMap<>();	
		Elements  chronicDiseaseElements = doc.getElementsByClass("chronic-disease-list");
		if(chronicDiseaseElements !=null && chronicDiseaseElements.size() ==1) {		
			Element chronicDiseaseElement =chronicDiseaseElements.get(0);
			result.put("常见慢性病", parseChronicDisease(chronicDiseaseElement));
		}
		
		Elements  otherElements = doc.getElementsByClass("disease-list-ct");
		if(otherElements != null) {
			
			result.put("常见病", parseOtherDisease(otherElements.get(0)));
			result.put("男性病", parseOtherDisease(otherElements.get(0)));
			result.put("女性病", parseOtherDisease(otherElements.get(0)));
			result.put("孕妇疾病", parseOtherDisease(otherElements.get(0)));
			result.put("儿童疾病", parseOtherDisease(otherElements.get(0)));
			result.put("三高疾病", parseOtherDisease(otherElements.get(0)));
			result.put("糖尿病", parseOtherDisease(otherElements.get(0)));
		}
		
		
		return result;
	}
	
	private List<String> parseChronicDisease(Element chronicDiseaseElement){
		return null;
	}
	
	private List<String> parseOtherDisease(Element element){
		return null;
	}
	
}
