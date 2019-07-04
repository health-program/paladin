package com.paladin.health.service.publicity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.core.AgencyContainer;
import com.paladin.health.mapper.publicity.PublicityMaterialGrantCountMapper;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryCountDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**   
 * @author 黄伟华
 * @version 2018年12月28日 下午1:36:03 
 */
@Service
public class PublicityMaterialGrantCountService extends ServiceSupport<PublicityMaterialGrant>{
    
    @Autowired
    private PublicityMaterialGrantCountMapper publicityMaterialGrantCountMapper;

    @Value("${grantor.agency.id}")
    private  String grantorAgencyId;

    
    public PageResult<PublicityMaterialGrantCountVO> publictyCount(PublicityMaterialGrantQueryCountDTO query){
        Page<PublicityMaterialGrantCountVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
         publicityMaterialGrantCountMapper.publictyYearCount(query,grantorAgencyId);
         return new PageResult<>(page);
    }
    
    public PageResult<PublicityMaterialGrantCountVO> publictyAgencyPageCount(PublicityMaterialGrantQueryCountDTO query){
        Page<PublicityMaterialGrantCountVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        if (isQueryEmpty(query)) {
            publictyAgencyCount(query);
        }else {
            publicityMaterialGrantCountMapper.publictyAgencyCountByIds(null,query);
        }
        return new PageResult<>(page);
    }

    private boolean isQueryEmpty(PublicityMaterialGrantQueryCountDTO query) {
        String agencyName = query.getAgencyName();
        String workCycle = query.getWorkCycle();
        return Strings.isNullOrEmpty(agencyName) && Strings.isNullOrEmpty(workCycle);
    }

    public List<PublicityMaterialGrantCountVO> publictyAgencyChildsCount(String id) {
        List<PublicityMaterialGrantCountVO> lists = null;
        List<String> childrenIds = AgencyContainer.getAgencyChildrenIds(id);
        if (childrenIds != null && childrenIds.size() > 0) {
            lists =  publicityMaterialGrantCountMapper.publictyAgencyCountByIds(childrenIds,new PublicityMaterialGrantQueryCountDTO());
            lists.stream().peek(m -> {
                AgencyContainer.Agency parent = AgencyContainer.getAgency(m.getId()).getParent();
                m.setpId(parent.getId());
                m.setpName(parent.getName());
            }).collect(Collectors.toList());
        }
        return lists;
    }

    @SuppressWarnings("unused")
    private List<PublicityMaterialGrantCountVO> publictyAgencyCount(PublicityMaterialGrantQueryCountDTO query){
             List<PublicityMaterialGrantCountVO> pCounts;
            List<AgencyContainer.Agency> roots = AgencyContainer.getRoots();
            List<String> parentAgencyIds = roots.stream().map(AgencyContainer.Agency::getId).collect(Collectors.toList());
            pCounts = publicityMaterialGrantCountMapper.publictyAgencyCountByIds(parentAgencyIds,query);
            for (PublicityMaterialGrantCountVO p : pCounts) {
                if (p.getId() != null) {
                    List<String> childsIds = AgencyContainer.getAgencyAllChildrenIds(p.getId());
                    if (childsIds != null && childsIds.size() > 0) {
                        List<PublicityMaterialGrantCountVO> lists = publicityMaterialGrantCountMapper.publictyAgencyCountByIds(childsIds,new PublicityMaterialGrantQueryCountDTO());
                        int count = 0;
                        for (PublicityMaterialGrantCountVO c : lists) {
                            count += c.getTotal();
                        }
                        p.setTotal( p.getTotal() + count);
                    }
                }
            }
        return pCounts;
    }

}
