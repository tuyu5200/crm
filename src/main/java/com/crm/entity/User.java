package com.crm.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_user", schema = "crm")
public class User {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Integer sex;
    private Integer enabled;
    private Integer locked;
    private String description;
    private Set<Client> clients;
    private Set<Complaint> complaints;
    private Set<ComplaintRecord> complaintRecords;
    private Set<Document> documents;
    private Set<Linkrecord> linkrecords;
    private Company company;
    private Dept dept;
    private Set<Role> roles = new HashSet<Role>();

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
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "sex")
    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "enabled")
    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "locked")
    public Integer getLocked() {
        return this.locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "user")
    public Set<Client> getClients() {
        return this.clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @OneToMany(mappedBy = "user")
    public Set<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "user")
    public Set<ComplaintRecord> getComplaintRecords() {
        return this.complaintRecords;
    }

    public void setComplaintRecords(Set<ComplaintRecord> complaintRecords) {
        this.complaintRecords = complaintRecords;
    }

    @OneToMany(mappedBy = "user")
    public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "user")
    public Set<Linkrecord> getLinkrecords() {
        return this.linkrecords;
    }

    public void setLinkrecords(Set<Linkrecord> linkrecords) {
        this.linkrecords = linkrecords;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    @JoinColumn(name = "deptno", referencedColumnName = "id")
    public Dept getDept() {
        return this.dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @ManyToMany
    @JoinTable(name = "crm_user_role", inverseJoinColumns = {@JoinColumn(name = "role_id")}, joinColumns = {
            @JoinColumn(name = "user_id")})
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (this.id != null ? !this.id.equals(user.id) : user.id != null) return false;
        if (this.name != null ? !this.name.equals(user.name) : user.name != null) return false;
        if (this.username != null ? !this.username.equals(user.username) : user.username != null) return false;
        if (this.password != null ? !this.password.equals(user.password) : user.password != null) return false;
        if (this.email != null ? !this.email.equals(user.email) : user.email != null) return false;
        if (this.sex != null ? !this.sex.equals(user.sex) : user.sex != null) return false;
        if (this.enabled != null ? !this.enabled.equals(user.enabled) : user.enabled != null) return false;
        if (this.locked != null ? !this.locked.equals(user.locked) : user.locked != null) return false;
        if (this.description != null ? !this.description.equals(user.description) : user.description != null)
            return false;
        if (this.company != null ? !this.company.equals(user.company) : user.company != null) return false;
        return this.dept != null ? this.dept.equals(user.dept) : user.dept == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.username != null ? this.username.hashCode() : 0);
        result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.sex != null ? this.sex.hashCode() : 0);
        result = 31 * result + (this.enabled != null ? this.enabled.hashCode() : 0);
        result = 31 * result + (this.locked != null ? this.locked.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.company != null ? this.company.hashCode() : 0);
        result = 31 * result + (this.dept != null ? this.dept.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", email='" + this.email + '\'' +
                ", sex=" + this.sex +
                ", enabled=" + this.enabled +
                ", locked=" + this.locked +
                ", description='" + this.description + '\'' +
                ", clients=" + this.clients +
                ", complaints=" + this.complaints +
                ", complaintRecords=" + this.complaintRecords +
                ", documents=" + this.documents +
                ", linkrecords=" + this.linkrecords +
                ", company=" + this.company +
                ", dept=" + this.dept +
                ", roles=" + this.roles +
                '}';
    }
}
