package com.paladin.health.mapper.publicity;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.service.publicity.dto.PublicityMaterialQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublicityMaterialMapper extends CustomMapper<PublicityMaterial> {
	public List<PublicityMaterialVO> selectByQuery(PublicityMaterialQueryDTO query);

	public PublicityMaterialVO getOne(String id);

    int updateCountById(@Param("id") String id, @Param("count") int count);

	int increaseCountById(@Param("id") String id, @Param("count") int count);
}