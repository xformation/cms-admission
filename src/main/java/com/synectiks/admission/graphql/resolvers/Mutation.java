package com.synectiks.admission.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.admission.graphql.types.AdmissionEnquiryInput;
import com.synectiks.admission.graphql.types.AdmissionEnquiryPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);


    @Autowired
    CmsAdmissionEnquiryService cmsAdmissionEnquiryService;


    public AdmissionEnquiryPayload addAdmissionEnquiry(AdmissionEnquiryInput cmsAdmissionEnquiryVo) {
    	CmsAdmissionEnquiryVo vo = this.cmsAdmissionEnquiryService.addAdmissionEnquiry(cmsAdmissionEnquiryVo);
    	return new AdmissionEnquiryPayload(vo);
    }

}
