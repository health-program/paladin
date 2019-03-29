package com.paladin.health.service.diagnose.vo;

import java.util.Date;

/**
 * <病人诊断历史记录前台简单展示>
 *
 * @author Huangguochen
 * @create 2019/3/20 11:17
 */
public class DiagnoseTargetSimpleVO {

    private String id;

    private String name;

    private Integer sex;

    private Date birthday;

    private String cellphone;

    private String factors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }
}
