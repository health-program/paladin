package com.paladin.health.controller.publicity;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.service.org.OrgAgencyService;
import com.paladin.health.service.publicity.PublicityMaterialService;
import com.paladin.health.service.publicity.dto.PublicityMaterialDTO;
import com.paladin.health.service.publicity.dto.PublicityMaterialQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/health/publicity/material")
public class PublicityMaterialController extends ControllerSupport {

    @Autowired
    private PublicityMaterialService publicityMaterialService;

    @Autowired
    private OrgAgencyService orgAgencyService;

    @Autowired
    private SysAttachmentService attachmentService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = PublicityMaterialQueryDTO.class)
    public String index(Model model) {
    	 Calendar calendar = Calendar.getInstance();//日历对象
    	 model.addAttribute("year", calendar.get(Calendar.YEAR));
        HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
        model.addAttribute("agencyList", orgAgencyService.findAll());
        if ( userSession.getRoleLevel() == HealthUserSession.ROLE_LEVEL_AGENCY ) {
            model.addAttribute("agencyId", userSession.getAgencyId());
        }
        return "/health/publicity/publicity_material_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    @QueryOutputMethod(queryClass = PublicityMaterialQueryDTO.class, paramIndex = 0)
    public Object findPage(PublicityMaterialQueryDTO query) {
        return CommonResponse.getSuccessResponse(publicityMaterialService.selectByQuery(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(beanCopy(publicityMaterialService.getOne(id), new PublicityMaterialVO()));
    }
    
    @RequestMapping("/add")
    public String addInput(Model model) {
    	model.addAttribute("workCycle",Calendar.getInstance().get(Calendar.YEAR));
    	HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
		model.addAttribute("agencyId", userSession.getAgencyId());
        return "/health/publicity/publicity_material_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("agencyList", orgAgencyService.findAll());
        return "/health/publicity/publicity_material_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid PublicityMaterialDTO publicityMaterialDTO, BindingResult bindingResult, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(publicityMaterialDTO.getAttachments(), attachmentFiles);
        if (attachments != null && attachments.size() > 4) {
            return CommonResponse.getErrorResponse("附件数量不能超过4张");
        }
        publicityMaterialDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
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
    public Object update(@Valid PublicityMaterialDTO publicityMaterialDTO, BindingResult bindingResult, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(publicityMaterialDTO.getAttachments(), attachmentFiles);
        if (attachments != null && attachments.size() > 4) {
            return CommonResponse.getErrorResponse("附件数量不能超过4张");
        }
        publicityMaterialDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
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
        return CommonResponse.getResponse(publicityMaterialService.removeMaterialById(id));
    }
}