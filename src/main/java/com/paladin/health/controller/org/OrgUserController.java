package com.paladin.health.controller.org;

import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.health.model.org.OrgUser;
import com.paladin.health.service.org.OrgUserService;
import com.paladin.health.service.org.dto.OrgUserQueryDTO;
import com.paladin.health.service.org.dto.OrgUserDTO;
import com.paladin.health.service.org.vo.OrgUserVO;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.framework.utils.uuid.UUIDUtil;

import com.paladin.health.service.syst.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/health/org/user")
public class OrgUserController extends ControllerSupport {


    @Autowired
    private OrgUserService orgUserService;

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/index")
    @QueryInputMethod(queryClass = OrgUserQueryDTO.class)
    public String index() {
        return "/health/org/org_user_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    @QueryOutputMethod(queryClass = OrgUserQueryDTO.class,paramIndex = 0)
    public Object findPage(OrgUserQueryDTO query) {
        return CommonResponse.getSuccessResponse(orgUserService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(beanCopy(orgUserService.get(id), new OrgUserVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/health/org/org_user_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/health/org/org_user_detail";
    }

    @RequestMapping("/validate")
    @ResponseBody
    public Object validate(String account) {
        return CommonResponse.getResponse(sysUserService.validateAccount(account));
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid OrgUserDTO orgUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        OrgUser model = beanCopy(orgUserDTO, new OrgUser());
        String id = UUIDUtil.createUUID();
        model.setId(id);
        if (sysUserService.createUserAccount(orgUserDTO.getAccount(), id) > 0) {
            if (orgUserService.save(model) > 0) {
                return CommonResponse.getSuccessResponse(orgUserService.get(id));
            }
        }
        return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgUserDTO orgUserDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = orgUserDTO.getId();
		OrgUser model = beanCopy(orgUserDTO, orgUserService.get(id));
		if (orgUserService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(orgUserService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/reset")
    @ResponseBody
    public Object reset(@RequestParam String account) {
        return CommonResponse.getResponse(sysUserService.resetPassword(account));
    }
}