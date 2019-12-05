package com.synectiks.admission.graphql.types.AdmissionEnquiry;

import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;

public class AdmissionEnquiryInput extends CmsAdmissionEnquiryVo{

	@Override
	public String toString() {
		return "AdmissionEnquiryInput [getId()=" + getId() + ", getStudentName()=" + getStudentName()
				+ ", getStudentMiddleName()=" + getStudentMiddleName() + ", getStudentLastName()="
				+ getStudentLastName() + ", getCellPhoneNo()=" + getCellPhoneNo() + ", getLandLinePhoneNo()="
				+ getLandLinePhoneNo() + ", getEmailId()=" + getEmailId() + ", getDateOfBirth()=" + getDateOfBirth()
				+ ", getGender()=" + getGender() + ", getHighestQualification()=" + getHighestQualification()
				+ ", getModeOfEnquiry()=" + getModeOfEnquiry() + ", getEnquiryDate()=" + getEnquiryDate()
				+ ", getComments()=" + getComments() + ", getBranchId()=" + getBranchId() + ", getDepartmentId()="
				+ getDepartmentId() + ", getCourseId()=" + getCourseId() + ", getSemesterId()=" + getSemesterId()
				+ ", getBatchId()=" + getBatchId() + ", getStateId()=" + getStateId() + ", getCityId()=" + getCityId()
				+ ", getAcademicYearId()=" + getAcademicYearId() + ", getEnquiryStatus()=" + getEnquiryStatus()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()="
				+ getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getStrDateOfBirth()=" + getStrDateOfBirth()
				+ ", getStrEnquiryDate()=" + getStrEnquiryDate() + ", getStrCreatedOn()=" + getStrCreatedOn()
				+ ", getStrUpdatedOn()=" + getStrUpdatedOn() + ", getStatus()=" + getStatus() + ", getErrorCode()="
				+ getErrorCode() + ", getErrorDescription()=" + getErrorDescription() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

    
}
