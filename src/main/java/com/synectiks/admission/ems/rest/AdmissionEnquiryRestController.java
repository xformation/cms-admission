package com.synectiks.admission.ems.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;

@RestController
@RequestMapping("/api")
public class AdmissionEnquiryRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CmsAdmissionEnquiryService cmsAdmissionEnquiryService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-enquiry-by-filters")
    public List<CmsAdmissionEnquiryVo> getCmsAdmissionEnquiryListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of CmsAdmissionEnquiry based on filter criteria");
        Long branchId = Long.parseLong(dataMap.get("branchId"));
        Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
        String enquiryStatus = dataMap.get("enquiryStatus");
        List<CmsAdmissionEnquiryVo> list = this.cmsAdmissionEnquiryService.getAdmissionEnquiryList(branchId, academicYearId, enquiryStatus);
        return list;
    }
	
}
