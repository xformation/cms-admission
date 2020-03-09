package com.synectiks.admission.domain;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.admission.utils.IESEntity;

/**
 * A AdmissionEnquiry.
 */
@Entity
@Table(name = "admission_enquiry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdmissionEnquiry implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_middle_name")
    private String studentMiddleName;

    @Column(name = "student_last_name")
    private String studentLastName;

    @Column(name = "cell_phone_no")
    private String cellPhoneNo;

    @Column(name = "land_line_phone_no")
    private String landLinePhoneNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "date_of_birth")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "highest_qualification")
    private String highestQualification;

    @Column(name = "mode_of_enquiry")
    private String modeOfEnquiry;

    @Column(name = "enquiry_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate enquiryDate;

    @Size(max = 5000)
    @Column(name = "comments", length = 5000)
    private String comments;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "semester_id")
    private Long semesterId;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "academic_year_id")
    private Long academicYearId;

    @Column(name = "enquiry_status")
    private String enquiryStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate updatedOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public AdmissionEnquiry studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public AdmissionEnquiry studentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
        return this;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public AdmissionEnquiry studentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
        return this;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public AdmissionEnquiry cellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
        return this;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getLandLinePhoneNo() {
        return landLinePhoneNo;
    }

    public AdmissionEnquiry landLinePhoneNo(String landLinePhoneNo) {
        this.landLinePhoneNo = landLinePhoneNo;
        return this;
    }

    public void setLandLinePhoneNo(String landLinePhoneNo) {
        this.landLinePhoneNo = landLinePhoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public AdmissionEnquiry emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public AdmissionEnquiry dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public AdmissionEnquiry gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public AdmissionEnquiry highestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
        return this;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getModeOfEnquiry() {
        return modeOfEnquiry;
    }

    public AdmissionEnquiry modeOfEnquiry(String modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
        return this;
    }

    public void setModeOfEnquiry(String modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
    }

    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }

    public AdmissionEnquiry enquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
        return this;
    }

    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getComments() {
        return comments;
    }

    public AdmissionEnquiry comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getBranchId() {
        return branchId;
    }

    public AdmissionEnquiry branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public AdmissionEnquiry departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public AdmissionEnquiry courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public AdmissionEnquiry semesterId(Long semesterId) {
        this.semesterId = semesterId;
        return this;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public AdmissionEnquiry batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getStateId() {
        return stateId;
    }

    public AdmissionEnquiry stateId(Long stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public AdmissionEnquiry cityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public AdmissionEnquiry academicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
        return this;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    public AdmissionEnquiry enquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
        return this;
    }

    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AdmissionEnquiry createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public AdmissionEnquiry createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public AdmissionEnquiry updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public AdmissionEnquiry updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmissionEnquiry)) {
            return false;
        }
        return id != null && id.equals(((AdmissionEnquiry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdmissionEnquiry{" +
            "id=" + getId() +
            ", studentName='" + getStudentName() + "'" +
            ", studentMiddleName='" + getStudentMiddleName() + "'" +
            ", studentLastName='" + getStudentLastName() + "'" +
            ", cellPhoneNo='" + getCellPhoneNo() + "'" +
            ", landLinePhoneNo='" + getLandLinePhoneNo() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", gender='" + getGender() + "'" +
            ", highestQualification='" + getHighestQualification() + "'" +
            ", modeOfEnquiry='" + getModeOfEnquiry() + "'" +
            ", enquiryDate='" + getEnquiryDate() + "'" +
            ", comments='" + getComments() + "'" +
            ", branchId=" + getBranchId() +
            ", departmentId=" + getDepartmentId() +
            ", courseId=" + getCourseId() +
            ", semesterId=" + getSemesterId() +
            ", batchId=" + getBatchId() +
            ", stateId=" + getStateId() +
            ", cityId=" + getCityId() +
            ", academicYearId=" + getAcademicYearId() +
            ", enquiryStatus='" + getEnquiryStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
