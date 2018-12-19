package com.paladin.health.controller.publicity;

import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.service.publicity.PublicityMaterialService;
import com.paladin.health.service.publicity.dto.PublicityMaterialQueryDTO;
import com.paladin.health.service.publicity.dto.PublicityMaterialDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.framework.utils.uuid.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/health/publicity/material")
public class PublicityMaterialController extends ControllerSupport {

    @Autowired
    private PublicityMaterialService publicityMaterialService;

    @RequestMapping("/index")
    public String index() {
        return "/health/publicity/publicity_material_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(PublicityMaterialQueryDTO query) {
        return CommonResponse.getSuccessResponse(publicityMaterialService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(publicityMaterialService.get(id), new PublicityMaterialVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/health/publicity/publicity_material_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/health/publicity/publicity_material_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid PublicityMaterialDTO publicityMaterialDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        PublicityMaterial model = beanCopy(publicityMaterialDTO, new PublicityMaterial());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (publicityMaterialService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityMaterialService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid PublicityMaterialDTO publicityMaterialDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = publicityMaterialDTO.getId();
		PublicityMaterial model = beanCopy(publicityMaterialDTO, publicityMaterialService.get(id));
		if (publicityMaterialService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityMaterialService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(publicityMaterialService.removeByPrimaryKey(id));
    }
}