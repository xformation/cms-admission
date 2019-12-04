package com.synectiks.admission.graphql.types.AdmissionApplication;

import com.synectiks.admission.domain.AdmissionApplication;

public class UpdateAdmissionApplicationPayload extends AbstractAdmissionApplicationPayload {
    public UpdateAdmissionApplicationPayload(AdmissionApplication admissionApplication)
    {
        super(admissionApplication);
    }
}
