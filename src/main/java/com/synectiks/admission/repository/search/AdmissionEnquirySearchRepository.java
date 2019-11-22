package com.synectiks.admission.repository.search;

import com.synectiks.admission.domain.AdmissionEnquiry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdmissionEnquiry} entity.
 */
public interface AdmissionEnquirySearchRepository extends ElasticsearchRepository<AdmissionEnquiry, Long> {
}
