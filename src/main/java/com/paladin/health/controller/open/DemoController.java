package com.paladin.health.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/open/demo")
public class DemoController {
	
	@RequestMapping("/index")
	public Object diagnoseIndex() {
		return "/health/open/demo_index";
	}
	
}
