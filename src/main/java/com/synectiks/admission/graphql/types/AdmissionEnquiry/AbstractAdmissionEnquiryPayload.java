package com.synectiks.admission.graphql.types.AdmissionEnquiry;

import com.synectiks.admission.domain.AdmissionEnquiry;

public class AbstractAdmissionEnquiryPayload {
    private final AdmissionEnquiry admissionEnquiry;

    public AbstractAdmissionEnquiryPayload(AdmissionEnquiry admissionEnquiry) {
        this.admissionEnquiry = admissionEnquiry;
    }

    public AdmissionEnquiry getAdmissionEnquiry() {
        return admissionEnquiry;
    }
}
