package com.synectiks.admission.graphql.resolvers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.constant.CmsConstants;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;

/**
 * Query Resolver for CMS Admission Queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);
    
	private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;

    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

//    @Autowired
//    private AdmissionApplicationProcessor admissionApplicationProcessor;
//
//    @Autowired
//    private CmsAdmissionApplicationService cmsAdmissionApplicationService;
    

    public Query(AdmissionEnquiryRepository admissionEnquiryRepository, 
    		AdmissionApplicationRepository admissionApplicationRepository 
    		) {
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
    }

    public List<CmsAdmissionEnquiryVo> getAdmissionEnquiryList(Long branchId, Long academicYearId, String enquiryStatus) throws Exception, Exception {
    	logger.debug("Query - getAdmissionEnquiryList :- Branch Id : "+branchId+", academicYearId : "+academicYearId+", enquiryStatus : "+enquiryStatus);
    	return this.cmsAdmissionEnquiryService.getAdmissionEnquiryList(branchId, academicYearId, enquiryStatus);
    }
    
    
}
