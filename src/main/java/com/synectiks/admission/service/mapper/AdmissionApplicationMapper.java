package com.synectiks.admission.service.mapper;

import com.synectiks.admission.domain.*;
import com.synectiks.admission.service.dto.AdmissionApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmissionApplication} and its DTO {@link AdmissionApplicationDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdmissionEnquiryMapper.class})
public interface AdmissionApplicationMapper extends EntityMapper<AdmissionApplicationDTO, AdmissionApplication> {

    @Mapping(source = "admissionEnquiry.id", target = "admissionEnquiryId")
    AdmissionApplicationDTO toDto(AdmissionApplication admissionApplication);

    @Mapping(source = "admissionEnquiryId", target = "admissionEnquiry")
    AdmissionApplication toEntity(AdmissionApplicationDTO admissionApplicationDTO);

    default AdmissionApplication fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdmissionApplication admissionApplication = new AdmissionApplication();
        admissionApplication.setId(id);
        return admissionApplication;
    }
}
