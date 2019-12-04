package com.synectiks.admission.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdmissionApplication entity.
 */
@Repository
public interface AdmissionApplicationRepository
		extends JPASearchRepository<AdmissionApplication, Long> {

}
