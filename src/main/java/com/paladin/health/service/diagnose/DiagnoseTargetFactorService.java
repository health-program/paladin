package com.paladin.health.service.diagnose;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.mapper.diagnose.DiagnoseTargetFactorMapper;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQueryDTO;
import com.paladin.health.service.diagnose.vo.DiagnoseRecordSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnoseTargetFactorService extends ServiceSupport<DiagnoseTargetFactor> {

	@Autowired
	private DiagnoseTargetFactorMapper diagnoseTargetFactorMapper;

	public int batchSave(List<DiagnoseTargetFactor> list) {
		return diagnoseTargetFactorMapper.insertByBatch(list);
	}

	public boolean removeByTarget(String targetId) {
		return diagnoseTargetFactorMapper.deleteByTarget(targetId) > 0;
	}

  public List<DiagnoseRecordSimpleVO> searchDiagnoseTargetFactor(DiagnoseRecordQueryDTO query) {
    return diagnoseTargetFactorMapper.searchDiagnoseTargetFactor(query);
  }
}