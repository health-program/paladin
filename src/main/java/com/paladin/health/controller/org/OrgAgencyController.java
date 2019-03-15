package com.paladin.health.controller.org;

import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.health.model.org.OrgAgency;
import com.paladin.health.model.org.OrgUser;
import com.paladin.health.service.org.OrgAgencyService;
import com.paladin.health.service.org.OrgUserService;
import com.paladin.health.service.org.dto.OrgAgencyQueryDTO;
import com.paladin.health.service.org.dto.OrgAgencyDTO;
import com.paladin.health.service.org.vo.OrgAgencyVO;

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
import java.util.List;

@Controller
@RequestMapping("/health/org/agency")
public class OrgAgencyController extends ControllerSupport {

    @Autowired
    private OrgAgencyService orgAgencyService;

    @Autowired
    private OrgUserService orgUserService;

    @RequestMapping("/index")
    public String index() {
        return "/health/org/org_agency_index";
    }
    
    
    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(OrgAgencyQueryDTO query) {
        return CommonResponse.getSuccessResponse(orgAgencyService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(orgAgencyService.get(id), new OrgAgencyVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/health/org/org_agency_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/health/org/org_agency_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid OrgAgencyDTO orgAgencyDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        OrgAgency model = beanCopy(orgAgencyDTO, new OrgAgency());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (orgAgencyService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(orgAgencyService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgAgencyDTO orgAgencyDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = orgAgencyDTO.getId();
		OrgAgency model = beanCopy(orgAgencyDTO, orgAgencyService.get(id));
		if (orgAgencyService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(orgAgencyService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        List<OrgUser> users = orgUserService.searchAll(new Condition(OrgUser.COLUMN_FIELD_AGENCY_ID, QueryType.EQUAL, id));
    if (users != null && users.size() > 0) {
        return  CommonResponse.getFailResponse("该机构下有关联人员,无法删除!");
    }else {
        return CommonResponse.getResponse(orgAgencyService.removeByPrimaryKey(id));
    }
    }
}