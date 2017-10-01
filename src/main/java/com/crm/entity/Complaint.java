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
@Table(name = "crm_complaint", schema = "crm")
public class Complaint {
    private Integer id;
    private String title;
    private Date accetpDate;
    private String tel;
    private String type;
    private Integer level;
    private String attitude;
    private Client client;
    private Linkman linkman;
    private User user;
    private ComplaintState complaintState;
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
    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "accetp_date")
    public Date getAccetpDate() {
        return this.accetpDate;
    }

    public void setAccetpDate(Date accetpDate) {
        this.accetpDate = accetpDate;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "attitude")
    public String getAttitude() {
        return this.attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
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
    @JoinColumn(name = "state", referencedColumnName = "id")
    public ComplaintState getComplaintState() {
        return this.complaintState;
    }

    public void setComplaintState(ComplaintState complaintState) {
        this.complaintState = complaintState;
    }

    @OneToMany(mappedBy = "complaint")
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

        Complaint complaint = (Complaint) o;

        if (this.id != null ? !this.id.equals(complaint.id) : complaint.id != null) return false;
        if (this.title != null ? !this.title.equals(complaint.title) : complaint.title != null) return false;
        if (this.accetpDate != null ? !this.accetpDate.equals(complaint.accetpDate) : complaint.accetpDate != null)
            return false;
        if (this.tel != null ? !this.tel.equals(complaint.tel) : complaint.tel != null) return false;
        if (this.type != null ? !this.type.equals(complaint.type) : complaint.type != null) return false;
        if (this.level != null ? !this.level.equals(complaint.level) : complaint.level != null) return false;
        if (this.attitude != null ? !this.attitude.equals(complaint.attitude) : complaint.attitude != null)
            return false;
        if (this.client != null ? !this.client.equals(complaint.client) : complaint.client != null) return false;
        if (this.linkman != null ? !this.linkman.equals(complaint.linkman) : complaint.linkman != null) return false;
        if (this.user != null ? !this.user.equals(complaint.user) : complaint.user != null) return false;
        return this.complaintState != null ? this.complaintState.equals(complaint.complaintState) : complaint.complaintState == null;
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.title != null ? this.title.hashCode() : 0);
        result = 31 * result + (this.accetpDate != null ? this.accetpDate.hashCode() : 0);
        result = 31 * result + (this.tel != null ? this.tel.hashCode() : 0);
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        result = 31 * result + (this.level != null ? this.level.hashCode() : 0);
        result = 31 * result + (this.attitude != null ? this.attitude.hashCode() : 0);
        result = 31 * result + (this.client != null ? this.client.hashCode() : 0);
        result = 31 * result + (this.linkman != null ? this.linkman.hashCode() : 0);
        result = 31 * result + (this.user != null ? this.user.hashCode() : 0);
        result = 31 * result + (this.complaintState != null ? this.complaintState.hashCode() : 0);
        return result;
    }
}
