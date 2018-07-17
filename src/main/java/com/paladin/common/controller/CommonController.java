package com.paladin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;

@Controller
@RequestMapping("/common")
public class CommonController {
		
	@RequestMapping("/constant")
    @ResponseBody
    public Object enumConstants(@RequestParam("code") String[] code) {		
		return CommonResponse.getSuccessResponse(ConstantsContainer.getTypeChildren(code));	
	}
	
}
