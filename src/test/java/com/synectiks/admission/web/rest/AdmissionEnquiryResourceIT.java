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
import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.repository.search.AdmissionEnquirySearchRepository;
import com.synectiks.admission.service.AdmissionEnquiryService;
import com.synectiks.admission.service.dto.AdmissionEnquiryDTO;
import com.synectiks.admission.service.mapper.AdmissionEnquiryMapper;
import com.synectiks.admission.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link AdmissionEnquiryResource} REST controller.
 */
@SpringBootTest(classes = AdmissionApp.class)
public class AdmissionEnquiryResourceIT {

    private static final String DEFAULT_STUDENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CELL_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CELL_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_LAND_LINE_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_LAND_LINE_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_HIGHEST_QUALIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_HIGHEST_QUALIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_ENQUIRY = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_ENQUIRY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ENQUIRY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENQUIRY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_SEMESTER_ID = 1L;
    private static final Long UPDATED_SEMESTER_ID = 2L;

    private static final Long DEFAULT_BATCH_ID = 1L;
    private static final Long UPDATED_BATCH_ID = 2L;

    private static final Long DEFAULT_STATE_ID = 1L;
    private static final Long UPDATED_STATE_ID = 2L;

    private static final Long DEFAULT_CITY_ID = 1L;
    private static final Long UPDATED_CITY_ID = 2L;

    private static final Long DEFAULT_ACADEMIC_YEAR_ID = 1L;
    private static final Long UPDATED_ACADEMIC_YEAR_ID = 2L;

    private static final String DEFAULT_ENQUIRY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ENQUIRY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;

    @Autowired
    private AdmissionEnquiryMapper admissionEnquiryMapper;

    @Autowired
    private AdmissionEnquiryService admissionEnquiryService;

    /**
     * This repository is mocked in the com.synectiks.admission.repository.search test package.
     *
     * @see com.synectiks.admission.repository.search.AdmissionEnquirySearchRepositoryMockConfiguration
     */
    @Autowired
    private AdmissionEnquirySearchRepository mockAdmissionEnquirySearchRepository;

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

    private MockMvc restAdmissionEnquiryMockMvc;

    private AdmissionEnquiry admissionEnquiry;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdmissionEnquiryResource admissionEnquiryResource = new AdmissionEnquiryResource(admissionEnquiryService);
        this.restAdmissionEnquiryMockMvc = MockMvcBuilders.standaloneSetup(admissionEnquiryResource)
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
    public static AdmissionEnquiry createEntity(EntityManager em) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry()
            .studentName(DEFAULT_STUDENT_NAME)
            .studentMiddleName(DEFAULT_STUDENT_MIDDLE_NAME)
            .studentLastName(DEFAULT_STUDENT_LAST_NAME)
            .cellPhoneNo(DEFAULT_CELL_PHONE_NO)
            .landLinePhoneNo(DEFAULT_LAND_LINE_PHONE_NO)
            .emailId(DEFAULT_EMAIL_ID)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .gender(DEFAULT_GENDER)
            .highestQualification(DEFAULT_HIGHEST_QUALIFICATION)
            .modeOfEnquiry(DEFAULT_MODE_OF_ENQUIRY)
            .enquiryDate(DEFAULT_ENQUIRY_DATE)
            .comments(DEFAULT_COMMENTS)
            .branchId(DEFAULT_BRANCH_ID)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .courseId(DEFAULT_COURSE_ID)
            .semesterId(DEFAULT_SEMESTER_ID)
            .batchId(DEFAULT_BATCH_ID)
            .stateId(DEFAULT_STATE_ID)
            .cityId(DEFAULT_CITY_ID)
            .academicYearId(DEFAULT_ACADEMIC_YEAR_ID)
            .enquiryStatus(DEFAULT_ENQUIRY_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return admissionEnquiry;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmissionEnquiry createUpdatedEntity(EntityManager em) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry()
            .studentName(UPDATED_STUDENT_NAME)
            .studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME)
            .studentLastName(UPDATED_STUDENT_LAST_NAME)
            .cellPhoneNo(UPDATED_CELL_PHONE_NO)
            .landLinePhoneNo(UPDATED_LAND_LINE_PHONE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .gender(UPDATED_GENDER)
            .highestQualification(UPDATED_HIGHEST_QUALIFICATION)
            .modeOfEnquiry(UPDATED_MODE_OF_ENQUIRY)
            .enquiryDate(UPDATED_ENQUIRY_DATE)
            .comments(UPDATED_COMMENTS)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .semesterId(UPDATED_SEMESTER_ID)
            .batchId(UPDATED_BATCH_ID)
            .stateId(UPDATED_STATE_ID)
            .cityId(UPDATED_CITY_ID)
            .academicYearId(UPDATED_ACADEMIC_YEAR_ID)
            .enquiryStatus(UPDATED_ENQUIRY_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return admissionEnquiry;
    }

    @BeforeEach
    public void initTest() {
        admissionEnquiry = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdmissionEnquiry() throws Exception {
        int databaseSizeBeforeCreate = admissionEnquiryRepository.findAll().size();

        // Create the AdmissionEnquiry
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);
        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isCreated());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeCreate + 1);
        AdmissionEnquiry testAdmissionEnquiry = admissionEnquiryList.get(admissionEnquiryList.size() - 1);
        assertThat(testAdmissionEnquiry.getStudentName()).isEqualTo(DEFAULT_STUDENT_NAME);
        assertThat(testAdmissionEnquiry.getStudentMiddleName()).isEqualTo(DEFAULT_STUDENT_MIDDLE_NAME);
        assertThat(testAdmissionEnquiry.getStudentLastName()).isEqualTo(DEFAULT_STUDENT_LAST_NAME);
        assertThat(testAdmissionEnquiry.getCellPhoneNo()).isEqualTo(DEFAULT_CELL_PHONE_NO);
        assertThat(testAdmissionEnquiry.getLandLinePhoneNo()).isEqualTo(DEFAULT_LAND_LINE_PHONE_NO);
        assertThat(testAdmissionEnquiry.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testAdmissionEnquiry.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testAdmissionEnquiry.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAdmissionEnquiry.getHighestQualification()).isEqualTo(DEFAULT_HIGHEST_QUALIFICATION);
        assertThat(testAdmissionEnquiry.getModeOfEnquiry()).isEqualTo(DEFAULT_MODE_OF_ENQUIRY);
        assertThat(testAdmissionEnquiry.getEnquiryDate()).isEqualTo(DEFAULT_ENQUIRY_DATE);
        assertThat(testAdmissionEnquiry.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testAdmissionEnquiry.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testAdmissionEnquiry.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testAdmissionEnquiry.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testAdmissionEnquiry.getSemesterId()).isEqualTo(DEFAULT_SEMESTER_ID);
        assertThat(testAdmissionEnquiry.getBatchId()).isEqualTo(DEFAULT_BATCH_ID);
        assertThat(testAdmissionEnquiry.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testAdmissionEnquiry.getCityId()).isEqualTo(DEFAULT_CITY_ID);
        assertThat(testAdmissionEnquiry.getAcademicYearId()).isEqualTo(DEFAULT_ACADEMIC_YEAR_ID);
        assertThat(testAdmissionEnquiry.getEnquiryStatus()).isEqualTo(DEFAULT_ENQUIRY_STATUS);
        assertThat(testAdmissionEnquiry.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAdmissionEnquiry.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testAdmissionEnquiry.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testAdmissionEnquiry.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(1)).save(testAdmissionEnquiry);
    }

    @Test
    @Transactional
    public void createAdmissionEnquiryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = admissionEnquiryRepository.findAll().size();

        // Create the AdmissionEnquiry with an existing ID
        admissionEnquiry.setId(1L);
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(0)).save(admissionEnquiry);
    }


    @Test
    @Transactional
    public void getAllAdmissionEnquiries() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        // Get all the admissionEnquiryList
        restAdmissionEnquiryMockMvc.perform(get("/api/admission-enquiries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionEnquiry.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].cellPhoneNo").value(hasItem(DEFAULT_CELL_PHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].landLinePhoneNo").value(hasItem(DEFAULT_LAND_LINE_PHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].highestQualification").value(hasItem(DEFAULT_HIGHEST_QUALIFICATION.toString())))
            .andExpect(jsonPath("$.[*].modeOfEnquiry").value(hasItem(DEFAULT_MODE_OF_ENQUIRY.toString())))
            .andExpect(jsonPath("$.[*].enquiryDate").value(hasItem(DEFAULT_ENQUIRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].semesterId").value(hasItem(DEFAULT_SEMESTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].cityId").value(hasItem(DEFAULT_CITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].academicYearId").value(hasItem(DEFAULT_ACADEMIC_YEAR_ID.intValue())))
            .andExpect(jsonPath("$.[*].enquiryStatus").value(hasItem(DEFAULT_ENQUIRY_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        // Get the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(get("/api/admission-enquiries/{id}", admissionEnquiry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(admissionEnquiry.getId().intValue()))
            .andExpect(jsonPath("$.studentName").value(DEFAULT_STUDENT_NAME.toString()))
            .andExpect(jsonPath("$.studentMiddleName").value(DEFAULT_STUDENT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.studentLastName").value(DEFAULT_STUDENT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.cellPhoneNo").value(DEFAULT_CELL_PHONE_NO.toString()))
            .andExpect(jsonPath("$.landLinePhoneNo").value(DEFAULT_LAND_LINE_PHONE_NO.toString()))
            .andExpect(jsonPath("$.emailId").value(DEFAULT_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.highestQualification").value(DEFAULT_HIGHEST_QUALIFICATION.toString()))
            .andExpect(jsonPath("$.modeOfEnquiry").value(DEFAULT_MODE_OF_ENQUIRY.toString()))
            .andExpect(jsonPath("$.enquiryDate").value(DEFAULT_ENQUIRY_DATE.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.semesterId").value(DEFAULT_SEMESTER_ID.intValue()))
            .andExpect(jsonPath("$.batchId").value(DEFAULT_BATCH_ID.intValue()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID.intValue()))
            .andExpect(jsonPath("$.cityId").value(DEFAULT_CITY_ID.intValue()))
            .andExpect(jsonPath("$.academicYearId").value(DEFAULT_ACADEMIC_YEAR_ID.intValue()))
            .andExpect(jsonPath("$.enquiryStatus").value(DEFAULT_ENQUIRY_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdmissionEnquiry() throws Exception {
        // Get the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(get("/api/admission-enquiries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        int databaseSizeBeforeUpdate = admissionEnquiryRepository.findAll().size();

        // Update the admissionEnquiry
        AdmissionEnquiry updatedAdmissionEnquiry = admissionEnquiryRepository.findById(admissionEnquiry.getId()).get();
        // Disconnect from session so that the updates on updatedAdmissionEnquiry are not directly saved in db
        em.detach(updatedAdmissionEnquiry);
        updatedAdmissionEnquiry
            .studentName(UPDATED_STUDENT_NAME)
            .studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME)
            .studentLastName(UPDATED_STUDENT_LAST_NAME)
            .cellPhoneNo(UPDATED_CELL_PHONE_NO)
            .landLinePhoneNo(UPDATED_LAND_LINE_PHONE_NO)
            .emailId(UPDATED_EMAIL_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .gender(UPDATED_GENDER)
            .highestQualification(UPDATED_HIGHEST_QUALIFICATION)
            .modeOfEnquiry(UPDATED_MODE_OF_ENQUIRY)
            .enquiryDate(UPDATED_ENQUIRY_DATE)
            .comments(UPDATED_COMMENTS)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .semesterId(UPDATED_SEMESTER_ID)
            .batchId(UPDATED_BATCH_ID)
            .stateId(UPDATED_STATE_ID)
            .cityId(UPDATED_CITY_ID)
            .academicYearId(UPDATED_ACADEMIC_YEAR_ID)
            .enquiryStatus(UPDATED_ENQUIRY_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(updatedAdmissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(put("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isOk());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeUpdate);
        AdmissionEnquiry testAdmissionEnquiry = admissionEnquiryList.get(admissionEnquiryList.size() - 1);
        assertThat(testAdmissionEnquiry.getStudentName()).isEqualTo(UPDATED_STUDENT_NAME);
        assertThat(testAdmissionEnquiry.getStudentMiddleName()).isEqualTo(UPDATED_STUDENT_MIDDLE_NAME);
        assertThat(testAdmissionEnquiry.getStudentLastName()).isEqualTo(UPDATED_STUDENT_LAST_NAME);
        assertThat(testAdmissionEnquiry.getCellPhoneNo()).isEqualTo(UPDATED_CELL_PHONE_NO);
        assertThat(testAdmissionEnquiry.getLandLinePhoneNo()).isEqualTo(UPDATED_LAND_LINE_PHONE_NO);
        assertThat(testAdmissionEnquiry.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testAdmissionEnquiry.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testAdmissionEnquiry.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAdmissionEnquiry.getHighestQualification()).isEqualTo(UPDATED_HIGHEST_QUALIFICATION);
        assertThat(testAdmissionEnquiry.getModeOfEnquiry()).isEqualTo(UPDATED_MODE_OF_ENQUIRY);
        assertThat(testAdmissionEnquiry.getEnquiryDate()).isEqualTo(UPDATED_ENQUIRY_DATE);
        assertThat(testAdmissionEnquiry.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testAdmissionEnquiry.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testAdmissionEnquiry.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testAdmissionEnquiry.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testAdmissionEnquiry.getSemesterId()).isEqualTo(UPDATED_SEMESTER_ID);
        assertThat(testAdmissionEnquiry.getBatchId()).isEqualTo(UPDATED_BATCH_ID);
        assertThat(testAdmissionEnquiry.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testAdmissionEnquiry.getCityId()).isEqualTo(UPDATED_CITY_ID);
        assertThat(testAdmissionEnquiry.getAcademicYearId()).isEqualTo(UPDATED_ACADEMIC_YEAR_ID);
        assertThat(testAdmissionEnquiry.getEnquiryStatus()).isEqualTo(UPDATED_ENQUIRY_STATUS);
        assertThat(testAdmissionEnquiry.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAdmissionEnquiry.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testAdmissionEnquiry.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testAdmissionEnquiry.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(1)).save(testAdmissionEnquiry);
    }

    @Test
    @Transactional
    public void updateNonExistingAdmissionEnquiry() throws Exception {
        int databaseSizeBeforeUpdate = admissionEnquiryRepository.findAll().size();

        // Create the AdmissionEnquiry
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmissionEnquiryMockMvc.perform(put("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(0)).save(admissionEnquiry);
    }

    @Test
    @Transactional
    public void deleteAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        int databaseSizeBeforeDelete = admissionEnquiryRepository.findAll().size();

        // Delete the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(delete("/api/admission-enquiries/{id}", admissionEnquiry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(1)).deleteById(admissionEnquiry.getId());
    }

    

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionEnquiry.class);
        AdmissionEnquiry admissionEnquiry1 = new AdmissionEnquiry();
        admissionEnquiry1.setId(1L);
        AdmissionEnquiry admissionEnquiry2 = new AdmissionEnquiry();
        admissionEnquiry2.setId(admissionEnquiry1.getId());
        assertThat(admissionEnquiry1).isEqualTo(admissionEnquiry2);
        admissionEnquiry2.setId(2L);
        assertThat(admissionEnquiry1).isNotEqualTo(admissionEnquiry2);
        admissionEnquiry1.setId(null);
        assertThat(admissionEnquiry1).isNotEqualTo(admissionEnquiry2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionEnquiryDTO.class);
        AdmissionEnquiryDTO admissionEnquiryDTO1 = new AdmissionEnquiryDTO();
        admissionEnquiryDTO1.setId(1L);
        AdmissionEnquiryDTO admissionEnquiryDTO2 = new AdmissionEnquiryDTO();
        assertThat(admissionEnquiryDTO1).isNotEqualTo(admissionEnquiryDTO2);
        admissionEnquiryDTO2.setId(admissionEnquiryDTO1.getId());
        assertThat(admissionEnquiryDTO1).isEqualTo(admissionEnquiryDTO2);
        admissionEnquiryDTO2.setId(2L);
        assertThat(admissionEnquiryDTO1).isNotEqualTo(admissionEnquiryDTO2);
        admissionEnquiryDTO1.setId(null);
        assertThat(admissionEnquiryDTO1).isNotEqualTo(admissionEnquiryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(admissionEnquiryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(admissionEnquiryMapper.fromId(null)).isNull();
    }
}
