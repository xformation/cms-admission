package com.synectiks.admission.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AdmissionApplication.
 */
@Entity
@Table(name = "admission_application")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "admissionapplication")
public class AdmissionApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "source_of_application")
    private String sourceOfApplication;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "admission_no")
    private Long admissionNo;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @ManyToOne
    @JsonIgnoreProperties("admissionApplications")
    private AdmissionEnquiry admissionEnquiry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceOfApplication() {
        return sourceOfApplication;
    }

    public AdmissionApplication sourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
        return this;
    }

    public void setSourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
    }

    public Long getStudentId() {
        return studentId;
    }

    public AdmissionApplication studentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public AdmissionApplication applicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
        return this;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public AdmissionApplication completionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Long getAdmissionNo() {
        return admissionNo;
    }

    public AdmissionApplication admissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
        return this;
    }

    public void setAdmissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public AdmissionApplication admissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
        return this;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getComments() {
        return comments;
    }

    public AdmissionApplication comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public AdmissionApplication applicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
        return this;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getBranchId() {
        return branchId;
    }

    public AdmissionApplication branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AdmissionApplication createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public AdmissionApplication createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public AdmissionApplication updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public AdmissionApplication updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AdmissionEnquiry getAdmissionEnquiry() {
        return admissionEnquiry;
    }

    public AdmissionApplication admissionEnquiry(AdmissionEnquiry admissionEnquiry) {
        this.admissionEnquiry = admissionEnquiry;
        return this;
    }

    public void setAdmissionEnquiry(AdmissionEnquiry admissionEnquiry) {
        this.admissionEnquiry = admissionEnquiry;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmissionApplication)) {
            return false;
        }
        return id != null && id.equals(((AdmissionApplication) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdmissionApplication{" +
            "id=" + getId() +
            ", sourceOfApplication='" + getSourceOfApplication() + "'" +
            ", studentId=" + getStudentId() +
            ", applicationDate='" + getApplicationDate() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", admissionNo=" + getAdmissionNo() +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", comments='" + getComments() + "'" +
            ", applicationStatus='" + getApplicationStatus() + "'" +
            ", branchId=" + getBranchId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
