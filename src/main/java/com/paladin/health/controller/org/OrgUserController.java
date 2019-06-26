package com.paladin.health.controller.org;

import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.service.org.OrgUserService;
import com.paladin.health.service.org.dto.OrgUserDTO;
import com.paladin.health.service.org.dto.OrgUserQueryDTO;
import com.paladin.health.service.org.vo.OrgUserVO;
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
    public String index() {
        return "/health/org/org_user_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(OrgUserQueryDTO query) {
        return CommonResponse.getSuccessResponse(orgUserService.findOwnUsersPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(beanCopy(orgUserService.get(id), new OrgUserVO()));
    }
    
	@RequestMapping("/find/own/role")
	@ResponseBody
	public Object findOwnRoles() {
		return CommonResponse.getSuccessResponse(orgUserService.searchOwnedRoles());
	}
    @RequestMapping("/add")
    public String addInput(Model model) {
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
        HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
        if (userSession.isAdminRoleLevel() || orgUserDTO.getAgencyId().equals(userSession.getAgencyId())){
            return CommonResponse.getSuccessResponse(orgUserService.createUser(orgUserDTO));
        }else {
            return  CommonResponse.getFailResponse("没有权限添加用户到该机构");
        }
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgUserDTO orgUserDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
        if (userSession.isAdminRoleLevel() || orgUserDTO.getAgencyId().equals(userSession.getAgencyId())){
            return CommonResponse.getSuccessResponse(orgUserService.updateUser(orgUserDTO));
        }else {
            return  CommonResponse.getFailResponse("没有权限添加用户到该机构");
        }
	}

    @RequestMapping("/reset")
    @ResponseBody
    public Object reset(@RequestParam String account) {
        return CommonResponse.getResponse(sysUserService.resetPassword(account));
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(orgUserService.deleteUserById(id));
    }
}