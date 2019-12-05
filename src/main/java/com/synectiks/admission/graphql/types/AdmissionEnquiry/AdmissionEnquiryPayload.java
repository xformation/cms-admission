package com.synectiks.admission.graphql.types.AdmissionEnquiry;

import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;

public class AdmissionEnquiryPayload {
    private final CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo;

    public AdmissionEnquiryPayload(CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo) {
        this.cmsAdmissionEnquiryVo = cmsAdmissionEnquiryVo;
    }

	public CmsAdmissionEnquiryVo getCmsAdmissionEnquiryVo() {
		return cmsAdmissionEnquiryVo;
	}
    
}
