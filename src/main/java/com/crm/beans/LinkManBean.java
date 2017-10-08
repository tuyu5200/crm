package com.crm.beans;

import com.crm.entity.Linkman;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/8
 * @description
 */
@Data
public class LinkManBean {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String job;
    private Integer active;
    private String phone;
    private String email;
    private String content;
    //    private Set<Complaint> complaints;
    private ClientBean client;
//    private Set<Linkrecord> linkrecords;

    public LinkManBean() {

    }

    public LinkManBean(Linkman linkman) {
        this.id = linkman.getId();
        this.name = linkman.getName();
        this.gender = linkman.getGender();
        this.birthday = linkman.getBirthday();
        this.job = linkman.getJob();
        this.active = linkman.getActive();
        this.phone = linkman.getPhone();
        this.email = linkman.getEmail();
        this.content = linkman.getContent();
        if (Objects.isNull(linkman.getClient()))
            this.client = null;
        else
            this.client = new ClientBean(linkman.getClient());
//        this.complaints = linkman.getComplaints();
//        this.linkrecords = linkman.getLinkrecords();
    }


}
