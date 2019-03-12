package com.paladin.health.mapper.publicity;

import java.util.List;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryDTO;
import com.paladin.health.service.publicity.vo.CheckCountVO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantVO;

public interface PublicityMaterialGrantMapper extends CustomMapper<PublicityMaterialGrant>{
	public List<PublicityMaterialGrantVO> selectByQuery(PublicityMaterialGrantQueryDTO query);
	public PublicityMaterialGrantVO getOne(String id);
	public CheckCountVO checkCount(String id);
}
