package com.synectiks.admission.graphql.resolvers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.admission.business.service.CmsAdmissionApplicationService;
import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationInput;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationPayload;
import com.synectiks.admission.graphql.types.AdmissionEnquiry.AdmissionEnquiryInput;
import com.synectiks.admission.graphql.types.AdmissionEnquiry.AdmissionEnquiryPayload;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;


@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;
    
        
    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @Autowired
    private CmsAdmissionApplicationService cmsAdmissionApplicationService;
    
    public Mutation(AdmissionEnquiryRepository admissionEnquiryRepository, 
    		AdmissionApplicationRepository admissionApplicationRepository) {
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
    }

    public AdmissionEnquiryPayload saveAdmissionEnquiry(AdmissionEnquiryInput admissionEnquiryInput) {
    	logger.debug("Mutation - saveAdmissionEnquiry() - admissionEnquiryInput : "+admissionEnquiryInput);
    	return cmsAdmissionEnquiryService.saveAdmissionEnquiry(admissionEnquiryInput);
    }

    public AdmissionApplicationPayload saveAdmissionApplication(AdmissionApplicationInput input) {
    	logger.debug("Mutation - saveAdmissionApplication() - admissionApplicationInput : "+input);
    	CmsAdmissionApplicationVo vo =  cmsAdmissionApplicationService.saveAdmissionApplication(input);
    	return new AdmissionApplicationPayload(vo);
    }
}
