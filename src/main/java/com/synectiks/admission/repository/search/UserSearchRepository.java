package com.synectiks.admission.repository.search;

import com.synectiks.admission.domain.User;
import com.synectiks.admission.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends JPASearchRepository<User, Long> {
}
