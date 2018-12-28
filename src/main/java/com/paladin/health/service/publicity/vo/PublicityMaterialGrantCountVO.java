package com.paladin.health.service.publicity.vo;
/**   
 * @author 黄伟华
 * @version 2018年12月28日 下午1:28:26 
 */
public class PublicityMaterialGrantCountVO{
    
    private String id;
    
    private String workCycle;
    
    private String name;
    
    private Integer total;

    public String getWorkCycle()
    {
        return workCycle;
    }

    public void setWorkCycle(String workCycle)
    {
        this.workCycle = workCycle;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }
    
}
