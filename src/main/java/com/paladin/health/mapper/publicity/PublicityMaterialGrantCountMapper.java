package com.paladin.health.mapper.publicity;



import java.util.List;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryCountDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantCountVO;

/**   
 * @author 黄伟华
 * @version 2018年12月28日 下午1:30:32 
 */
public interface PublicityMaterialGrantCountMapper extends CustomMapper<PublicityMaterialGrant>{
    
     List<PublicityMaterialGrantCountVO> publictyCount(PublicityMaterialGrantQueryCountDTO query);
     
     List<PublicityMaterialGrantCountVO>publictyAgencyCount(PublicityMaterialGrantQueryCountDTO query);
    
}
