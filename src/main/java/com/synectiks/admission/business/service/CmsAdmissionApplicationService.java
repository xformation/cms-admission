package com.synectiks.admission.business.service;


import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.admission.constant.CmsConstants;
import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationInput;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationPayload;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;
import com.synectiks.admission.utils.SynectiksJPARepo;

@Service
@Transactional
public class CmsAdmissionApplicationService {
	
	private final static Logger logger = LoggerFactory.getLogger(CmsAdmissionApplicationService.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public AdmissionApplicationPayload addAdmissionApplication(AdmissionApplicationInput input, Long admissionNo) {
    	logger.info("Adding admission application");
    	SynectiksJPARepo synectiksJPARepo = new SynectiksJPARepo(AdmissionApplication.class, this.entityManager);
    	AdmissionApplication ae = CommonUtil.createCopyProperties(input, AdmissionApplication.class);
    	ae.setApplicationStatus(CmsConstants.STATUS_ADMISSION_GRANTED);
    	
    	if(CmsConstants.STATUS_ADMISSION_GRANTED.equalsIgnoreCase(input.getApplicationStatus())) {
    		ae.setApplicationDate(LocalDate.now());
    		ae.setCompletionDate(LocalDate.now());
    		ae.setAdmissionDate(LocalDate.now());
    		ae.setComments("Admission granted based on an enquiry");
    		ae.setAdmissionNo(admissionNo);
    	}
    	
    	ae.setCreatedOn(LocalDate.now());
    	
    	ae = (AdmissionApplication)synectiksJPARepo.save(ae);
    	
    	CmsAdmissionApplicationVo vo = CommonUtil.createCopyProperties(ae, CmsAdmissionApplicationVo.class);
    	if(ae.getApplicationDate() != null) {
    		vo.setStrApplicationDate(DateFormatUtil.changeLocalDateFormat(ae.getApplicationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setApplicationDate(null);
    	}
    	
    	if(ae.getCompletionDate() != null) {
    		vo.setStrCompletionDate(DateFormatUtil.changeLocalDateFormat(ae.getCompletionDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setCompletionDate(null);
    	}
    	
    	if(ae.getAdmissionDate() != null) {
    		vo.setStrAdmissionDate(DateFormatUtil.changeLocalDateFormat(ae.getAdmissionDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setAdmissionDate(null);
    	}
    	
    	if(ae.getCreatedOn() != null) {
    		vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ae.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setCreatedOn(null);
    	}
    	if(ae.getUpdatedOn() != null) {
    		vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ae.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setUpdatedOn(null);
    	}
    	vo.setExitCode(0L);
    	vo.setExitDescription("Admission application saved successfully");
    	logger.info("Admission enquiry added successfully");
    	return new AdmissionApplicationPayload(vo);
    }



}
