package com.paladin.health.service.diagnose;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.mapper.diagnose.DiagnoseRecordMapper;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQuery;

import com.paladin.health.service.diagnose.vo.DiagnoseRecordCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
		DiagnoseRecord record = diagnoseRecordMapper.findRecordBySearchId(searchId, accessKey);
		if (record == null) {
			throw new BusinessException("找不到对应健康处方记录");
		}
		return record;
	}

	public DiagnoseRecord getLastRecordByIdentificationId(String identificationId, String accessKey) {
		DiagnoseRecord record = diagnoseRecordMapper.findLastRecordByIdentificationId(identificationId);
		return record;
	}

	public boolean updateCorrectPrescription(String id, String correctPrescription, String sendMessage, int sendStatus, String sendError, String sendCellphone,
			String confirmer) {
		return diagnoseRecordMapper.updateCorrectPrescription(id, correctPrescription, sendMessage, sendStatus, sendError, sendCellphone, confirmer) > 0;
	}

	public DiagnoseRecord getHealthRecord(String targrtId) {
		if (targrtId == null || targrtId.length() == 0) {
			throw new BusinessException("身份证号码不能为空");
		}
		return diagnoseRecordMapper.findLastRecordByIdentificationId(targrtId);
	}

	public List<DiagnoseRecordCountVO> countRecordByHospitalName(String hospitalName, Date bgTime, Date endTime, boolean isSendMessage) {
		return isSendMessage ? diagnoseRecordMapper.countSendedRecordByHospitalName(hospitalName, bgTime, endTime)
				: diagnoseRecordMapper.countRecordByHospitalName(hospitalName, bgTime, endTime);
	}
}