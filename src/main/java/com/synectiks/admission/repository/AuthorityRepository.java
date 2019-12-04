package com.synectiks.admission.repository;

import com.synectiks.admission.domain.Authority;
import com.synectiks.admission.utils.JPASearchRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JPASearchRepository<Authority, String> {
}
