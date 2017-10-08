package com.crm.beans;

import com.crm.entity.Client;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Data
public class ClientBean {
    private Integer clientId;
    private String name;
    private String legal = "";
    private Integer postcode = null;
    private String phone;
    private String email = "";
    private Date registerDate;
    private Date nextTime;
    private Integer level;
    private Integer userId;
    private String city;
//    private Integer cityId;


    public ClientBean() {
    }

    public ClientBean(Client client) {
        if (Objects.isNull(client.getCity()))
            this.city = null;
        else
            this.city = client.getCity().getName();
        this.clientId = client.getId();
        this.name = client.getName();
        this.legal = client.getLegal();
        this.phone = client.getPhone();
        this.postcode = client.getPostcode();
        this.email = client.getEmail();
        this.registerDate = client.getRegisterDate();
        this.nextTime = client.getNextTime();
        this.level = client.getLevel();
        this.userId = client.getUser().getId();
    }
}
