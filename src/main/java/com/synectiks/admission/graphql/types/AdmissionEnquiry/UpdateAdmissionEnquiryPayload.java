package com.synectiks.admission.graphql.types.AdmissionEnquiry;

import com.synectiks.admission.domain.AdmissionEnquiry;

public class UpdateAdmissionEnquiryPayload extends AbstractAdmissionEnquiryPayload{
    public UpdateAdmissionEnquiryPayload(AdmissionEnquiry admissionEnquiry) {
        super(admissionEnquiry); }
}
