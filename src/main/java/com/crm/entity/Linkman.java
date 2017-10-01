package com.crm.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_linkman", schema = "crm")
public class Linkman {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String job;
    private Integer active;
    private String phone;
    private String email;
    private String content;
    private Set<Complaint> complaints;
    private Client client;
    private Set<Linkrecord> linkrecords;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender")
    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "job")
    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return this.active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(mappedBy = "linkman")
    public Set<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "linkman")
    public Set<Linkrecord> getLinkrecords() {
        return this.linkrecords;
    }

    public void setLinkrecords(Set<Linkrecord> linkrecords) {
        this.linkrecords = linkrecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linkman linkman = (Linkman) o;

        if (this.id != null ? !this.id.equals(linkman.id) : linkman.id != null) return false;
        if (this.name != null ? !this.name.equals(linkman.name) : linkman.name != null) return false;
        if (this.gender != null ? !this.gender.equals(linkman.gender) : linkman.gender != null) return false;
        if (this.birthday != null ? !this.birthday.equals(linkman.birthday) : linkman.birthday != null) return false;
        if (this.job != null ? !this.job.equals(linkman.job) : linkman.job != null) return false;
        if (this.active != null ? !this.active.equals(linkman.active) : linkman.active != null) return false;
        if (this.phone != null ? !this.phone.equals(linkman.phone) : linkman.phone != null) return false;
        if (this.email != null ? !this.email.equals(linkman.email) : linkman.email != null) return false;
        if (this.content != null ? !this.content.equals(linkman.content) : linkman.content != null) return false;
        return this.client != null ? this.client.equals(linkman.client) : linkman.client == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.gender != null ? this.gender.hashCode() : 0);
        result = 31 * result + (this.birthday != null ? this.birthday.hashCode() : 0);
        result = 31 * result + (this.job != null ? this.job.hashCode() : 0);
        result = 31 * result + (this.active != null ? this.active.hashCode() : 0);
        result = 31 * result + (this.phone != null ? this.phone.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.content != null ? this.content.hashCode() : 0);
        result = 31 * result + (this.client != null ? this.client.hashCode() : 0);
        return result;
    }
}
