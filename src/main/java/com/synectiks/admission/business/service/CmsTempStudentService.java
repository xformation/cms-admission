package com.synectiks.admission.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.synectiks.admission.constant.CmsConstants;
import com.synectiks.admission.domain.TempStudent;
import com.synectiks.admission.domain.vo.CmsTempStudentVo;
import com.synectiks.admission.graphql.types.TempStudent.TempStudentInput;
import com.synectiks.admission.graphql.types.TempStudent.TempStudentPayload;
import com.synectiks.admission.repository.TempStudentRepository;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.service.util.DateFormatUtil;
import com.synectiks.admission.utils.IUtils;

@Service
@Transactional
public class CmsTempStudentService {
	
	private final static Logger logger = LoggerFactory.getLogger(CmsTempStudentService.class);

	@Autowired
	TempStudentRepository tempStudentRepository;
	
    public CmsTempStudentVo getTempStudent(Long branchId, Long academicYearId, String stateMachineId) {
        logger.info("Start getting temporary student data");
        TempStudent tempStudent = new TempStudent();
        tempStudent.setBranchId(branchId);
        tempStudent.setAcademicYearId(academicYearId);
        if(!IUtils.isNullOrEmpty(stateMachineId)) {
        	tempStudent.setStateMachineId(stateMachineId);
        }

        Example<TempStudent> example = Example.of(tempStudent);
        Optional<TempStudent> ots = tempStudentRepository.findOne(example);
        if(ots.isPresent()) {
        	logger.debug("TempStudent data : "+ots.get().toString());
            CmsTempStudentVo vo = CommonUtil.createCopyProperties(ots.get(), CmsTempStudentVo.class);
            if(ots.get().getDateOfBirth() != null) {
            	vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(ots.get().getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }

            if(ots.get().getDisabilityCertificateIssueDate() != null) {
            	vo.setStrDisabilityCertificateIssueDate(DateFormatUtil.changeLocalDateFormat(ots.get().getDisabilityCertificateIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            return vo;
        }
        logger.info("End of getting TempStudent data");
        return null;
    }

    public TempStudentPayload saveTempStudent(TempStudentInput input) {
    	CmsTempStudentVo tsvo = getTempStudent(input.getBranchId(), input.getAcademicYearId(), input.getStateMachineId());
    	if(tsvo == null) {
    		return addTempStudent(input);
    	}
    	return updateTempStudent(tsvo, input);
    }

    public TempStudentPayload addTempStudent(TempStudentInput input) {
    	logger.info("Adding temp student");
    	CmsTempStudentVo vo = null;
    	try {
    		TempStudent tempStudent = CommonUtil.createCopyProperties(input, TempStudent.class);
        	tempStudent.setStatus(CmsConstants.STATUS_DRAFT);
        	if(input.getStrDateOfBirth() != null) {
        		tempStudent.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(input.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	}
        	if(input.getStrDisabilityCertificateIssueDate() != null) {
        		tempStudent.setDisabilityCertificateIssueDate(DateFormatUtil.convertStringToLocalDate(input.getStrDisabilityCertificateIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	}
        	tempStudent = tempStudentRepository.save(tempStudent);
        	
        	vo = CommonUtil.createCopyProperties(tempStudent, CmsTempStudentVo.class);
        	if(tempStudent.getDateOfBirth() != null) {
        		vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(tempStudent.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	}
            if(tempStudent.getDisabilityCertificateIssueDate() != null) {
            	vo.setStrDisabilityCertificateIssueDate(DateFormatUtil.changeLocalDateFormat(tempStudent.getDisabilityCertificateIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
        	
        	vo.setExitCode(0L);
        	vo.setExitDescription("Temp student saved successfully");
        	logger.info("Temp student added successfully");
    	}catch(Exception e) {
    		logger.error("Exception in adding temp student: ",e);
    		vo = new CmsTempStudentVo();
    		vo.setExitCode(1L);
        	vo.setExitDescription("Temp student add failed");
    	}
    	
    	return new TempStudentPayload(vo);
    }
    
    public TempStudentPayload updateTempStudent(CmsTempStudentVo tsvo, TempStudentInput input) {
    	CmsTempStudentVo vo = null;
    	try {
    		
    		logger.info("Updating temp student");
    		TempStudent tempStudent = CommonUtil.createCopyProperties(tsvo, TempStudent.class);
    		
    		if(input.getStudentName() !=  null) {
    			tempStudent.setStudentName(input.getStudentName());
    		}
            if(input.getStudentMiddleName() != null) {
            	tempStudent.setStudentMiddleName(input.getStudentMiddleName());
            }
            if(input.getStudentLastName() != null) {
            	tempStudent.setStudentLastName(input.getStudentLastName());
            }
            if(input.getFatherName() != null) { 
            	tempStudent.setFatherName(input.getFatherName());
            }
            if(input.getFatherMiddleName() != null) { 
            	tempStudent.setFatherMiddleName(input.getFatherMiddleName());
            }
            if(input.getFatherLastName() != null) {
            	tempStudent.setFatherLastName(input.getFatherLastName());
            }
            if(input.getMotherName() != null) {
            	tempStudent.setMotherName(input.getMotherName());
            }
            if(input.getMotherMiddleName() != null) {
            	tempStudent.setMotherMiddleName(input.getMotherMiddleName());
            }
            if(input.getMotherLastName() != null) {
            	tempStudent.setMotherLastName(input.getMotherLastName());
            }
            if(input.getStudentAadharNo() != null) {
            	tempStudent.setStudentAadharNo(input.getStudentAadharNo());
            }
            if(input.getStudentPanNo() != null) {
            	tempStudent.setStudentPanNo(input.getStudentPanNo());
            }
            if(input.getStudentSocialSecurityNo() != null) {
            	tempStudent.setStudentSocialSecurityNo(input.getStudentSocialSecurityNo());
            }
            if(input.getStudentTaxReferenceNo() != null) {
            	tempStudent.setStudentTaxReferenceNo(input.getStudentTaxReferenceNo());
            }
            if(input.getStudentBplNo() != null) {
            	tempStudent.setStudentBplNo(input.getStudentBplNo());
            }
            if(input.getStudentDrivingLicenseNo() != null) {
            	tempStudent.setStudentDrivingLicenseNo(input.getStudentDrivingLicenseNo());
            }
            if(input.getStudentPassportNo() != null) {
            	tempStudent.setStudentPassportNo(input.getStudentPassportNo());
            }
            if(input.getPlaceOfBirth() != null) {
            	tempStudent.setPlaceOfBirth(input.getPlaceOfBirth());
            }
            if(input.getReligion() != null) {
            	tempStudent.setReligion(input.getReligion());
            }
            if(input.getCaste() != null) {
            	tempStudent.setCaste(input.getCaste());
            }
            if(input.getCaste() != null) {
            	tempStudent.setCaste(input.getCaste());
            }
            if(input.getSubCaste() != null) {
            	tempStudent.setSubCaste(input.getSubCaste());
            }
            if(input.getSex() != null) {
            	tempStudent.setSex(input.getSex());
            }
            if(input.getStudentLocalAddress() != null) {
            	tempStudent.setStudentLocalAddress(input.getStudentLocalAddress());
            }
            if(input.getStudentPermanentAddress() != null) {
            	tempStudent.setStudentPermanentAddress(input.getStudentPermanentAddress());
            }
            if(input.getCity() != null) {
            	tempStudent.setCity(input.getCity());
            }
            if(input.getState() != null) {
            	tempStudent.setState(input.getState());
            }
            if(input.getCountry() != null) {
            	tempStudent.setCountry(input.getCountry());
            }
            if(input.getPinCode() != null) {
            	tempStudent.setPinCode(input.getPinCode());
            }
            if(input.getStudentPrimaryCellNumber() != null) {
            	tempStudent.setStudentPrimaryCellNumber(input.getStudentPrimaryCellNumber());
            }
            if(input.getStudentAlternateCellNumber() != null) {
            	tempStudent.setStudentAlternateCellNumber(input.getStudentAlternateCellNumber());
            }
            if(input.getStudentLandLinePhoneNumber() != null) {
            	tempStudent.setStudentLandLinePhoneNumber(input.getStudentLandLinePhoneNumber());
            }
            if(input.getStudentPrimaryEmailId() != null) {
            	tempStudent.setStudentPrimaryEmailId(input.getStudentPrimaryEmailId());
            }
            if(input.getStudentAlternateEmailId() != null) {
            	tempStudent.setStudentAlternateEmailId(input.getStudentAlternateEmailId());
            }
            if(input.getRelationWithStudent() != null) {
            	tempStudent.setRelationWithStudent(input.getRelationWithStudent());
            }
            if(input.getEmergencyContactName() != null) {
            	tempStudent.setEmergencyContactName(input.getEmergencyContactName());
            }
            if(input.getEmergencyContactMiddleName() != null) {
            	tempStudent.setEmergencyContactMiddleName(input.getEmergencyContactMiddleName());
            }
            if(input.getEmergencyContactLastName() != null) {
            	tempStudent.setEmergencyContactLastName(input.getEmergencyContactLastName());
            }
            if(input.getEmergencyContactCellNumber() != null) {
            	tempStudent.setEmergencyContactCellNumber(input.getEmergencyContactCellNumber());
            }
            if (input.getEmergencyContactLandLinePhoneNumber() != null) {
            	tempStudent.setEmergencyContactLandLinePhoneNumber(input.getEmergencyContactLandLinePhoneNumber());
            }
           if (input.getEmergencyContactEmailId() != null) {
        	   tempStudent.setEmergencyContactEmailId(input.getEmergencyContactEmailId());
           }
           if (input.getStudentImagePath()!= null) {
        	   tempStudent.setStudentImagePath(input.getStudentImagePath());
           }
           if (input.getAdmissionNo()!= null) {
        	   tempStudent.setAdmissionNo(input.getAdmissionNo());
           }
           if (input.getEnrollmentNo()!= null) {
        	   tempStudent.setEnrollmentNo(input.getEnrollmentNo());
           }
           if (input.getRollNo()!= null) {
        	   tempStudent.setRollNo(input.getRollNo());
           }
           if (input.getStudentType()!= null) {
        	   tempStudent.setStudentType(input.getStudentType());
           }
           if (input.getFatherCellNumber()!= null) {
        	   tempStudent.setFatherCellNumber(input.getFatherCellNumber());
           }
           if (input.getFatherEmailId()!= null) {
        	   tempStudent.setFatherEmailId(input.getFatherEmailId());
           }
           if (input.getFatherOccupation()!= null) {
        	   tempStudent.setFatherOccupation(input.getFatherOccupation());
           }
           if (input.getFatherOfficeAddress()!= null) {
        	   tempStudent.setFatherOfficeAddress(input.getFatherOfficeAddress());
           }
           if (input.getFatherOfficeCellNumber()!= null) {
        	   tempStudent.setFatherOfficeCellNumber(input.getFatherOfficeCellNumber());
           }
           if (input.getFatherOfficeLandLinePhoneNumber()!= null) {
        	   tempStudent.setFatherOfficeLandLinePhoneNumber(input.getFatherOfficeLandLinePhoneNumber());
           }
           if (input.getFatherAadharNo()!= null) {
        	   tempStudent.setFatherAadharNo(input.getFatherAadharNo());
           }
           if (input.getFatherPanNo()!= null) {
        	   tempStudent.setFatherPanNo(input.getFatherPanNo());
           }
           if (input.getFatherSocialSecurityNo()!= null) {
        	   tempStudent.setFatherSocialSecurityNo(input.getFatherSocialSecurityNo());
           }
           if (input.getFatherTaxReferenceNo()!= null) {
        	   tempStudent.setFatherTaxReferenceNo(input.getFatherTaxReferenceNo());
           }
           if (input.getFatherBplNo()!= null) {
        	   tempStudent.setFatherBplNo(input.getFatherBplNo());
           }
           if (input.getFatherDrivingLicenseNo()!= null) {
        	   tempStudent.setFatherDrivingLicenseNo(input.getFatherDrivingLicenseNo());
           }
           if (input.getFatherPassportNo()!= null) {
        	   tempStudent.setFatherPassportNo(input.getFatherPassportNo());
           }
           if (input.getFatherImagePath()!= null) {
        	   tempStudent.setFatherImagePath(input.getFatherImagePath());
           }
           if (input.getMotherCellNumber()!= null) {
        	   tempStudent.setMotherCellNumber(input.getMotherCellNumber());
           }
           if (input.getMotherEmailId()!= null) {
        	   tempStudent.setMotherEmailId(input.getMotherEmailId());
           }
           if (input.getMotherOccupation()!= null) {
        	   tempStudent.setMotherOccupation(input.getMotherOccupation());
           }
           if (input.getMotherOfficeAddress()!= null) {
        	   tempStudent.setMotherOfficeAddress(input.getMotherOfficeAddress());
           }
           if (input.getMotherOfficeCellNumber()!= null) {
        	   tempStudent.setMotherOfficeCellNumber(input.getMotherOfficeCellNumber());
           }
           if (input.getMotherOfficeLandLinePhoneNumber() != null) {
        	   tempStudent.setMotherOfficeLandLinePhoneNumber(input.getMotherOfficeLandLinePhoneNumber());
           }
           if (input.getMotherAadharNo() != null) {
        	   tempStudent.setMotherAadharNo(input.getMotherAadharNo());
           }
           if (input.getMotherPanNo() != null) {
        	   tempStudent.setMotherPanNo(input.getMotherPanNo());
           }
           if (input.getMotherSocialSecurityNo() != null) {
        	   tempStudent.setMotherSocialSecurityNo(input.getMotherSocialSecurityNo());
           }
           if (input.getMotherTaxReferenceNo() != null) {
        	   tempStudent.setMotherTaxReferenceNo(input.getMotherTaxReferenceNo());
           }
           if (input.getMotherBplNo() != null) {
        	   tempStudent.setMotherBplNo(input.getMotherBplNo());
           }
           if (input.getMotherDrivingLicenseNo() != null) {
        	   tempStudent.setMotherDrivingLicenseNo(input.getMotherDrivingLicenseNo());
           }
           if (input.getMotherPassportNo() != null) {
        	   tempStudent.setMotherPassportNo(input.getMotherPassportNo());
           }
           if (input.getMotherImagePath() != null) {
        	   tempStudent.setMotherImagePath(input.getMotherImagePath());
           }
           if (input.getGuardianName() != null) {
        	   tempStudent.setGuardianName(input.getGuardianName());
           }
           if (input.getGuardianMiddleName() != null) {
        	   tempStudent.setGuardianMiddleName(input.getGuardianMiddleName());
           }
           if (input.getGuardianLastName() != null) {
        	   tempStudent.setGuardianLastName(input.getGuardianLastName());
           }
           if (input.getGuardianAddress() != null) {
        	   tempStudent.setGuardianAddress(input.getGuardianAddress());
           }
           if (input.getGuardianCellNumber() != null) {
        	   tempStudent.setGuardianCellNumber(input.getGuardianCellNumber());
           }
           if (input.getGuardianLandLinePhoneNumber() != null) {
        	   tempStudent.setGuardianLandLinePhoneNumber(input.getGuardianLandLinePhoneNumber());
           }
           if (input.getGuardianEmailId() != null) {
        	   tempStudent.setGuardianEmailId(input.getGuardianEmailId());
           }
           if (input.getGuardianOccupation() != null) {
        	   tempStudent.setGuardianOccupation(input.getGuardianOccupation());
           }
           if (input.getGuardianOfficeEmailId() != null) {
        	   tempStudent.setGuardianOfficeEmailId(input.getGuardianOfficeEmailId());
           }
           if (input.getGuardianOfficeAddress() != null) {
        	   tempStudent.setGuardianOfficeAddress(input.getGuardianOfficeAddress());
           }
           if (input.getGuardianOfficeCellNumber() != null) {
        	   tempStudent.setGuardianOfficeCellNumber(input.getGuardianOfficeCellNumber());
           }
           if (input.getGuardianOfficeLandLinePhoneNumber() != null) {
        	   tempStudent.setGuardianOfficeLandLinePhoneNumber(input.getGuardianOfficeLandLinePhoneNumber());
           }
           if (input.getGuardianImagePath() != null) {
        	   tempStudent.setGuardianImagePath(input.getGuardianImagePath());
           }
           if (input.getIsGuardianSponsorAgency() != null) {
        	   tempStudent.setIsGuardianSponsorAgency(input.getIsGuardianSponsorAgency());
           }
           if (input.getSponsorAgencyName() != null) {
        	   tempStudent.setSponsorAgencyName(input.getSponsorAgencyName());
           }
           if (input.getSponsorAgencyRegistrationNo() != null) {
        	   tempStudent.setSponsorAgencyRegistrationNo(input.getSponsorAgencyRegistrationNo());
           }
           if (input.getSponsorAgencyAddress() != null) {
        	   tempStudent.setSponsorAgencyAddress(input.getSponsorAgencyAddress());
           }
           if (input.getSponsorAgencyCellNumber() != null) {
        	   tempStudent.setSponsorAgencyCellNumber(input.getSponsorAgencyCellNumber());
           }
           if (input.getSponsorAgencyLandLineNumber() != null) {
        	   tempStudent.setSponsorAgencyLandLineNumber(input.getSponsorAgencyLandLineNumber());
           }
           if (input.getSponsorAgencyEmailId() != null) {
        	   tempStudent.setSponsorAgencyEmailId(input.getSponsorAgencyEmailId());
           }
           if (input.getSponsorAgencyAppointeeName() != null) {
        	   tempStudent.setSponsorAgencyAppointeeName(input.getSponsorAgencyAppointeeName());
           }
           if (input.getSponsorAgencyAppointeeDesignation() != null) {
        	   tempStudent.setSponsorAgencyAppointeeDesignation(input.getSponsorAgencyAppointeeDesignation());
           }
           if (input.getSponsorAgencyAppointeeCellNumber() != null) {
        	   tempStudent.setSponsorAgencyAppointeeCellNumber(input.getSponsorAgencyAppointeeCellNumber());
           }
           if (input.getSponsorAgencyAppointeeLandLineNumber() != null) {
        	   tempStudent.setSponsorAgencyAppointeeLandLineNumber(input.getSponsorAgencyAppointeeLandLineNumber());
           }
           if (input.getSponsorAgencyAppointeeOfficeAddress() != null) {
        	   tempStudent.setSponsorAgencyAppointeeOfficeAddress(input.getSponsorAgencyAppointeeOfficeAddress());
           }
           if (input.getSponsorAgencyAppointeeEmailId() != null) {
        	   tempStudent.setSponsorAgencyAppointeeEmailId(input.getSponsorAgencyAppointeeEmailId());
           }
           if (input.getIsPhysicallyChallenged() != null) {
        	   tempStudent.setIsPhysicallyChallenged(input.getIsPhysicallyChallenged());
           }
           if (input.getDetailsOfDisability() != null) {
        	   tempStudent.setDetailsOfDisability(input.getDetailsOfDisability());
           }
           if (input.getDisabilityCertificateNo() != null) {
        	   tempStudent.setDisabilityCertificateNo(input.getDisabilityCertificateNo());
           }
           if (input.getDisabilityCertificateIssueAuthority() != null) {
        	   tempStudent.setDisabilityCertificateIssueAuthority(input.getDisabilityCertificateIssueAuthority());
           }
           if (input.getDisabilityCertificateNo() != null) {
        	   tempStudent.setDisabilityCertificateNo(input.getDisabilityCertificateNo());
           }
           if (input.getPercentagOfDisability() != null) {
        	   tempStudent.setPercentagOfDisability(input.getPercentagOfDisability());
           }
           if (input.getBloodGroup() != null) {
        	   tempStudent.setBloodGroup(input.getBloodGroup());
           }
           if (input.getVaccinationDetails() != null) {
        	   tempStudent.setVaccinationDetails(input.getVaccinationDetails());
           }
           if (input.getOtherMedicalDetails() != null) {
        	   tempStudent.setOtherMedicalDetails(input.getOtherMedicalDetails());
           }
           if (input.getStatus() != null) {
        	   tempStudent.setStatus(input.getStatus());
           }
           if (input.getComments() != null) {
        	   tempStudent.setComments(input.getComments());
           }
            
           if(input.getHighestQualification() != null) {
        	   tempStudent.setHighestQualification(input.getHighestQualification());
           }
           
           if(input.getYearOfPassing() != null) {
        	   tempStudent.setYearOfPassing(input.getYearOfPassing());
           }
           
           if(input.getLastQualificationPercentage() != null) {
        	   tempStudent.setLastQualificationPercentage(input.getLastQualificationPercentage());
           }
           
           if(input.getLastCollegeAttended() != null) {
        	   tempStudent.setLastCollegeAttended(input.getLastCollegeAttended());
           }
           
            tempStudent.setDateOfBirth(input.getStrDateOfBirth() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            tempStudent.setBranchId(input.getBranchId());
            tempStudent.setDepartmentId(input.getBranchId());
            tempStudent.setBatchId(input.getBatchId());
            tempStudent.setSectionId(input.getSectionId());
            tempStudent.setAcademicYearId(input.getAcademicYearId());
        	
            tempStudent = tempStudentRepository.save(tempStudent);
        	
        	vo = CommonUtil.createCopyProperties(tempStudent, CmsTempStudentVo.class);
        	if(tempStudent.getDateOfBirth() != null) {
        		vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(tempStudent.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	}
        	if(tempStudent.getDisabilityCertificateIssueDate() != null) {
            	vo.setStrDisabilityCertificateIssueDate(DateFormatUtil.changeLocalDateFormat(tempStudent.getDisabilityCertificateIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
        	vo.setExitCode(0L);
        	vo.setExitDescription("Temp student updated successfully");
        	
        	logger.info("Temp student updated successfully");
    	}catch(Exception e) {
    		logger.error("Exception in updating temp student: ",e);
    		vo = new CmsTempStudentVo();
    		vo.setExitCode(1L);
        	vo.setExitDescription("Temp student updated failed");
    	}
    	return new TempStudentPayload(vo);
    }
    
   private Long deleteTempStudentData(Long id) {
    	logger.info("Start deleting temp student record");
    	this.tempStudentRepository.deleteById(id);
    	logger.info("Temp student record deleted successfully");
		return id;
    }
}


