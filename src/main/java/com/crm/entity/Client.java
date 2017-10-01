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
@Table(name = "crm_client", schema = "crm")
public class Client {
    private Integer id;
    private Integer name;
    private String legal;
    private Integer postcode;
    private String telephone;
    private String phone;
    private String fax;
    private String email;
    private String url;
    private Date registerDate;
    private Date nextTime;
    private Integer level;
    private User user;
    private City city;
    private Set<Complaint> complaints;
    private Set<Document> documents;
    private Set<Linkman> linkmen;
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
    public Integer getName() {
        return this.name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    @Basic
    @Column(name = "legal")
    public String getLegal() {
        return this.legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    @Basic
    @Column(name = "postcode")
    public Integer getPostcode() {
        return this.postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "telphone")
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "fax")
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
    @Column(name = "url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "register_date")
    public Date getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "next_time")
    public Date getNextTime() {
        return this.nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "id")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "client")
    public Set<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "client")
    public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "client")
    public Set<Linkman> getLinkmen() {
        return this.linkmen;
    }

    public void setLinkmen(Set<Linkman> linkmen) {
        this.linkmen = linkmen;
    }

    @OneToMany(mappedBy = "client")
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

        Client client = (Client) o;

        if (this.id != null ? !this.id.equals(client.id) : client.id != null) return false;
        if (this.name != null ? !this.name.equals(client.name) : client.name != null) return false;
        if (this.legal != null ? !this.legal.equals(client.legal) : client.legal != null) return false;
        if (this.postcode != null ? !this.postcode.equals(client.postcode) : client.postcode != null) return false;
        if (this.telephone != null ? !this.telephone.equals(client.telephone) : client.telephone != null) return false;
        if (this.phone != null ? !this.phone.equals(client.phone) : client.phone != null) return false;
        if (this.fax != null ? !this.fax.equals(client.fax) : client.fax != null) return false;
        if (this.email != null ? !this.email.equals(client.email) : client.email != null) return false;
        if (this.url != null ? !this.url.equals(client.url) : client.url != null) return false;
        if (this.registerDate != null ? !this.registerDate.equals(client.registerDate) : client.registerDate != null)
            return false;
        if (this.nextTime != null ? !this.nextTime.equals(client.nextTime) : client.nextTime != null) return false;
        if (this.level != null ? !this.level.equals(client.level) : client.level != null) return false;
        if (this.user != null ? !this.user.equals(client.user) : client.user != null) return false;
        return this.city != null ? this.city.equals(client.city) : client.city == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.legal != null ? this.legal.hashCode() : 0);
        result = 31 * result + (this.postcode != null ? this.postcode.hashCode() : 0);
        result = 31 * result + (this.telephone != null ? this.telephone.hashCode() : 0);
        result = 31 * result + (this.phone != null ? this.phone.hashCode() : 0);
        result = 31 * result + (this.fax != null ? this.fax.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.url != null ? this.url.hashCode() : 0);
        result = 31 * result + (this.registerDate != null ? this.registerDate.hashCode() : 0);
        result = 31 * result + (this.nextTime != null ? this.nextTime.hashCode() : 0);
        result = 31 * result + (this.level != null ? this.level.hashCode() : 0);
        result = 31 * result + (this.user != null ? this.user.hashCode() : 0);
        result = 31 * result + (this.city != null ? this.city.hashCode() : 0);
        return result;
    }
}
