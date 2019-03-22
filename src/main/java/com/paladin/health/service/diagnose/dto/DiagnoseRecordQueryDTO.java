package com.paladin.health.service.diagnose.dto;

import com.paladin.framework.common.OffsetPage;

import java.util.Date;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/3/20 14:46
 */
public class DiagnoseRecordQueryDTO  extends OffsetPage {

    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
