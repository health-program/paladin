package com.paladin.common.controller.org;

import com.paladin.common.model.org.OrgPermission;
import com.paladin.common.service.org.OrgPermissionService;
import com.paladin.common.service.org.dto.OrgPermissionQueryDTO;
import com.paladin.common.service.org.dto.OrgPermissionDTO;
import com.paladin.common.service.org.vo.OrgPermissionVO;

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
@RequestMapping("/common/org/permission")
public class OrgPermissionController extends ControllerSupport {

    @Autowired
    private OrgPermissionService orgPermissionService;

    @RequestMapping("/index")
    public String index() {
        return "/common/org/org_permission_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(OrgPermissionQueryDTO query) {
        return CommonResponse.getSuccessResponse(orgPermissionService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(orgPermissionService.get(id), new OrgPermissionVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/common/org/org_permission_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/common/org/org_permission_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid OrgPermissionDTO orgPermissionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        OrgPermission model = beanCopy(orgPermissionDTO, new OrgPermission());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (orgPermissionService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(orgPermissionService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgPermissionDTO orgPermissionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = orgPermissionDTO.getId();
		OrgPermission model = beanCopy(orgPermissionDTO, orgPermissionService.get(id));
		if (orgPermissionService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(orgPermissionService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(orgPermissionService.removeByPrimaryKey(id));
    }
}