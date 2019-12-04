package com.synectiks.admission.graphql.types.AdmissionApplication;

import com.synectiks.admission.domain.AdmissionApplication;

public class AbstractAdmissionApplicationPayload {
    private final AdmissionApplication admissionApplication;

    public AbstractAdmissionApplicationPayload(AdmissionApplication admissionApplication){
        this.admissionApplication = admissionApplication;
    }

    public AdmissionApplication getAdmissionApplication() {
        return admissionApplication;
    }
}
