package com.crm.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_company", schema = "crm")
public class Company {
    private Integer id;
    private String cname;
    private String description;


    private Set<Dept> depts;
    private Set<Role> roles;
    private Set<User> users;

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
    @Column(name = "cname")
    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "companies")
    public Set<Dept> getDepts() {
        return this.depts;
    }

    public void setDepts(Set<Dept> depts) {
        this.depts = depts;
    }

    @OneToMany(mappedBy = "company")
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "company")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (this.id != null ? !this.id.equals(company.id) : company.id != null) return false;
        if (this.cname != null ? !this.cname.equals(company.cname) : company.cname != null) return false;
        return this.description != null ? this.description.equals(company.description) : company.description == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.cname != null ? this.cname.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }
}
