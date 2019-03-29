package com.paladin.health.service.diagnose;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQuery;

import org.springframework.stereotype.Service;

@Service
public class DiagnoseRecordService extends ServiceSupport<DiagnoseRecord> {

	public PageResult<DiagnoseRecord> findRecordByTarget(DiagnoseRecordQuery query) {
		String targetId = query.getTargetId();
		if(targetId == null || targetId.length() == 0) {
			throw new BusinessException("必须传入查询目标ID");
		}
		
		return searchPage(query);
	}

}