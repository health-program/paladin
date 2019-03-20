package com.paladin.health.service.diagnose;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.diagnose.DiagnoseTargetFactorMapper;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.framework.core.ServiceSupport;

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

}