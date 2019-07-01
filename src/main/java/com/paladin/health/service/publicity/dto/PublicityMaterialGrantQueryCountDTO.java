package com.paladin.health.service.publicity.dto;

import com.paladin.framework.common.OffsetPage;

/**   
 * @author 黄伟华
 * @version 2018年12月28日 下午4:44:08 
 */
public class PublicityMaterialGrantQueryCountDTO extends OffsetPage{

    private  String id;
    
    private String workCycle;
    
    private String agencyName;


    public String getWorkCycle()
    {
        return workCycle;
    }

    public void setWorkCycle(String workCycle)
    {
        this.workCycle = workCycle;
    }

    public String getAgencyName()
    {
        return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
