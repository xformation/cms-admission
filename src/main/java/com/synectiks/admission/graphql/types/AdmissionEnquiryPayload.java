package com.synectiks.admission.graphql.types;


import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;

public class AdmissionEnquiryPayload {
    private final CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo;

    public AdmissionEnquiryPayload(CmsAdmissionEnquiryVo college){
        this.cmsAdmissionEnquiryVo = college;
    }

	public CmsAdmissionEnquiryVo getCmsAdmissionEnquiryVo() {
		return cmsAdmissionEnquiryVo;
	}


}
