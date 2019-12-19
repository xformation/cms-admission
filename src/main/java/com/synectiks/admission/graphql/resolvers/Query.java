package com.synectiks.admission.graphql.resolvers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.config.ApplicationProperties;
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

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
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

    public List<CmsAdmissionEnquiryVo> getStudentList(Long branchId, Long academicYearId) throws Exception, Exception {
    	logger.debug("Query - getStudentList :- Branch Id : "+branchId+", academicYearId : "+academicYearId);
    	String url = applicationProperties.getCmsBackEndUrl()+"/api/cms-students-for-admission?branchId="+branchId;
    	List<CmsAdmissionEnquiryVo> ls = new ArrayList<>();
    	try {
    		CmsAdmissionEnquiryVo[] temp = restTemplate.getForObject(url, CmsAdmissionEnquiryVo[].class);
			ls = Arrays.asList(temp);
		}catch(Exception e) {
			logger.error("Student list could not be retrieved. Exception : ", e);
			throw e;
		}
    	return ls;
    }
}
