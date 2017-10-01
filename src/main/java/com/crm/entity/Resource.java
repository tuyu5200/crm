package com.crm.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_resource", schema = "crm")
public class Resource {
    private Integer id;
    private String name;
    private String title;
    private String href;
    private String target;
    private String constant;
    private Integer shown;
    private Integer enabled;
    private Integer type;
    private Resource parent;
    private Set<Resource> resources;
    private Set<RoleResource> roleResources;

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
    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "href")
    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "target")
    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "constant")
    public String getConstant() {
        return this.constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    @Basic
    @Column(name = "shown")
    public Integer getShown() {
        return this.shown;
    }

    public void setShown(Integer shown) {
        this.shown = shown;
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
    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (this.id != null ? !this.id.equals(resource.id) : resource.id != null) return false;
        if (this.name != null ? !this.name.equals(resource.name) : resource.name != null) return false;
        if (this.title != null ? !this.title.equals(resource.title) : resource.title != null) return false;
        if (this.href != null ? !this.href.equals(resource.href) : resource.href != null) return false;
        if (this.target != null ? !this.target.equals(resource.target) : resource.target != null)
            return false;
        if (this.constant != null ? !this.constant.equals(resource.constant) : resource.constant != null)
            return false;
        if (this.shown != null ? !this.shown.equals(resource.shown) : resource.shown != null) return false;
        if (this.enabled != null ? !this.enabled.equals(resource.enabled) : resource.enabled != null)
            return false;
        if (this.type != null ? !this.type.equals(resource.type) : resource.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.title != null ? this.title.hashCode() : 0);
        result = 31 * result + (this.href != null ? this.href.hashCode() : 0);
        result = 31 * result + (this.target != null ? this.target.hashCode() : 0);
        result = 31 * result + (this.constant != null ? this.constant.hashCode() : 0);
        result = 31 * result + (this.shown != null ? this.shown.hashCode() : 0);
        result = 31 * result + (this.enabled != null ? this.enabled.hashCode() : 0);
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public Resource getParent() {
        return this.parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Set<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    @OneToMany(mappedBy = "resource")
    public Set<RoleResource> getRoleResources() {
        return this.roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }
}
