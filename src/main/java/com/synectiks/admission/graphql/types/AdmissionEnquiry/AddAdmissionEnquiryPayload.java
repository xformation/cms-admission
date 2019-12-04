package com.synectiks.admission.graphql.types.AdmissionEnquiry;

import com.synectiks.admission.domain.AdmissionEnquiry;

public class AddAdmissionEnquiryPayload extends AbstractAdmissionEnquiryPayload {

    public AddAdmissionEnquiryPayload(AdmissionEnquiry admissionEnquiry){
        super(admissionEnquiry);
    }
}
