
package com.synectiks.admission.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Query Resolver for preference queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);
    private final AdmissionEnquiryRepository admissionEnquiryRepository;

    private UserRepository userRepository;

	public Query(UserRepository userRepository, AdmissionEnquiryRepository admissionEnquiryRepository) {
		this.userRepository = userRepository;
		this.admissionEnquiryRepository = admissionEnquiryRepository;
	}



    public AdmissionEnquiry admissionEnquiry(long id) {
        return admissionEnquiryRepository.findById(id).get();
    }

    public List<AdmissionEnquiry> admissionEnquiries() {
        return Lists.newArrayList(admissionEnquiryRepository.findAll());
    }

}
