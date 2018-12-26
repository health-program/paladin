package com.paladin.health.service.publicity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.health.mapper.publicity.PublicityMaterialMapper;
import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.service.publicity.dto.PublicityMaterialQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;
import com.paladin.framework.core.ServiceSupport;

@Service
public class PublicityMaterialService extends ServiceSupport<PublicityMaterial> {
	
	@Autowired
	private PublicityMaterialMapper publicityMaterialMapper;
	
	public List<PublicityMaterialVO> selectByQuery(PublicityMaterialQueryDTO query) {
		return publicityMaterialMapper.selectByQuery(query);
	}
	
	public PublicityMaterialVO getOne(String id){
		return publicityMaterialMapper.getOne(id);
	}
}