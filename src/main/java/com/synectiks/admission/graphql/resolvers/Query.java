/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.synectiks.admission.graphql.resolvers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.admission.business.service.CmsAdmissionApplicationService;
import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.filter.admissionapplication.AdmissionApplicationProcessor;
import com.synectiks.admission.filter.admissionenquiry.AdmissionEnquiryProcessor;
import com.synectiks.admission.filter.common.CommonGraphiqlFilter;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;

/**
 * Query Resolver for CMS Queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);
    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;

    @Autowired
    private AdmissionEnquiryProcessor admissionEnquiryProcessor;

    @Autowired
    private CommonGraphiqlFilter commonGraphiqlFilter;


    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @Autowired
    private AdmissionApplicationProcessor admissionApplicationProcessor;

    @Autowired
    private CmsAdmissionApplicationService cmsAdmissionApplicationService;

    public Query(AdmissionEnquiryRepository admissionEnquiryRepository, 
    		AdmissionApplicationRepository admissionApplicationRepository 
    		) {
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
    }

    public Long getAbc() {
    	return 1L;
    }
    
}
