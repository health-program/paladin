package com.paladin.health.service.diagnose;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.mapper.diagnose.DiagnoseRecordMapper;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseRecordService extends ServiceSupport<DiagnoseRecord> {

	@Autowired
	private DiagnoseRecordMapper diagnoseRecordMapper;

	public PageResult<DiagnoseRecord> findRecordByTarget(DiagnoseRecordQuery query) {
		String targetId = query.getTargetId();
		if (targetId == null || targetId.length() == 0) {
			throw new BusinessException("必须传入查询目标ID");
		}

		return searchPage(query);
	}

	public DiagnoseRecord getRecordBySearchId(String searchId, String accessKey) {	
		DiagnoseRecord record = diagnoseRecordMapper.findRecordBySearchId(searchId,accessKey);		
		if (record == null) {
			throw new BusinessException("找不到对应健康处方记录");
		}
		return record;
	}

	public boolean updateCorrectPrescription(String id, String correctPrescription) {
		return diagnoseRecordMapper.updateCorrectPrescription(id, correctPrescription) > 0;
	}

}