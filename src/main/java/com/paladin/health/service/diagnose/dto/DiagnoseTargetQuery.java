package com.paladin.health.service.diagnose.dto;

import com.paladin.framework.common.OffsetPage;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/3/20 14:46
 */
public class DiagnoseTargetQuery  extends OffsetPage {

    private String name;

    private String targetId;

    private String factorCode;

    private Integer factorType;

    private Integer factorLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public Integer getFactorType() {
        return factorType;
    }

    public void setFactorType(Integer factorType) {
        this.factorType = factorType;
    }

    public Integer getFactorLevel() {
        return factorLevel;
    }

    public void setFactorLevel(Integer factorLevel) {
        this.factorLevel = factorLevel;
    }
}
