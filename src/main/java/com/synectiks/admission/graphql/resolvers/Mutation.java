package com.synectiks.admission.graphql.resolvers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.admission.filter.admissionapplication.AdmissionApplicationProcessor;
import com.synectiks.admission.filter.admissionenquiry.AdmissionEnquiryProcessor;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;



@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AdmissionEnquiryProcessor admissionEnquiryProcessor;

    @Autowired
    private AdmissionApplicationProcessor admissionApplicationProcessor;

    public Mutation(AdmissionEnquiryRepository admissionEnquiryRepository, 
    		AdmissionApplicationRepository admissionApplicationRepository, 
    		EntityManager entityManager) {
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
        this.entityManager = entityManager;
    }

    public Long getAbc() {
    	return 1L;
    }
    

}
