package com.synectiks.admission.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class CmsAdmissionEnquiryVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String studentName;
    private String studentMiddleName;
    private String studentLastName;
    private String cellPhoneNo;
    private String landLinePhoneNo;
    private String emailId;
    private LocalDate dateOfBirth;
    private String gender;
    private String highestQualification;
    private String modeOfEnquiry;
    private LocalDate enquiryDate;
    private String comments;
    private String enquiryStatus;
    private Long branchId;
    private Long departmentId;
    private Long courseId;
    private Long semesterId;
    private Long batchId;
    private Long stateId;
    private Long cityId;
    private Long academicYearId;
    private String strEnquiryDate;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getLandLinePhoneNo() {
        return landLinePhoneNo;
    }

    public void setLandLinePhoneNo(String landLinePhoneNo) {
        this.landLinePhoneNo = landLinePhoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getModeOfEnquiry() {
        return modeOfEnquiry;
    }

    public void setModeOfEnquiry(String modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
    }

    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getStrEnquiryDate() {
        return strEnquiryDate;
    }

    public void setStrEnquiryDate(String strEnquiryDate) {
        this.strEnquiryDate = strEnquiryDate;
    }


}
