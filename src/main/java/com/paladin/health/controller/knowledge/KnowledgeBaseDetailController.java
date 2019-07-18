package com.paladin.health.controller.knowledge;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.knowledge.KnowledgeBaseDetail;
import com.paladin.health.service.knowledge.KnowledgeBaseDetailService;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDetailDTO;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDetailQuery;
import com.paladin.health.service.knowledge.vo.KnowledgeBaseDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/health/knowledge/base/detail")
public class KnowledgeBaseDetailController extends ControllerSupport {

    @Autowired
    private KnowledgeBaseDetailService knowledgeBaseDetailService;

    @RequestMapping("/index")
    public String index(String knowledgeId,String name,  Model model) {
        model.addAttribute("knowledgeId",knowledgeId);
        model.addAttribute("name",name);
        return "/health/knowledge/knowledge_base_detail_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(KnowledgeBaseDetailQuery query) {
        return CommonResponse.getSuccessResponse(knowledgeBaseDetailService.searchknowledgeSimpleInfo(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {
        KnowledgeBaseDetailVO vo;
        try {
            vo = knowledgeBaseDetailService.getKnowledgeDetailById(id);
        } catch (IOException e) {
            return CommonResponse.getFailResponse("json数据解析异常");
        }
        return CommonResponse.getSuccessResponse(vo);
    }
    
    @RequestMapping("/add")
    public String addInput(String name, String knowledgeId,  Model model) {
        model.addAttribute("name", name);
        model.addAttribute("knowledgeId", knowledgeId);
        return "/health/knowledge/knowledge_base_detail_add";
    }

    @RequestMapping("/info")
    public String detailInput(String id, String name, String knowledgeId, String title, Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("name", name);
    	model.addAttribute("knowledgeId", knowledgeId);
    	model.addAttribute("title", title);
        return "/health/knowledge/knowledge_base_detail_info";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid @RequestBody KnowledgeBaseDetailDTO knowledgeBaseDetailDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		return CommonResponse.getResponse(knowledgeBaseDetailService.saveOrUpdateKnowledgeBaseDetail(knowledgeBaseDetailDTO));
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid @RequestBody KnowledgeBaseDetailDTO knowledgeBaseDetailDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        return CommonResponse.getResponse(knowledgeBaseDetailService.saveOrUpdateKnowledgeBaseDetail(knowledgeBaseDetailDTO));
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(knowledgeBaseDetailService.removeByPrimaryKey(id));
    }
}