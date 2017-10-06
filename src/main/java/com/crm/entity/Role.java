package com.crm.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_role", schema = "crm")
public class Role {
    private Integer id;
    private String name;
    private Integer enabled;
    private String description;
    private RoleConstant roleConstant;
    private Dept dept;
    private Company company;
    private Set<RoleResource> roleResources;
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
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (this.id != null ? !this.id.equals(role.id) : role.id != null) return false;
        if (this.name != null ? !this.name.equals(role.name) : role.name != null) return false;
        if (this.enabled != null ? !this.enabled.equals(role.enabled) : role.enabled != null) return false;
        if (this.description != null ? !this.description.equals(role.description) : role.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.enabled != null ? this.enabled.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "constant", referencedColumnName = "id")
    public RoleConstant getRoleConstant() {
        return this.roleConstant;
    }

    public void setRoleConstant(RoleConstant roleConstant) {
        this.roleConstant = roleConstant;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public Dept getDept() {
        return this.dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToMany(mappedBy = "role")
    public Set<RoleResource> getRoleResources() {
        return this.roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }


    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
