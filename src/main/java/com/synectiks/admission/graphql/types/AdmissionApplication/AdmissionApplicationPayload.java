package com.synectiks.admission.graphql.types.AdmissionApplication;

import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;

public class AdmissionApplicationPayload {
    private final CmsAdmissionApplicationVo cmsAdmissionApplicationVo;

    public AdmissionApplicationPayload(CmsAdmissionApplicationVo cmsAdmissionApplicationVo){
        this.cmsAdmissionApplicationVo = cmsAdmissionApplicationVo;
    }

	public CmsAdmissionApplicationVo getCmsAdmissionApplicationVo() {
		return cmsAdmissionApplicationVo;
	}

    
}
