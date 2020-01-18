package com.synectiks.admission.business.service;


import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.admission.constant.CmsConstants;
import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationInput;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationPayload;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;

@Service
@Transactional
public class CmsAdmissionApplicationService {
	
	private final static Logger logger = LoggerFactory.getLogger(CmsAdmissionApplicationService.class);
	
	@Autowired
	private AdmissionApplicationRepository admissionApplicationRepository;
	
	public AdmissionApplicationPayload addAdmissionApplication(AdmissionApplicationInput input, Long admissionNo) {
    	logger.info("Adding admission application");
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
    	
    	ae = admissionApplicationRepository.save(ae);
    	
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

	public synchronized Long generateAdmissionNo(Long branchId) {
		AdmissionApplication aa = new AdmissionApplication();
		aa.setBranchId(branchId);
		
		List<AdmissionApplication> list = this.admissionApplicationRepository.findAll(Example.of(aa), Sort.by(Direction.DESC, "id"));
		if(list.size() == 0) {
			return 1L;
		}
		return list.get(0).getId()+1;
	}

}
