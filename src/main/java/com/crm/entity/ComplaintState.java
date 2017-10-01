package com.crm.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Entity
@Table(name = "crm_complaint_state", schema = "crm")
public class ComplaintState {
    private Integer id;
    private String name;
    private Set<Complaint> complaints;
    private Set<ComplaintRecord> complaintRecords;

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

    @OneToMany(mappedBy = "complaintState")
    public Set<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "complaintState")
    public Set<ComplaintRecord> getComplaintRecords() {
        return this.complaintRecords;
    }

    public void setComplaintRecords(Set<ComplaintRecord> complaintRecords) {

        this.complaintRecords = complaintRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintState that = (ComplaintState) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null) return false;
        return this.name != null ? this.name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }
}
