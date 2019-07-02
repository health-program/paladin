package com.paladin.health.controller.org;

import com.google.common.base.Strings;
import com.paladin.health.core.AgencyContainer;
import com.paladin.health.core.AgencyContainer.Agency;
import com.paladin.health.model.org.OrgAgency;
import com.paladin.health.service.org.OrgAgencyService;
import com.paladin.health.service.org.OrgUserService;
import com.paladin.health.service.org.dto.OrgAgencyDTO;

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

	private OrgAgency getOrgAgency(String id) {
		Agency agency = AgencyContainer.getAgency(id);
		return agency == null ? null : agency.getOrgAgency();
	}

	@RequestMapping("/find/own/tree")
	@ResponseBody
	public Object findOwnTree() {
		return CommonResponse.getSuccessResponse(orgUserService.findOwnedAgencies());
	}

	@RequestMapping("/find/all/tree")
	@ResponseBody
	public Object findAllTree() {
		return CommonResponse.getSuccessResponse(AgencyContainer.getRoots());
	}

	@RequestMapping("/find/all/list")
	@ResponseBody
	public Object findAllList() {
		return CommonResponse.getSuccessResponse(orgAgencyService.findAll());
	}

	@RequestMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(getOrgAgency(id));
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

		String pid = model.getParentId();
		if (Strings.isNullOrEmpty(pid)) {
			model.setParentId(null);
		}
		if (pid != null && pid.length() > 0 && AgencyContainer.getAgency(pid) == null) {
			return CommonResponse.getFailResponse();
		}

		if (orgAgencyService.save(model) > 0) {
			AgencyContainer.updateData();
			return CommonResponse.getSuccessResponse(getOrgAgency(id));
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
		Agency agency = AgencyContainer.getAgency(id);
		String nowPid = orgAgencyDTO.getParentId();
		if (Strings.isNullOrEmpty(nowPid)) {
			orgAgencyDTO.setParentId(null);
		}
		if(nowPid != null && nowPid.length() >0) {				
			if(nowPid.equals(id)) {
				return CommonResponse.getFailResponse("不能选择自己为上级机构");
			}
			
			if(AgencyContainer.getAgency(nowPid) == null) {
				return CommonResponse.getFailResponse("找不到对应机构");
			}
			
			if(AgencyContainer.isChild(id, nowPid)) {
				return CommonResponse.getFailResponse("不能修改上级机构为自己的子机构");
			}	
		} 

		OrgAgency model = beanCopy(orgAgencyDTO, agency.getOrgAgency());
		if (orgAgencyService.update(model) > 0) {
			AgencyContainer.updateData();
			return CommonResponse.getSuccessResponse(getOrgAgency(id));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(orgAgencyService.removeAgency(id), "请先删除机构下人员和子机构");
	}
}