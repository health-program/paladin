package com.paladin.health.mapper.publicity;

import java.util.List;
import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.service.publicity.dto.PublicityMaterialQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;
import com.paladin.framework.mybatis.CustomMapper;

public interface PublicityMaterialMapper extends CustomMapper<PublicityMaterial>{
	public List<PublicityMaterialVO> selectByQuery(PublicityMaterialQueryDTO query);
	public PublicityMaterialVO getOne(String id);
}