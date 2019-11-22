package com.synectiks.admission.repository.search;

import com.synectiks.admission.domain.AdmissionApplication;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdmissionApplication} entity.
 */
public interface AdmissionApplicationSearchRepository extends ElasticsearchRepository<AdmissionApplication, Long> {
}
