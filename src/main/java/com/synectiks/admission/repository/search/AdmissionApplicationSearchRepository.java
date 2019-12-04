package com.synectiks.admission.repository.search;

import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionApplication entity.
 */
public interface AdmissionApplicationSearchRepository extends JPASearchRepository<AdmissionApplication, Long> {
}
