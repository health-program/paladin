package com.paladin.health.controller.publicity;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.org.OrgAgencyService;
import com.paladin.health.service.publicity.PublicityMaterialGrantService;
import com.paladin.health.service.publicity.PublicityMaterialService;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantDTO;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantVO;
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
@RequestMapping("/health/publicity/material/grant")
public class PublicityMaterialGrantController extends ControllerSupport {
	@Autowired
    private OrgAgencyService orgAgencyService;
	
    @Autowired
    private PublicityMaterialGrantService publicityMaterialGrantService;

    @Autowired
    private PublicityMaterialService publicityMaterialService;

    @Autowired
    private SysAttachmentService attachmentService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = PublicityMaterialGrantQueryDTO.class)
    public String index(Model model) {
    	 Calendar calendar = Calendar.getInstance();
    	 model.addAttribute("year", calendar.get(Calendar.YEAR));
    	 model.addAttribute("agencyList", orgAgencyService.findAll());
        return "/health/publicity/publicity_material_grant_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    @QueryOutputMethod(queryClass = PublicityMaterialGrantQueryDTO.class, paramIndex = 0)
    public Object findPage(PublicityMaterialGrantQueryDTO query) {
        return CommonResponse.getSuccessResponse(publicityMaterialGrantService.selectByQuery(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {   	
        return CommonResponse.getSuccessResponse(beanCopy(publicityMaterialGrantService.getOne(id), new PublicityMaterialGrantVO()));
    }
    
    @RequestMapping("/checkCount")
    @ResponseBody
    public Object checkCount(@RequestParam String id) {
        return CommonResponse.getSuccessResponse(publicityMaterialGrantService.checkCount(id));
    }
    
    @RequestMapping("/add")
    public String addInput(@RequestParam String id, Model model) {
		model.addAttribute("materialId", id);
        PublicityMaterialVO materialVO = publicityMaterialService.getOne(id);
        if (materialVO == null) {
            throw new BusinessException("发放的宣传资料不存在");
        }
        model.addAttribute("material",materialVO);
        return "/health/publicity/publicity_material_grant_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("agencyList", orgAgencyService.findAll());
        return "/health/publicity/publicity_material_grant_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid PublicityMaterialGrantDTO publicityMaterialGrantDTO, @RequestParam(required = false) MultipartFile[] attachmentFiles, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String receiveMaterialId = UUIDUtil.createUUID();
        int i =  publicityMaterialService.saveTargetMaterial(publicityMaterialGrantDTO, receiveMaterialId);
        if (i <= 0) {
            throw  new BusinessException("发送宣传资料失败");
        }
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(publicityMaterialGrantDTO.getAttachments(), attachmentFiles);
        if (attachments != null && attachments.size() > 4) {
            return CommonResponse.getErrorResponse("附件数量不能超过4张");
        }
        publicityMaterialGrantDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
		PublicityMaterialGrant model = beanCopy(publicityMaterialGrantDTO, new PublicityMaterialGrant());
		String id = UUIDUtil.createUUID();
		model.setId(id);
        Integer type = model.getGrantTargetType();
        if (type == 1) {
            model.setReceiveMaterialId(receiveMaterialId);
        }
        if (publicityMaterialGrantService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityMaterialGrantService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(publicityMaterialGrantService.removeRecordById(id));
    }
}