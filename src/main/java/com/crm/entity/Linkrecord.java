package com.crm.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_linkrecord", schema = "crm")
public class Linkrecord {
    private Integer id;
    private String content;
    private Date linkDate;
    private String linkType;
    private String linkWay;
    private Linkman linkman;
    private User user;
    private Client client;

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
    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "link_date")
    public Date getLinkDate() {
        return this.linkDate;
    }

    public void setLinkDate(Date linkDate) {
        this.linkDate = linkDate;
    }

    @Basic
    @Column(name = "link_type")
    public String getLinkType() {
        return this.linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @Basic
    @Column(name = "link_way")
    public String getLinkWay() {
        return this.linkWay;
    }

    public void setLinkWay(String linkWay) {
        this.linkWay = linkWay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linkrecord that = (Linkrecord) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null) return false;
        if (this.content != null ? !this.content.equals(that.content) : that.content != null) return false;
        if (this.linkDate != null ? !this.linkDate.equals(that.linkDate) : that.linkDate != null) return false;
        if (this.linkType != null ? !this.linkType.equals(that.linkType) : that.linkType != null) return false;
        if (this.linkWay != null ? !this.linkWay.equals(that.linkWay) : that.linkWay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.content != null ? this.content.hashCode() : 0);
        result = 31 * result + (this.linkDate != null ? this.linkDate.hashCode() : 0);
        result = 31 * result + (this.linkType != null ? this.linkType.hashCode() : 0);
        result = 31 * result + (this.linkWay != null ? this.linkWay.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "linkman_id", referencedColumnName = "id")
    public Linkman getLinkman() {
        return this.linkman;
    }

    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
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
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
