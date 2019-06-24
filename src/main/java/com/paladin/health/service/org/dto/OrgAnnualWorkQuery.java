package com.paladin.health.service.org.dto;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class OrgAnnualWorkQuery extends OffsetPage {
    // 类型
    private Integer type;

    // 名称
    private String name;

    // 负责人
    private String principal;

    @QueryCondition(type = QueryType.EQUAL)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @QueryCondition(type = QueryType.LIKE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @QueryCondition(type = QueryType.LIKE)
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}