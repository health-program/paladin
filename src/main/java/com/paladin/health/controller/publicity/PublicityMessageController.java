package com.paladin.health.controller.publicity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.publicity.PublicityMessageService;

@Controller
@RequestMapping("/health/publicity")
public class PublicityMessageController {

	@Autowired
	PublicityMessageService publicityMessageService;
	
	@RequestMapping("/index")
	public String index() {
		
		
		return "/health/publicity/message_index";		
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public Object findAll() {
		return CommonResponse.getSuccessResponse(publicityMessageService.findAll());
	}
	
}
