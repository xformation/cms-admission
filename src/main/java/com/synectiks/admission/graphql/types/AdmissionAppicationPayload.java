package com.synectiks.admission.graphql.types;


import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;

public class AdmissionAppicationPayload {
    private final CmsAdmissionApplicationVo cmsAdmissionAppicationVo;

    public AdmissionAppicationPayload(CmsAdmissionApplicationVo admissionAppication){
        this.cmsAdmissionAppicationVo = admissionAppication;
    }

	public CmsAdmissionApplicationVo getCmsAdmissionAppicationVo() {
		return cmsAdmissionAppicationVo;
	}


}
