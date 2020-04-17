package com.synectiks.admission.business.service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationInput;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationPayload;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;

@Service
@Transactional
public class CmsAdmissionApplicationService {
	
	private final static Logger logger = LoggerFactory.getLogger(CmsAdmissionApplicationService.class);
	
	@Autowired
	private AdmissionApplicationRepository admissionApplicationRepository;
	
	@Autowired
	private AdmissionEnquiryRepository admissionEnquiryRepository;
	
	@Autowired
	private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;
	
	public AdmissionApplicationPayload addAdmissionApplication(AdmissionApplicationInput input, Long admissionNo) {
    	logger.info("Adding admission application");
    	AdmissionApplication ae = CommonUtil.createCopyProperties(input, AdmissionApplication.class);
    	ae.setApplicationStatus(CmsConstants.STATUS_ADMISSION_GRANTED);
    	
    	if(CmsConstants.STATUS_ADMISSION_GRANTED.equalsIgnoreCase(input.getApplicationStatus())) {
    		ae.setApplicationDate(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    		ae.setCompletionDate(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    		ae.setAdmissionDate(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    		ae.setComments("Admission granted based on an enquiry");
    		ae.setAdmissionNo(admissionNo);
    	}
    	
    	ae.setCreatedOn(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    	
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
	
	public List<CmsAdmissionApplicationVo> getCmsAdmissionApplicationList(Long branchId, Long academicYearId){
		AdmissionApplication aa = new AdmissionApplication();
		aa.setBranchId(branchId);
		aa.setAcademicYearId(academicYearId);
		
    	List<AdmissionApplication> list = this.admissionApplicationRepository.findAll(Example.of(aa));
    	List<CmsAdmissionApplicationVo> ls = changeAdmissionApplicationToCmsAdmissionApplicationList(list);
    	Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	return ls;
    }
	
	public List<AdmissionApplication> getAdmissionApplicationList(Long branchId, Long academicYearId){
		AdmissionApplication aa = new AdmissionApplication();
		aa.setBranchId(branchId);
		aa.setAcademicYearId(academicYearId);
		
    	List<AdmissionApplication> list = this.admissionApplicationRepository.findAll(Example.of(aa));
    	if(list.size() > 0) {
    		Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
    	}
    	return list;
    }
	
	private List<CmsAdmissionApplicationVo> changeAdmissionApplicationToCmsAdmissionApplicationList(List<AdmissionApplication> list){
    	List<CmsAdmissionApplicationVo> ls = new ArrayList<>();
    	for(AdmissionApplication o: list) {
    		CmsAdmissionApplicationVo vo = CommonUtil.createCopyProperties(o, CmsAdmissionApplicationVo.class);
    		convertDatesAndProvideDependencies(o, vo);
    		ls.add(vo);
    	}
    	return ls;
    }

	public void convertDatesAndProvideDependencies(AdmissionApplication o, CmsAdmissionApplicationVo vo) {
		if(o.getCreatedOn() != null) {
			vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(o.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getUpdatedOn() != null) {
			vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(o.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getApplicationDate() != null) {
			vo.setStrApplicationDate(DateFormatUtil.changeLocalDateFormat(o.getApplicationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getCompletionDate() != null ) {
			vo.setStrCompletionDate(DateFormatUtil.changeLocalDateFormat(o.getCompletionDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
	    }
		if(o.getAdmissionDate() != null) {
			vo.setStrAdmissionDate(DateFormatUtil.changeLocalDateFormat(o.getAdmissionDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
		}
		if(o.getAdmissionEnquiry() != null) {
			CmsAdmissionEnquiryVo evo = CommonUtil.createCopyProperties(o.getAdmissionEnquiry(), CmsAdmissionEnquiryVo.class);
			
			if(o.getAdmissionEnquiry().getDateOfBirth() != null) {
        		evo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(o.getAdmissionEnquiry().getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		evo.setDateOfBirth(null);
        	}
        	if(o.getAdmissionEnquiry().getCreatedOn() != null) {
        		evo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(o.getAdmissionEnquiry().getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		evo.setCreatedOn(null);
        	}
        	if(o.getAdmissionEnquiry().getUpdatedOn() != null) {
        		evo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(o.getAdmissionEnquiry().getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		evo.setUpdatedOn(null);
        	}
        	if(o.getAdmissionEnquiry().getEnquiryDate() != null) {
        		evo.setStrEnquiryDate(DateFormatUtil.changeLocalDateFormat(o.getAdmissionEnquiry().getEnquiryDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		evo.setEnquiryDate(null);
        	}
        	
			vo.setCmsAdmissionEnquiryVo(evo);
		}
	}
	
	public CmsAdmissionApplicationVo saveAdmissionApplication(AdmissionApplicationInput input) {
    	logger.info("Saving AdmissionApplication");
    	CmsAdmissionApplicationVo vo = null;
    	try {
    		AdmissionApplication admissionApplication = null;
    		if(input.getId() == null) {
    			logger.debug("Adding new admission application");
    			admissionApplication = CommonUtil.createCopyProperties(input, AdmissionApplication.class);
    			admissionApplication.setCreatedOn(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
//    			admissionApplication.setComments(input.getComments());
    			admissionApplication.applicationDate(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    			
    		}else {
    			logger.debug("Updating existing admission application");
    			admissionApplication = this.admissionApplicationRepository.findById(input.getId()).get();
    			admissionApplication.setUpdatedOn(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
//    			if(input.getComments() != null) {
//    				admissionApplication.setComments(input.getComments());
//    			}
    		}
    		if(CmsConstants.STATUS_ACTIVE.equalsIgnoreCase(input.getApplicationStatus())){
    			admissionApplication.setAdmissionNo(this.generateAdmissionNo(input.getBranchId()));
    		}
    		admissionApplication.completionDate(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    		admissionApplication.admissionDate(LocalDate.now(ZoneId.of(CmsConstants.ZONE_ID)));
    		Optional<AdmissionEnquiry> oe = this.admissionEnquiryRepository.findById(input.getAdmissionEnquiryId());
    		if(oe.isPresent()) {
    			AdmissionEnquiry ae = oe.get();
    			if(CmsConstants.STATUS_ACTIVE.equalsIgnoreCase(input.getApplicationStatus())){
    				ae.setEnquiryStatus(CmsConstants.STATUS_CONVERTED_TO_ADMISSION);
    				ae = this.admissionEnquiryRepository.save(ae);
        		}
    			admissionApplication.setAdmissionEnquiry(ae);
    		}
    		
    		admissionApplication = this.admissionApplicationRepository.save(admissionApplication);
        	vo = CommonUtil.createCopyProperties(admissionApplication , CmsAdmissionApplicationVo.class);
        	vo.setStrCreatedOn(admissionApplication.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(admissionApplication.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrUpdatedOn(admissionApplication.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(admissionApplication.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
    		
        	vo.setStrApplicationDate(admissionApplication.getApplicationDate() != null ? DateFormatUtil.changeLocalDateFormat(admissionApplication.getApplicationDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrCompletionDate(admissionApplication.getCompletionDate() != null ? DateFormatUtil.changeLocalDateFormat(admissionApplication.getCompletionDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
        	vo.setStrAdmissionDate(admissionApplication.getAdmissionDate() != null ? DateFormatUtil.changeLocalDateFormat(admissionApplication.getAdmissionDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
    		
    		vo.setCreatedOn(null);
        	vo.setUpdatedOn(null);
        	vo.setApplicationDate(null);
        	vo.setCompletionDate(null);
        	vo.setAdmissionDate(null);
        	
        	vo.setExitCode(0L);
        	if(input.getId() == null) {
        		vo.setExitDescription("AdmissionApplication is added successfully");
        		logger.debug("AdmissionApplication is added successfully");
        	}else {
        		vo.setExitDescription("AdmissionApplication is updated successfully");
        		logger.debug("AdmissionApplication is updated successfully");
        	}
        	List<CmsAdmissionApplicationVo> ls =  getCmsAdmissionApplicationList(input.getBranchId(), input.getAcademicYearId());
            vo.setDataList(ls);
            
            List<CmsAdmissionEnquiryVo> enquiryList = new ArrayList<>();
            for(CmsAdmissionApplicationVo avo: ls) {
            	CmsAdmissionEnquiryVo evo = avo.getCmsAdmissionEnquiryVo();
            	if(!CmsConstants.STATUS_CONVERTED_TO_ADMISSION.equalsIgnoreCase(evo.getEnquiryStatus())) {
            		enquiryList.add(evo);
            	}
            }
            
        	
        }catch(Exception e) {
        	vo.setExitCode(1L);
        	vo.setExitDescription("Due to some exception, admissionApplication data could not be saved");
    		logger.error("AdmissionApplication save failed. Exception : ",e);
    	}
    	logger.info("AdmissionApplication saved successfully");
    	
        return vo;
        
    }

}
