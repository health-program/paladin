package com.paladin.health.controller.knowledge;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.knowledge.KnowledgeBase;
import com.paladin.health.service.knowledge.KnowledgeBaseService;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDTO;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseQuery;
import com.paladin.health.service.knowledge.vo.KnowledgeBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/health/knowledge/base")
public class KnowledgeBaseController extends ControllerSupport {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @RequestMapping("/index")
    public String index() {
        return "/health/knowledge/knowledge_base_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(KnowledgeBaseQuery query) {
        return CommonResponse.getSuccessResponse(knowledgeBaseService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(knowledgeBaseService.get(id), new KnowledgeBaseVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/health/knowledge/knowledge_base_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/health/knowledge/knowledge_base_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid KnowledgeBaseDTO knowledgeBaseDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        KnowledgeBase model = beanCopy(knowledgeBaseDTO, new KnowledgeBase());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (knowledgeBaseService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(knowledgeBaseService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid KnowledgeBaseDTO knowledgeBaseDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = knowledgeBaseDTO.getId();
		KnowledgeBase model = beanCopy(knowledgeBaseDTO, knowledgeBaseService.get(id));
		if (knowledgeBaseService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(knowledgeBaseService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(knowledgeBaseService.removeByPrimaryKey(id));
    }
}