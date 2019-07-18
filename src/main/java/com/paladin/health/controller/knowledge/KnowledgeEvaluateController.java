package com.paladin.health.controller.knowledge;

import com.paladin.health.core.knowledge.KnowledgeManageContainer;
import com.paladin.health.model.knowledge.KnowledgeEvaluate;
import com.paladin.health.service.knowledge.KnowledgeEvaluateService;
import com.paladin.health.service.knowledge.dto.KnowledgeEvaluateQuery;
import com.paladin.health.service.knowledge.dto.KnowledgeEvaluateDTO;
import com.paladin.health.service.knowledge.vo.KnowledgeEvaluateVO;

import com.paladin.framework.core.ControllerSupport;
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
@RequestMapping("/health/knowledge/evaluate")
public class KnowledgeEvaluateController extends ControllerSupport {

	@Autowired
	private KnowledgeEvaluateService knowledgeEvaluateService;

	@GetMapping("/index")
	public String index(@RequestParam String serviceId, @RequestParam String serviceName, Model model) {
		model.addAttribute("serviceId", serviceId);
		model.addAttribute("serviceName", serviceName);
		return "/health/knowledge/knowledge_evaluate_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findPage(KnowledgeEvaluateQuery query) {
		return CommonResponse.getSuccessResponse(knowledgeEvaluateService.searchPage(query));
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(knowledgeEvaluateService.get(id), new KnowledgeEvaluateVO()));
	}

	@GetMapping("/add")
	public String addInput(@RequestParam String serviceId, @RequestParam String serviceName, Model model) {
		model.addAttribute("serviceId", serviceId);
		model.addAttribute("serviceName", serviceName);
		return "/health/knowledge/knowledge_evaluate_add";
	}

	@GetMapping("/detail")
	public String detailInput(@RequestParam String serviceId, @RequestParam String serviceName, @RequestParam String id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("serviceId", serviceId);
		model.addAttribute("serviceName", serviceName);
		return "/health/knowledge/knowledge_evaluate_detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid KnowledgeEvaluateDTO knowledgeEvaluateDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		KnowledgeEvaluate model = beanCopy(knowledgeEvaluateDTO, new KnowledgeEvaluate());
		if (knowledgeEvaluateService.save(model) > 0) {
			KnowledgeManageContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(knowledgeEvaluateService.get(model.getCode()), new KnowledgeEvaluateVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping("/update")
	@ResponseBody
	public Object update(@Valid KnowledgeEvaluateDTO knowledgeEvaluateDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = knowledgeEvaluateDTO.getCode();
		KnowledgeEvaluate origin = knowledgeEvaluateService.get(id);
		if (origin != null) {
			KnowledgeEvaluate model = beanCopy(knowledgeEvaluateDTO, origin);
			if (knowledgeEvaluateService.update(model) > 0) {
				KnowledgeManageContainer.updateData();
				return CommonResponse.getSuccessResponse(beanCopy(knowledgeEvaluateService.get(id), new KnowledgeEvaluateVO()));
			}
		}

		return CommonResponse.getFailResponse();
	}

	@RequestMapping(value = "/start", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object start(@RequestParam String id) {
		return CommonResponse.getResponse(knowledgeEvaluateService.startEvaluate(id));
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object stop(@RequestParam String id) {
		return CommonResponse.getResponse(knowledgeEvaluateService.stopEvaluate(id));
	}

}