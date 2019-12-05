package com.synectiks.admission.domain.vo;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.synectiks.admission.domain.AdmissionEnquiry;

public class CmsAdmissionApplicationVo extends CmsCommonVo implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String sourceOfApplication;
    private Long studentId;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate applicationDate;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate completionDate;
    
    private Long admissionNo;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate admissionDate;
    
    private String comments;
    private String applicationStatus;
    private Long branchId;
    private String createdBy;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate createdOn;
    
    private String updatedBy;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate updatedOn;
    
    private AdmissionEnquiry admissionEnquiry;

    private String strApplicationDate;
    private String strCompletionDate;
    private String strAdmissionDate;
    private String strCreatedOn;
    private String strUpdatedOn;
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
	public AdmissionEnquiry getAdmissionEnquiry() {
		return admissionEnquiry;
	}
	public void setAdmissionEnquiry(AdmissionEnquiry admissionEnquiry) {
		this.admissionEnquiry = admissionEnquiry;
	}
	public String getStrApplicationDate() {
		return strApplicationDate;
	}
	public void setStrApplicationDate(String strApplicationDate) {
		this.strApplicationDate = strApplicationDate;
	}
	public String getStrCompletionDate() {
		return strCompletionDate;
	}
	public void setStrCompletionDate(String strCompletionDate) {
		this.strCompletionDate = strCompletionDate;
	}
	public String getStrAdmissionDate() {
		return strAdmissionDate;
	}
	public void setStrAdmissionDate(String strAdmissionDate) {
		this.strAdmissionDate = strAdmissionDate;
	}
	public String getStrCreatedOn() {
		return strCreatedOn;
	}
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}
	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}
	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
	}
	public Long getAdmissionEnquiryId() {
		return admissionEnquiryId;
	}
	public void setAdmissionEnquiryId(Long admissionEnquiryId) {
		this.admissionEnquiryId = admissionEnquiryId;
	}

}
