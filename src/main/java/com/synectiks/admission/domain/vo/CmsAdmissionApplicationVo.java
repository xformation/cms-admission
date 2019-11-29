package com.synectiks.admission.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.admission.domain.AdmissionEnquiry;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AdmissionApplication.
 */

public class CmsAdmissionApplicationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String sourceOfApplication;

    private Long studentId;

    private LocalDate applicationDate;

    private LocalDate completionDate;

    private Long admissionNo;

    private LocalDate admissionDate;

    private String comments;

    private String applicationStatus;




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

    public CmsAdmissionApplicationVo sourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
        return this;
    }

    public void setSourceOfApplication(String sourceOfApplication) {
        this.sourceOfApplication = sourceOfApplication;
    }

    public Long getStudentId() {
        return studentId;
    }

    public CmsAdmissionApplicationVo studentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public CmsAdmissionApplicationVo applicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
        return this;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public CmsAdmissionApplicationVo completionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Long getAdmissionNo() {
        return admissionNo;
    }

    public CmsAdmissionApplicationVo admissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
        return this;
    }

    public void setAdmissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public CmsAdmissionApplicationVo admissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
        return this;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getComments() {
        return comments;
    }

    public CmsAdmissionApplicationVo comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public CmsAdmissionApplicationVo applicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
        return this;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CmsAdmissionApplicationVo)) {
            return false;
        }
        return id != null && id.equals(((CmsAdmissionApplicationVo) o).id);
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

            "}";
    }
}
