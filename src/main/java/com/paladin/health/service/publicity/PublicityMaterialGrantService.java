package com.paladin.health.service.publicity;

import java.util.List;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.mapper.publicity.PublicityMaterialGrantMapper;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.publicity.vo.CheckCountVO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantVO;

@Service
public class PublicityMaterialGrantService extends ServiceSupport<PublicityMaterialGrant>{
	
	@Autowired
	private PublicityMaterialGrantMapper publicityMaterialGrantMapper;
	
	public List<PublicityMaterialGrantVO> selectByQuery(PublicityMaterialGrantQueryDTO query){
		return publicityMaterialGrantMapper.selectByQuery(query);
	}
	
	public PublicityMaterialGrantVO getOne(String id){
		return publicityMaterialGrantMapper.getOne(id);
	}
	
	public 	CheckCountVO checkCount(String id){
		return publicityMaterialGrantMapper.checkCount(id);
	}
}
