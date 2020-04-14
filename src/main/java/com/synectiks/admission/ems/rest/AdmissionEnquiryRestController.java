package com.synectiks.admission.ems.rest;

import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.constant.CmsConstants;
import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;
import com.synectiks.admission.utils.IUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdmissionEnquiryRestController {

    private final Logger logger = LoggerFactory.getLogger(AdmissionEnquiryRestController.class);

    private static final String ENTITY_NAME = "admissionEnquiry";

    private EntityManager entityManager;

    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;

   @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-enquiries")
    public List<CmsAdmissionEnquiryVo> getAdmissionEnquiryList(Long branchId, Long academicYearId, String enquiryStatus) {
       logger.debug("Query - getAdmissionEnquiryList :- Branch Id : "+branchId+", academicYearId : "+academicYearId+", enquiryStatus : "+enquiryStatus);
       return this.cmsAdmissionEnquiryService.getAdmissionEnquiryList(branchId, academicYearId, enquiryStatus);
   }

    @RequestMapping(method = RequestMethod.GET, value = "/total-followup")
    public Long getTotalFollowup(Long branchId,Long academicYearId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        if (branchId != null) {
            admissionEnquiry.setBranchId(branchId);
        }
        if(academicYearId != null){
            admissionEnquiry.setBranchId(branchId);
        }
        admissionEnquiry.setEnquiryStatus(CmsConstants.STATUS_FOLLOWUP);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/total-converted")
    public Long getTotalConverted(Long branchId,Long academicYearId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        if (branchId != null) {
            admissionEnquiry.setBranchId(branchId);
        }
        if(academicYearId != null){
            admissionEnquiry.setBranchId(branchId);
        }
        admissionEnquiry.setEnquiryStatus(CmsConstants.STATUS_CONVERTED_TO_ADMISSION);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/total-declined")
    public Long getTotalDeclined(Long branchId,Long academicYearId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        if (branchId != null) {
            admissionEnquiry.setBranchId(branchId);
        }
        if(academicYearId != null){
            admissionEnquiry.setBranchId(branchId);
        }
        admissionEnquiry.setEnquiryStatus(CmsConstants.STATUS_DECLINED);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }
}
