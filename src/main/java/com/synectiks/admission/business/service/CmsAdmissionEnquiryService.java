package com.synectiks.admission.business.service;

import com.synectiks.admission.config.Constants;
import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CmsAdmissionEnquiryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdmissionEnquiryRepository admissionEnquiryRepository;



    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public CmsAdmissionEnquiryVo addAdmissionEnquiry(CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo) {
        CmsAdmissionEnquiryVo vo = null;
        if(isAdmissionEnquiryExists()) {
            vo = new CmsAdmissionEnquiryVo();
            vo.setErrorCode(1L);
            vo.setErrorDescription(Constants.ERROR_ADMISSIONENQUIRY_ALREADY_EXISTS);
            logger.error(Constants.VALIDATION_FAILURE + Constants.ERROR_ADMISSIONENQUIRY_ALREADY_EXISTS);
            return vo;
        }
        vo = cmsAdmissionEnquiryVo;
        logger.info("Saving AdmissionEnquiry");
        return vo;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public AdmissionEnquiry saveAdmissionEnquiry(CmsAdmissionEnquiryVo vo) {
        logger.debug("Saving AdmissionEnquiry");
        AdmissionEnquiry admissionEnquiry = CommonUtil.createCopyProperties(vo, AdmissionEnquiry.class);
        admissionEnquiry.setCreatedOn(LocalDate.now());
        admissionEnquiry = admissionEnquiryRepository.save(admissionEnquiry);
        vo.setStrCreatedOn(admissionEnquiry.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(admissionEnquiry.getCreatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        vo.setStrUpdatedOn(admissionEnquiry.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(admissionEnquiry.getUpdatedOn(), Constants.DATE_FORMAT_dd_MM_yyyy) : "");
        vo.setCreatedOn(null);
        vo.setUpdatedOn(null);
        logger.debug("AdmissionEnquiry saved successfully");
        return admissionEnquiry;
    }


    private boolean isAdmissionEnquiryExists() {
        List<AdmissionEnquiry> existingAdmissionEnquiryList = admissionEnquiryRepository.findAll();
        if(existingAdmissionEnquiryList.size() >= 1) {
            return true;
        }
        return false;
    }

    public Long getTotalAdmissions(Long branchId) {
        Long a = getTotalFollowup(branchId);
        Long b = getTotalDeclined(branchId);
        Long c = getTotalConverted(branchId);
        return a + b + c;
    }

    public Long getTotalFollowup(Long branchId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();

        admissionEnquiry.setEnquiryStatus(Constants.STATUS_FOLLOWUP);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    public Long getTotalDeclined(Long branchId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();

        admissionEnquiry.setEnquiryStatus(Constants.STATUS_DECLINED);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

//    public Long getTotalConverted(Long branchId) {
//        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
//
//        admissionEnquiry.setEnquiryStatus(Constants.STATUS_RECEIVED);
//        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
//        Long cnt = this.admissionEnquiryRepository.count(example);
//        return cnt;
//    }

    public Long getTotalConverted(Long branchId){
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setEnquiryStatus(Constants.STATUS_RECEIVED);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return  cnt;
    }

    public List<CmsAdmissionEnquiryVo> searchAdmissionOnType(String enquiryStatus,Long branchId) throws Exception {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
//        Branch branch = new Branch();
//        branch.setId(branchId);
//        admissionEnquiry.setBranch(branch);

        if(!enquiryStatus.equalsIgnoreCase("RECEIVED")) {
            if(enquiryStatus.equalsIgnoreCase("FOLLOWUP")) {
                admissionEnquiry.setEnquiryStatus(Constants.STATUS_FOLLOWUP);
            }else if(enquiryStatus.equalsIgnoreCase("DECLINED")) {
                admissionEnquiry.setEnquiryStatus(Constants.STATUS_DECLINED);
            }else if(enquiryStatus.equalsIgnoreCase("CONVERTED")) {
                admissionEnquiry.setEnquiryStatus(Constants.STATUS_CONVERTED_TO_ADMISSION);
            }
        }

        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        List<AdmissionEnquiry> list = this.admissionEnquiryRepository.findAll(example);
        List<CmsAdmissionEnquiryVo> ls = new ArrayList<>();
        for(AdmissionEnquiry temp: list) {
            CmsAdmissionEnquiryVo cae = CommonUtil.createCopyProperties(temp, CmsAdmissionEnquiryVo.class);
            cae.setStrEnquiryDate(DateFormatUtil.changeDateFormat(Constants.DATE_FORMAT_dd_MM_yyyy, Constants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(Constants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getEnquiryDate()))));

            ls.add(cae);
        }
        return ls;
    }

}
