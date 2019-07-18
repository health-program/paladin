package com.paladin.health.controller.knowledge;

import com.paladin.health.core.knowledge.KnowledgeManageContainer;
import com.paladin.health.model.knowledge.KnowledgeService;
import com.paladin.health.service.knowledge.KnowledgeServiceService;
import com.paladin.health.service.knowledge.dto.KnowledgeServiceQuery;
import com.paladin.health.service.knowledge.dto.KnowledgeServiceDTO;
import com.paladin.health.service.knowledge.vo.KnowledgeServiceVO;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.Valid;

@Controller
@RequestMapping("/health/knowledge/service")
public class KnowledgeServiceController extends ControllerSupport {

	@Autowired
	private KnowledgeServiceService knowledgeServiceService;

	@GetMapping("/index")
	@QueryInputMethod(queryClass = KnowledgeServiceQuery.class)
	public String index() {
		return "/health/knowledge/knowledge_service_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@QueryOutputMethod(queryClass = KnowledgeServiceQuery.class, paramIndex = 0)
	public Object findPage(KnowledgeServiceQuery query) {
		return CommonResponse.getSuccessResponse(knowledgeServiceService.searchPage(query));
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(knowledgeServiceService.get(id), new KnowledgeServiceVO()));
	}

	@GetMapping("/add")
	public String addInput() {
		return "/health/knowledge/knowledge_service_add";
	}

	@GetMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/knowledge/knowledge_service_detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid KnowledgeServiceDTO knowledgeServiceDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		KnowledgeService model = beanCopy(knowledgeServiceDTO, new KnowledgeService());
		if (knowledgeServiceService.save(model) > 0) {
			KnowledgeManageContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(knowledgeServiceService.get(model.getServiceCode()), new KnowledgeServiceVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping("/update")
	@ResponseBody
	public Object update(@Valid KnowledgeServiceDTO knowledgeServiceDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = knowledgeServiceDTO.getServiceCode();
		KnowledgeService origin = knowledgeServiceService.get(id);
		if (origin != null) {
			KnowledgeService model = beanCopy(knowledgeServiceDTO, origin);
			if (knowledgeServiceService.update(model) > 0) {
				KnowledgeManageContainer.updateData();
				return CommonResponse.getSuccessResponse(beanCopy(knowledgeServiceService.get(id), new KnowledgeServiceVO()));
			}
		}

		return CommonResponse.getFailResponse();
	}
	
	@RequestMapping(value = "/start", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object start(@RequestParam String id) {
		return CommonResponse.getResponse(knowledgeServiceService.startService(id));
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object stop(@RequestParam String id) {
		return CommonResponse.getResponse(knowledgeServiceService.stopService(id));
	}

}