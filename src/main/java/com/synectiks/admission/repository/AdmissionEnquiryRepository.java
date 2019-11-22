package com.synectiks.admission.repository;

import com.synectiks.admission.domain.AdmissionEnquiry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdmissionEnquiry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmissionEnquiryRepository extends JpaRepository<AdmissionEnquiry, Long> {

}
