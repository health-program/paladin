package com.paladin.health.service.publicity;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.mapper.publicity.PublicityMaterialGrantMapper;
import com.paladin.health.model.publicity.PublicityMaterial;
import com.paladin.health.model.publicity.PublicityMaterialGrant;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryDTO;
import com.paladin.health.service.publicity.vo.CheckCountVO;
import com.paladin.health.service.publicity.vo.PublicityMaterialGrantVO;
import com.paladin.health.service.publicity.vo.PublicityMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicityMaterialGrantService extends ServiceSupport<PublicityMaterialGrant>{
	
	@Autowired
	private PublicityMaterialGrantMapper publicityMaterialGrantMapper;

	@Autowired
	private PublicityMaterialService publicityMaterialService;

	public List<PublicityMaterialGrantVO> selectByQuery(PublicityMaterialGrantQueryDTO query){
		return publicityMaterialGrantMapper.selectByQuery(query);
	}
	
	public PublicityMaterialGrantVO getOne(String id){
		return publicityMaterialGrantMapper.getOne(id);
	}
	
	public 	CheckCountVO checkCount(String id){
		return publicityMaterialGrantMapper.checkCount(id);
	}

	public int removeRecordById(String id) {
		PublicityMaterialGrant record = get(id);
		if (record == null) {
			throw new BusinessException("记录不存在");
		}
		int count;
		int i;
		Integer type = record.getGrantTargetType();
		PublicityMaterial publicityMaterial = new PublicityMaterial();
		PublicityMaterialVO receiveMaterial = null;
		if (type == 1) {
			String receiveMaterialId = record.getReceiveMaterialId();
			receiveMaterial = publicityMaterialService.getOne(receiveMaterialId);
			if (receiveMaterial == null) {
				throw new BusinessException("接收的宣传资料不存在");
			}
			count = receiveMaterial.getCount();
			if (count == 0) {
				return removeByPrimaryKey(id);
			}else {
				publicityMaterialService.updateCountById(receiveMaterialId,count);
			}
		}else {
		    count = record.getCount();
		}
		String materialId = record.getMaterialId();
		PublicityMaterialVO material = publicityMaterialService.getOne(materialId);
		if (material != null) {
			i = publicityMaterialService.increaseCountById(materialId, count);
		}else {
			if (receiveMaterial == null) {
				throw new BusinessException("接收的宣传资料不存在");
			}
			SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(receiveMaterial,publicityMaterial);
			publicityMaterial.setId(UUIDUtil.createUUID());
			publicityMaterial.setCount(count);
			publicityMaterial.setAgencyId(record.getGrantorAgency());
			i = publicityMaterialService.save(publicityMaterial);
		}
	  	return  i > 0 ? removeByPrimaryKey(id) : 0;
 	}

}
