package com.paladin.health.controller.org;

import com.google.common.base.Strings;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.org.OrgAnnualWork;
import com.paladin.health.service.org.OrgAnnualWorkService;
import com.paladin.health.service.org.dto.OrgAnnualWorkDTO;
import com.paladin.health.service.org.dto.OrgAnnualWorkQuery;
import com.paladin.health.service.org.vo.OrgAnnualWorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/health/org/annual/work")
public class OrgAnnualWorkController extends ControllerSupport {

    @Autowired
    private OrgAnnualWorkService orgAnnualWorkService;

    @Autowired
    private SysAttachmentService attachmentService;

    @RequestMapping("/index/{type}")
    public String index(@PathVariable String type,Model model) {
        model.addAttribute("type",type);
        return "/health/org/org_annual_work_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(OrgAnnualWorkQuery query) {
        return CommonResponse.getSuccessResponse(orgAnnualWorkService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(orgAnnualWorkService.get(id), new OrgAnnualWorkVO()));
    }
    
    @RequestMapping("/add/{type}")
    public String addInput(@PathVariable String type,Model model) {
        model.addAttribute("type",type);
        return "/health/org/org_annual_work_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, @RequestParam String type,Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("type", type);
        return "/health/org/org_annual_work_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid OrgAnnualWorkDTO orgAnnualWorkDTO, BindingResult bindingResult, @RequestParam(required = false) MultipartFile[] attachmentsFile) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
        String agencyId = userSession.getAgencyId();
        if (Strings.isNullOrEmpty(agencyId)) {
            throw new BusinessException("管理机构为空");
        }
        orgAnnualWorkDTO.setAgencyId(agencyId);
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(orgAnnualWorkDTO.getAttachments(), attachmentsFile);
        if (attachments != null && attachments.size() > 4) {
            return CommonResponse.getErrorResponse("附件数量不能超过4张");
        }
        orgAnnualWorkDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
        OrgAnnualWork model = beanCopy(orgAnnualWorkDTO, new OrgAnnualWork());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (orgAnnualWorkService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(orgAnnualWorkService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgAnnualWorkDTO orgAnnualWorkDTO, BindingResult bindingResult, @RequestParam(required = false) MultipartFile[] attachmentsFile) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
        String agencyId = userSession.getAgencyId();
        if (Strings.isNullOrEmpty(agencyId)) {
            throw new BusinessException("管理机构为空");
        }
        orgAnnualWorkDTO.setAgencyId(agencyId);
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(orgAnnualWorkDTO.getAttachments(), attachmentsFile);
        if (attachments != null && attachments.size() > 4) {
            return CommonResponse.getErrorResponse("附件数量不能超过4张");
        }
        orgAnnualWorkDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = orgAnnualWorkDTO.getId();
		OrgAnnualWork model = beanCopy(orgAnnualWorkDTO, orgAnnualWorkService.get(id));
		if (orgAnnualWorkService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(orgAnnualWorkService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(orgAnnualWorkService.removeByPrimaryKey(id));
    }
}