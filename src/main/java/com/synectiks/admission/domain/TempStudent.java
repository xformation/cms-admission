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

/**
 * A TempStudent.
 */
@Entity
@Table(name = "temp_student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TempStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "state_machine_id")
    private String stateMachineId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_middle_name")
    private String studentMiddleName;

    @Column(name = "student_last_name")
    private String studentLastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_middle_name")
    private String fatherMiddleName;

    @Column(name = "father_last_name")
    private String fatherLastName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_middle_name")
    private String motherMiddleName;

    @Column(name = "mother_last_name")
    private String motherLastName;

    @Column(name = "student_aadhar_no")
    private String studentAadharNo;

    @Column(name = "student_pan_no")
    private String studentPanNo;

    @Column(name = "student_social_security_no")
    private String studentSocialSecurityNo;

    @Column(name = "student_tax_reference_no")
    private String studentTaxReferenceNo;

    @Column(name = "student_bpl_no")
    private String studentBplNo;

    @Column(name = "student_driving_license_no")
    private String studentDrivingLicenseNo;

    @Column(name = "student_passport_no")
    private String studentPassportNo;

    @Column(name = "date_of_birth")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "religion")
    private String religion;

    @Column(name = "caste")
    private String caste;

    @Column(name = "sub_caste")
    private String subCaste;

    @Column(name = "sex")
    private String sex;

    @Column(name = "student_local_address")
    private String studentLocalAddress;

    @Column(name = "student_permanent_address")
    private String studentPermanentAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "student_primary_cell_number")
    private String studentPrimaryCellNumber;

    @Column(name = "student_alternate_cell_number")
    private String studentAlternateCellNumber;

    @Column(name = "student_land_line_phone_number")
    private String studentLandLinePhoneNumber;

    @Column(name = "student_primary_email_id")
    private String studentPrimaryEmailId;

    @Column(name = "student_alternate_email_id")
    private String studentAlternateEmailId;

    @Column(name = "relation_with_student")
    private String relationWithStudent;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_middle_name")
    private String emergencyContactMiddleName;

    @Column(name = "emergency_contact_last_name")
    private String emergencyContactLastName;

    @Column(name = "emergency_contact_cell_number")
    private String emergencyContactCellNumber;

    @Column(name = "emergency_contact_land_line_phone_number")
    private String emergencyContactLandLinePhoneNumber;

    @Column(name = "emergency_contact_email_id")
    private String emergencyContactEmailId;

    @Column(name = "student_image_path")
    private String studentImagePath;

    @Column(name = "admission_no")
    private String admissionNo;

    @Column(name = "enrollment_no")
    private String enrollmentNo;

    @Column(name = "roll_no")
    private String rollNo;

    @Column(name = "student_type")
    private String studentType;

    @Column(name = "father_cell_number")
    private String fatherCellNumber;

    @Column(name = "father_email_id")
    private String fatherEmailId;

    @Column(name = "father_occupation")
    private String fatherOccupation;

    @Column(name = "father_office_email_id")
    private String fatherOfficeEmailId;

    @Column(name = "father_office_address")
    private String fatherOfficeAddress;

    @Column(name = "father_office_cell_number")
    private String fatherOfficeCellNumber;

    @Column(name = "father_office_land_line_phone_number")
    private String fatherOfficeLandLinePhoneNumber;

    @Column(name = "father_aadhar_no")
    private String fatherAadharNo;

    @Column(name = "father_pan_no")
    private String fatherPanNo;

    @Column(name = "father_social_security_no")
    private String fatherSocialSecurityNo;

    @Column(name = "father_tax_reference_no")
    private String fatherTaxReferenceNo;

    @Column(name = "father_bpl_no")
    private String fatherBplNo;

    @Column(name = "father_driving_license_no")
    private String fatherDrivingLicenseNo;

    @Column(name = "father_passport_no")
    private String fatherPassportNo;

    @Column(name = "father_image_path")
    private String fatherImagePath;

    @Column(name = "mother_cell_number")
    private String motherCellNumber;

    @Column(name = "mother_email_id")
    private String motherEmailId;

    @Column(name = "mother_occupation")
    private String motherOccupation;

    @Column(name = "mother_office_email_id")
    private String motherOfficeEmailId;

    @Column(name = "mother_office_address")
    private String motherOfficeAddress;

    @Column(name = "mother_office_cell_number")
    private String motherOfficeCellNumber;

    @Column(name = "mother_office_land_line_phone_number")
    private String motherOfficeLandLinePhoneNumber;

    @Column(name = "mother_aadhar_no")
    private String motherAadharNo;

    @Column(name = "mother_pan_no")
    private String motherPanNo;

    @Column(name = "mother_social_security_no")
    private String motherSocialSecurityNo;

    @Column(name = "mother_tax_reference_no")
    private String motherTaxReferenceNo;

    @Column(name = "mother_bpl_no")
    private String motherBplNo;

    @Column(name = "mother_driving_license_no")
    private String motherDrivingLicenseNo;

    @Column(name = "mother_passport_no")
    private String motherPassportNo;

    @Column(name = "mother_image_path")
    private String motherImagePath;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "guardian_middle_name")
    private String guardianMiddleName;

    @Column(name = "guardian_last_name")
    private String guardianLastName;

    @Column(name = "guardian_address")
    private String guardianAddress;

    @Column(name = "guardian_cell_number")
    private String guardianCellNumber;

    @Column(name = "guardian_land_line_phone_number")
    private String guardianLandLinePhoneNumber;

    @Column(name = "guardian_email_id")
    private String guardianEmailId;

    @Column(name = "guardian_occupation")
    private String guardianOccupation;

    @Column(name = "guardian_office_email_id")
    private String guardianOfficeEmailId;

    @Column(name = "guardian_office_address")
    private String guardianOfficeAddress;

    @Column(name = "guardian_office_cell_number")
    private String guardianOfficeCellNumber;

    @Column(name = "guardian_office_land_line_phone_number")
    private String guardianOfficeLandLinePhoneNumber;

    @Column(name = "guardian_image_path")
    private String guardianImagePath;

    @Size(max = 3)
    @Column(name = "is_guardian_sponsor_agency", length = 3)
    private String isGuardianSponsorAgency;

    @Column(name = "sponsor_agency_name")
    private String sponsorAgencyName;

    @Column(name = "sponsor_agency_registration_no")
    private String sponsorAgencyRegistrationNo;

    @Column(name = "sponsor_agency_address")
    private String sponsorAgencyAddress;

    @Column(name = "sponsor_agency_cell_number")
    private String sponsorAgencyCellNumber;

    @Column(name = "sponsor_agency_land_line_number")
    private String sponsorAgencyLandLineNumber;

    @Column(name = "sponsor_agency_email_id")
    private String sponsorAgencyEmailId;

    @Column(name = "sponsor_agency_appointee_name")
    private String sponsorAgencyAppointeeName;

    @Column(name = "sponsor_agency_appointee_designation")
    private String sponsorAgencyAppointeeDesignation;

    @Column(name = "sponsor_agency_appointee_cell_number")
    private String sponsorAgencyAppointeeCellNumber;

    @Column(name = "sponsor_agency_appointee_land_line_number")
    private String sponsorAgencyAppointeeLandLineNumber;

    @Column(name = "sponsor_agency_appointee_email_id")
    private String sponsorAgencyAppointeeEmailId;

    @Column(name = "sponsor_agency_appointee_office_address")
    private String sponsorAgencyAppointeeOfficeAddress;

    @Size(max = 3)
    @Column(name = "is_physically_challenged", length = 3)
    private String isPhysicallyChallenged;

    @Size(max = 2000)
    @Column(name = "details_of_disability", length = 2000)
    private String detailsOfDisability;

    @Column(name = "disability_certificate_no")
    private String disabilityCertificateNo;

    @Column(name = "disability_certificate_issue_authority")
    private String disabilityCertificateIssueAuthority;

    @Column(name = "disability_certificate_issue_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate disabilityCertificateIssueDate;

    @Column(name = "percentag_of_disability")
    private Integer percentagOfDisability;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Size(max = 2000)
    @Column(name = "vaccination_details", length = 2000)
    private String vaccinationDetails;

    @Size(max = 2000)
    @Column(name = "other_medical_details", length = 2000)
    private String otherMedicalDetails;

    @Column(name = "status")
    private String status;

    @Column(name = "comments")
    private String comments;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "academic_year_id")
    private Long academicYearId;

    @Column(name = "highest_qualification")
    private String highestQualification;
    
    @Column(name = "year_of_passing")
    private String yearOfPassing;
    
    @Column(name = "last_qalification_percentage")
    private String lastQualificationPercentage;
    
    @Column(name = "last_college_attended")
    private String lastCollegeAttended;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateMachineId() {
        return stateMachineId;
    }

    public TempStudent stateMachineId(String stateMachineId) {
        this.stateMachineId = stateMachineId;
        return this;
    }

    public void setStateMachineId(String stateMachineId) {
        this.stateMachineId = stateMachineId;
    }

    public String getStudentName() {
        return studentName;
    }

    public TempStudent studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public TempStudent studentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
        return this;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public TempStudent studentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
        return this;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public TempStudent fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public TempStudent fatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
        return this;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public TempStudent fatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
        return this;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public TempStudent motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public TempStudent motherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
        return this;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public TempStudent motherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
        return this;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getStudentAadharNo() {
        return studentAadharNo;
    }

    public TempStudent studentAadharNo(String studentAadharNo) {
        this.studentAadharNo = studentAadharNo;
        return this;
    }

    public void setStudentAadharNo(String studentAadharNo) {
        this.studentAadharNo = studentAadharNo;
    }

    public String getStudentPanNo() {
        return studentPanNo;
    }

    public TempStudent studentPanNo(String studentPanNo) {
        this.studentPanNo = studentPanNo;
        return this;
    }

    public void setStudentPanNo(String studentPanNo) {
        this.studentPanNo = studentPanNo;
    }

    public String getStudentSocialSecurityNo() {
        return studentSocialSecurityNo;
    }

    public TempStudent studentSocialSecurityNo(String studentSocialSecurityNo) {
        this.studentSocialSecurityNo = studentSocialSecurityNo;
        return this;
    }

    public void setStudentSocialSecurityNo(String studentSocialSecurityNo) {
        this.studentSocialSecurityNo = studentSocialSecurityNo;
    }

    public String getStudentTaxReferenceNo() {
        return studentTaxReferenceNo;
    }

    public TempStudent studentTaxReferenceNo(String studentTaxReferenceNo) {
        this.studentTaxReferenceNo = studentTaxReferenceNo;
        return this;
    }

    public void setStudentTaxReferenceNo(String studentTaxReferenceNo) {
        this.studentTaxReferenceNo = studentTaxReferenceNo;
    }

    public String getStudentBplNo() {
        return studentBplNo;
    }

    public TempStudent studentBplNo(String studentBplNo) {
        this.studentBplNo = studentBplNo;
        return this;
    }

    public void setStudentBplNo(String studentBplNo) {
        this.studentBplNo = studentBplNo;
    }

    public String getStudentDrivingLicenseNo() {
        return studentDrivingLicenseNo;
    }

    public TempStudent studentDrivingLicenseNo(String studentDrivingLicenseNo) {
        this.studentDrivingLicenseNo = studentDrivingLicenseNo;
        return this;
    }

    public void setStudentDrivingLicenseNo(String studentDrivingLicenseNo) {
        this.studentDrivingLicenseNo = studentDrivingLicenseNo;
    }

    public String getStudentPassportNo() {
        return studentPassportNo;
    }

    public TempStudent studentPassportNo(String studentPassportNo) {
        this.studentPassportNo = studentPassportNo;
        return this;
    }

    public void setStudentPassportNo(String studentPassportNo) {
        this.studentPassportNo = studentPassportNo;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public TempStudent dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public TempStudent placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getReligion() {
        return religion;
    }

    public TempStudent religion(String religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public TempStudent caste(String caste) {
        this.caste = caste;
        return this;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public TempStudent subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public String getSex() {
        return sex;
    }

    public TempStudent sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentLocalAddress() {
        return studentLocalAddress;
    }

    public TempStudent studentLocalAddress(String studentLocalAddress) {
        this.studentLocalAddress = studentLocalAddress;
        return this;
    }

    public void setStudentLocalAddress(String studentLocalAddress) {
        this.studentLocalAddress = studentLocalAddress;
    }

    public String getStudentPermanentAddress() {
        return studentPermanentAddress;
    }

    public TempStudent studentPermanentAddress(String studentPermanentAddress) {
        this.studentPermanentAddress = studentPermanentAddress;
        return this;
    }

    public void setStudentPermanentAddress(String studentPermanentAddress) {
        this.studentPermanentAddress = studentPermanentAddress;
    }

    public String getCity() {
        return city;
    }

    public TempStudent city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public TempStudent state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public TempStudent country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public TempStudent pinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getStudentPrimaryCellNumber() {
        return studentPrimaryCellNumber;
    }

    public TempStudent studentPrimaryCellNumber(String studentPrimaryCellNumber) {
        this.studentPrimaryCellNumber = studentPrimaryCellNumber;
        return this;
    }

    public void setStudentPrimaryCellNumber(String studentPrimaryCellNumber) {
        this.studentPrimaryCellNumber = studentPrimaryCellNumber;
    }

    public String getStudentAlternateCellNumber() {
        return studentAlternateCellNumber;
    }

    public TempStudent studentAlternateCellNumber(String studentAlternateCellNumber) {
        this.studentAlternateCellNumber = studentAlternateCellNumber;
        return this;
    }

    public void setStudentAlternateCellNumber(String studentAlternateCellNumber) {
        this.studentAlternateCellNumber = studentAlternateCellNumber;
    }

    public String getStudentLandLinePhoneNumber() {
        return studentLandLinePhoneNumber;
    }

    public TempStudent studentLandLinePhoneNumber(String studentLandLinePhoneNumber) {
        this.studentLandLinePhoneNumber = studentLandLinePhoneNumber;
        return this;
    }

    public void setStudentLandLinePhoneNumber(String studentLandLinePhoneNumber) {
        this.studentLandLinePhoneNumber = studentLandLinePhoneNumber;
    }

    public String getStudentPrimaryEmailId() {
        return studentPrimaryEmailId;
    }

    public TempStudent studentPrimaryEmailId(String studentPrimaryEmailId) {
        this.studentPrimaryEmailId = studentPrimaryEmailId;
        return this;
    }

    public void setStudentPrimaryEmailId(String studentPrimaryEmailId) {
        this.studentPrimaryEmailId = studentPrimaryEmailId;
    }

    public String getStudentAlternateEmailId() {
        return studentAlternateEmailId;
    }

    public TempStudent studentAlternateEmailId(String studentAlternateEmailId) {
        this.studentAlternateEmailId = studentAlternateEmailId;
        return this;
    }

    public void setStudentAlternateEmailId(String studentAlternateEmailId) {
        this.studentAlternateEmailId = studentAlternateEmailId;
    }

    public String getRelationWithStudent() {
        return relationWithStudent;
    }

    public TempStudent relationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
        return this;
    }

    public void setRelationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public TempStudent emergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
        return this;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactMiddleName() {
        return emergencyContactMiddleName;
    }

    public TempStudent emergencyContactMiddleName(String emergencyContactMiddleName) {
        this.emergencyContactMiddleName = emergencyContactMiddleName;
        return this;
    }

    public void setEmergencyContactMiddleName(String emergencyContactMiddleName) {
        this.emergencyContactMiddleName = emergencyContactMiddleName;
    }

    public String getEmergencyContactLastName() {
        return emergencyContactLastName;
    }

    public TempStudent emergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
        return this;
    }

    public void setEmergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
    }

    public String getEmergencyContactCellNumber() {
        return emergencyContactCellNumber;
    }

    public TempStudent emergencyContactCellNumber(String emergencyContactCellNumber) {
        this.emergencyContactCellNumber = emergencyContactCellNumber;
        return this;
    }

    public void setEmergencyContactCellNumber(String emergencyContactCellNumber) {
        this.emergencyContactCellNumber = emergencyContactCellNumber;
    }

    public String getEmergencyContactLandLinePhoneNumber() {
        return emergencyContactLandLinePhoneNumber;
    }

    public TempStudent emergencyContactLandLinePhoneNumber(String emergencyContactLandLinePhoneNumber) {
        this.emergencyContactLandLinePhoneNumber = emergencyContactLandLinePhoneNumber;
        return this;
    }

    public void setEmergencyContactLandLinePhoneNumber(String emergencyContactLandLinePhoneNumber) {
        this.emergencyContactLandLinePhoneNumber = emergencyContactLandLinePhoneNumber;
    }

    public String getEmergencyContactEmailId() {
        return emergencyContactEmailId;
    }

    public TempStudent emergencyContactEmailId(String emergencyContactEmailId) {
        this.emergencyContactEmailId = emergencyContactEmailId;
        return this;
    }

    public void setEmergencyContactEmailId(String emergencyContactEmailId) {
        this.emergencyContactEmailId = emergencyContactEmailId;
    }

    public String getStudentImagePath() {
        return studentImagePath;
    }

    public TempStudent studentImagePath(String studentImagePath) {
        this.studentImagePath = studentImagePath;
        return this;
    }

    public void setStudentImagePath(String studentImagePath) {
        this.studentImagePath = studentImagePath;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public TempStudent admissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
        return this;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public TempStudent enrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
        return this;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getRollNo() {
        return rollNo;
    }

    public TempStudent rollNo(String rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getStudentType() {
        return studentType;
    }

    public TempStudent studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getFatherCellNumber() {
        return fatherCellNumber;
    }

    public TempStudent fatherCellNumber(String fatherCellNumber) {
        this.fatherCellNumber = fatherCellNumber;
        return this;
    }

    public void setFatherCellNumber(String fatherCellNumber) {
        this.fatherCellNumber = fatherCellNumber;
    }

    public String getFatherEmailId() {
        return fatherEmailId;
    }

    public TempStudent fatherEmailId(String fatherEmailId) {
        this.fatherEmailId = fatherEmailId;
        return this;
    }

    public void setFatherEmailId(String fatherEmailId) {
        this.fatherEmailId = fatherEmailId;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public TempStudent fatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
        return this;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherOfficeEmailId() {
        return fatherOfficeEmailId;
    }

    public TempStudent fatherOfficeEmailId(String fatherOfficeEmailId) {
        this.fatherOfficeEmailId = fatherOfficeEmailId;
        return this;
    }

    public void setFatherOfficeEmailId(String fatherOfficeEmailId) {
        this.fatherOfficeEmailId = fatherOfficeEmailId;
    }

    public String getFatherOfficeAddress() {
        return fatherOfficeAddress;
    }

    public TempStudent fatherOfficeAddress(String fatherOfficeAddress) {
        this.fatherOfficeAddress = fatherOfficeAddress;
        return this;
    }

    public void setFatherOfficeAddress(String fatherOfficeAddress) {
        this.fatherOfficeAddress = fatherOfficeAddress;
    }

    public String getFatherOfficeCellNumber() {
        return fatherOfficeCellNumber;
    }

    public TempStudent fatherOfficeCellNumber(String fatherOfficeCellNumber) {
        this.fatherOfficeCellNumber = fatherOfficeCellNumber;
        return this;
    }

    public void setFatherOfficeCellNumber(String fatherOfficeCellNumber) {
        this.fatherOfficeCellNumber = fatherOfficeCellNumber;
    }

    public String getFatherOfficeLandLinePhoneNumber() {
        return fatherOfficeLandLinePhoneNumber;
    }

    public TempStudent fatherOfficeLandLinePhoneNumber(String fatherOfficeLandLinePhoneNumber) {
        this.fatherOfficeLandLinePhoneNumber = fatherOfficeLandLinePhoneNumber;
        return this;
    }

    public void setFatherOfficeLandLinePhoneNumber(String fatherOfficeLandLinePhoneNumber) {
        this.fatherOfficeLandLinePhoneNumber = fatherOfficeLandLinePhoneNumber;
    }

    public String getFatherAadharNo() {
        return fatherAadharNo;
    }

    public TempStudent fatherAadharNo(String fatherAadharNo) {
        this.fatherAadharNo = fatherAadharNo;
        return this;
    }

    public void setFatherAadharNo(String fatherAadharNo) {
        this.fatherAadharNo = fatherAadharNo;
    }

    public String getFatherPanNo() {
        return fatherPanNo;
    }

    public TempStudent fatherPanNo(String fatherPanNo) {
        this.fatherPanNo = fatherPanNo;
        return this;
    }

    public void setFatherPanNo(String fatherPanNo) {
        this.fatherPanNo = fatherPanNo;
    }

    public String getFatherSocialSecurityNo() {
        return fatherSocialSecurityNo;
    }

    public TempStudent fatherSocialSecurityNo(String fatherSocialSecurityNo) {
        this.fatherSocialSecurityNo = fatherSocialSecurityNo;
        return this;
    }

    public void setFatherSocialSecurityNo(String fatherSocialSecurityNo) {
        this.fatherSocialSecurityNo = fatherSocialSecurityNo;
    }

    public String getFatherTaxReferenceNo() {
        return fatherTaxReferenceNo;
    }

    public TempStudent fatherTaxReferenceNo(String fatherTaxReferenceNo) {
        this.fatherTaxReferenceNo = fatherTaxReferenceNo;
        return this;
    }

    public void setFatherTaxReferenceNo(String fatherTaxReferenceNo) {
        this.fatherTaxReferenceNo = fatherTaxReferenceNo;
    }

    public String getFatherBplNo() {
        return fatherBplNo;
    }

    public TempStudent fatherBplNo(String fatherBplNo) {
        this.fatherBplNo = fatherBplNo;
        return this;
    }

    public void setFatherBplNo(String fatherBplNo) {
        this.fatherBplNo = fatherBplNo;
    }

    public String getFatherDrivingLicenseNo() {
        return fatherDrivingLicenseNo;
    }

    public TempStudent fatherDrivingLicenseNo(String fatherDrivingLicenseNo) {
        this.fatherDrivingLicenseNo = fatherDrivingLicenseNo;
        return this;
    }

    public void setFatherDrivingLicenseNo(String fatherDrivingLicenseNo) {
        this.fatherDrivingLicenseNo = fatherDrivingLicenseNo;
    }

    public String getFatherPassportNo() {
        return fatherPassportNo;
    }

    public TempStudent fatherPassportNo(String fatherPassportNo) {
        this.fatherPassportNo = fatherPassportNo;
        return this;
    }

    public void setFatherPassportNo(String fatherPassportNo) {
        this.fatherPassportNo = fatherPassportNo;
    }

    public String getFatherImagePath() {
        return fatherImagePath;
    }

    public TempStudent fatherImagePath(String fatherImagePath) {
        this.fatherImagePath = fatherImagePath;
        return this;
    }

    public void setFatherImagePath(String fatherImagePath) {
        this.fatherImagePath = fatherImagePath;
    }

    public String getMotherCellNumber() {
        return motherCellNumber;
    }

    public TempStudent motherCellNumber(String motherCellNumber) {
        this.motherCellNumber = motherCellNumber;
        return this;
    }

    public void setMotherCellNumber(String motherCellNumber) {
        this.motherCellNumber = motherCellNumber;
    }

    public String getMotherEmailId() {
        return motherEmailId;
    }

    public TempStudent motherEmailId(String motherEmailId) {
        this.motherEmailId = motherEmailId;
        return this;
    }

    public void setMotherEmailId(String motherEmailId) {
        this.motherEmailId = motherEmailId;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public TempStudent motherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
        return this;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherOfficeEmailId() {
        return motherOfficeEmailId;
    }

    public TempStudent motherOfficeEmailId(String motherOfficeEmailId) {
        this.motherOfficeEmailId = motherOfficeEmailId;
        return this;
    }

    public void setMotherOfficeEmailId(String motherOfficeEmailId) {
        this.motherOfficeEmailId = motherOfficeEmailId;
    }

    public String getMotherOfficeAddress() {
        return motherOfficeAddress;
    }

    public TempStudent motherOfficeAddress(String motherOfficeAddress) {
        this.motherOfficeAddress = motherOfficeAddress;
        return this;
    }

    public void setMotherOfficeAddress(String motherOfficeAddress) {
        this.motherOfficeAddress = motherOfficeAddress;
    }

    public String getMotherOfficeCellNumber() {
        return motherOfficeCellNumber;
    }

    public TempStudent motherOfficeCellNumber(String motherOfficeCellNumber) {
        this.motherOfficeCellNumber = motherOfficeCellNumber;
        return this;
    }

    public void setMotherOfficeCellNumber(String motherOfficeCellNumber) {
        this.motherOfficeCellNumber = motherOfficeCellNumber;
    }

    public String getMotherOfficeLandLinePhoneNumber() {
        return motherOfficeLandLinePhoneNumber;
    }

    public TempStudent motherOfficeLandLinePhoneNumber(String motherOfficeLandLinePhoneNumber) {
        this.motherOfficeLandLinePhoneNumber = motherOfficeLandLinePhoneNumber;
        return this;
    }

    public void setMotherOfficeLandLinePhoneNumber(String motherOfficeLandLinePhoneNumber) {
        this.motherOfficeLandLinePhoneNumber = motherOfficeLandLinePhoneNumber;
    }

    public String getMotherAadharNo() {
        return motherAadharNo;
    }

    public TempStudent motherAadharNo(String motherAadharNo) {
        this.motherAadharNo = motherAadharNo;
        return this;
    }

    public void setMotherAadharNo(String motherAadharNo) {
        this.motherAadharNo = motherAadharNo;
    }

    public String getMotherPanNo() {
        return motherPanNo;
    }

    public TempStudent motherPanNo(String motherPanNo) {
        this.motherPanNo = motherPanNo;
        return this;
    }

    public void setMotherPanNo(String motherPanNo) {
        this.motherPanNo = motherPanNo;
    }

    public String getMotherSocialSecurityNo() {
        return motherSocialSecurityNo;
    }

    public TempStudent motherSocialSecurityNo(String motherSocialSecurityNo) {
        this.motherSocialSecurityNo = motherSocialSecurityNo;
        return this;
    }

    public void setMotherSocialSecurityNo(String motherSocialSecurityNo) {
        this.motherSocialSecurityNo = motherSocialSecurityNo;
    }

    public String getMotherTaxReferenceNo() {
        return motherTaxReferenceNo;
    }

    public TempStudent motherTaxReferenceNo(String motherTaxReferenceNo) {
        this.motherTaxReferenceNo = motherTaxReferenceNo;
        return this;
    }

    public void setMotherTaxReferenceNo(String motherTaxReferenceNo) {
        this.motherTaxReferenceNo = motherTaxReferenceNo;
    }

    public String getMotherBplNo() {
        return motherBplNo;
    }

    public TempStudent motherBplNo(String motherBplNo) {
        this.motherBplNo = motherBplNo;
        return this;
    }

    public void setMotherBplNo(String motherBplNo) {
        this.motherBplNo = motherBplNo;
    }

    public String getMotherDrivingLicenseNo() {
        return motherDrivingLicenseNo;
    }

    public TempStudent motherDrivingLicenseNo(String motherDrivingLicenseNo) {
        this.motherDrivingLicenseNo = motherDrivingLicenseNo;
        return this;
    }

    public void setMotherDrivingLicenseNo(String motherDrivingLicenseNo) {
        this.motherDrivingLicenseNo = motherDrivingLicenseNo;
    }

    public String getMotherPassportNo() {
        return motherPassportNo;
    }

    public TempStudent motherPassportNo(String motherPassportNo) {
        this.motherPassportNo = motherPassportNo;
        return this;
    }

    public void setMotherPassportNo(String motherPassportNo) {
        this.motherPassportNo = motherPassportNo;
    }

    public String getMotherImagePath() {
        return motherImagePath;
    }

    public TempStudent motherImagePath(String motherImagePath) {
        this.motherImagePath = motherImagePath;
        return this;
    }

    public void setMotherImagePath(String motherImagePath) {
        this.motherImagePath = motherImagePath;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public TempStudent guardianName(String guardianName) {
        this.guardianName = guardianName;
        return this;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianMiddleName() {
        return guardianMiddleName;
    }

    public TempStudent guardianMiddleName(String guardianMiddleName) {
        this.guardianMiddleName = guardianMiddleName;
        return this;
    }

    public void setGuardianMiddleName(String guardianMiddleName) {
        this.guardianMiddleName = guardianMiddleName;
    }

    public String getGuardianLastName() {
        return guardianLastName;
    }

    public TempStudent guardianLastName(String guardianLastName) {
        this.guardianLastName = guardianLastName;
        return this;
    }

    public void setGuardianLastName(String guardianLastName) {
        this.guardianLastName = guardianLastName;
    }

    public String getGuardianAddress() {
        return guardianAddress;
    }

    public TempStudent guardianAddress(String guardianAddress) {
        this.guardianAddress = guardianAddress;
        return this;
    }

    public void setGuardianAddress(String guardianAddress) {
        this.guardianAddress = guardianAddress;
    }

    public String getGuardianCellNumber() {
        return guardianCellNumber;
    }

    public TempStudent guardianCellNumber(String guardianCellNumber) {
        this.guardianCellNumber = guardianCellNumber;
        return this;
    }

    public void setGuardianCellNumber(String guardianCellNumber) {
        this.guardianCellNumber = guardianCellNumber;
    }

    public String getGuardianLandLinePhoneNumber() {
        return guardianLandLinePhoneNumber;
    }

    public TempStudent guardianLandLinePhoneNumber(String guardianLandLinePhoneNumber) {
        this.guardianLandLinePhoneNumber = guardianLandLinePhoneNumber;
        return this;
    }

    public void setGuardianLandLinePhoneNumber(String guardianLandLinePhoneNumber) {
        this.guardianLandLinePhoneNumber = guardianLandLinePhoneNumber;
    }

    public String getGuardianEmailId() {
        return guardianEmailId;
    }

    public TempStudent guardianEmailId(String guardianEmailId) {
        this.guardianEmailId = guardianEmailId;
        return this;
    }

    public void setGuardianEmailId(String guardianEmailId) {
        this.guardianEmailId = guardianEmailId;
    }

    public String getGuardianOccupation() {
        return guardianOccupation;
    }

    public TempStudent guardianOccupation(String guardianOccupation) {
        this.guardianOccupation = guardianOccupation;
        return this;
    }

    public void setGuardianOccupation(String guardianOccupation) {
        this.guardianOccupation = guardianOccupation;
    }

    public String getGuardianOfficeEmailId() {
        return guardianOfficeEmailId;
    }

    public TempStudent guardianOfficeEmailId(String guardianOfficeEmailId) {
        this.guardianOfficeEmailId = guardianOfficeEmailId;
        return this;
    }

    public void setGuardianOfficeEmailId(String guardianOfficeEmailId) {
        this.guardianOfficeEmailId = guardianOfficeEmailId;
    }

    public String getGuardianOfficeAddress() {
        return guardianOfficeAddress;
    }

    public TempStudent guardianOfficeAddress(String guardianOfficeAddress) {
        this.guardianOfficeAddress = guardianOfficeAddress;
        return this;
    }

    public void setGuardianOfficeAddress(String guardianOfficeAddress) {
        this.guardianOfficeAddress = guardianOfficeAddress;
    }

    public String getGuardianOfficeCellNumber() {
        return guardianOfficeCellNumber;
    }

    public TempStudent guardianOfficeCellNumber(String guardianOfficeCellNumber) {
        this.guardianOfficeCellNumber = guardianOfficeCellNumber;
        return this;
    }

    public void setGuardianOfficeCellNumber(String guardianOfficeCellNumber) {
        this.guardianOfficeCellNumber = guardianOfficeCellNumber;
    }

    public String getGuardianOfficeLandLinePhoneNumber() {
        return guardianOfficeLandLinePhoneNumber;
    }

    public TempStudent guardianOfficeLandLinePhoneNumber(String guardianOfficeLandLinePhoneNumber) {
        this.guardianOfficeLandLinePhoneNumber = guardianOfficeLandLinePhoneNumber;
        return this;
    }

    public void setGuardianOfficeLandLinePhoneNumber(String guardianOfficeLandLinePhoneNumber) {
        this.guardianOfficeLandLinePhoneNumber = guardianOfficeLandLinePhoneNumber;
    }

    public String getGuardianImagePath() {
        return guardianImagePath;
    }

    public TempStudent guardianImagePath(String guardianImagePath) {
        this.guardianImagePath = guardianImagePath;
        return this;
    }

    public void setGuardianImagePath(String guardianImagePath) {
        this.guardianImagePath = guardianImagePath;
    }

    public String getIsGuardianSponsorAgency() {
        return isGuardianSponsorAgency;
    }

    public TempStudent isGuardianSponsorAgency(String isGuardianSponsorAgency) {
        this.isGuardianSponsorAgency = isGuardianSponsorAgency;
        return this;
    }

    public void setIsGuardianSponsorAgency(String isGuardianSponsorAgency) {
        this.isGuardianSponsorAgency = isGuardianSponsorAgency;
    }

    public String getSponsorAgencyName() {
        return sponsorAgencyName;
    }

    public TempStudent sponsorAgencyName(String sponsorAgencyName) {
        this.sponsorAgencyName = sponsorAgencyName;
        return this;
    }

    public void setSponsorAgencyName(String sponsorAgencyName) {
        this.sponsorAgencyName = sponsorAgencyName;
    }

    public String getSponsorAgencyRegistrationNo() {
        return sponsorAgencyRegistrationNo;
    }

    public TempStudent sponsorAgencyRegistrationNo(String sponsorAgencyRegistrationNo) {
        this.sponsorAgencyRegistrationNo = sponsorAgencyRegistrationNo;
        return this;
    }

    public void setSponsorAgencyRegistrationNo(String sponsorAgencyRegistrationNo) {
        this.sponsorAgencyRegistrationNo = sponsorAgencyRegistrationNo;
    }

    public String getSponsorAgencyAddress() {
        return sponsorAgencyAddress;
    }

    public TempStudent sponsorAgencyAddress(String sponsorAgencyAddress) {
        this.sponsorAgencyAddress = sponsorAgencyAddress;
        return this;
    }

    public void setSponsorAgencyAddress(String sponsorAgencyAddress) {
        this.sponsorAgencyAddress = sponsorAgencyAddress;
    }

    public String getSponsorAgencyCellNumber() {
        return sponsorAgencyCellNumber;
    }

    public TempStudent sponsorAgencyCellNumber(String sponsorAgencyCellNumber) {
        this.sponsorAgencyCellNumber = sponsorAgencyCellNumber;
        return this;
    }

    public void setSponsorAgencyCellNumber(String sponsorAgencyCellNumber) {
        this.sponsorAgencyCellNumber = sponsorAgencyCellNumber;
    }

    public String getSponsorAgencyLandLineNumber() {
        return sponsorAgencyLandLineNumber;
    }

    public TempStudent sponsorAgencyLandLineNumber(String sponsorAgencyLandLineNumber) {
        this.sponsorAgencyLandLineNumber = sponsorAgencyLandLineNumber;
        return this;
    }

    public void setSponsorAgencyLandLineNumber(String sponsorAgencyLandLineNumber) {
        this.sponsorAgencyLandLineNumber = sponsorAgencyLandLineNumber;
    }

    public String getSponsorAgencyEmailId() {
        return sponsorAgencyEmailId;
    }

    public TempStudent sponsorAgencyEmailId(String sponsorAgencyEmailId) {
        this.sponsorAgencyEmailId = sponsorAgencyEmailId;
        return this;
    }

    public void setSponsorAgencyEmailId(String sponsorAgencyEmailId) {
        this.sponsorAgencyEmailId = sponsorAgencyEmailId;
    }

    public String getSponsorAgencyAppointeeName() {
        return sponsorAgencyAppointeeName;
    }

    public TempStudent sponsorAgencyAppointeeName(String sponsorAgencyAppointeeName) {
        this.sponsorAgencyAppointeeName = sponsorAgencyAppointeeName;
        return this;
    }

    public void setSponsorAgencyAppointeeName(String sponsorAgencyAppointeeName) {
        this.sponsorAgencyAppointeeName = sponsorAgencyAppointeeName;
    }

    public String getSponsorAgencyAppointeeDesignation() {
        return sponsorAgencyAppointeeDesignation;
    }

    public TempStudent sponsorAgencyAppointeeDesignation(String sponsorAgencyAppointeeDesignation) {
        this.sponsorAgencyAppointeeDesignation = sponsorAgencyAppointeeDesignation;
        return this;
    }

    public void setSponsorAgencyAppointeeDesignation(String sponsorAgencyAppointeeDesignation) {
        this.sponsorAgencyAppointeeDesignation = sponsorAgencyAppointeeDesignation;
    }

    public String getSponsorAgencyAppointeeCellNumber() {
        return sponsorAgencyAppointeeCellNumber;
    }

    public TempStudent sponsorAgencyAppointeeCellNumber(String sponsorAgencyAppointeeCellNumber) {
        this.sponsorAgencyAppointeeCellNumber = sponsorAgencyAppointeeCellNumber;
        return this;
    }

    public void setSponsorAgencyAppointeeCellNumber(String sponsorAgencyAppointeeCellNumber) {
        this.sponsorAgencyAppointeeCellNumber = sponsorAgencyAppointeeCellNumber;
    }

    public String getSponsorAgencyAppointeeLandLineNumber() {
        return sponsorAgencyAppointeeLandLineNumber;
    }

    public TempStudent sponsorAgencyAppointeeLandLineNumber(String sponsorAgencyAppointeeLandLineNumber) {
        this.sponsorAgencyAppointeeLandLineNumber = sponsorAgencyAppointeeLandLineNumber;
        return this;
    }

    public void setSponsorAgencyAppointeeLandLineNumber(String sponsorAgencyAppointeeLandLineNumber) {
        this.sponsorAgencyAppointeeLandLineNumber = sponsorAgencyAppointeeLandLineNumber;
    }

    public String getSponsorAgencyAppointeeEmailId() {
        return sponsorAgencyAppointeeEmailId;
    }

    public TempStudent sponsorAgencyAppointeeEmailId(String sponsorAgencyAppointeeEmailId) {
        this.sponsorAgencyAppointeeEmailId = sponsorAgencyAppointeeEmailId;
        return this;
    }

    public void setSponsorAgencyAppointeeEmailId(String sponsorAgencyAppointeeEmailId) {
        this.sponsorAgencyAppointeeEmailId = sponsorAgencyAppointeeEmailId;
    }

    public String getSponsorAgencyAppointeeOfficeAddress() {
        return sponsorAgencyAppointeeOfficeAddress;
    }

    public TempStudent sponsorAgencyAppointeeOfficeAddress(String sponsorAgencyAppointeeOfficeAddress) {
        this.sponsorAgencyAppointeeOfficeAddress = sponsorAgencyAppointeeOfficeAddress;
        return this;
    }

    public void setSponsorAgencyAppointeeOfficeAddress(String sponsorAgencyAppointeeOfficeAddress) {
        this.sponsorAgencyAppointeeOfficeAddress = sponsorAgencyAppointeeOfficeAddress;
    }

    public String getIsPhysicallyChallenged() {
        return isPhysicallyChallenged;
    }

    public TempStudent isPhysicallyChallenged(String isPhysicallyChallenged) {
        this.isPhysicallyChallenged = isPhysicallyChallenged;
        return this;
    }

    public void setIsPhysicallyChallenged(String isPhysicallyChallenged) {
        this.isPhysicallyChallenged = isPhysicallyChallenged;
    }

    public String getDetailsOfDisability() {
        return detailsOfDisability;
    }

    public TempStudent detailsOfDisability(String detailsOfDisability) {
        this.detailsOfDisability = detailsOfDisability;
        return this;
    }

    public void setDetailsOfDisability(String detailsOfDisability) {
        this.detailsOfDisability = detailsOfDisability;
    }

    public String getDisabilityCertificateNo() {
        return disabilityCertificateNo;
    }

    public TempStudent disabilityCertificateNo(String disabilityCertificateNo) {
        this.disabilityCertificateNo = disabilityCertificateNo;
        return this;
    }

    public void setDisabilityCertificateNo(String disabilityCertificateNo) {
        this.disabilityCertificateNo = disabilityCertificateNo;
    }

    public String getDisabilityCertificateIssueAuthority() {
        return disabilityCertificateIssueAuthority;
    }

    public TempStudent disabilityCertificateIssueAuthority(String disabilityCertificateIssueAuthority) {
        this.disabilityCertificateIssueAuthority = disabilityCertificateIssueAuthority;
        return this;
    }

    public void setDisabilityCertificateIssueAuthority(String disabilityCertificateIssueAuthority) {
        this.disabilityCertificateIssueAuthority = disabilityCertificateIssueAuthority;
    }

    public LocalDate getDisabilityCertificateIssueDate() {
        return disabilityCertificateIssueDate;
    }

    public TempStudent disabilityCertificateIssueDate(LocalDate disabilityCertificateIssueDate) {
        this.disabilityCertificateIssueDate = disabilityCertificateIssueDate;
        return this;
    }

    public void setDisabilityCertificateIssueDate(LocalDate disabilityCertificateIssueDate) {
        this.disabilityCertificateIssueDate = disabilityCertificateIssueDate;
    }

    public Integer getPercentagOfDisability() {
        return percentagOfDisability;
    }

    public TempStudent percentagOfDisability(Integer percentagOfDisability) {
        this.percentagOfDisability = percentagOfDisability;
        return this;
    }

    public void setPercentagOfDisability(Integer percentagOfDisability) {
        this.percentagOfDisability = percentagOfDisability;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public TempStudent bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getVaccinationDetails() {
        return vaccinationDetails;
    }

    public TempStudent vaccinationDetails(String vaccinationDetails) {
        this.vaccinationDetails = vaccinationDetails;
        return this;
    }

    public void setVaccinationDetails(String vaccinationDetails) {
        this.vaccinationDetails = vaccinationDetails;
    }

    public String getOtherMedicalDetails() {
        return otherMedicalDetails;
    }

    public TempStudent otherMedicalDetails(String otherMedicalDetails) {
        this.otherMedicalDetails = otherMedicalDetails;
        return this;
    }

    public void setOtherMedicalDetails(String otherMedicalDetails) {
        this.otherMedicalDetails = otherMedicalDetails;
    }

    public String getStatus() {
        return status;
    }

    public TempStudent status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public TempStudent comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public TempStudent departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public TempStudent branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public TempStudent sectionId(Long sectionId) {
        this.sectionId = sectionId;
        return this;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public TempStudent batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public TempStudent academicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
        return this;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TempStudent)) {
            return false;
        }
        return id != null && id.equals(((TempStudent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TempStudent{" +
            "id=" + getId() +
            ", stateMachineId='" + getStateMachineId() + "'" +
            ", studentName='" + getStudentName() + "'" +
            ", studentMiddleName='" + getStudentMiddleName() + "'" +
            ", studentLastName='" + getStudentLastName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", fatherMiddleName='" + getFatherMiddleName() + "'" +
            ", fatherLastName='" + getFatherLastName() + "'" +
            ", motherName='" + getMotherName() + "'" +
            ", motherMiddleName='" + getMotherMiddleName() + "'" +
            ", motherLastName='" + getMotherLastName() + "'" +
            ", studentAadharNo='" + getStudentAadharNo() + "'" +
            ", studentPanNo='" + getStudentPanNo() + "'" +
            ", studentSocialSecurityNo='" + getStudentSocialSecurityNo() + "'" +
            ", studentTaxReferenceNo='" + getStudentTaxReferenceNo() + "'" +
            ", studentBplNo='" + getStudentBplNo() + "'" +
            ", studentDrivingLicenseNo='" + getStudentDrivingLicenseNo() + "'" +
            ", studentPassportNo='" + getStudentPassportNo() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", placeOfBirth='" + getPlaceOfBirth() + "'" +
            ", religion='" + getReligion() + "'" +
            ", caste='" + getCaste() + "'" +
            ", subCaste='" + getSubCaste() + "'" +
            ", sex='" + getSex() + "'" +
            ", studentLocalAddress='" + getStudentLocalAddress() + "'" +
            ", studentPermanentAddress='" + getStudentPermanentAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", studentPrimaryCellNumber='" + getStudentPrimaryCellNumber() + "'" +
            ", studentAlternateCellNumber='" + getStudentAlternateCellNumber() + "'" +
            ", studentLandLinePhoneNumber='" + getStudentLandLinePhoneNumber() + "'" +
            ", studentPrimaryEmailId='" + getStudentPrimaryEmailId() + "'" +
            ", studentAlternateEmailId='" + getStudentAlternateEmailId() + "'" +
            ", relationWithStudent='" + getRelationWithStudent() + "'" +
            ", emergencyContactName='" + getEmergencyContactName() + "'" +
            ", emergencyContactMiddleName='" + getEmergencyContactMiddleName() + "'" +
            ", emergencyContactLastName='" + getEmergencyContactLastName() + "'" +
            ", emergencyContactCellNumber='" + getEmergencyContactCellNumber() + "'" +
            ", emergencyContactLandLinePhoneNumber='" + getEmergencyContactLandLinePhoneNumber() + "'" +
            ", emergencyContactEmailId='" + getEmergencyContactEmailId() + "'" +
            ", studentImagePath='" + getStudentImagePath() + "'" +
            ", admissionNo='" + getAdmissionNo() + "'" +
            ", enrollmentNo='" + getEnrollmentNo() + "'" +
            ", rollNo='" + getRollNo() + "'" +
            ", studentType='" + getStudentType() + "'" +
            ", fatherCellNumber='" + getFatherCellNumber() + "'" +
            ", fatherEmailId='" + getFatherEmailId() + "'" +
            ", fatherOccupation='" + getFatherOccupation() + "'" +
            ", fatherOfficeEmailId='" + getFatherOfficeEmailId() + "'" +
            ", fatherOfficeAddress='" + getFatherOfficeAddress() + "'" +
            ", fatherOfficeCellNumber='" + getFatherOfficeCellNumber() + "'" +
            ", fatherOfficeLandLinePhoneNumber='" + getFatherOfficeLandLinePhoneNumber() + "'" +
            ", fatherAadharNo='" + getFatherAadharNo() + "'" +
            ", fatherPanNo='" + getFatherPanNo() + "'" +
            ", fatherSocialSecurityNo='" + getFatherSocialSecurityNo() + "'" +
            ", fatherTaxReferenceNo='" + getFatherTaxReferenceNo() + "'" +
            ", fatherBplNo='" + getFatherBplNo() + "'" +
            ", fatherDrivingLicenseNo='" + getFatherDrivingLicenseNo() + "'" +
            ", fatherPassportNo='" + getFatherPassportNo() + "'" +
            ", fatherImagePath='" + getFatherImagePath() + "'" +
            ", motherCellNumber='" + getMotherCellNumber() + "'" +
            ", motherEmailId='" + getMotherEmailId() + "'" +
            ", motherOccupation='" + getMotherOccupation() + "'" +
            ", motherOfficeEmailId='" + getMotherOfficeEmailId() + "'" +
            ", motherOfficeAddress='" + getMotherOfficeAddress() + "'" +
            ", motherOfficeCellNumber='" + getMotherOfficeCellNumber() + "'" +
            ", motherOfficeLandLinePhoneNumber='" + getMotherOfficeLandLinePhoneNumber() + "'" +
            ", motherAadharNo='" + getMotherAadharNo() + "'" +
            ", motherPanNo='" + getMotherPanNo() + "'" +
            ", motherSocialSecurityNo='" + getMotherSocialSecurityNo() + "'" +
            ", motherTaxReferenceNo='" + getMotherTaxReferenceNo() + "'" +
            ", motherBplNo='" + getMotherBplNo() + "'" +
            ", motherDrivingLicenseNo='" + getMotherDrivingLicenseNo() + "'" +
            ", motherPassportNo='" + getMotherPassportNo() + "'" +
            ", motherImagePath='" + getMotherImagePath() + "'" +
            ", guardianName='" + getGuardianName() + "'" +
            ", guardianMiddleName='" + getGuardianMiddleName() + "'" +
            ", guardianLastName='" + getGuardianLastName() + "'" +
            ", guardianAddress='" + getGuardianAddress() + "'" +
            ", guardianCellNumber='" + getGuardianCellNumber() + "'" +
            ", guardianLandLinePhoneNumber='" + getGuardianLandLinePhoneNumber() + "'" +
            ", guardianEmailId='" + getGuardianEmailId() + "'" +
            ", guardianOccupation='" + getGuardianOccupation() + "'" +
            ", guardianOfficeEmailId='" + getGuardianOfficeEmailId() + "'" +
            ", guardianOfficeAddress='" + getGuardianOfficeAddress() + "'" +
            ", guardianOfficeCellNumber='" + getGuardianOfficeCellNumber() + "'" +
            ", guardianOfficeLandLinePhoneNumber='" + getGuardianOfficeLandLinePhoneNumber() + "'" +
            ", guardianImagePath='" + getGuardianImagePath() + "'" +
            ", isGuardianSponsorAgency='" + getIsGuardianSponsorAgency() + "'" +
            ", sponsorAgencyName='" + getSponsorAgencyName() + "'" +
            ", sponsorAgencyRegistrationNo='" + getSponsorAgencyRegistrationNo() + "'" +
            ", sponsorAgencyAddress='" + getSponsorAgencyAddress() + "'" +
            ", sponsorAgencyCellNumber='" + getSponsorAgencyCellNumber() + "'" +
            ", sponsorAgencyLandLineNumber='" + getSponsorAgencyLandLineNumber() + "'" +
            ", sponsorAgencyEmailId='" + getSponsorAgencyEmailId() + "'" +
            ", sponsorAgencyAppointeeName='" + getSponsorAgencyAppointeeName() + "'" +
            ", sponsorAgencyAppointeeDesignation='" + getSponsorAgencyAppointeeDesignation() + "'" +
            ", sponsorAgencyAppointeeCellNumber='" + getSponsorAgencyAppointeeCellNumber() + "'" +
            ", sponsorAgencyAppointeeLandLineNumber='" + getSponsorAgencyAppointeeLandLineNumber() + "'" +
            ", sponsorAgencyAppointeeEmailId='" + getSponsorAgencyAppointeeEmailId() + "'" +
            ", sponsorAgencyAppointeeOfficeAddress='" + getSponsorAgencyAppointeeOfficeAddress() + "'" +
            ", isPhysicallyChallenged='" + getIsPhysicallyChallenged() + "'" +
            ", detailsOfDisability='" + getDetailsOfDisability() + "'" +
            ", disabilityCertificateNo='" + getDisabilityCertificateNo() + "'" +
            ", disabilityCertificateIssueAuthority='" + getDisabilityCertificateIssueAuthority() + "'" +
            ", disabilityCertificateIssueDate='" + getDisabilityCertificateIssueDate() + "'" +
            ", percentagOfDisability=" + getPercentagOfDisability() +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", vaccinationDetails='" + getVaccinationDetails() + "'" +
            ", otherMedicalDetails='" + getOtherMedicalDetails() + "'" +
            ", status='" + getStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", departmentId=" + getDepartmentId() +
            ", branchId=" + getBranchId() +
            ", sectionId=" + getSectionId() +
            ", batchId=" + getBatchId() +
            ", academicYearId=" + getAcademicYearId() +
            "}";
    }

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public String getLastQualificationPercentage() {
		return lastQualificationPercentage;
	}

	public void setLastQualificationPercentage(String lastQualificationPercentage) {
		this.lastQualificationPercentage = lastQualificationPercentage;
	}

	public String getLastCollegeAttended() {
		return lastCollegeAttended;
	}

	public void setLastCollegeAttended(String lastCollegeAttended) {
		this.lastCollegeAttended = lastCollegeAttended;
	}
}
