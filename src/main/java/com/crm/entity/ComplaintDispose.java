package com.crm.entity;

import javax.persistence.*;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description： 投诉处理表
 */
@Entity
@Table(name = "crm_complaint_dispose", schema = "crm")
public class ComplaintDispose {
    private Integer id;
    private String dispose;
    private ComplaintRecord complaintRecord;

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
    @Column(name = "dispose")
    public String getDispose() {
        return this.dispose;
    }

    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintDispose that = (ComplaintDispose) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null) return false;
        if (this.dispose != null ? !this.dispose.equals(that.dispose) : that.dispose != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.dispose != null ? this.dispose.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "id")
    public ComplaintRecord getComplaintRecord() {
        return this.complaintRecord;
    }

    public void setComplaintRecord(ComplaintRecord complaintRecord) {
        this.complaintRecord = complaintRecord;
    }
}
