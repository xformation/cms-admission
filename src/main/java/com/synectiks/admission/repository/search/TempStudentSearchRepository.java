package com.synectiks.admission.repository.search;

import com.synectiks.admission.domain.TempStudent;
import com.synectiks.admission.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TempStudent} entity.
 */
public interface TempStudentSearchRepository extends JPASearchRepository<TempStudent, Long> {
}
