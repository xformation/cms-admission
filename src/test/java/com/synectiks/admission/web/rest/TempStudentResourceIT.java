package com.synectiks.admission.web.rest;

import static com.synectiks.admission.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.synectiks.admission.AdmissionApp;
import com.synectiks.admission.domain.TempStudent;
import com.synectiks.admission.repository.TempStudentRepository;
import com.synectiks.admission.repository.search.TempStudentSearchRepository;
import com.synectiks.admission.service.TempStudentService;
import com.synectiks.admission.service.dto.TempStudentDTO;
import com.synectiks.admission.service.mapper.TempStudentMapper;
import com.synectiks.admission.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link TempStudentResource} REST controller.
 */
@SpringBootTest(classes = AdmissionApp.class)
public class TempStudentResourceIT {

    private static final String DEFAULT_STATE_MACHINE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STATE_MACHINE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_AADHAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_AADHAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_PAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_PAN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_SOCIAL_SECURITY_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_SOCIAL_SECURITY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_TAX_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_TAX_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_BPL_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_BPL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_DRIVING_LICENSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_DRIVING_LICENSE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_PASSPORT_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLACE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_RELIGION = "AAAAAAAAAA";
    private static final String UPDATED_RELIGION = "BBBBBBBBBB";

    private static final String DEFAULT_CASTE = "AAAAAAAAAA";
    private static final String UPDATED_CASTE = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CASTE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CASTE = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_LOCAL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_LOCAL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_PERMANENT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_PERMANENT_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_PRIMARY_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_PRIMARY_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_ALTERNATE_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_ALTERNATE_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_LAND_LINE_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_LAND_LINE_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_PRIMARY_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_PRIMARY_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_ALTERNATE_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_ALTERNATE_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION_WITH_STUDENT = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_WITH_STUDENT = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_ADMISSION_NO = "AAAAAAAAAA";
    private static final String UPDATED_ADMISSION_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ENROLLMENT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ENROLLMENT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ROLL_NO = "AAAAAAAAAA";
    private static final String UPDATED_ROLL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_OCCUPATION = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_OCCUPATION = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_OFFICE_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_OFFICE_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_OFFICE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_OFFICE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_OFFICE_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_OFFICE_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_AADHAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_AADHAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_PAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_PAN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_SOCIAL_SECURITY_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_SOCIAL_SECURITY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_TAX_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_TAX_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_BPL_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_BPL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_DRIVING_LICENSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_DRIVING_LICENSE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_PASSPORT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_OCCUPATION = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_OCCUPATION = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_OFFICE_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_OFFICE_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_OFFICE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_OFFICE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_OFFICE_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_OFFICE_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_AADHAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_AADHAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_PAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_PAN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_SOCIAL_SECURITY_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_SOCIAL_SECURITY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_TAX_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_TAX_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_BPL_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_BPL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_DRIVING_LICENSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_DRIVING_LICENSE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_PASSPORT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_LAND_LINE_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_LAND_LINE_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_OCCUPATION = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_OCCUPATION = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_OFFICE_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_OFFICE_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_OFFICE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_OFFICE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_OFFICE_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_OFFICE_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GUARDIAN_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_IS_GUARDIAN_SPONSOR_AGENCY = "AAA";
    private static final String UPDATED_IS_GUARDIAN_SPONSOR_AGENCY = "BBB";

    private static final String DEFAULT_SPONSOR_AGENCY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_REGISTRATION_NO = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_REGISTRATION_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_LAND_LINE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_LAND_LINE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_APPOINTEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_APPOINTEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_APPOINTEE_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_APPOINTEE_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_IS_PHYSICALLY_CHALLENGED = "AAA";
    private static final String UPDATED_IS_PHYSICALLY_CHALLENGED = "BBB";

    private static final String DEFAULT_DETAILS_OF_DISABILITY = "AAAAAAAAAA";
    private static final String UPDATED_DETAILS_OF_DISABILITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISABILITY_CERTIFICATE_NO = "AAAAAAAAAA";
    private static final String UPDATED_DISABILITY_CERTIFICATE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY = "AAAAAAAAAA";
    private static final String UPDATED_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DISABILITY_CERTIFICATE_ISSUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DISABILITY_CERTIFICATE_ISSUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PERCENTAG_OF_DISABILITY = 1;
    private static final Integer UPDATED_PERCENTAG_OF_DISABILITY = 2;

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_VACCINATION_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_VACCINATION_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_MEDICAL_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_MEDICAL_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_SECTION_ID = 1L;
    private static final Long UPDATED_SECTION_ID = 2L;

    private static final Long DEFAULT_BATCH_ID = 1L;
    private static final Long UPDATED_BATCH_ID = 2L;

    private static final Long DEFAULT_ACADEMIC_YEAR_ID = 1L;
    private static final Long UPDATED_ACADEMIC_YEAR_ID = 2L;

    @Autowired
    private TempStudentRepository tempStudentRepository;

    @Autowired
    private TempStudentMapper tempStudentMapper;

    @Autowired
    private TempStudentService tempStudentService;

    /**
     * This repository is mocked in the com.synectiks.admission.repository.search test package.
     *
     * @see com.synectiks.admission.repository.search.TempStudentSearchRepositoryMockConfiguration
     */
    @Autowired
    private TempStudentSearchRepository mockTempStudentSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTempStudentMockMvc;

    private TempStudent tempStudent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TempStudentResource tempStudentResource = new TempStudentResource(tempStudentService);
        this.restTempStudentMockMvc = MockMvcBuilders.standaloneSetup(tempStudentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TempStudent createEntity(EntityManager em) {
        TempStudent tempStudent = new TempStudent()
            .stateMachineId(DEFAULT_STATE_MACHINE_ID)
            .studentName(DEFAULT_STUDENT_NAME)
            .studentMiddleName(DEFAULT_STUDENT_MIDDLE_NAME)
            .studentLastName(DEFAULT_STUDENT_LAST_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .fatherMiddleName(DEFAULT_FATHER_MIDDLE_NAME)
            .fatherLastName(DEFAULT_FATHER_LAST_NAME)
            .motherName(DEFAULT_MOTHER_NAME)
            .motherMiddleName(DEFAULT_MOTHER_MIDDLE_NAME)
            .motherLastName(DEFAULT_MOTHER_LAST_NAME)
            .studentAadharNo(DEFAULT_STUDENT_AADHAR_NO)
            .studentPanNo(DEFAULT_STUDENT_PAN_NO)
            .studentSocialSecurityNo(DEFAULT_STUDENT_SOCIAL_SECURITY_NO)
            .studentTaxReferenceNo(DEFAULT_STUDENT_TAX_REFERENCE_NO)
            .studentBplNo(DEFAULT_STUDENT_BPL_NO)
            .studentDrivingLicenseNo(DEFAULT_STUDENT_DRIVING_LICENSE_NO)
            .studentPassportNo(DEFAULT_STUDENT_PASSPORT_NO)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .placeOfBirth(DEFAULT_PLACE_OF_BIRTH)
            .religion(DEFAULT_RELIGION)
            .caste(DEFAULT_CASTE)
            .subCaste(DEFAULT_SUB_CASTE)
            .sex(DEFAULT_SEX)
            .studentLocalAddress(DEFAULT_STUDENT_LOCAL_ADDRESS)
            .studentPermanentAddress(DEFAULT_STUDENT_PERMANENT_ADDRESS)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .pinCode(DEFAULT_PIN_CODE)
            .studentPrimaryCellNumber(DEFAULT_STUDENT_PRIMARY_CELL_NUMBER)
            .studentAlternateCellNumber(DEFAULT_STUDENT_ALTERNATE_CELL_NUMBER)
            .studentLandLinePhoneNumber(DEFAULT_STUDENT_LAND_LINE_PHONE_NUMBER)
            .studentPrimaryEmailId(DEFAULT_STUDENT_PRIMARY_EMAIL_ID)
            .studentAlternateEmailId(DEFAULT_STUDENT_ALTERNATE_EMAIL_ID)
            .relationWithStudent(DEFAULT_RELATION_WITH_STUDENT)
            .emergencyContactName(DEFAULT_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactCellNumber(DEFAULT_EMERGENCY_CONTACT_CELL_NUMBER)
            .emergencyContactLandLinePhoneNumber(DEFAULT_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER)
            .emergencyContactEmailId(DEFAULT_EMERGENCY_CONTACT_EMAIL_ID)
            .studentImagePath(DEFAULT_STUDENT_IMAGE_PATH)
            .admissionNo(DEFAULT_ADMISSION_NO)
            .enrollmentNo(DEFAULT_ENROLLMENT_NO)
            .rollNo(DEFAULT_ROLL_NO)
            .studentType(DEFAULT_STUDENT_TYPE)
            .fatherCellNumber(DEFAULT_FATHER_CELL_NUMBER)
            .fatherEmailId(DEFAULT_FATHER_EMAIL_ID)
            .fatherOccupation(DEFAULT_FATHER_OCCUPATION)
            .fatherOfficeEmailId(DEFAULT_FATHER_OFFICE_EMAIL_ID)
            .fatherOfficeAddress(DEFAULT_FATHER_OFFICE_ADDRESS)
            .fatherOfficeCellNumber(DEFAULT_FATHER_OFFICE_CELL_NUMBER)
            .fatherOfficeLandLinePhoneNumber(DEFAULT_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER)
            .fatherAadharNo(DEFAULT_FATHER_AADHAR_NO)
            .fatherPanNo(DEFAULT_FATHER_PAN_NO)
            .fatherSocialSecurityNo(DEFAULT_FATHER_SOCIAL_SECURITY_NO)
            .fatherTaxReferenceNo(DEFAULT_FATHER_TAX_REFERENCE_NO)
            .fatherBplNo(DEFAULT_FATHER_BPL_NO)
            .fatherDrivingLicenseNo(DEFAULT_FATHER_DRIVING_LICENSE_NO)
            .fatherPassportNo(DEFAULT_FATHER_PASSPORT_NO)
            .fatherImagePath(DEFAULT_FATHER_IMAGE_PATH)
            .motherCellNumber(DEFAULT_MOTHER_CELL_NUMBER)
            .motherEmailId(DEFAULT_MOTHER_EMAIL_ID)
            .motherOccupation(DEFAULT_MOTHER_OCCUPATION)
            .motherOfficeEmailId(DEFAULT_MOTHER_OFFICE_EMAIL_ID)
            .motherOfficeAddress(DEFAULT_MOTHER_OFFICE_ADDRESS)
            .motherOfficeCellNumber(DEFAULT_MOTHER_OFFICE_CELL_NUMBER)
            .motherOfficeLandLinePhoneNumber(DEFAULT_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER)
            .motherAadharNo(DEFAULT_MOTHER_AADHAR_NO)
            .motherPanNo(DEFAULT_MOTHER_PAN_NO)
            .motherSocialSecurityNo(DEFAULT_MOTHER_SOCIAL_SECURITY_NO)
            .motherTaxReferenceNo(DEFAULT_MOTHER_TAX_REFERENCE_NO)
            .motherBplNo(DEFAULT_MOTHER_BPL_NO)
            .motherDrivingLicenseNo(DEFAULT_MOTHER_DRIVING_LICENSE_NO)
            .motherPassportNo(DEFAULT_MOTHER_PASSPORT_NO)
            .motherImagePath(DEFAULT_MOTHER_IMAGE_PATH)
            .guardianName(DEFAULT_GUARDIAN_NAME)
            .guardianMiddleName(DEFAULT_GUARDIAN_MIDDLE_NAME)
            .guardianLastName(DEFAULT_GUARDIAN_LAST_NAME)
            .guardianAddress(DEFAULT_GUARDIAN_ADDRESS)
            .guardianCellNumber(DEFAULT_GUARDIAN_CELL_NUMBER)
            .guardianLandLinePhoneNumber(DEFAULT_GUARDIAN_LAND_LINE_PHONE_NUMBER)
            .guardianEmailId(DEFAULT_GUARDIAN_EMAIL_ID)
            .guardianOccupation(DEFAULT_GUARDIAN_OCCUPATION)
            .guardianOfficeEmailId(DEFAULT_GUARDIAN_OFFICE_EMAIL_ID)
            .guardianOfficeAddress(DEFAULT_GUARDIAN_OFFICE_ADDRESS)
            .guardianOfficeCellNumber(DEFAULT_GUARDIAN_OFFICE_CELL_NUMBER)
            .guardianOfficeLandLinePhoneNumber(DEFAULT_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER)
            .guardianImagePath(DEFAULT_GUARDIAN_IMAGE_PATH)
            .isGuardianSponsorAgency(DEFAULT_IS_GUARDIAN_SPONSOR_AGENCY)
            .sponsorAgencyName(DEFAULT_SPONSOR_AGENCY_NAME)
            .sponsorAgencyRegistrationNo(DEFAULT_SPONSOR_AGENCY_REGISTRATION_NO)
            .sponsorAgencyAddress(DEFAULT_SPONSOR_AGENCY_ADDRESS)
            .sponsorAgencyCellNumber(DEFAULT_SPONSOR_AGENCY_CELL_NUMBER)
            .sponsorAgencyLandLineNumber(DEFAULT_SPONSOR_AGENCY_LAND_LINE_NUMBER)
            .sponsorAgencyEmailId(DEFAULT_SPONSOR_AGENCY_EMAIL_ID)
            .sponsorAgencyAppointeeName(DEFAULT_SPONSOR_AGENCY_APPOINTEE_NAME)
            .sponsorAgencyAppointeeDesignation(DEFAULT_SPONSOR_AGENCY_APPOINTEE_DESIGNATION)
            .sponsorAgencyAppointeeCellNumber(DEFAULT_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER)
            .sponsorAgencyAppointeeLandLineNumber(DEFAULT_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER)
            .sponsorAgencyAppointeeEmailId(DEFAULT_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID)
            .sponsorAgencyAppointeeOfficeAddress(DEFAULT_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS)
            .isPhysicallyChallenged(DEFAULT_IS_PHYSICALLY_CHALLENGED)
            .detailsOfDisability(DEFAULT_DETAILS_OF_DISABILITY)
            .disabilityCertificateNo(DEFAULT_DISABILITY_CERTIFICATE_NO)
            .disabilityCertificateIssueAuthority(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY)
            .disabilityCertificateIssueDate(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_DATE)
            .percentagOfDisability(DEFAULT_PERCENTAG_OF_DISABILITY)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .vaccinationDetails(DEFAULT_VACCINATION_DETAILS)
            .otherMedicalDetails(DEFAULT_OTHER_MEDICAL_DETAILS)
            .status(DEFAULT_STATUS)
            .comments(DEFAULT_COMMENTS)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .branchId(DEFAULT_BRANCH_ID)
            .sectionId(DEFAULT_SECTION_ID)
            .batchId(DEFAULT_BATCH_ID)
            .academicYearId(DEFAULT_ACADEMIC_YEAR_ID);
        return tempStudent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TempStudent createUpdatedEntity(EntityManager em) {
        TempStudent tempStudent = new TempStudent()
            .stateMachineId(UPDATED_STATE_MACHINE_ID)
            .studentName(UPDATED_STUDENT_NAME)
            .studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME)
            .studentLastName(UPDATED_STUDENT_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
            .studentAadharNo(UPDATED_STUDENT_AADHAR_NO)
            .studentPanNo(UPDATED_STUDENT_PAN_NO)
            .studentSocialSecurityNo(UPDATED_STUDENT_SOCIAL_SECURITY_NO)
            .studentTaxReferenceNo(UPDATED_STUDENT_TAX_REFERENCE_NO)
            .studentBplNo(UPDATED_STUDENT_BPL_NO)
            .studentDrivingLicenseNo(UPDATED_STUDENT_DRIVING_LICENSE_NO)
            .studentPassportNo(UPDATED_STUDENT_PASSPORT_NO)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .religion(UPDATED_RELIGION)
            .caste(UPDATED_CASTE)
            .subCaste(UPDATED_SUB_CASTE)
            .sex(UPDATED_SEX)
            .studentLocalAddress(UPDATED_STUDENT_LOCAL_ADDRESS)
            .studentPermanentAddress(UPDATED_STUDENT_PERMANENT_ADDRESS)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .pinCode(UPDATED_PIN_CODE)
            .studentPrimaryCellNumber(UPDATED_STUDENT_PRIMARY_CELL_NUMBER)
            .studentAlternateCellNumber(UPDATED_STUDENT_ALTERNATE_CELL_NUMBER)
            .studentLandLinePhoneNumber(UPDATED_STUDENT_LAND_LINE_PHONE_NUMBER)
            .studentPrimaryEmailId(UPDATED_STUDENT_PRIMARY_EMAIL_ID)
            .studentAlternateEmailId(UPDATED_STUDENT_ALTERNATE_EMAIL_ID)
            .relationWithStudent(UPDATED_RELATION_WITH_STUDENT)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(UPDATED_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactCellNumber(UPDATED_EMERGENCY_CONTACT_CELL_NUMBER)
            .emergencyContactLandLinePhoneNumber(UPDATED_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER)
            .emergencyContactEmailId(UPDATED_EMERGENCY_CONTACT_EMAIL_ID)
            .studentImagePath(UPDATED_STUDENT_IMAGE_PATH)
            .admissionNo(UPDATED_ADMISSION_NO)
            .enrollmentNo(UPDATED_ENROLLMENT_NO)
            .rollNo(UPDATED_ROLL_NO)
            .studentType(UPDATED_STUDENT_TYPE)
            .fatherCellNumber(UPDATED_FATHER_CELL_NUMBER)
            .fatherEmailId(UPDATED_FATHER_EMAIL_ID)
            .fatherOccupation(UPDATED_FATHER_OCCUPATION)
            .fatherOfficeEmailId(UPDATED_FATHER_OFFICE_EMAIL_ID)
            .fatherOfficeAddress(UPDATED_FATHER_OFFICE_ADDRESS)
            .fatherOfficeCellNumber(UPDATED_FATHER_OFFICE_CELL_NUMBER)
            .fatherOfficeLandLinePhoneNumber(UPDATED_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER)
            .fatherAadharNo(UPDATED_FATHER_AADHAR_NO)
            .fatherPanNo(UPDATED_FATHER_PAN_NO)
            .fatherSocialSecurityNo(UPDATED_FATHER_SOCIAL_SECURITY_NO)
            .fatherTaxReferenceNo(UPDATED_FATHER_TAX_REFERENCE_NO)
            .fatherBplNo(UPDATED_FATHER_BPL_NO)
            .fatherDrivingLicenseNo(UPDATED_FATHER_DRIVING_LICENSE_NO)
            .fatherPassportNo(UPDATED_FATHER_PASSPORT_NO)
            .fatherImagePath(UPDATED_FATHER_IMAGE_PATH)
            .motherCellNumber(UPDATED_MOTHER_CELL_NUMBER)
            .motherEmailId(UPDATED_MOTHER_EMAIL_ID)
            .motherOccupation(UPDATED_MOTHER_OCCUPATION)
            .motherOfficeEmailId(UPDATED_MOTHER_OFFICE_EMAIL_ID)
            .motherOfficeAddress(UPDATED_MOTHER_OFFICE_ADDRESS)
            .motherOfficeCellNumber(UPDATED_MOTHER_OFFICE_CELL_NUMBER)
            .motherOfficeLandLinePhoneNumber(UPDATED_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER)
            .motherAadharNo(UPDATED_MOTHER_AADHAR_NO)
            .motherPanNo(UPDATED_MOTHER_PAN_NO)
            .motherSocialSecurityNo(UPDATED_MOTHER_SOCIAL_SECURITY_NO)
            .motherTaxReferenceNo(UPDATED_MOTHER_TAX_REFERENCE_NO)
            .motherBplNo(UPDATED_MOTHER_BPL_NO)
            .motherDrivingLicenseNo(UPDATED_MOTHER_DRIVING_LICENSE_NO)
            .motherPassportNo(UPDATED_MOTHER_PASSPORT_NO)
            .motherImagePath(UPDATED_MOTHER_IMAGE_PATH)
            .guardianName(UPDATED_GUARDIAN_NAME)
            .guardianMiddleName(UPDATED_GUARDIAN_MIDDLE_NAME)
            .guardianLastName(UPDATED_GUARDIAN_LAST_NAME)
            .guardianAddress(UPDATED_GUARDIAN_ADDRESS)
            .guardianCellNumber(UPDATED_GUARDIAN_CELL_NUMBER)
            .guardianLandLinePhoneNumber(UPDATED_GUARDIAN_LAND_LINE_PHONE_NUMBER)
            .guardianEmailId(UPDATED_GUARDIAN_EMAIL_ID)
            .guardianOccupation(UPDATED_GUARDIAN_OCCUPATION)
            .guardianOfficeEmailId(UPDATED_GUARDIAN_OFFICE_EMAIL_ID)
            .guardianOfficeAddress(UPDATED_GUARDIAN_OFFICE_ADDRESS)
            .guardianOfficeCellNumber(UPDATED_GUARDIAN_OFFICE_CELL_NUMBER)
            .guardianOfficeLandLinePhoneNumber(UPDATED_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER)
            .guardianImagePath(UPDATED_GUARDIAN_IMAGE_PATH)
            .isGuardianSponsorAgency(UPDATED_IS_GUARDIAN_SPONSOR_AGENCY)
            .sponsorAgencyName(UPDATED_SPONSOR_AGENCY_NAME)
            .sponsorAgencyRegistrationNo(UPDATED_SPONSOR_AGENCY_REGISTRATION_NO)
            .sponsorAgencyAddress(UPDATED_SPONSOR_AGENCY_ADDRESS)
            .sponsorAgencyCellNumber(UPDATED_SPONSOR_AGENCY_CELL_NUMBER)
            .sponsorAgencyLandLineNumber(UPDATED_SPONSOR_AGENCY_LAND_LINE_NUMBER)
            .sponsorAgencyEmailId(UPDATED_SPONSOR_AGENCY_EMAIL_ID)
            .sponsorAgencyAppointeeName(UPDATED_SPONSOR_AGENCY_APPOINTEE_NAME)
            .sponsorAgencyAppointeeDesignation(UPDATED_SPONSOR_AGENCY_APPOINTEE_DESIGNATION)
            .sponsorAgencyAppointeeCellNumber(UPDATED_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER)
            .sponsorAgencyAppointeeLandLineNumber(UPDATED_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER)
            .sponsorAgencyAppointeeEmailId(UPDATED_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID)
            .sponsorAgencyAppointeeOfficeAddress(UPDATED_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS)
            .isPhysicallyChallenged(UPDATED_IS_PHYSICALLY_CHALLENGED)
            .detailsOfDisability(UPDATED_DETAILS_OF_DISABILITY)
            .disabilityCertificateNo(UPDATED_DISABILITY_CERTIFICATE_NO)
            .disabilityCertificateIssueAuthority(UPDATED_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY)
            .disabilityCertificateIssueDate(UPDATED_DISABILITY_CERTIFICATE_ISSUE_DATE)
            .percentagOfDisability(UPDATED_PERCENTAG_OF_DISABILITY)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .vaccinationDetails(UPDATED_VACCINATION_DETAILS)
            .otherMedicalDetails(UPDATED_OTHER_MEDICAL_DETAILS)
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .branchId(UPDATED_BRANCH_ID)
            .sectionId(UPDATED_SECTION_ID)
            .batchId(UPDATED_BATCH_ID)
            .academicYearId(UPDATED_ACADEMIC_YEAR_ID);
        return tempStudent;
    }

    @BeforeEach
    public void initTest() {
        tempStudent = createEntity(em);
    }

    @Test
    @Transactional
    public void createTempStudent() throws Exception {
        int databaseSizeBeforeCreate = tempStudentRepository.findAll().size();

        // Create the TempStudent
        TempStudentDTO tempStudentDTO = tempStudentMapper.toDto(tempStudent);
        restTempStudentMockMvc.perform(post("/api/temp-students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tempStudentDTO)))
            .andExpect(status().isCreated());

        // Validate the TempStudent in the database
        List<TempStudent> tempStudentList = tempStudentRepository.findAll();
        assertThat(tempStudentList).hasSize(databaseSizeBeforeCreate + 1);
        TempStudent testTempStudent = tempStudentList.get(tempStudentList.size() - 1);
        assertThat(testTempStudent.getStateMachineId()).isEqualTo(DEFAULT_STATE_MACHINE_ID);
        assertThat(testTempStudent.getStudentName()).isEqualTo(DEFAULT_STUDENT_NAME);
        assertThat(testTempStudent.getStudentMiddleName()).isEqualTo(DEFAULT_STUDENT_MIDDLE_NAME);
        assertThat(testTempStudent.getStudentLastName()).isEqualTo(DEFAULT_STUDENT_LAST_NAME);
        assertThat(testTempStudent.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testTempStudent.getFatherMiddleName()).isEqualTo(DEFAULT_FATHER_MIDDLE_NAME);
        assertThat(testTempStudent.getFatherLastName()).isEqualTo(DEFAULT_FATHER_LAST_NAME);
        assertThat(testTempStudent.getMotherName()).isEqualTo(DEFAULT_MOTHER_NAME);
        assertThat(testTempStudent.getMotherMiddleName()).isEqualTo(DEFAULT_MOTHER_MIDDLE_NAME);
        assertThat(testTempStudent.getMotherLastName()).isEqualTo(DEFAULT_MOTHER_LAST_NAME);
        assertThat(testTempStudent.getStudentAadharNo()).isEqualTo(DEFAULT_STUDENT_AADHAR_NO);
        assertThat(testTempStudent.getStudentPanNo()).isEqualTo(DEFAULT_STUDENT_PAN_NO);
        assertThat(testTempStudent.getStudentSocialSecurityNo()).isEqualTo(DEFAULT_STUDENT_SOCIAL_SECURITY_NO);
        assertThat(testTempStudent.getStudentTaxReferenceNo()).isEqualTo(DEFAULT_STUDENT_TAX_REFERENCE_NO);
        assertThat(testTempStudent.getStudentBplNo()).isEqualTo(DEFAULT_STUDENT_BPL_NO);
        assertThat(testTempStudent.getStudentDrivingLicenseNo()).isEqualTo(DEFAULT_STUDENT_DRIVING_LICENSE_NO);
        assertThat(testTempStudent.getStudentPassportNo()).isEqualTo(DEFAULT_STUDENT_PASSPORT_NO);
        assertThat(testTempStudent.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testTempStudent.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testTempStudent.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testTempStudent.getCaste()).isEqualTo(DEFAULT_CASTE);
        assertThat(testTempStudent.getSubCaste()).isEqualTo(DEFAULT_SUB_CASTE);
        assertThat(testTempStudent.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testTempStudent.getStudentLocalAddress()).isEqualTo(DEFAULT_STUDENT_LOCAL_ADDRESS);
        assertThat(testTempStudent.getStudentPermanentAddress()).isEqualTo(DEFAULT_STUDENT_PERMANENT_ADDRESS);
        assertThat(testTempStudent.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testTempStudent.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testTempStudent.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testTempStudent.getPinCode()).isEqualTo(DEFAULT_PIN_CODE);
        assertThat(testTempStudent.getStudentPrimaryCellNumber()).isEqualTo(DEFAULT_STUDENT_PRIMARY_CELL_NUMBER);
        assertThat(testTempStudent.getStudentAlternateCellNumber()).isEqualTo(DEFAULT_STUDENT_ALTERNATE_CELL_NUMBER);
        assertThat(testTempStudent.getStudentLandLinePhoneNumber()).isEqualTo(DEFAULT_STUDENT_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getStudentPrimaryEmailId()).isEqualTo(DEFAULT_STUDENT_PRIMARY_EMAIL_ID);
        assertThat(testTempStudent.getStudentAlternateEmailId()).isEqualTo(DEFAULT_STUDENT_ALTERNATE_EMAIL_ID);
        assertThat(testTempStudent.getRelationWithStudent()).isEqualTo(DEFAULT_RELATION_WITH_STUDENT);
        assertThat(testTempStudent.getEmergencyContactName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NAME);
        assertThat(testTempStudent.getEmergencyContactMiddleName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testTempStudent.getEmergencyContactLastName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testTempStudent.getEmergencyContactCellNumber()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_CELL_NUMBER);
        assertThat(testTempStudent.getEmergencyContactLandLinePhoneNumber()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getEmergencyContactEmailId()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_EMAIL_ID);
        assertThat(testTempStudent.getStudentImagePath()).isEqualTo(DEFAULT_STUDENT_IMAGE_PATH);
        assertThat(testTempStudent.getAdmissionNo()).isEqualTo(DEFAULT_ADMISSION_NO);
        assertThat(testTempStudent.getEnrollmentNo()).isEqualTo(DEFAULT_ENROLLMENT_NO);
        assertThat(testTempStudent.getRollNo()).isEqualTo(DEFAULT_ROLL_NO);
        assertThat(testTempStudent.getStudentType()).isEqualTo(DEFAULT_STUDENT_TYPE);
        assertThat(testTempStudent.getFatherCellNumber()).isEqualTo(DEFAULT_FATHER_CELL_NUMBER);
        assertThat(testTempStudent.getFatherEmailId()).isEqualTo(DEFAULT_FATHER_EMAIL_ID);
        assertThat(testTempStudent.getFatherOccupation()).isEqualTo(DEFAULT_FATHER_OCCUPATION);
        assertThat(testTempStudent.getFatherOfficeEmailId()).isEqualTo(DEFAULT_FATHER_OFFICE_EMAIL_ID);
        assertThat(testTempStudent.getFatherOfficeAddress()).isEqualTo(DEFAULT_FATHER_OFFICE_ADDRESS);
        assertThat(testTempStudent.getFatherOfficeCellNumber()).isEqualTo(DEFAULT_FATHER_OFFICE_CELL_NUMBER);
        assertThat(testTempStudent.getFatherOfficeLandLinePhoneNumber()).isEqualTo(DEFAULT_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getFatherAadharNo()).isEqualTo(DEFAULT_FATHER_AADHAR_NO);
        assertThat(testTempStudent.getFatherPanNo()).isEqualTo(DEFAULT_FATHER_PAN_NO);
        assertThat(testTempStudent.getFatherSocialSecurityNo()).isEqualTo(DEFAULT_FATHER_SOCIAL_SECURITY_NO);
        assertThat(testTempStudent.getFatherTaxReferenceNo()).isEqualTo(DEFAULT_FATHER_TAX_REFERENCE_NO);
        assertThat(testTempStudent.getFatherBplNo()).isEqualTo(DEFAULT_FATHER_BPL_NO);
        assertThat(testTempStudent.getFatherDrivingLicenseNo()).isEqualTo(DEFAULT_FATHER_DRIVING_LICENSE_NO);
        assertThat(testTempStudent.getFatherPassportNo()).isEqualTo(DEFAULT_FATHER_PASSPORT_NO);
        assertThat(testTempStudent.getFatherImagePath()).isEqualTo(DEFAULT_FATHER_IMAGE_PATH);
        assertThat(testTempStudent.getMotherCellNumber()).isEqualTo(DEFAULT_MOTHER_CELL_NUMBER);
        assertThat(testTempStudent.getMotherEmailId()).isEqualTo(DEFAULT_MOTHER_EMAIL_ID);
        assertThat(testTempStudent.getMotherOccupation()).isEqualTo(DEFAULT_MOTHER_OCCUPATION);
        assertThat(testTempStudent.getMotherOfficeEmailId()).isEqualTo(DEFAULT_MOTHER_OFFICE_EMAIL_ID);
        assertThat(testTempStudent.getMotherOfficeAddress()).isEqualTo(DEFAULT_MOTHER_OFFICE_ADDRESS);
        assertThat(testTempStudent.getMotherOfficeCellNumber()).isEqualTo(DEFAULT_MOTHER_OFFICE_CELL_NUMBER);
        assertThat(testTempStudent.getMotherOfficeLandLinePhoneNumber()).isEqualTo(DEFAULT_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getMotherAadharNo()).isEqualTo(DEFAULT_MOTHER_AADHAR_NO);
        assertThat(testTempStudent.getMotherPanNo()).isEqualTo(DEFAULT_MOTHER_PAN_NO);
        assertThat(testTempStudent.getMotherSocialSecurityNo()).isEqualTo(DEFAULT_MOTHER_SOCIAL_SECURITY_NO);
        assertThat(testTempStudent.getMotherTaxReferenceNo()).isEqualTo(DEFAULT_MOTHER_TAX_REFERENCE_NO);
        assertThat(testTempStudent.getMotherBplNo()).isEqualTo(DEFAULT_MOTHER_BPL_NO);
        assertThat(testTempStudent.getMotherDrivingLicenseNo()).isEqualTo(DEFAULT_MOTHER_DRIVING_LICENSE_NO);
        assertThat(testTempStudent.getMotherPassportNo()).isEqualTo(DEFAULT_MOTHER_PASSPORT_NO);
        assertThat(testTempStudent.getMotherImagePath()).isEqualTo(DEFAULT_MOTHER_IMAGE_PATH);
        assertThat(testTempStudent.getGuardianName()).isEqualTo(DEFAULT_GUARDIAN_NAME);
        assertThat(testTempStudent.getGuardianMiddleName()).isEqualTo(DEFAULT_GUARDIAN_MIDDLE_NAME);
        assertThat(testTempStudent.getGuardianLastName()).isEqualTo(DEFAULT_GUARDIAN_LAST_NAME);
        assertThat(testTempStudent.getGuardianAddress()).isEqualTo(DEFAULT_GUARDIAN_ADDRESS);
        assertThat(testTempStudent.getGuardianCellNumber()).isEqualTo(DEFAULT_GUARDIAN_CELL_NUMBER);
        assertThat(testTempStudent.getGuardianLandLinePhoneNumber()).isEqualTo(DEFAULT_GUARDIAN_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getGuardianEmailId()).isEqualTo(DEFAULT_GUARDIAN_EMAIL_ID);
        assertThat(testTempStudent.getGuardianOccupation()).isEqualTo(DEFAULT_GUARDIAN_OCCUPATION);
        assertThat(testTempStudent.getGuardianOfficeEmailId()).isEqualTo(DEFAULT_GUARDIAN_OFFICE_EMAIL_ID);
        assertThat(testTempStudent.getGuardianOfficeAddress()).isEqualTo(DEFAULT_GUARDIAN_OFFICE_ADDRESS);
        assertThat(testTempStudent.getGuardianOfficeCellNumber()).isEqualTo(DEFAULT_GUARDIAN_OFFICE_CELL_NUMBER);
        assertThat(testTempStudent.getGuardianOfficeLandLinePhoneNumber()).isEqualTo(DEFAULT_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getGuardianImagePath()).isEqualTo(DEFAULT_GUARDIAN_IMAGE_PATH);
        assertThat(testTempStudent.getIsGuardianSponsorAgency()).isEqualTo(DEFAULT_IS_GUARDIAN_SPONSOR_AGENCY);
        assertThat(testTempStudent.getSponsorAgencyName()).isEqualTo(DEFAULT_SPONSOR_AGENCY_NAME);
        assertThat(testTempStudent.getSponsorAgencyRegistrationNo()).isEqualTo(DEFAULT_SPONSOR_AGENCY_REGISTRATION_NO);
        assertThat(testTempStudent.getSponsorAgencyAddress()).isEqualTo(DEFAULT_SPONSOR_AGENCY_ADDRESS);
        assertThat(testTempStudent.getSponsorAgencyCellNumber()).isEqualTo(DEFAULT_SPONSOR_AGENCY_CELL_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyLandLineNumber()).isEqualTo(DEFAULT_SPONSOR_AGENCY_LAND_LINE_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyEmailId()).isEqualTo(DEFAULT_SPONSOR_AGENCY_EMAIL_ID);
        assertThat(testTempStudent.getSponsorAgencyAppointeeName()).isEqualTo(DEFAULT_SPONSOR_AGENCY_APPOINTEE_NAME);
        assertThat(testTempStudent.getSponsorAgencyAppointeeDesignation()).isEqualTo(DEFAULT_SPONSOR_AGENCY_APPOINTEE_DESIGNATION);
        assertThat(testTempStudent.getSponsorAgencyAppointeeCellNumber()).isEqualTo(DEFAULT_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyAppointeeLandLineNumber()).isEqualTo(DEFAULT_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyAppointeeEmailId()).isEqualTo(DEFAULT_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID);
        assertThat(testTempStudent.getSponsorAgencyAppointeeOfficeAddress()).isEqualTo(DEFAULT_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS);
        assertThat(testTempStudent.getIsPhysicallyChallenged()).isEqualTo(DEFAULT_IS_PHYSICALLY_CHALLENGED);
        assertThat(testTempStudent.getDetailsOfDisability()).isEqualTo(DEFAULT_DETAILS_OF_DISABILITY);
        assertThat(testTempStudent.getDisabilityCertificateNo()).isEqualTo(DEFAULT_DISABILITY_CERTIFICATE_NO);
        assertThat(testTempStudent.getDisabilityCertificateIssueAuthority()).isEqualTo(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY);
        assertThat(testTempStudent.getDisabilityCertificateIssueDate()).isEqualTo(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_DATE);
        assertThat(testTempStudent.getPercentagOfDisability()).isEqualTo(DEFAULT_PERCENTAG_OF_DISABILITY);
        assertThat(testTempStudent.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testTempStudent.getVaccinationDetails()).isEqualTo(DEFAULT_VACCINATION_DETAILS);
        assertThat(testTempStudent.getOtherMedicalDetails()).isEqualTo(DEFAULT_OTHER_MEDICAL_DETAILS);
        assertThat(testTempStudent.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTempStudent.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testTempStudent.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testTempStudent.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testTempStudent.getSectionId()).isEqualTo(DEFAULT_SECTION_ID);
        assertThat(testTempStudent.getBatchId()).isEqualTo(DEFAULT_BATCH_ID);
        assertThat(testTempStudent.getAcademicYearId()).isEqualTo(DEFAULT_ACADEMIC_YEAR_ID);

        // Validate the TempStudent in Elasticsearch
        verify(mockTempStudentSearchRepository, times(1)).save(testTempStudent);
    }

    @Test
    @Transactional
    public void createTempStudentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tempStudentRepository.findAll().size();

        // Create the TempStudent with an existing ID
        tempStudent.setId(1L);
        TempStudentDTO tempStudentDTO = tempStudentMapper.toDto(tempStudent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTempStudentMockMvc.perform(post("/api/temp-students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tempStudentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TempStudent in the database
        List<TempStudent> tempStudentList = tempStudentRepository.findAll();
        assertThat(tempStudentList).hasSize(databaseSizeBeforeCreate);

        // Validate the TempStudent in Elasticsearch
        verify(mockTempStudentSearchRepository, times(0)).save(tempStudent);
    }


    @Test
    @Transactional
    public void getAllTempStudents() throws Exception {
        // Initialize the database
        tempStudentRepository.saveAndFlush(tempStudent);

        // Get all the tempStudentList
        restTempStudentMockMvc.perform(get("/api/temp-students?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tempStudent.getId().intValue())))
            .andExpect(jsonPath("$.[*].stateMachineId").value(hasItem(DEFAULT_STATE_MACHINE_ID.toString())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentAadharNo").value(hasItem(DEFAULT_STUDENT_AADHAR_NO.toString())))
            .andExpect(jsonPath("$.[*].studentPanNo").value(hasItem(DEFAULT_STUDENT_PAN_NO.toString())))
            .andExpect(jsonPath("$.[*].studentSocialSecurityNo").value(hasItem(DEFAULT_STUDENT_SOCIAL_SECURITY_NO.toString())))
            .andExpect(jsonPath("$.[*].studentTaxReferenceNo").value(hasItem(DEFAULT_STUDENT_TAX_REFERENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].studentBplNo").value(hasItem(DEFAULT_STUDENT_BPL_NO.toString())))
            .andExpect(jsonPath("$.[*].studentDrivingLicenseNo").value(hasItem(DEFAULT_STUDENT_DRIVING_LICENSE_NO.toString())))
            .andExpect(jsonPath("$.[*].studentPassportNo").value(hasItem(DEFAULT_STUDENT_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].caste").value(hasItem(DEFAULT_CASTE.toString())))
            .andExpect(jsonPath("$.[*].subCaste").value(hasItem(DEFAULT_SUB_CASTE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].studentLocalAddress").value(hasItem(DEFAULT_STUDENT_LOCAL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].studentPermanentAddress").value(hasItem(DEFAULT_STUDENT_PERMANENT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE.toString())))
            .andExpect(jsonPath("$.[*].studentPrimaryCellNumber").value(hasItem(DEFAULT_STUDENT_PRIMARY_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].studentAlternateCellNumber").value(hasItem(DEFAULT_STUDENT_ALTERNATE_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].studentLandLinePhoneNumber").value(hasItem(DEFAULT_STUDENT_LAND_LINE_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].studentPrimaryEmailId").value(hasItem(DEFAULT_STUDENT_PRIMARY_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].studentAlternateEmailId").value(hasItem(DEFAULT_STUDENT_ALTERNATE_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].relationWithStudent").value(hasItem(DEFAULT_RELATION_WITH_STUDENT.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactCellNumber").value(hasItem(DEFAULT_EMERGENCY_CONTACT_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactLandLinePhoneNumber").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactEmailId").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].studentImagePath").value(hasItem(DEFAULT_STUDENT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO.toString())))
            .andExpect(jsonPath("$.[*].enrollmentNo").value(hasItem(DEFAULT_ENROLLMENT_NO.toString())))
            .andExpect(jsonPath("$.[*].rollNo").value(hasItem(DEFAULT_ROLL_NO.toString())))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fatherCellNumber").value(hasItem(DEFAULT_FATHER_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].fatherEmailId").value(hasItem(DEFAULT_FATHER_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].fatherOccupation").value(hasItem(DEFAULT_FATHER_OCCUPATION.toString())))
            .andExpect(jsonPath("$.[*].fatherOfficeEmailId").value(hasItem(DEFAULT_FATHER_OFFICE_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].fatherOfficeAddress").value(hasItem(DEFAULT_FATHER_OFFICE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].fatherOfficeCellNumber").value(hasItem(DEFAULT_FATHER_OFFICE_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].fatherOfficeLandLinePhoneNumber").value(hasItem(DEFAULT_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].fatherAadharNo").value(hasItem(DEFAULT_FATHER_AADHAR_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherPanNo").value(hasItem(DEFAULT_FATHER_PAN_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherSocialSecurityNo").value(hasItem(DEFAULT_FATHER_SOCIAL_SECURITY_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherTaxReferenceNo").value(hasItem(DEFAULT_FATHER_TAX_REFERENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherBplNo").value(hasItem(DEFAULT_FATHER_BPL_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherDrivingLicenseNo").value(hasItem(DEFAULT_FATHER_DRIVING_LICENSE_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherPassportNo").value(hasItem(DEFAULT_FATHER_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].fatherImagePath").value(hasItem(DEFAULT_FATHER_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].motherCellNumber").value(hasItem(DEFAULT_MOTHER_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].motherEmailId").value(hasItem(DEFAULT_MOTHER_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].motherOccupation").value(hasItem(DEFAULT_MOTHER_OCCUPATION.toString())))
            .andExpect(jsonPath("$.[*].motherOfficeEmailId").value(hasItem(DEFAULT_MOTHER_OFFICE_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].motherOfficeAddress").value(hasItem(DEFAULT_MOTHER_OFFICE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].motherOfficeCellNumber").value(hasItem(DEFAULT_MOTHER_OFFICE_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].motherOfficeLandLinePhoneNumber").value(hasItem(DEFAULT_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].motherAadharNo").value(hasItem(DEFAULT_MOTHER_AADHAR_NO.toString())))
            .andExpect(jsonPath("$.[*].motherPanNo").value(hasItem(DEFAULT_MOTHER_PAN_NO.toString())))
            .andExpect(jsonPath("$.[*].motherSocialSecurityNo").value(hasItem(DEFAULT_MOTHER_SOCIAL_SECURITY_NO.toString())))
            .andExpect(jsonPath("$.[*].motherTaxReferenceNo").value(hasItem(DEFAULT_MOTHER_TAX_REFERENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].motherBplNo").value(hasItem(DEFAULT_MOTHER_BPL_NO.toString())))
            .andExpect(jsonPath("$.[*].motherDrivingLicenseNo").value(hasItem(DEFAULT_MOTHER_DRIVING_LICENSE_NO.toString())))
            .andExpect(jsonPath("$.[*].motherPassportNo").value(hasItem(DEFAULT_MOTHER_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].motherImagePath").value(hasItem(DEFAULT_MOTHER_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].guardianName").value(hasItem(DEFAULT_GUARDIAN_NAME.toString())))
            .andExpect(jsonPath("$.[*].guardianMiddleName").value(hasItem(DEFAULT_GUARDIAN_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].guardianLastName").value(hasItem(DEFAULT_GUARDIAN_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].guardianAddress").value(hasItem(DEFAULT_GUARDIAN_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].guardianCellNumber").value(hasItem(DEFAULT_GUARDIAN_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].guardianLandLinePhoneNumber").value(hasItem(DEFAULT_GUARDIAN_LAND_LINE_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].guardianEmailId").value(hasItem(DEFAULT_GUARDIAN_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].guardianOccupation").value(hasItem(DEFAULT_GUARDIAN_OCCUPATION.toString())))
            .andExpect(jsonPath("$.[*].guardianOfficeEmailId").value(hasItem(DEFAULT_GUARDIAN_OFFICE_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].guardianOfficeAddress").value(hasItem(DEFAULT_GUARDIAN_OFFICE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].guardianOfficeCellNumber").value(hasItem(DEFAULT_GUARDIAN_OFFICE_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].guardianOfficeLandLinePhoneNumber").value(hasItem(DEFAULT_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].guardianImagePath").value(hasItem(DEFAULT_GUARDIAN_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].isGuardianSponsorAgency").value(hasItem(DEFAULT_IS_GUARDIAN_SPONSOR_AGENCY.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyName").value(hasItem(DEFAULT_SPONSOR_AGENCY_NAME.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyRegistrationNo").value(hasItem(DEFAULT_SPONSOR_AGENCY_REGISTRATION_NO.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAddress").value(hasItem(DEFAULT_SPONSOR_AGENCY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyCellNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyLandLineNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_LAND_LINE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyEmailId").value(hasItem(DEFAULT_SPONSOR_AGENCY_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeName").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_NAME.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeDesignation").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeCellNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeLandLineNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeEmailId").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeOfficeAddress").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].isPhysicallyChallenged").value(hasItem(DEFAULT_IS_PHYSICALLY_CHALLENGED.toString())))
            .andExpect(jsonPath("$.[*].detailsOfDisability").value(hasItem(DEFAULT_DETAILS_OF_DISABILITY.toString())))
            .andExpect(jsonPath("$.[*].disabilityCertificateNo").value(hasItem(DEFAULT_DISABILITY_CERTIFICATE_NO.toString())))
            .andExpect(jsonPath("$.[*].disabilityCertificateIssueAuthority").value(hasItem(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY.toString())))
            .andExpect(jsonPath("$.[*].disabilityCertificateIssueDate").value(hasItem(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].percentagOfDisability").value(hasItem(DEFAULT_PERCENTAG_OF_DISABILITY)))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].vaccinationDetails").value(hasItem(DEFAULT_VACCINATION_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].otherMedicalDetails").value(hasItem(DEFAULT_OTHER_MEDICAL_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].sectionId").value(hasItem(DEFAULT_SECTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].academicYearId").value(hasItem(DEFAULT_ACADEMIC_YEAR_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getTempStudent() throws Exception {
        // Initialize the database
        tempStudentRepository.saveAndFlush(tempStudent);

        // Get the tempStudent
        restTempStudentMockMvc.perform(get("/api/temp-students/{id}", tempStudent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tempStudent.getId().intValue()))
            .andExpect(jsonPath("$.stateMachineId").value(DEFAULT_STATE_MACHINE_ID.toString()))
            .andExpect(jsonPath("$.studentName").value(DEFAULT_STUDENT_NAME.toString()))
            .andExpect(jsonPath("$.studentMiddleName").value(DEFAULT_STUDENT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.studentLastName").value(DEFAULT_STUDENT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.fatherMiddleName").value(DEFAULT_FATHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.fatherLastName").value(DEFAULT_FATHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.motherName").value(DEFAULT_MOTHER_NAME.toString()))
            .andExpect(jsonPath("$.motherMiddleName").value(DEFAULT_MOTHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.motherLastName").value(DEFAULT_MOTHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.studentAadharNo").value(DEFAULT_STUDENT_AADHAR_NO.toString()))
            .andExpect(jsonPath("$.studentPanNo").value(DEFAULT_STUDENT_PAN_NO.toString()))
            .andExpect(jsonPath("$.studentSocialSecurityNo").value(DEFAULT_STUDENT_SOCIAL_SECURITY_NO.toString()))
            .andExpect(jsonPath("$.studentTaxReferenceNo").value(DEFAULT_STUDENT_TAX_REFERENCE_NO.toString()))
            .andExpect(jsonPath("$.studentBplNo").value(DEFAULT_STUDENT_BPL_NO.toString()))
            .andExpect(jsonPath("$.studentDrivingLicenseNo").value(DEFAULT_STUDENT_DRIVING_LICENSE_NO.toString()))
            .andExpect(jsonPath("$.studentPassportNo").value(DEFAULT_STUDENT_PASSPORT_NO.toString()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.placeOfBirth").value(DEFAULT_PLACE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.caste").value(DEFAULT_CASTE.toString()))
            .andExpect(jsonPath("$.subCaste").value(DEFAULT_SUB_CASTE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.studentLocalAddress").value(DEFAULT_STUDENT_LOCAL_ADDRESS.toString()))
            .andExpect(jsonPath("$.studentPermanentAddress").value(DEFAULT_STUDENT_PERMANENT_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.pinCode").value(DEFAULT_PIN_CODE.toString()))
            .andExpect(jsonPath("$.studentPrimaryCellNumber").value(DEFAULT_STUDENT_PRIMARY_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.studentAlternateCellNumber").value(DEFAULT_STUDENT_ALTERNATE_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.studentLandLinePhoneNumber").value(DEFAULT_STUDENT_LAND_LINE_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.studentPrimaryEmailId").value(DEFAULT_STUDENT_PRIMARY_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.studentAlternateEmailId").value(DEFAULT_STUDENT_ALTERNATE_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.relationWithStudent").value(DEFAULT_RELATION_WITH_STUDENT.toString()))
            .andExpect(jsonPath("$.emergencyContactName").value(DEFAULT_EMERGENCY_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactMiddleName").value(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactLastName").value(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactCellNumber").value(DEFAULT_EMERGENCY_CONTACT_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.emergencyContactLandLinePhoneNumber").value(DEFAULT_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.emergencyContactEmailId").value(DEFAULT_EMERGENCY_CONTACT_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.studentImagePath").value(DEFAULT_STUDENT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.admissionNo").value(DEFAULT_ADMISSION_NO.toString()))
            .andExpect(jsonPath("$.enrollmentNo").value(DEFAULT_ENROLLMENT_NO.toString()))
            .andExpect(jsonPath("$.rollNo").value(DEFAULT_ROLL_NO.toString()))
            .andExpect(jsonPath("$.studentType").value(DEFAULT_STUDENT_TYPE.toString()))
            .andExpect(jsonPath("$.fatherCellNumber").value(DEFAULT_FATHER_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.fatherEmailId").value(DEFAULT_FATHER_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.fatherOccupation").value(DEFAULT_FATHER_OCCUPATION.toString()))
            .andExpect(jsonPath("$.fatherOfficeEmailId").value(DEFAULT_FATHER_OFFICE_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.fatherOfficeAddress").value(DEFAULT_FATHER_OFFICE_ADDRESS.toString()))
            .andExpect(jsonPath("$.fatherOfficeCellNumber").value(DEFAULT_FATHER_OFFICE_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.fatherOfficeLandLinePhoneNumber").value(DEFAULT_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.fatherAadharNo").value(DEFAULT_FATHER_AADHAR_NO.toString()))
            .andExpect(jsonPath("$.fatherPanNo").value(DEFAULT_FATHER_PAN_NO.toString()))
            .andExpect(jsonPath("$.fatherSocialSecurityNo").value(DEFAULT_FATHER_SOCIAL_SECURITY_NO.toString()))
            .andExpect(jsonPath("$.fatherTaxReferenceNo").value(DEFAULT_FATHER_TAX_REFERENCE_NO.toString()))
            .andExpect(jsonPath("$.fatherBplNo").value(DEFAULT_FATHER_BPL_NO.toString()))
            .andExpect(jsonPath("$.fatherDrivingLicenseNo").value(DEFAULT_FATHER_DRIVING_LICENSE_NO.toString()))
            .andExpect(jsonPath("$.fatherPassportNo").value(DEFAULT_FATHER_PASSPORT_NO.toString()))
            .andExpect(jsonPath("$.fatherImagePath").value(DEFAULT_FATHER_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.motherCellNumber").value(DEFAULT_MOTHER_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.motherEmailId").value(DEFAULT_MOTHER_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.motherOccupation").value(DEFAULT_MOTHER_OCCUPATION.toString()))
            .andExpect(jsonPath("$.motherOfficeEmailId").value(DEFAULT_MOTHER_OFFICE_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.motherOfficeAddress").value(DEFAULT_MOTHER_OFFICE_ADDRESS.toString()))
            .andExpect(jsonPath("$.motherOfficeCellNumber").value(DEFAULT_MOTHER_OFFICE_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.motherOfficeLandLinePhoneNumber").value(DEFAULT_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.motherAadharNo").value(DEFAULT_MOTHER_AADHAR_NO.toString()))
            .andExpect(jsonPath("$.motherPanNo").value(DEFAULT_MOTHER_PAN_NO.toString()))
            .andExpect(jsonPath("$.motherSocialSecurityNo").value(DEFAULT_MOTHER_SOCIAL_SECURITY_NO.toString()))
            .andExpect(jsonPath("$.motherTaxReferenceNo").value(DEFAULT_MOTHER_TAX_REFERENCE_NO.toString()))
            .andExpect(jsonPath("$.motherBplNo").value(DEFAULT_MOTHER_BPL_NO.toString()))
            .andExpect(jsonPath("$.motherDrivingLicenseNo").value(DEFAULT_MOTHER_DRIVING_LICENSE_NO.toString()))
            .andExpect(jsonPath("$.motherPassportNo").value(DEFAULT_MOTHER_PASSPORT_NO.toString()))
            .andExpect(jsonPath("$.motherImagePath").value(DEFAULT_MOTHER_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.guardianName").value(DEFAULT_GUARDIAN_NAME.toString()))
            .andExpect(jsonPath("$.guardianMiddleName").value(DEFAULT_GUARDIAN_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.guardianLastName").value(DEFAULT_GUARDIAN_LAST_NAME.toString()))
            .andExpect(jsonPath("$.guardianAddress").value(DEFAULT_GUARDIAN_ADDRESS.toString()))
            .andExpect(jsonPath("$.guardianCellNumber").value(DEFAULT_GUARDIAN_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.guardianLandLinePhoneNumber").value(DEFAULT_GUARDIAN_LAND_LINE_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.guardianEmailId").value(DEFAULT_GUARDIAN_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.guardianOccupation").value(DEFAULT_GUARDIAN_OCCUPATION.toString()))
            .andExpect(jsonPath("$.guardianOfficeEmailId").value(DEFAULT_GUARDIAN_OFFICE_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.guardianOfficeAddress").value(DEFAULT_GUARDIAN_OFFICE_ADDRESS.toString()))
            .andExpect(jsonPath("$.guardianOfficeCellNumber").value(DEFAULT_GUARDIAN_OFFICE_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.guardianOfficeLandLinePhoneNumber").value(DEFAULT_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.guardianImagePath").value(DEFAULT_GUARDIAN_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.isGuardianSponsorAgency").value(DEFAULT_IS_GUARDIAN_SPONSOR_AGENCY.toString()))
            .andExpect(jsonPath("$.sponsorAgencyName").value(DEFAULT_SPONSOR_AGENCY_NAME.toString()))
            .andExpect(jsonPath("$.sponsorAgencyRegistrationNo").value(DEFAULT_SPONSOR_AGENCY_REGISTRATION_NO.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAddress").value(DEFAULT_SPONSOR_AGENCY_ADDRESS.toString()))
            .andExpect(jsonPath("$.sponsorAgencyCellNumber").value(DEFAULT_SPONSOR_AGENCY_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.sponsorAgencyLandLineNumber").value(DEFAULT_SPONSOR_AGENCY_LAND_LINE_NUMBER.toString()))
            .andExpect(jsonPath("$.sponsorAgencyEmailId").value(DEFAULT_SPONSOR_AGENCY_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAppointeeName").value(DEFAULT_SPONSOR_AGENCY_APPOINTEE_NAME.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAppointeeDesignation").value(DEFAULT_SPONSOR_AGENCY_APPOINTEE_DESIGNATION.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAppointeeCellNumber").value(DEFAULT_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAppointeeLandLineNumber").value(DEFAULT_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAppointeeEmailId").value(DEFAULT_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.sponsorAgencyAppointeeOfficeAddress").value(DEFAULT_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS.toString()))
            .andExpect(jsonPath("$.isPhysicallyChallenged").value(DEFAULT_IS_PHYSICALLY_CHALLENGED.toString()))
            .andExpect(jsonPath("$.detailsOfDisability").value(DEFAULT_DETAILS_OF_DISABILITY.toString()))
            .andExpect(jsonPath("$.disabilityCertificateNo").value(DEFAULT_DISABILITY_CERTIFICATE_NO.toString()))
            .andExpect(jsonPath("$.disabilityCertificateIssueAuthority").value(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY.toString()))
            .andExpect(jsonPath("$.disabilityCertificateIssueDate").value(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_DATE.toString()))
            .andExpect(jsonPath("$.percentagOfDisability").value(DEFAULT_PERCENTAG_OF_DISABILITY))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.vaccinationDetails").value(DEFAULT_VACCINATION_DETAILS.toString()))
            .andExpect(jsonPath("$.otherMedicalDetails").value(DEFAULT_OTHER_MEDICAL_DETAILS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.sectionId").value(DEFAULT_SECTION_ID.intValue()))
            .andExpect(jsonPath("$.batchId").value(DEFAULT_BATCH_ID.intValue()))
            .andExpect(jsonPath("$.academicYearId").value(DEFAULT_ACADEMIC_YEAR_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTempStudent() throws Exception {
        // Get the tempStudent
        restTempStudentMockMvc.perform(get("/api/temp-students/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTempStudent() throws Exception {
        // Initialize the database
        tempStudentRepository.saveAndFlush(tempStudent);

        int databaseSizeBeforeUpdate = tempStudentRepository.findAll().size();

        // Update the tempStudent
        TempStudent updatedTempStudent = tempStudentRepository.findById(tempStudent.getId()).get();
        // Disconnect from session so that the updates on updatedTempStudent are not directly saved in db
        em.detach(updatedTempStudent);
        updatedTempStudent
            .stateMachineId(UPDATED_STATE_MACHINE_ID)
            .studentName(UPDATED_STUDENT_NAME)
            .studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME)
            .studentLastName(UPDATED_STUDENT_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
            .studentAadharNo(UPDATED_STUDENT_AADHAR_NO)
            .studentPanNo(UPDATED_STUDENT_PAN_NO)
            .studentSocialSecurityNo(UPDATED_STUDENT_SOCIAL_SECURITY_NO)
            .studentTaxReferenceNo(UPDATED_STUDENT_TAX_REFERENCE_NO)
            .studentBplNo(UPDATED_STUDENT_BPL_NO)
            .studentDrivingLicenseNo(UPDATED_STUDENT_DRIVING_LICENSE_NO)
            .studentPassportNo(UPDATED_STUDENT_PASSPORT_NO)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .religion(UPDATED_RELIGION)
            .caste(UPDATED_CASTE)
            .subCaste(UPDATED_SUB_CASTE)
            .sex(UPDATED_SEX)
            .studentLocalAddress(UPDATED_STUDENT_LOCAL_ADDRESS)
            .studentPermanentAddress(UPDATED_STUDENT_PERMANENT_ADDRESS)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .pinCode(UPDATED_PIN_CODE)
            .studentPrimaryCellNumber(UPDATED_STUDENT_PRIMARY_CELL_NUMBER)
            .studentAlternateCellNumber(UPDATED_STUDENT_ALTERNATE_CELL_NUMBER)
            .studentLandLinePhoneNumber(UPDATED_STUDENT_LAND_LINE_PHONE_NUMBER)
            .studentPrimaryEmailId(UPDATED_STUDENT_PRIMARY_EMAIL_ID)
            .studentAlternateEmailId(UPDATED_STUDENT_ALTERNATE_EMAIL_ID)
            .relationWithStudent(UPDATED_RELATION_WITH_STUDENT)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(UPDATED_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactCellNumber(UPDATED_EMERGENCY_CONTACT_CELL_NUMBER)
            .emergencyContactLandLinePhoneNumber(UPDATED_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER)
            .emergencyContactEmailId(UPDATED_EMERGENCY_CONTACT_EMAIL_ID)
            .studentImagePath(UPDATED_STUDENT_IMAGE_PATH)
            .admissionNo(UPDATED_ADMISSION_NO)
            .enrollmentNo(UPDATED_ENROLLMENT_NO)
            .rollNo(UPDATED_ROLL_NO)
            .studentType(UPDATED_STUDENT_TYPE)
            .fatherCellNumber(UPDATED_FATHER_CELL_NUMBER)
            .fatherEmailId(UPDATED_FATHER_EMAIL_ID)
            .fatherOccupation(UPDATED_FATHER_OCCUPATION)
            .fatherOfficeEmailId(UPDATED_FATHER_OFFICE_EMAIL_ID)
            .fatherOfficeAddress(UPDATED_FATHER_OFFICE_ADDRESS)
            .fatherOfficeCellNumber(UPDATED_FATHER_OFFICE_CELL_NUMBER)
            .fatherOfficeLandLinePhoneNumber(UPDATED_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER)
            .fatherAadharNo(UPDATED_FATHER_AADHAR_NO)
            .fatherPanNo(UPDATED_FATHER_PAN_NO)
            .fatherSocialSecurityNo(UPDATED_FATHER_SOCIAL_SECURITY_NO)
            .fatherTaxReferenceNo(UPDATED_FATHER_TAX_REFERENCE_NO)
            .fatherBplNo(UPDATED_FATHER_BPL_NO)
            .fatherDrivingLicenseNo(UPDATED_FATHER_DRIVING_LICENSE_NO)
            .fatherPassportNo(UPDATED_FATHER_PASSPORT_NO)
            .fatherImagePath(UPDATED_FATHER_IMAGE_PATH)
            .motherCellNumber(UPDATED_MOTHER_CELL_NUMBER)
            .motherEmailId(UPDATED_MOTHER_EMAIL_ID)
            .motherOccupation(UPDATED_MOTHER_OCCUPATION)
            .motherOfficeEmailId(UPDATED_MOTHER_OFFICE_EMAIL_ID)
            .motherOfficeAddress(UPDATED_MOTHER_OFFICE_ADDRESS)
            .motherOfficeCellNumber(UPDATED_MOTHER_OFFICE_CELL_NUMBER)
            .motherOfficeLandLinePhoneNumber(UPDATED_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER)
            .motherAadharNo(UPDATED_MOTHER_AADHAR_NO)
            .motherPanNo(UPDATED_MOTHER_PAN_NO)
            .motherSocialSecurityNo(UPDATED_MOTHER_SOCIAL_SECURITY_NO)
            .motherTaxReferenceNo(UPDATED_MOTHER_TAX_REFERENCE_NO)
            .motherBplNo(UPDATED_MOTHER_BPL_NO)
            .motherDrivingLicenseNo(UPDATED_MOTHER_DRIVING_LICENSE_NO)
            .motherPassportNo(UPDATED_MOTHER_PASSPORT_NO)
            .motherImagePath(UPDATED_MOTHER_IMAGE_PATH)
            .guardianName(UPDATED_GUARDIAN_NAME)
            .guardianMiddleName(UPDATED_GUARDIAN_MIDDLE_NAME)
            .guardianLastName(UPDATED_GUARDIAN_LAST_NAME)
            .guardianAddress(UPDATED_GUARDIAN_ADDRESS)
            .guardianCellNumber(UPDATED_GUARDIAN_CELL_NUMBER)
            .guardianLandLinePhoneNumber(UPDATED_GUARDIAN_LAND_LINE_PHONE_NUMBER)
            .guardianEmailId(UPDATED_GUARDIAN_EMAIL_ID)
            .guardianOccupation(UPDATED_GUARDIAN_OCCUPATION)
            .guardianOfficeEmailId(UPDATED_GUARDIAN_OFFICE_EMAIL_ID)
            .guardianOfficeAddress(UPDATED_GUARDIAN_OFFICE_ADDRESS)
            .guardianOfficeCellNumber(UPDATED_GUARDIAN_OFFICE_CELL_NUMBER)
            .guardianOfficeLandLinePhoneNumber(UPDATED_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER)
            .guardianImagePath(UPDATED_GUARDIAN_IMAGE_PATH)
            .isGuardianSponsorAgency(UPDATED_IS_GUARDIAN_SPONSOR_AGENCY)
            .sponsorAgencyName(UPDATED_SPONSOR_AGENCY_NAME)
            .sponsorAgencyRegistrationNo(UPDATED_SPONSOR_AGENCY_REGISTRATION_NO)
            .sponsorAgencyAddress(UPDATED_SPONSOR_AGENCY_ADDRESS)
            .sponsorAgencyCellNumber(UPDATED_SPONSOR_AGENCY_CELL_NUMBER)
            .sponsorAgencyLandLineNumber(UPDATED_SPONSOR_AGENCY_LAND_LINE_NUMBER)
            .sponsorAgencyEmailId(UPDATED_SPONSOR_AGENCY_EMAIL_ID)
            .sponsorAgencyAppointeeName(UPDATED_SPONSOR_AGENCY_APPOINTEE_NAME)
            .sponsorAgencyAppointeeDesignation(UPDATED_SPONSOR_AGENCY_APPOINTEE_DESIGNATION)
            .sponsorAgencyAppointeeCellNumber(UPDATED_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER)
            .sponsorAgencyAppointeeLandLineNumber(UPDATED_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER)
            .sponsorAgencyAppointeeEmailId(UPDATED_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID)
            .sponsorAgencyAppointeeOfficeAddress(UPDATED_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS)
            .isPhysicallyChallenged(UPDATED_IS_PHYSICALLY_CHALLENGED)
            .detailsOfDisability(UPDATED_DETAILS_OF_DISABILITY)
            .disabilityCertificateNo(UPDATED_DISABILITY_CERTIFICATE_NO)
            .disabilityCertificateIssueAuthority(UPDATED_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY)
            .disabilityCertificateIssueDate(UPDATED_DISABILITY_CERTIFICATE_ISSUE_DATE)
            .percentagOfDisability(UPDATED_PERCENTAG_OF_DISABILITY)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .vaccinationDetails(UPDATED_VACCINATION_DETAILS)
            .otherMedicalDetails(UPDATED_OTHER_MEDICAL_DETAILS)
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .branchId(UPDATED_BRANCH_ID)
            .sectionId(UPDATED_SECTION_ID)
            .batchId(UPDATED_BATCH_ID)
            .academicYearId(UPDATED_ACADEMIC_YEAR_ID);
        TempStudentDTO tempStudentDTO = tempStudentMapper.toDto(updatedTempStudent);

        restTempStudentMockMvc.perform(put("/api/temp-students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tempStudentDTO)))
            .andExpect(status().isOk());

        // Validate the TempStudent in the database
        List<TempStudent> tempStudentList = tempStudentRepository.findAll();
        assertThat(tempStudentList).hasSize(databaseSizeBeforeUpdate);
        TempStudent testTempStudent = tempStudentList.get(tempStudentList.size() - 1);
        assertThat(testTempStudent.getStateMachineId()).isEqualTo(UPDATED_STATE_MACHINE_ID);
        assertThat(testTempStudent.getStudentName()).isEqualTo(UPDATED_STUDENT_NAME);
        assertThat(testTempStudent.getStudentMiddleName()).isEqualTo(UPDATED_STUDENT_MIDDLE_NAME);
        assertThat(testTempStudent.getStudentLastName()).isEqualTo(UPDATED_STUDENT_LAST_NAME);
        assertThat(testTempStudent.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testTempStudent.getFatherMiddleName()).isEqualTo(UPDATED_FATHER_MIDDLE_NAME);
        assertThat(testTempStudent.getFatherLastName()).isEqualTo(UPDATED_FATHER_LAST_NAME);
        assertThat(testTempStudent.getMotherName()).isEqualTo(UPDATED_MOTHER_NAME);
        assertThat(testTempStudent.getMotherMiddleName()).isEqualTo(UPDATED_MOTHER_MIDDLE_NAME);
        assertThat(testTempStudent.getMotherLastName()).isEqualTo(UPDATED_MOTHER_LAST_NAME);
        assertThat(testTempStudent.getStudentAadharNo()).isEqualTo(UPDATED_STUDENT_AADHAR_NO);
        assertThat(testTempStudent.getStudentPanNo()).isEqualTo(UPDATED_STUDENT_PAN_NO);
        assertThat(testTempStudent.getStudentSocialSecurityNo()).isEqualTo(UPDATED_STUDENT_SOCIAL_SECURITY_NO);
        assertThat(testTempStudent.getStudentTaxReferenceNo()).isEqualTo(UPDATED_STUDENT_TAX_REFERENCE_NO);
        assertThat(testTempStudent.getStudentBplNo()).isEqualTo(UPDATED_STUDENT_BPL_NO);
        assertThat(testTempStudent.getStudentDrivingLicenseNo()).isEqualTo(UPDATED_STUDENT_DRIVING_LICENSE_NO);
        assertThat(testTempStudent.getStudentPassportNo()).isEqualTo(UPDATED_STUDENT_PASSPORT_NO);
        assertThat(testTempStudent.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testTempStudent.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testTempStudent.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testTempStudent.getCaste()).isEqualTo(UPDATED_CASTE);
        assertThat(testTempStudent.getSubCaste()).isEqualTo(UPDATED_SUB_CASTE);
        assertThat(testTempStudent.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testTempStudent.getStudentLocalAddress()).isEqualTo(UPDATED_STUDENT_LOCAL_ADDRESS);
        assertThat(testTempStudent.getStudentPermanentAddress()).isEqualTo(UPDATED_STUDENT_PERMANENT_ADDRESS);
        assertThat(testTempStudent.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testTempStudent.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testTempStudent.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testTempStudent.getPinCode()).isEqualTo(UPDATED_PIN_CODE);
        assertThat(testTempStudent.getStudentPrimaryCellNumber()).isEqualTo(UPDATED_STUDENT_PRIMARY_CELL_NUMBER);
        assertThat(testTempStudent.getStudentAlternateCellNumber()).isEqualTo(UPDATED_STUDENT_ALTERNATE_CELL_NUMBER);
        assertThat(testTempStudent.getStudentLandLinePhoneNumber()).isEqualTo(UPDATED_STUDENT_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getStudentPrimaryEmailId()).isEqualTo(UPDATED_STUDENT_PRIMARY_EMAIL_ID);
        assertThat(testTempStudent.getStudentAlternateEmailId()).isEqualTo(UPDATED_STUDENT_ALTERNATE_EMAIL_ID);
        assertThat(testTempStudent.getRelationWithStudent()).isEqualTo(UPDATED_RELATION_WITH_STUDENT);
        assertThat(testTempStudent.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testTempStudent.getEmergencyContactMiddleName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testTempStudent.getEmergencyContactLastName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testTempStudent.getEmergencyContactCellNumber()).isEqualTo(UPDATED_EMERGENCY_CONTACT_CELL_NUMBER);
        assertThat(testTempStudent.getEmergencyContactLandLinePhoneNumber()).isEqualTo(UPDATED_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getEmergencyContactEmailId()).isEqualTo(UPDATED_EMERGENCY_CONTACT_EMAIL_ID);
        assertThat(testTempStudent.getStudentImagePath()).isEqualTo(UPDATED_STUDENT_IMAGE_PATH);
        assertThat(testTempStudent.getAdmissionNo()).isEqualTo(UPDATED_ADMISSION_NO);
        assertThat(testTempStudent.getEnrollmentNo()).isEqualTo(UPDATED_ENROLLMENT_NO);
        assertThat(testTempStudent.getRollNo()).isEqualTo(UPDATED_ROLL_NO);
        assertThat(testTempStudent.getStudentType()).isEqualTo(UPDATED_STUDENT_TYPE);
        assertThat(testTempStudent.getFatherCellNumber()).isEqualTo(UPDATED_FATHER_CELL_NUMBER);
        assertThat(testTempStudent.getFatherEmailId()).isEqualTo(UPDATED_FATHER_EMAIL_ID);
        assertThat(testTempStudent.getFatherOccupation()).isEqualTo(UPDATED_FATHER_OCCUPATION);
        assertThat(testTempStudent.getFatherOfficeEmailId()).isEqualTo(UPDATED_FATHER_OFFICE_EMAIL_ID);
        assertThat(testTempStudent.getFatherOfficeAddress()).isEqualTo(UPDATED_FATHER_OFFICE_ADDRESS);
        assertThat(testTempStudent.getFatherOfficeCellNumber()).isEqualTo(UPDATED_FATHER_OFFICE_CELL_NUMBER);
        assertThat(testTempStudent.getFatherOfficeLandLinePhoneNumber()).isEqualTo(UPDATED_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getFatherAadharNo()).isEqualTo(UPDATED_FATHER_AADHAR_NO);
        assertThat(testTempStudent.getFatherPanNo()).isEqualTo(UPDATED_FATHER_PAN_NO);
        assertThat(testTempStudent.getFatherSocialSecurityNo()).isEqualTo(UPDATED_FATHER_SOCIAL_SECURITY_NO);
        assertThat(testTempStudent.getFatherTaxReferenceNo()).isEqualTo(UPDATED_FATHER_TAX_REFERENCE_NO);
        assertThat(testTempStudent.getFatherBplNo()).isEqualTo(UPDATED_FATHER_BPL_NO);
        assertThat(testTempStudent.getFatherDrivingLicenseNo()).isEqualTo(UPDATED_FATHER_DRIVING_LICENSE_NO);
        assertThat(testTempStudent.getFatherPassportNo()).isEqualTo(UPDATED_FATHER_PASSPORT_NO);
        assertThat(testTempStudent.getFatherImagePath()).isEqualTo(UPDATED_FATHER_IMAGE_PATH);
        assertThat(testTempStudent.getMotherCellNumber()).isEqualTo(UPDATED_MOTHER_CELL_NUMBER);
        assertThat(testTempStudent.getMotherEmailId()).isEqualTo(UPDATED_MOTHER_EMAIL_ID);
        assertThat(testTempStudent.getMotherOccupation()).isEqualTo(UPDATED_MOTHER_OCCUPATION);
        assertThat(testTempStudent.getMotherOfficeEmailId()).isEqualTo(UPDATED_MOTHER_OFFICE_EMAIL_ID);
        assertThat(testTempStudent.getMotherOfficeAddress()).isEqualTo(UPDATED_MOTHER_OFFICE_ADDRESS);
        assertThat(testTempStudent.getMotherOfficeCellNumber()).isEqualTo(UPDATED_MOTHER_OFFICE_CELL_NUMBER);
        assertThat(testTempStudent.getMotherOfficeLandLinePhoneNumber()).isEqualTo(UPDATED_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getMotherAadharNo()).isEqualTo(UPDATED_MOTHER_AADHAR_NO);
        assertThat(testTempStudent.getMotherPanNo()).isEqualTo(UPDATED_MOTHER_PAN_NO);
        assertThat(testTempStudent.getMotherSocialSecurityNo()).isEqualTo(UPDATED_MOTHER_SOCIAL_SECURITY_NO);
        assertThat(testTempStudent.getMotherTaxReferenceNo()).isEqualTo(UPDATED_MOTHER_TAX_REFERENCE_NO);
        assertThat(testTempStudent.getMotherBplNo()).isEqualTo(UPDATED_MOTHER_BPL_NO);
        assertThat(testTempStudent.getMotherDrivingLicenseNo()).isEqualTo(UPDATED_MOTHER_DRIVING_LICENSE_NO);
        assertThat(testTempStudent.getMotherPassportNo()).isEqualTo(UPDATED_MOTHER_PASSPORT_NO);
        assertThat(testTempStudent.getMotherImagePath()).isEqualTo(UPDATED_MOTHER_IMAGE_PATH);
        assertThat(testTempStudent.getGuardianName()).isEqualTo(UPDATED_GUARDIAN_NAME);
        assertThat(testTempStudent.getGuardianMiddleName()).isEqualTo(UPDATED_GUARDIAN_MIDDLE_NAME);
        assertThat(testTempStudent.getGuardianLastName()).isEqualTo(UPDATED_GUARDIAN_LAST_NAME);
        assertThat(testTempStudent.getGuardianAddress()).isEqualTo(UPDATED_GUARDIAN_ADDRESS);
        assertThat(testTempStudent.getGuardianCellNumber()).isEqualTo(UPDATED_GUARDIAN_CELL_NUMBER);
        assertThat(testTempStudent.getGuardianLandLinePhoneNumber()).isEqualTo(UPDATED_GUARDIAN_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getGuardianEmailId()).isEqualTo(UPDATED_GUARDIAN_EMAIL_ID);
        assertThat(testTempStudent.getGuardianOccupation()).isEqualTo(UPDATED_GUARDIAN_OCCUPATION);
        assertThat(testTempStudent.getGuardianOfficeEmailId()).isEqualTo(UPDATED_GUARDIAN_OFFICE_EMAIL_ID);
        assertThat(testTempStudent.getGuardianOfficeAddress()).isEqualTo(UPDATED_GUARDIAN_OFFICE_ADDRESS);
        assertThat(testTempStudent.getGuardianOfficeCellNumber()).isEqualTo(UPDATED_GUARDIAN_OFFICE_CELL_NUMBER);
        assertThat(testTempStudent.getGuardianOfficeLandLinePhoneNumber()).isEqualTo(UPDATED_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER);
        assertThat(testTempStudent.getGuardianImagePath()).isEqualTo(UPDATED_GUARDIAN_IMAGE_PATH);
        assertThat(testTempStudent.getIsGuardianSponsorAgency()).isEqualTo(UPDATED_IS_GUARDIAN_SPONSOR_AGENCY);
        assertThat(testTempStudent.getSponsorAgencyName()).isEqualTo(UPDATED_SPONSOR_AGENCY_NAME);
        assertThat(testTempStudent.getSponsorAgencyRegistrationNo()).isEqualTo(UPDATED_SPONSOR_AGENCY_REGISTRATION_NO);
        assertThat(testTempStudent.getSponsorAgencyAddress()).isEqualTo(UPDATED_SPONSOR_AGENCY_ADDRESS);
        assertThat(testTempStudent.getSponsorAgencyCellNumber()).isEqualTo(UPDATED_SPONSOR_AGENCY_CELL_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyLandLineNumber()).isEqualTo(UPDATED_SPONSOR_AGENCY_LAND_LINE_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyEmailId()).isEqualTo(UPDATED_SPONSOR_AGENCY_EMAIL_ID);
        assertThat(testTempStudent.getSponsorAgencyAppointeeName()).isEqualTo(UPDATED_SPONSOR_AGENCY_APPOINTEE_NAME);
        assertThat(testTempStudent.getSponsorAgencyAppointeeDesignation()).isEqualTo(UPDATED_SPONSOR_AGENCY_APPOINTEE_DESIGNATION);
        assertThat(testTempStudent.getSponsorAgencyAppointeeCellNumber()).isEqualTo(UPDATED_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyAppointeeLandLineNumber()).isEqualTo(UPDATED_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER);
        assertThat(testTempStudent.getSponsorAgencyAppointeeEmailId()).isEqualTo(UPDATED_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID);
        assertThat(testTempStudent.getSponsorAgencyAppointeeOfficeAddress()).isEqualTo(UPDATED_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS);
        assertThat(testTempStudent.getIsPhysicallyChallenged()).isEqualTo(UPDATED_IS_PHYSICALLY_CHALLENGED);
        assertThat(testTempStudent.getDetailsOfDisability()).isEqualTo(UPDATED_DETAILS_OF_DISABILITY);
        assertThat(testTempStudent.getDisabilityCertificateNo()).isEqualTo(UPDATED_DISABILITY_CERTIFICATE_NO);
        assertThat(testTempStudent.getDisabilityCertificateIssueAuthority()).isEqualTo(UPDATED_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY);
        assertThat(testTempStudent.getDisabilityCertificateIssueDate()).isEqualTo(UPDATED_DISABILITY_CERTIFICATE_ISSUE_DATE);
        assertThat(testTempStudent.getPercentagOfDisability()).isEqualTo(UPDATED_PERCENTAG_OF_DISABILITY);
        assertThat(testTempStudent.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testTempStudent.getVaccinationDetails()).isEqualTo(UPDATED_VACCINATION_DETAILS);
        assertThat(testTempStudent.getOtherMedicalDetails()).isEqualTo(UPDATED_OTHER_MEDICAL_DETAILS);
        assertThat(testTempStudent.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTempStudent.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testTempStudent.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testTempStudent.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testTempStudent.getSectionId()).isEqualTo(UPDATED_SECTION_ID);
        assertThat(testTempStudent.getBatchId()).isEqualTo(UPDATED_BATCH_ID);
        assertThat(testTempStudent.getAcademicYearId()).isEqualTo(UPDATED_ACADEMIC_YEAR_ID);

        // Validate the TempStudent in Elasticsearch
        verify(mockTempStudentSearchRepository, times(1)).save(testTempStudent);
    }

    @Test
    @Transactional
    public void updateNonExistingTempStudent() throws Exception {
        int databaseSizeBeforeUpdate = tempStudentRepository.findAll().size();

        // Create the TempStudent
        TempStudentDTO tempStudentDTO = tempStudentMapper.toDto(tempStudent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTempStudentMockMvc.perform(put("/api/temp-students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tempStudentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TempStudent in the database
        List<TempStudent> tempStudentList = tempStudentRepository.findAll();
        assertThat(tempStudentList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TempStudent in Elasticsearch
        verify(mockTempStudentSearchRepository, times(0)).save(tempStudent);
    }

    @Test
    @Transactional
    public void deleteTempStudent() throws Exception {
        // Initialize the database
        tempStudentRepository.saveAndFlush(tempStudent);

        int databaseSizeBeforeDelete = tempStudentRepository.findAll().size();

        // Delete the tempStudent
        restTempStudentMockMvc.perform(delete("/api/temp-students/{id}", tempStudent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TempStudent> tempStudentList = tempStudentRepository.findAll();
        assertThat(tempStudentList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TempStudent in Elasticsearch
        verify(mockTempStudentSearchRepository, times(1)).deleteById(tempStudent.getId());
    }

    @Test
    @Transactional
    public void searchTempStudent() throws Exception {
        // Initialize the database
        tempStudentRepository.saveAndFlush(tempStudent);
//        when(mockTempStudentSearchRepository.search(queryStringQuery("id:" + tempStudent.getId())))
//            .thenReturn(Collections.singletonList(tempStudent));
        // Search the tempStudent
        restTempStudentMockMvc.perform(get("/api/_search/temp-students?query=id:" + tempStudent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tempStudent.getId().intValue())))
            .andExpect(jsonPath("$.[*].stateMachineId").value(hasItem(DEFAULT_STATE_MACHINE_ID)))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME)))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME)))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].studentAadharNo").value(hasItem(DEFAULT_STUDENT_AADHAR_NO)))
            .andExpect(jsonPath("$.[*].studentPanNo").value(hasItem(DEFAULT_STUDENT_PAN_NO)))
            .andExpect(jsonPath("$.[*].studentSocialSecurityNo").value(hasItem(DEFAULT_STUDENT_SOCIAL_SECURITY_NO)))
            .andExpect(jsonPath("$.[*].studentTaxReferenceNo").value(hasItem(DEFAULT_STUDENT_TAX_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].studentBplNo").value(hasItem(DEFAULT_STUDENT_BPL_NO)))
            .andExpect(jsonPath("$.[*].studentDrivingLicenseNo").value(hasItem(DEFAULT_STUDENT_DRIVING_LICENSE_NO)))
            .andExpect(jsonPath("$.[*].studentPassportNo").value(hasItem(DEFAULT_STUDENT_PASSPORT_NO)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].caste").value(hasItem(DEFAULT_CASTE)))
            .andExpect(jsonPath("$.[*].subCaste").value(hasItem(DEFAULT_SUB_CASTE)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].studentLocalAddress").value(hasItem(DEFAULT_STUDENT_LOCAL_ADDRESS)))
            .andExpect(jsonPath("$.[*].studentPermanentAddress").value(hasItem(DEFAULT_STUDENT_PERMANENT_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE)))
            .andExpect(jsonPath("$.[*].studentPrimaryCellNumber").value(hasItem(DEFAULT_STUDENT_PRIMARY_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].studentAlternateCellNumber").value(hasItem(DEFAULT_STUDENT_ALTERNATE_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].studentLandLinePhoneNumber").value(hasItem(DEFAULT_STUDENT_LAND_LINE_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].studentPrimaryEmailId").value(hasItem(DEFAULT_STUDENT_PRIMARY_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].studentAlternateEmailId").value(hasItem(DEFAULT_STUDENT_ALTERNATE_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].relationWithStudent").value(hasItem(DEFAULT_RELATION_WITH_STUDENT)))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactCellNumber").value(hasItem(DEFAULT_EMERGENCY_CONTACT_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].emergencyContactLandLinePhoneNumber").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAND_LINE_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].emergencyContactEmailId").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].studentImagePath").value(hasItem(DEFAULT_STUDENT_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO)))
            .andExpect(jsonPath("$.[*].enrollmentNo").value(hasItem(DEFAULT_ENROLLMENT_NO)))
            .andExpect(jsonPath("$.[*].rollNo").value(hasItem(DEFAULT_ROLL_NO)))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE)))
            .andExpect(jsonPath("$.[*].fatherCellNumber").value(hasItem(DEFAULT_FATHER_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].fatherEmailId").value(hasItem(DEFAULT_FATHER_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].fatherOccupation").value(hasItem(DEFAULT_FATHER_OCCUPATION)))
            .andExpect(jsonPath("$.[*].fatherOfficeEmailId").value(hasItem(DEFAULT_FATHER_OFFICE_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].fatherOfficeAddress").value(hasItem(DEFAULT_FATHER_OFFICE_ADDRESS)))
            .andExpect(jsonPath("$.[*].fatherOfficeCellNumber").value(hasItem(DEFAULT_FATHER_OFFICE_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].fatherOfficeLandLinePhoneNumber").value(hasItem(DEFAULT_FATHER_OFFICE_LAND_LINE_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].fatherAadharNo").value(hasItem(DEFAULT_FATHER_AADHAR_NO)))
            .andExpect(jsonPath("$.[*].fatherPanNo").value(hasItem(DEFAULT_FATHER_PAN_NO)))
            .andExpect(jsonPath("$.[*].fatherSocialSecurityNo").value(hasItem(DEFAULT_FATHER_SOCIAL_SECURITY_NO)))
            .andExpect(jsonPath("$.[*].fatherTaxReferenceNo").value(hasItem(DEFAULT_FATHER_TAX_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].fatherBplNo").value(hasItem(DEFAULT_FATHER_BPL_NO)))
            .andExpect(jsonPath("$.[*].fatherDrivingLicenseNo").value(hasItem(DEFAULT_FATHER_DRIVING_LICENSE_NO)))
            .andExpect(jsonPath("$.[*].fatherPassportNo").value(hasItem(DEFAULT_FATHER_PASSPORT_NO)))
            .andExpect(jsonPath("$.[*].fatherImagePath").value(hasItem(DEFAULT_FATHER_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].motherCellNumber").value(hasItem(DEFAULT_MOTHER_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].motherEmailId").value(hasItem(DEFAULT_MOTHER_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].motherOccupation").value(hasItem(DEFAULT_MOTHER_OCCUPATION)))
            .andExpect(jsonPath("$.[*].motherOfficeEmailId").value(hasItem(DEFAULT_MOTHER_OFFICE_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].motherOfficeAddress").value(hasItem(DEFAULT_MOTHER_OFFICE_ADDRESS)))
            .andExpect(jsonPath("$.[*].motherOfficeCellNumber").value(hasItem(DEFAULT_MOTHER_OFFICE_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].motherOfficeLandLinePhoneNumber").value(hasItem(DEFAULT_MOTHER_OFFICE_LAND_LINE_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].motherAadharNo").value(hasItem(DEFAULT_MOTHER_AADHAR_NO)))
            .andExpect(jsonPath("$.[*].motherPanNo").value(hasItem(DEFAULT_MOTHER_PAN_NO)))
            .andExpect(jsonPath("$.[*].motherSocialSecurityNo").value(hasItem(DEFAULT_MOTHER_SOCIAL_SECURITY_NO)))
            .andExpect(jsonPath("$.[*].motherTaxReferenceNo").value(hasItem(DEFAULT_MOTHER_TAX_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].motherBplNo").value(hasItem(DEFAULT_MOTHER_BPL_NO)))
            .andExpect(jsonPath("$.[*].motherDrivingLicenseNo").value(hasItem(DEFAULT_MOTHER_DRIVING_LICENSE_NO)))
            .andExpect(jsonPath("$.[*].motherPassportNo").value(hasItem(DEFAULT_MOTHER_PASSPORT_NO)))
            .andExpect(jsonPath("$.[*].motherImagePath").value(hasItem(DEFAULT_MOTHER_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].guardianName").value(hasItem(DEFAULT_GUARDIAN_NAME)))
            .andExpect(jsonPath("$.[*].guardianMiddleName").value(hasItem(DEFAULT_GUARDIAN_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].guardianLastName").value(hasItem(DEFAULT_GUARDIAN_LAST_NAME)))
            .andExpect(jsonPath("$.[*].guardianAddress").value(hasItem(DEFAULT_GUARDIAN_ADDRESS)))
            .andExpect(jsonPath("$.[*].guardianCellNumber").value(hasItem(DEFAULT_GUARDIAN_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].guardianLandLinePhoneNumber").value(hasItem(DEFAULT_GUARDIAN_LAND_LINE_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].guardianEmailId").value(hasItem(DEFAULT_GUARDIAN_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].guardianOccupation").value(hasItem(DEFAULT_GUARDIAN_OCCUPATION)))
            .andExpect(jsonPath("$.[*].guardianOfficeEmailId").value(hasItem(DEFAULT_GUARDIAN_OFFICE_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].guardianOfficeAddress").value(hasItem(DEFAULT_GUARDIAN_OFFICE_ADDRESS)))
            .andExpect(jsonPath("$.[*].guardianOfficeCellNumber").value(hasItem(DEFAULT_GUARDIAN_OFFICE_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].guardianOfficeLandLinePhoneNumber").value(hasItem(DEFAULT_GUARDIAN_OFFICE_LAND_LINE_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].guardianImagePath").value(hasItem(DEFAULT_GUARDIAN_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].isGuardianSponsorAgency").value(hasItem(DEFAULT_IS_GUARDIAN_SPONSOR_AGENCY)))
            .andExpect(jsonPath("$.[*].sponsorAgencyName").value(hasItem(DEFAULT_SPONSOR_AGENCY_NAME)))
            .andExpect(jsonPath("$.[*].sponsorAgencyRegistrationNo").value(hasItem(DEFAULT_SPONSOR_AGENCY_REGISTRATION_NO)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAddress").value(hasItem(DEFAULT_SPONSOR_AGENCY_ADDRESS)))
            .andExpect(jsonPath("$.[*].sponsorAgencyCellNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].sponsorAgencyLandLineNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_LAND_LINE_NUMBER)))
            .andExpect(jsonPath("$.[*].sponsorAgencyEmailId").value(hasItem(DEFAULT_SPONSOR_AGENCY_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeName").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_NAME)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeDesignation").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_DESIGNATION)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeCellNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeLandLineNumber").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_LAND_LINE_NUMBER)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeEmailId").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].sponsorAgencyAppointeeOfficeAddress").value(hasItem(DEFAULT_SPONSOR_AGENCY_APPOINTEE_OFFICE_ADDRESS)))
            .andExpect(jsonPath("$.[*].isPhysicallyChallenged").value(hasItem(DEFAULT_IS_PHYSICALLY_CHALLENGED)))
            .andExpect(jsonPath("$.[*].detailsOfDisability").value(hasItem(DEFAULT_DETAILS_OF_DISABILITY)))
            .andExpect(jsonPath("$.[*].disabilityCertificateNo").value(hasItem(DEFAULT_DISABILITY_CERTIFICATE_NO)))
            .andExpect(jsonPath("$.[*].disabilityCertificateIssueAuthority").value(hasItem(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_AUTHORITY)))
            .andExpect(jsonPath("$.[*].disabilityCertificateIssueDate").value(hasItem(DEFAULT_DISABILITY_CERTIFICATE_ISSUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].percentagOfDisability").value(hasItem(DEFAULT_PERCENTAG_OF_DISABILITY)))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].vaccinationDetails").value(hasItem(DEFAULT_VACCINATION_DETAILS)))
            .andExpect(jsonPath("$.[*].otherMedicalDetails").value(hasItem(DEFAULT_OTHER_MEDICAL_DETAILS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].sectionId").value(hasItem(DEFAULT_SECTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].academicYearId").value(hasItem(DEFAULT_ACADEMIC_YEAR_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TempStudent.class);
        TempStudent tempStudent1 = new TempStudent();
        tempStudent1.setId(1L);
        TempStudent tempStudent2 = new TempStudent();
        tempStudent2.setId(tempStudent1.getId());
        assertThat(tempStudent1).isEqualTo(tempStudent2);
        tempStudent2.setId(2L);
        assertThat(tempStudent1).isNotEqualTo(tempStudent2);
        tempStudent1.setId(null);
        assertThat(tempStudent1).isNotEqualTo(tempStudent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TempStudentDTO.class);
        TempStudentDTO tempStudentDTO1 = new TempStudentDTO();
        tempStudentDTO1.setId(1L);
        TempStudentDTO tempStudentDTO2 = new TempStudentDTO();
        assertThat(tempStudentDTO1).isNotEqualTo(tempStudentDTO2);
        tempStudentDTO2.setId(tempStudentDTO1.getId());
        assertThat(tempStudentDTO1).isEqualTo(tempStudentDTO2);
        tempStudentDTO2.setId(2L);
        assertThat(tempStudentDTO1).isNotEqualTo(tempStudentDTO2);
        tempStudentDTO1.setId(null);
        assertThat(tempStudentDTO1).isNotEqualTo(tempStudentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tempStudentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tempStudentMapper.fromId(null)).isNull();
    }
}
