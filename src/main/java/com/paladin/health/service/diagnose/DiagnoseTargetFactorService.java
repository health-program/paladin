package com.paladin.health.service.diagnose;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.mapper.diagnose.DiagnoseTargetFactorMapper;
import com.paladin.health.model.diagnose.DiagnoseTargetFactor;
import com.paladin.health.service.diagnose.dto.DiagnoseTargetQuery;
import com.paladin.health.service.diagnose.vo.DiagnoseTargetSimpleVO;
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
	
    public PageResult<DiagnoseTargetSimpleVO> searchDiagnoseTargetByQuery(DiagnoseTargetQuery query) {
        Page<DiagnoseTargetSimpleVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
        diagnoseTargetFactorMapper.searchDiagnoseTargetFactor(query);
        return  new PageResult<>(page);
    }
}