package com.synectiks.admission.graphql.types.TempStudent;

import com.synectiks.admission.domain.vo.CmsTempStudentVo;

public class TempStudentInput extends CmsTempStudentVo{

	@Override
	public String toString() {
		return "TempStudentInput [getId()=" + getId() + ", getStateMachineId()=" + getStateMachineId()
				+ ", getStudentName()=" + getStudentName() + ", getStudentMiddleName()=" + getStudentMiddleName()
				+ ", getStudentLastName()=" + getStudentLastName() + ", getFatherName()=" + getFatherName()
				+ ", getFatherMiddleName()=" + getFatherMiddleName() + ", getFatherLastName()=" + getFatherLastName()
				+ ", getMotherName()=" + getMotherName() + ", getMotherMiddleName()=" + getMotherMiddleName()
				+ ", getMotherLastName()=" + getMotherLastName() + ", getStudentAadharNo()=" + getStudentAadharNo()
				+ ", getStudentPanNo()=" + getStudentPanNo() + ", getStudentSocialSecurityNo()="
				+ getStudentSocialSecurityNo() + ", getStudentTaxReferenceNo()=" + getStudentTaxReferenceNo()
				+ ", getStudentBplNo()=" + getStudentBplNo() + ", getStudentDrivingLicenseNo()="
				+ getStudentDrivingLicenseNo() + ", getStudentPassportNo()=" + getStudentPassportNo()
				+ ", getPlaceOfBirth()=" + getPlaceOfBirth() + ", getReligion()=" + getReligion() + ", getCaste()="
				+ getCaste() + ", getSubCaste()=" + getSubCaste() + ", getSex()=" + getSex()
				+ ", getStudentLocalAddress()=" + getStudentLocalAddress() + ", getStudentPermanentAddress()="
				+ getStudentPermanentAddress() + ", getCity()=" + getCity() + ", getState()=" + getState()
				+ ", getCountry()=" + getCountry() + ", getPinCode()=" + getPinCode()
				+ ", getStudentPrimaryCellNumber()=" + getStudentPrimaryCellNumber()
				+ ", getStudentAlternateCellNumber()=" + getStudentAlternateCellNumber()
				+ ", getStudentLandLinePhoneNumber()=" + getStudentLandLinePhoneNumber()
				+ ", getStudentPrimaryEmailId()=" + getStudentPrimaryEmailId() + ", getStudentAlternateEmailId()="
				+ getStudentAlternateEmailId() + ", getRelationWithStudent()=" + getRelationWithStudent()
				+ ", getEmergencyContactName()=" + getEmergencyContactName() + ", getEmergencyContactMiddleName()="
				+ getEmergencyContactMiddleName() + ", getEmergencyContactLastName()=" + getEmergencyContactLastName()
				+ ", getEmergencyContactCellNumber()=" + getEmergencyContactCellNumber()
				+ ", getEmergencyContactLandLinePhoneNumber()=" + getEmergencyContactLandLinePhoneNumber()
				+ ", getEmergencyContactEmailId()=" + getEmergencyContactEmailId() + ", getStudentImagePath()="
				+ getStudentImagePath() + ", getAdmissionNo()=" + getAdmissionNo() + ", getEnrollmentNo()="
				+ getEnrollmentNo() + ", getRollNo()=" + getRollNo() + ", getStudentType()=" + getStudentType()
				+ ", getFatherCellNumber()=" + getFatherCellNumber() + ", getFatherEmailId()=" + getFatherEmailId()
				+ ", getFatherOccupation()=" + getFatherOccupation() + ", getFatherOfficeEmailId()="
				+ getFatherOfficeEmailId() + ", getFatherOfficeAddress()=" + getFatherOfficeAddress()
				+ ", getFatherOfficeCellNumber()=" + getFatherOfficeCellNumber()
				+ ", getFatherOfficeLandLinePhoneNumber()=" + getFatherOfficeLandLinePhoneNumber()
				+ ", getFatherAadharNo()=" + getFatherAadharNo() + ", getFatherPanNo()=" + getFatherPanNo()
				+ ", getFatherSocialSecurityNo()=" + getFatherSocialSecurityNo() + ", getFatherTaxReferenceNo()="
				+ getFatherTaxReferenceNo() + ", getFatherBplNo()=" + getFatherBplNo()
				+ ", getFatherDrivingLicenseNo()=" + getFatherDrivingLicenseNo() + ", getFatherPassportNo()="
				+ getFatherPassportNo() + ", getFatherImagePath()=" + getFatherImagePath() + ", getMotherCellNumber()="
				+ getMotherCellNumber() + ", getMotherEmailId()=" + getMotherEmailId() + ", getMotherOccupation()="
				+ getMotherOccupation() + ", getMotherOfficeEmailId()=" + getMotherOfficeEmailId()
				+ ", getMotherOfficeAddress()=" + getMotherOfficeAddress() + ", getMotherOfficeCellNumber()="
				+ getMotherOfficeCellNumber() + ", getMotherOfficeLandLinePhoneNumber()="
				+ getMotherOfficeLandLinePhoneNumber() + ", getMotherAadharNo()=" + getMotherAadharNo()
				+ ", getMotherPanNo()=" + getMotherPanNo() + ", getMotherSocialSecurityNo()="
				+ getMotherSocialSecurityNo() + ", getMotherTaxReferenceNo()=" + getMotherTaxReferenceNo()
				+ ", getMotherBplNo()=" + getMotherBplNo() + ", getMotherDrivingLicenseNo()="
				+ getMotherDrivingLicenseNo() + ", getMotherPassportNo()=" + getMotherPassportNo()
				+ ", getMotherImagePath()=" + getMotherImagePath() + ", getGuardianName()=" + getGuardianName()
				+ ", getGuardianMiddleName()=" + getGuardianMiddleName() + ", getGuardianLastName()="
				+ getGuardianLastName() + ", getGuardianAddress()=" + getGuardianAddress()
				+ ", getGuardianCellNumber()=" + getGuardianCellNumber() + ", getGuardianLandLinePhoneNumber()="
				+ getGuardianLandLinePhoneNumber() + ", getGuardianEmailId()=" + getGuardianEmailId()
				+ ", getGuardianOccupation()=" + getGuardianOccupation() + ", getGuardianOfficeEmailId()="
				+ getGuardianOfficeEmailId() + ", getGuardianOfficeAddress()=" + getGuardianOfficeAddress()
				+ ", getGuardianOfficeCellNumber()=" + getGuardianOfficeCellNumber()
				+ ", getGuardianOfficeLandLinePhoneNumber()=" + getGuardianOfficeLandLinePhoneNumber()
				+ ", getGuardianImagePath()=" + getGuardianImagePath() + ", getIsGuardianSponsorAgency()="
				+ getIsGuardianSponsorAgency() + ", getSponsorAgencyName()=" + getSponsorAgencyName()
				+ ", getSponsorAgencyRegistrationNo()=" + getSponsorAgencyRegistrationNo()
				+ ", getSponsorAgencyAddress()=" + getSponsorAgencyAddress() + ", getSponsorAgencyCellNumber()="
				+ getSponsorAgencyCellNumber() + ", getSponsorAgencyLandLineNumber()="
				+ getSponsorAgencyLandLineNumber() + ", getSponsorAgencyEmailId()=" + getSponsorAgencyEmailId()
				+ ", getSponsorAgencyAppointeeName()=" + getSponsorAgencyAppointeeName()
				+ ", getSponsorAgencyAppointeeDesignation()=" + getSponsorAgencyAppointeeDesignation()
				+ ", getSponsorAgencyAppointeeCellNumber()=" + getSponsorAgencyAppointeeCellNumber()
				+ ", getSponsorAgencyAppointeeLandLineNumber()=" + getSponsorAgencyAppointeeLandLineNumber()
				+ ", getSponsorAgencyAppointeeEmailId()=" + getSponsorAgencyAppointeeEmailId()
				+ ", getSponsorAgencyAppointeeOfficeAddress()=" + getSponsorAgencyAppointeeOfficeAddress()
				+ ", getIsPhysicallyChallenged()=" + getIsPhysicallyChallenged() + ", getDetailsOfDisability()="
				+ getDetailsOfDisability() + ", getDisabilityCertificateNo()=" + getDisabilityCertificateNo()
				+ ", getDisabilityCertificateIssueAuthority()=" + getDisabilityCertificateIssueAuthority()
				+ ", getDisabilityCertificateIssueDate()=" + getDisabilityCertificateIssueDate()
				+ ", getPercentagOfDisability()=" + getPercentagOfDisability() + ", getBloodGroup()=" + getBloodGroup()
				+ ", getVaccinationDetails()=" + getVaccinationDetails() + ", getOtherMedicalDetails()="
				+ getOtherMedicalDetails() + ", getStatus()=" + getStatus() + ", getComments()=" + getComments()
				+ ", getDepartmentId()=" + getDepartmentId() + ", getBranchId()=" + getBranchId() + ", getSectionId()="
				+ getSectionId() + ", getBatchId()=" + getBatchId() + ", getAcademicYearId()=" + getAcademicYearId()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedOn()=" + getUpdatedOn() + ", getStrCreatedOn()=" + getStrCreatedOn()
				+ ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getExitCode()=" + getExitCode()
				+ ", getExitDescription()=" + getExitDescription() + ", getClass()=" + getClass() + "]";
	}

		
    
}
