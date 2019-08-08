package com.paladin.health.controller.xk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基于熙康知识库
 * 
 * @author TontoZhou
 * @since 2019/03/14
 */
@Controller
@RequestMapping("/health/demo")
public class XKDemoController {
	
	@RequestMapping("/evaluate/input")
	public Object diagnoseInput() {
		return "/health/xk/diagnose_input";
	}
	
}
