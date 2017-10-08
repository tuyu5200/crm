package com.crm.beans;

import com.crm.entity.City;
import lombok.Data;

/**
 * @author walker tu
 * @date 2017/10/8
 * @description
 */
@Data
public class CityBean {
    private Integer id;
    private String code;
    private String name;
    private String firstLetter;
    private Integer level;
    private CityBean parent;

    public CityBean(City city) {
        this.id = city.getId();
        this.code = city.getCode();
        this.name = city.getName();
        this.firstLetter = city.getFirstLetter();
        this.level = city.getLevel();
        this.parent = new CityBean(city.getParent());
    }
}
