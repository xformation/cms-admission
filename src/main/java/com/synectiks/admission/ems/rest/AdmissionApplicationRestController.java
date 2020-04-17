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

import com.synectiks.admission.business.service.CmsAdmissionApplicationService;
import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;

@RestController
@RequestMapping("/api")
public class AdmissionApplicationRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CmsAdmissionApplicationService cmsAdmissionApplicationService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-application-by-filters")
    public List<CmsAdmissionApplicationVo> getCmsAdmissionApplicationListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of CmsAdmissionApplications based on filter criteria");
        Long branchId = Long.parseLong(dataMap.get("branchId"));
        Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
        List<CmsAdmissionApplicationVo> list = this.cmsAdmissionApplicationService.getCmsAdmissionApplicationList(branchId, academicYearId);
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/admission-application-by-filters")
    public List<AdmissionApplication> getAdmissionApplicationListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of AdmissionApplications based on filter criteria");
        Long branchId = Long.parseLong(dataMap.get("branchId"));
        Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
        List<AdmissionApplication> list = this.cmsAdmissionApplicationService.getAdmissionApplicationList(branchId, academicYearId);
        return list;
    }
}
