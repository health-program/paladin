package com.paladin.health.service.diagnose;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.diagnose.dto.DiagnoseRecordQueryDTO;
import com.paladin.health.service.diagnose.vo.DiagnoseRecordSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseRecordService extends ServiceSupport<DiagnoseRecord>{

    @Autowired
    private DiagnoseTargetFactorService diagnoseTargetFactorService;

    public PageResult<DiagnoseRecordSimpleVO> searchDiagnoseRecordsByQuery(DiagnoseRecordQueryDTO query) {
      /*  PageResult<DiagnoseTarget> results = diagnoseTargetService.searchPage(query);
        List<String> ids = new ArrayList<>(5);
        if (results != null && results.getData() != null && results.getData().size() > 0 ) {
            List<DiagnoseTarget> targets = results.getData();
          for (DiagnoseTarget target : targets) {
            ids.add(target.getId());
          }
        }*/
        Page<DiagnoseRecordSimpleVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
        diagnoseTargetFactorService.searchDiagnoseTargetFactor(query);
        return  new PageResult<>(page);
    }
}