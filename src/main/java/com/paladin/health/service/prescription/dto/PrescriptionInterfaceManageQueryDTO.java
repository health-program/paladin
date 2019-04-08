package com.paladin.health.service.prescription.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class PrescriptionInterfaceManageQueryDTO extends OffsetPage {

    // 第三方名称，例如某某社区
    private String name;

    @QueryCondition(type = QueryType.LIKE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}