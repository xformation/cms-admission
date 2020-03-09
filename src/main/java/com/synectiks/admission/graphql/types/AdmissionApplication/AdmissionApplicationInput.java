package com.synectiks.admission.graphql.types.AdmissionApplication;

import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;

public class AdmissionApplicationInput extends CmsAdmissionApplicationVo{

	@Override
	public String toString() {
		return "AdmissionApplicationInput [getId()=" + getId() + ", getSourceOfApplication()="
				+ getSourceOfApplication() + ", getApplicationDate()=" + getApplicationDate() + ", getCompletionDate()="
				+ getCompletionDate() + ", getAdmissionNo()=" + getAdmissionNo() + ", getAdmissionDate()="
				+ getAdmissionDate() + ", getComments()=" + getComments() + ", getApplicationStatus()="
				+ getApplicationStatus() + ", getBranchId()=" + getBranchId() + ", getStrApplicationDate()="
				+ getStrApplicationDate() + ", getStrCompletionDate()=" + getStrCompletionDate()
				+ ", getStrAdmissionDate()=" + getStrAdmissionDate() + ", getAdmissionEnquiryId()="
				+ getAdmissionEnquiryId() + ", getDataList()=" + getDataList() + ", getCmsAdmissionEnquiryVo()="
				+ getCmsAdmissionEnquiryVo() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedOn()="
				+ getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedOn()=" + getUpdatedOn()
				+ ", getStatus()=" + getStatus() + ", getStrCreatedOn()=" + getStrCreatedOn() + ", getStrUpdatedOn()="
				+ getStrUpdatedOn() + ", getExitCode()=" + getExitCode() + ", getExitDescription()="
				+ getExitDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
    
}
