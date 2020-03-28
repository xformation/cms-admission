package com.synectiks.admission.graphql.resolvers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.admission.business.service.CmsAdmissionApplicationService;
import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.business.service.CmsTempStudentService;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationInput;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationPayload;
import com.synectiks.admission.graphql.types.AdmissionEnquiry.AdmissionEnquiryInput;
import com.synectiks.admission.graphql.types.AdmissionEnquiry.AdmissionEnquiryPayload;
import com.synectiks.admission.graphql.types.TempStudent.TempStudentInput;
import com.synectiks.admission.graphql.types.TempStudent.TempStudentPayload;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.repository.TempStudentRepository;


@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;
    private final TempStudentRepository tempStudentRepository;
        
    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @Autowired
    private CmsAdmissionApplicationService cmsAdmissionApplicationService;
    
    @Autowired
    private CmsTempStudentService cmsTempStudentService;
    
    public Mutation(AdmissionEnquiryRepository admissionEnquiryRepository, 
    		AdmissionApplicationRepository admissionApplicationRepository,
    		TempStudentRepository tempStudentRepository) {
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
        this.tempStudentRepository = tempStudentRepository;
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
    
    public TempStudentPayload saveTempStudent(TempStudentInput input) {
    	logger.debug("Mutation - saveTempStudent() - tempStudentInput : "+input);
    	TempStudentPayload tempStudentPayload =  cmsTempStudentService.saveTempStudent(input);
    	return tempStudentPayload;
    }
    
}
