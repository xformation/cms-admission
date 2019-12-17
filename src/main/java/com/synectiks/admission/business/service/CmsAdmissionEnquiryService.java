package com.synectiks.admission.business.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.synectiks.admission.config.ApplicationProperties;
import com.synectiks.admission.constant.CmsConstants;
import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;
import com.synectiks.admission.graphql.types.AdmissionApplication.AdmissionApplicationInput;
import com.synectiks.admission.graphql.types.AdmissionEnquiry.AdmissionEnquiryInput;
import com.synectiks.admission.graphql.types.AdmissionEnquiry.AdmissionEnquiryPayload;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;
import com.synectiks.admission.utils.IUtils;
import com.synectiks.admission.utils.SynectiksJPARepo;

@Service
@Transactional
public class CmsAdmissionEnquiryService {
	
	private final static Logger logger = LoggerFactory.getLogger(CmsAdmissionEnquiryService.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private CmsAdmissionApplicationService cmsAdmissionApplicationService;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
    public List<CmsAdmissionEnquiryVo> getAdmissionEnquiryList(Long branchId, Long academicYearId, String enquiryStatus) throws ParseException, Exception {
        logger.info("Start getting admission enquiry data");
    	AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setBranchId(branchId);
        admissionEnquiry.setAcademicYearId(academicYearId);
        if(!IUtils.isNullOrEmpty(enquiryStatus)) {
        	admissionEnquiry.setEnquiryStatus(enquiryStatus);
        }

        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        SynectiksJPARepo synectiksJPARepo = new SynectiksJPARepo(AdmissionEnquiry.class, this.entityManager);
        List<AdmissionEnquiry> list = synectiksJPARepo.findAll(example);
        List<CmsAdmissionEnquiryVo> ls = new ArrayList<>();
        for(AdmissionEnquiry temp: list) {
        	logger.debug("Admission enquiry data : "+temp.toString());
            CmsAdmissionEnquiryVo cae = CommonUtil.createCopyProperties(temp, CmsAdmissionEnquiryVo.class);
            if(temp.getEnquiryDate() != null) {
            	cae.setStrEnquiryDate(DateFormatUtil.changeLocalDateFormat(temp.getEnquiryDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cae.setEnquiryDate(null);
            }
            if(temp.getDateOfBirth() != null) {
            	cae.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(temp.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cae.setDateOfBirth(null);
            }
            if(temp.getCreatedOn() != null) {
            	cae.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(temp.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cae.setCreatedOn(null);
            }
            if(temp.getUpdatedOn() != null) {
            	cae.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(temp.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cae.setUpdatedOn(null);
            }
            ls.add(cae);
        }
        logger.info("End of getting admission enquiry data");
        return ls;
    }

    public AdmissionEnquiryPayload saveAdmissionEnquiry(AdmissionEnquiryInput input) {
    	if(input.getId() == null) {
    		return addAdmissionEnquiry(input);
    	}
    	return updateAdmissionEnquiry(input);
    }

    public AdmissionEnquiryPayload addAdmissionEnquiry(AdmissionEnquiryInput input) {
    	logger.info("Adding admission enquiry");
    	SynectiksJPARepo synectiksJPARepo = new SynectiksJPARepo(AdmissionEnquiry.class, this.entityManager);
    	AdmissionEnquiry ae = CommonUtil.createCopyProperties(input, AdmissionEnquiry.class);
    	ae.setEnquiryStatus(CmsConstants.STATUS_RECEIVED);
    	if(input.getStrDateOfBirth() != null) {
    		ae.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(input.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    	}
    	
    	ae.setCreatedOn(LocalDate.now());
    	ae.setEnquiryDate(LocalDate.now());
    	
    	ae = (AdmissionEnquiry)synectiksJPARepo.save(ae);
    	
    	CmsAdmissionEnquiryVo vo = CommonUtil.createCopyProperties(ae, CmsAdmissionEnquiryVo.class);
    	if(ae.getDateOfBirth() != null) {
    		vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(ae.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setDateOfBirth(null);
    	}
    	if(ae.getCreatedOn() != null) {
    		vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ae.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setCreatedOn(null);
    	}
    	if(ae.getUpdatedOn() != null) {
    		vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ae.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setUpdatedOn(null);
    	}
    	if(ae.getEnquiryDate() != null) {
    		vo.setStrEnquiryDate(DateFormatUtil.changeLocalDateFormat(ae.getEnquiryDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		vo.setEnquiryDate(null);
    	}
    	vo.setExitCode(0L);
    	vo.setExitDescription("Admission enquiry saved successfully");
    	logger.info("Admission enquiry added successfully");
    	return new AdmissionEnquiryPayload(vo);
    }
    
    public AdmissionEnquiryPayload updateAdmissionEnquiry(AdmissionEnquiryInput input) {
    	CmsAdmissionEnquiryVo vo = null;
    	Long studentId = null;
    	try {
    		if(CmsConstants.TRANSACTION_SOURCE_ADMISSION_PAGE.equalsIgnoreCase(input.getTransactionSource())) {
    			logger.info("Converting enquiry to student profile");
    			studentId = saveStudentData(input);
    			logger.info("Enquiry converted to student profile successfully");
        	}
    		logger.info("Updating admission enquiry");
        	SynectiksJPARepo synectiksJPARepo = new SynectiksJPARepo(AdmissionEnquiry.class, this.entityManager);
        	AdmissionEnquiry ae = CommonUtil.createCopyProperties(input, AdmissionEnquiry.class);
        	if(input.getStrDateOfBirth() != null) {
        		ae.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(input.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	}
        	ae.setUpdatedOn(LocalDate.now());
        	ae = (AdmissionEnquiry)synectiksJPARepo.save(ae);
        	vo = CommonUtil.createCopyProperties(ae, CmsAdmissionEnquiryVo.class);
        	if(ae.getDateOfBirth() != null) {
        		vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(ae.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		vo.setDateOfBirth(null);
        	}
        	if(ae.getCreatedOn() != null) {
        		vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ae.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		vo.setCreatedOn(null);
        	}
        	if(ae.getUpdatedOn() != null) {
        		vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ae.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		vo.setUpdatedOn(null);
        	}
        	if(ae.getEnquiryDate() != null) {
        		vo.setStrEnquiryDate(DateFormatUtil.changeLocalDateFormat(ae.getEnquiryDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        		vo.setEnquiryDate(null);
        	}
        	if(CmsConstants.TRANSACTION_SOURCE_ADMISSION_PAGE.equalsIgnoreCase(input.getTransactionSource())) {
        		logger.info("Converting enquiry to admission and generating admission number");
        		AdmissionApplicationInput apInput = new AdmissionApplicationInput();
        		apInput.setApplicationStatus(CmsConstants.STATUS_ADMISSION_GRANTED);
        		apInput.setSourceOfApplication(CmsConstants.SOURCE_ADMISSION_ENQUIRY);
        		apInput.setAdmissionEnquiryId(ae.getId());
        		apInput.setAdmissionEnquiry(ae);
        		apInput.setStudentId(studentId);
        		cmsAdmissionApplicationService.addAdmissionApplication(apInput);
        		logger.info("Admission number generated successfully. Now creating student profile from admission enquiry");
        	}
        	vo.setExitCode(0L);
        	vo.setExitDescription("Admission enquiry updated successfully");
        	logger.info("Admission enquiry updated successfully");
    	}catch(Exception e) {
    		logger.error("Exception in converting ");
    		if(CmsConstants.TRANSACTION_SOURCE_ADMISSION_PAGE.equalsIgnoreCase(input.getTransactionSource())) {
    			deleteStudentData(studentId);
        	}
    	}
    	return new AdmissionEnquiryPayload(vo);
    }
    
    private Long saveStudentData(AdmissionEnquiryInput input) throws Exception {
    	Long id = null;
    	AdmissionEnquiryInput inp = CommonUtil.createCopyProperties(input, AdmissionEnquiryInput.class);
    	inp.setId(null);
    	Long admissionNo = CommonUtil.generateAdmissionNo(input.getId());
    	String url = applicationProperties.getPrefSrvUrl()+"/api/cms-grant-admission-to-student?admissionNo="+admissionNo;
		try {
			id = restTemplate.postForObject(url, inp, Long.class);
		}catch(Exception e) {
			logger.error("Student record could not be saved. Exception : ", e);
			throw e;
		}
		logger.info("Admission granted to student. New student id : "+id);
		return id;
    }
    
    private Long deleteStudentData(Long id) {
    	logger.info("Start deleting student record");
    	String url = applicationProperties.getPrefSrvUrl()+"/api/cmsstudents/"+id;
    	try {
    		restTemplate.delete(url, new Object());;
		}catch(Exception e) {
			logger.error("Student record could not be deleted. Exception : ", e);
		}
		logger.info("Student record deleted successfully");
		return id;
    }
}


