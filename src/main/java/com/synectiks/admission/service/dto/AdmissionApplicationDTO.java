package com.synectiks.admission.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.admission.domain.AdmissionApplication} entity.
 */
public class AdmissionApplicationDTO implements Serializable {

    private Long id;

    private String sourceOfApplication;

    private Long studentId;

    private LocalDate applicationDate;

    private LocalDate completionDate;

    private Long admissionNo;

    private LocalDate admissionDate;

    private String comments;

    private String applicationStatus;

    private Long branchId;

    private String createdBy;

    private LocalDate createdOn;

    private String updatedBy;

    private LocalDate updatedOn;


    private Long admissionEnquiryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceOfApplication() {
        return sourceOfApplication;
    }

    public void setSourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Long getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getAdmissionEnquiryId() {
        return admissionEnquiryId;
    }

    public void setAdmissionEnquiryId(Long admissionEnquiryId) {
        this.admissionEnquiryId = admissionEnquiryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdmissionApplicationDTO admissionApplicationDTO = (AdmissionApplicationDTO) o;
        if (admissionApplicationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), admissionApplicationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdmissionApplicationDTO{" +
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
            ", admissionEnquiry=" + getAdmissionEnquiryId() +
            "}";
    }
}
