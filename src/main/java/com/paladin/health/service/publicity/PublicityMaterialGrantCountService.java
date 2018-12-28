package com.paladin.health.service.publicity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.mapper.publicity.PublicityMaterialGrantCountMapper;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryCountDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantCountVO;

/**   
 * @author 黄伟华
 * @version 2018年12月28日 下午1:36:03 
 */
@Service
public class PublicityMaterialGrantCountService extends ServiceSupport<PublicityMaterialGrant>{
    
    @Autowired
    private PublicityMaterialGrantCountMapper publicityMaterialGrantCountMapper;
    
    public PageResult<PublicityMaterialGrantCountVO> publictyCount(PublicityMaterialGrantQueryCountDTO query){
        Page<PublicityMaterialGrantCountVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
         publicityMaterialGrantCountMapper.publictyCount(query);
         return new PageResult<>(page);
    }
    
    public List<PublicityMaterialGrantCountVO> publictyAgencyCount(PublicityMaterialGrantQueryCountDTO query){
        
         return publicityMaterialGrantCountMapper.publictyAgencyCount(query);
    }
    
    
}
