package com.synectiks.admission.service.mapper;

import com.synectiks.admission.domain.*;
import com.synectiks.admission.service.dto.AdmissionEnquiryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdmissionEnquiry and its DTO AdmissionEnquiryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmissionEnquiryMapper extends EntityMapper<AdmissionEnquiryDTO, AdmissionEnquiry> {

//    @Mapping(source = "branch.id", target = "branchId")
//    @Mapping(source = "department.id", target = "departmentId")
//    @Mapping(source = "batch.id", target = "batchId")
//    @Mapping(source = "state.id", target = "stateId")
//    @Mapping(source = "city.id", target = "cityId")
//    @Mapping(source = "country.id", target = "countryId")
    AdmissionEnquiryDTO toDto(AdmissionEnquiry admissionEnquiry);

//    @Mapping(source = "branchId", target = "branch")
//    @Mapping(source = "departmentId", target = "department")
//    @Mapping(source = "batchId", target = "batch")
//    @Mapping(source = "stateId", target = "state")
//    @Mapping(source = "cityId", target = "city")
//    @Mapping(source = "countryId", target = "country")
    AdmissionEnquiry toEntity(AdmissionEnquiryDTO admissionEnquiryDTO);

    default AdmissionEnquiry fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setId(id);
        return admissionEnquiry;
    }
}
