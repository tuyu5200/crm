package com.crm.beans;

import com.crm.entity.Company;
import com.crm.entity.Dept;
import lombok.Data;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Data
public class DeptBean {
    private int id;
    private String name;
    private String description;
    private Company company;

    public DeptBean(Dept dept) {
        this.id = dept.getId();
        this.name = dept.getDname();
        this.description = dept.getDescription();
    }

}
