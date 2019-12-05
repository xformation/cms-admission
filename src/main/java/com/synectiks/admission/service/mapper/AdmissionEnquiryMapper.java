package com.synectiks.admission.service.mapper;

import com.synectiks.admission.domain.*;
import com.synectiks.admission.service.dto.AdmissionEnquiryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmissionEnquiry} and its DTO {@link AdmissionEnquiryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmissionEnquiryMapper extends EntityMapper<AdmissionEnquiryDTO, AdmissionEnquiry> {



    default AdmissionEnquiry fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setId(id);
        return admissionEnquiry;
    }
}
