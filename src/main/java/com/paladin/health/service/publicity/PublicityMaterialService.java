package com.paladin.health.service.publicity;

import com.google.common.base.Strings;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.mapper.publicity.PublicityMaterialMapper;
import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantDTO;
import com.paladin.health.service.publicity.dto.PublicityMaterialQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Transactional
	public int saveTargetMaterial(PublicityMaterialGrantDTO publicityMaterialGrantDTO) {
		String materialId = publicityMaterialGrantDTO.getMaterialId();
		if (Strings.isNullOrEmpty(materialId)) {
			throw new BusinessException("发放的宣传资料不存在");
		}
		PublicityMaterialVO materialVO = getOne(materialId);
		if (materialVO == null) {
			throw new BusinessException("发放的宣传资料不存在");
		}
		Integer count = publicityMaterialGrantDTO.getCount();
		int i = updateCountById(materialId, count);
		if (i == 0) {
			throw new BusinessException("宣传资料发放的数量不正确");
		}
		Integer type = publicityMaterialGrantDTO.getGrantTargetType();
		if (type == 2) {
			return  i;
		}
		materialVO.setAgencyId(publicityMaterialGrantDTO.getGrantTarget());
		materialVO.setCount(count);
		materialVO.setId(UUIDUtil.createUUID());
		PublicityMaterial publicityMaterial = new PublicityMaterial();
		SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(materialVO,publicityMaterial);
		return save(publicityMaterial);
	}

	@Transactional
	  public int updateCountById(String id, int count) { return  publicityMaterialMapper.updateCountById(id,count);}
}