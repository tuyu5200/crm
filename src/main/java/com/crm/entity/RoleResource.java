package com.crm.entity;

import javax.persistence.*;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_role_resource", schema = "crm")
public class RoleResource {
    private Integer enabled;
    private Resource resource;
    private Role role;

    @Basic
    @Column(name = "enabled")
    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleResource that = (RoleResource) o;

        if (this.enabled != null ? !this.enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.enabled != null ? this.enabled.hashCode() : 0;
        result = 31 * result + (this.resource != null ? this.resource.hashCode() : 0);
        result = 31 * result + (this.role != null ? this.role.hashCode() : 0);
        result = 31 * result + (this.id != null ? this.id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
