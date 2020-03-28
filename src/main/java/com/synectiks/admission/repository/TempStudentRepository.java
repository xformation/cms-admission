package com.synectiks.admission.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.admission.domain.TempStudent;
import com.synectiks.admission.utils.JPASearchRepository;


/**
 * Spring Data  repository for the TempStudent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TempStudentRepository extends JPASearchRepository<TempStudent, Long> {

}
