package com.synectiks.admission.repository.search;

import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionEnquiry entity.
 */
public interface AdmissionEnquirySearchRepository extends JPASearchRepository<AdmissionEnquiry, Long> {
}
