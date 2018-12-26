package com.paladin.health.controller.publicity;

import java.util.Calendar;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.org.OrgAgencyService;
import com.paladin.health.service.publicity.PublicityMaterialGrantService;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantDTO;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantVO;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
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
@RequestMapping("/health/publicity/material/grant")
public class PublicityMaterialGrantController extends ControllerSupport {
	@Autowired
    private OrgAgencyService orgAgencyService;
	
    @Autowired
    private PublicityMaterialGrantService publicityMaterialGrantService;

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
    public String addInput(@RequestParam String id, @RequestParam String name, @RequestParam String type, Model model) {
		model.addAttribute("workCycle",Calendar.getInstance().get(Calendar.YEAR));
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		model.addAttribute("materialId", id);
		HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
		model.addAttribute("agencyId", userSession.getAgencyId());
		model.addAttribute("agencyList", orgAgencyService.findAll());
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
    public Object save(@Valid PublicityMaterialGrantDTO publicityMaterialGrantDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		PublicityMaterialGrant model = beanCopy(publicityMaterialGrantDTO, new PublicityMaterialGrant());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (publicityMaterialGrantService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityMaterialGrantService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid PublicityMaterialGrantDTO publicityMaterialGrantDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = publicityMaterialGrantDTO.getId();
		PublicityMaterialGrant model = beanCopy(publicityMaterialGrantDTO, publicityMaterialGrantService.get(id));
		if (publicityMaterialGrantService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityMaterialGrantService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(publicityMaterialGrantService.removeByPrimaryKey(id));
    }
}