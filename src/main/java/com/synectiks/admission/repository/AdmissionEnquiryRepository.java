package com.synectiks.admission.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AdmissionEnquiry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmissionEnquiryRepository extends JPASearchRepository<AdmissionEnquiry, Long> {

}
