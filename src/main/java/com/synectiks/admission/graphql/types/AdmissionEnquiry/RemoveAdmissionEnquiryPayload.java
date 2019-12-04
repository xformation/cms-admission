package com.synectiks.admission.graphql.types.AdmissionEnquiry;

import com.synectiks.admission.domain.AdmissionEnquiry;

import java.util.List;

public class RemoveAdmissionEnquiryPayload {

    private final List<AdmissionEnquiry> admissionEnquiries;

    public RemoveAdmissionEnquiryPayload(List<AdmissionEnquiry> admissionEnquiries){
        this.admissionEnquiries = admissionEnquiries;
    }
    public List<AdmissionEnquiry> getAdmissionEnquiries(){
        return admissionEnquiries;
    }
}
