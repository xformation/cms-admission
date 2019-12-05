package com.synectiks.admission.graphql.types.AdmissionApplication;

import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;

public class AdmissionApplicationInput extends CmsAdmissionApplicationVo{

	@Override
	public String toString() {
		return "AdmissionApplicationInput [getId()=" + getId() + ", getSourceOfApplication()="
				+ getSourceOfApplication() + ", getStudentId()=" + getStudentId() + ", getApplicationDate()="
				+ getApplicationDate() + ", getCompletionDate()=" + getCompletionDate() + ", getAdmissionNo()="
				+ getAdmissionNo() + ", getAdmissionDate()=" + getAdmissionDate() + ", getComments()=" + getComments()
				+ ", getApplicationStatus()=" + getApplicationStatus() + ", getBranchId()=" + getBranchId()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()="
				+ getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getAdmissionEnquiry()="
				+ getAdmissionEnquiry() + ", getStrApplicationDate()=" + getStrApplicationDate()
				+ ", getStrCompletionDate()=" + getStrCompletionDate() + ", getStrAdmissionDate()="
				+ getStrAdmissionDate() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()="
				+ getStrUpdatedOn() + ", getAdmissionEnquiryId()=" + getAdmissionEnquiryId() + ", getStatus()="
				+ getStatus() + ", getErrorCode()=" + getErrorCode() + ", getErrorDescription()="
				+ getErrorDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
    
}
