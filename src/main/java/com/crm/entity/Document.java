package com.crm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_document", schema = "crm")
public class Document {
    private Integer id;
    private String name;
    private Integer size;
    private String suffix;
    private String content;
    private String path;
    private Timestamp uploaddate;
    private Client client;
    private User user;

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
    @Column(name = "size")
    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "suffix")
    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
    @Column(name = "path")
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "uploaddate")
    public Timestamp getUploaddate() {
        return this.uploaddate;
    }

    public void setUploaddate(Timestamp uploaddate) {
        this.uploaddate = uploaddate;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (this.id != null ? !this.id.equals(document.id) : document.id != null) return false;
        if (this.name != null ? !this.name.equals(document.name) : document.name != null) return false;
        if (this.size != null ? !this.size.equals(document.size) : document.size != null) return false;
        if (this.suffix != null ? !this.suffix.equals(document.suffix) : document.suffix != null) return false;
        if (this.content != null ? !this.content.equals(document.content) : document.content != null) return false;
        if (this.path != null ? !this.path.equals(document.path) : document.path != null) return false;
        if (this.uploaddate != null ? !this.uploaddate.equals(document.uploaddate) : document.uploaddate != null)
            return false;
        if (this.client != null ? !this.client.equals(document.client) : document.client != null) return false;
        return this.user != null ? this.user.equals(document.user) : document.user == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.size != null ? this.size.hashCode() : 0);
        result = 31 * result + (this.suffix != null ? this.suffix.hashCode() : 0);
        result = 31 * result + (this.content != null ? this.content.hashCode() : 0);
        result = 31 * result + (this.path != null ? this.path.hashCode() : 0);
        result = 31 * result + (this.uploaddate != null ? this.uploaddate.hashCode() : 0);
        result = 31 * result + (this.client != null ? this.client.hashCode() : 0);
        result = 31 * result + (this.user != null ? this.user.hashCode() : 0);
        return result;
    }
}
