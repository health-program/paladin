package com.paladin.health.service.diagnose.vo;

/**
 * <短信统计>
 *
 * @author Huangguochen
 * @create 2019/7/12 8:55
 */
public class DiagnoseRecordCountVO {

    private String hospitalName;

    private Integer total;

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
