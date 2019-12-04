package com.synectiks.admission.graphql.types.AdmissionApplication;

import com.synectiks.admission.domain.AdmissionApplication;

public class AddAdmissionApplicationPayload extends AbstractAdmissionApplicationPayload {

    public AddAdmissionApplicationPayload(AdmissionApplication admissionApplication)
    {
        super(admissionApplication);
    }
}
