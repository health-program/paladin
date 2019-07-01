package com.paladin.health.controller.publicity;

import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.publicity.PublicityMaterialGrantCountService;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @author 黄伟华
 * @version 2018年12月28日 下午12:53:17 
 */
@Controller
@RequestMapping("/health/publicity/material/grant/count")
public class PublicityMaterialGrantCountController{
    
    @Autowired
    private PublicityMaterialGrantCountService publicityMaterialGrantCountService;
    
    @RequestMapping("/index")
    public String index(){return "/health/publicity/publicity_material_grant_count_index";}
    
    @RequestMapping("/agency/index")
    public String agencyIndex(){return "/health/publicity/publicity_material_grant_agency_index";}
    
    
    @RequestMapping("/year")
    @ResponseBody
    @QueryOutputMethod(queryClass = PublicityMaterialGrantQueryCountDTO.class, paramIndex = 0)
    public Object publictyCount(PublicityMaterialGrantQueryCountDTO query) {
        return CommonResponse.getSuccessResponse(publicityMaterialGrantCountService.publictyCount(query));
    }
    
    @RequestMapping("/agency")
    @ResponseBody
    @QueryOutputMethod(queryClass = PublicityMaterialGrantQueryCountDTO.class, paramIndex = 0)
    public Object publictyAgencyCount(PublicityMaterialGrantQueryCountDTO query) {
        if (query.getId() == null) {
            return CommonResponse.getSuccessResponse(publicityMaterialGrantCountService.publictyAgencyPageCount(query));
        }else {
            return CommonResponse.getSuccessResponse(publicityMaterialGrantCountService.publictyAgencyChildsCount(query.getId()));
        }
    }

}
