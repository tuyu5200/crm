package com.crm.beans;

import lombok.Data;

import java.sql.Date;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Data
public class ClientBean {
    private String name;
    private String legal;
    private Integer postcode;
    private String phone;
    private String email;
    private Date registerDate;
    private Date nextTime;
    private Integer level;
    private Integer userId;
    private Integer cityId;

}
