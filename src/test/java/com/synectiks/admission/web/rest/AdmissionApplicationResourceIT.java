package com.synectiks.admission.web.rest;

import static com.synectiks.admission.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
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
import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
import com.synectiks.admission.repository.search.AdmissionApplicationSearchRepository;
import com.synectiks.admission.service.AdmissionApplicationService;
import com.synectiks.admission.service.dto.AdmissionApplicationDTO;
import com.synectiks.admission.service.mapper.AdmissionApplicationMapper;
import com.synectiks.admission.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link AdmissionApplicationResource} REST controller.
 */
@SpringBootTest(classes = AdmissionApp.class)
public class AdmissionApplicationResourceIT {

    private static final String DEFAULT_SOURCE_OF_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_OF_APPLICATION = "BBBBBBBBBB";

    private static final Long DEFAULT_STUDENT_ID = 1L;
    private static final Long UPDATED_STUDENT_ID = 2L;

    private static final LocalDate DEFAULT_APPLICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_COMPLETION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPLETION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ADMISSION_NO = 1L;
    private static final Long UPDATED_ADMISSION_NO = 2L;

    private static final LocalDate DEFAULT_ADMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    @Autowired
    private AdmissionApplicationMapper admissionApplicationMapper;

    @Autowired
    private AdmissionApplicationService admissionApplicationService;

    /**
     * This repository is mocked in the com.synectiks.admission.repository.search test package.
     *
     * @see com.synectiks.admission.repository.search.AdmissionApplicationSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdmissionApplicationSearchRepository mockAdmissionApplicationSearchRepository;

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

    private MockMvc restAdmissionApplicationMockMvc;

    private AdmissionApplication admissionApplication;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdmissionApplicationResource admissionApplicationResource = new AdmissionApplicationResource(admissionApplicationService);
        this.restAdmissionApplicationMockMvc = MockMvcBuilders.standaloneSetup(admissionApplicationResource)
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
    public static AdmissionApplication createEntity(EntityManager em) {
        AdmissionApplication admissionApplication = new AdmissionApplication()
            .sourceOfApplication(DEFAULT_SOURCE_OF_APPLICATION)
            .studentId(DEFAULT_STUDENT_ID)
            .applicationDate(DEFAULT_APPLICATION_DATE)
            .completionDate(DEFAULT_COMPLETION_DATE)
            .admissionNo(DEFAULT_ADMISSION_NO)
            .admissionDate(DEFAULT_ADMISSION_DATE)
            .comments(DEFAULT_COMMENTS)
            .applicationStatus(DEFAULT_APPLICATION_STATUS)
            .branchId(DEFAULT_BRANCH_ID)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return admissionApplication;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmissionApplication createUpdatedEntity(EntityManager em) {
        AdmissionApplication admissionApplication = new AdmissionApplication()
            .sourceOfApplication(UPDATED_SOURCE_OF_APPLICATION)
            .studentId(UPDATED_STUDENT_ID)
            .applicationDate(UPDATED_APPLICATION_DATE)
            .completionDate(UPDATED_COMPLETION_DATE)
            .admissionNo(UPDATED_ADMISSION_NO)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .comments(UPDATED_COMMENTS)
            .applicationStatus(UPDATED_APPLICATION_STATUS)
            .branchId(UPDATED_BRANCH_ID)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return admissionApplication;
    }

    @BeforeEach
    public void initTest() {
        admissionApplication = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdmissionApplication() throws Exception {
        int databaseSizeBeforeCreate = admissionApplicationRepository.findAll().size();

        // Create the AdmissionApplication
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);
        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isCreated());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeCreate + 1);
        AdmissionApplication testAdmissionApplication = admissionApplicationList.get(admissionApplicationList.size() - 1);
        assertThat(testAdmissionApplication.getSourceOfApplication()).isEqualTo(DEFAULT_SOURCE_OF_APPLICATION);
        assertThat(testAdmissionApplication.getStudentId()).isEqualTo(DEFAULT_STUDENT_ID);
        assertThat(testAdmissionApplication.getApplicationDate()).isEqualTo(DEFAULT_APPLICATION_DATE);
        assertThat(testAdmissionApplication.getCompletionDate()).isEqualTo(DEFAULT_COMPLETION_DATE);
        assertThat(testAdmissionApplication.getAdmissionNo()).isEqualTo(DEFAULT_ADMISSION_NO);
        assertThat(testAdmissionApplication.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
        assertThat(testAdmissionApplication.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testAdmissionApplication.getApplicationStatus()).isEqualTo(DEFAULT_APPLICATION_STATUS);
        assertThat(testAdmissionApplication.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testAdmissionApplication.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAdmissionApplication.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testAdmissionApplication.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testAdmissionApplication.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(1)).save(testAdmissionApplication);
    }

    @Test
    @Transactional
    public void createAdmissionApplicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = admissionApplicationRepository.findAll().size();

        // Create the AdmissionApplication with an existing ID
        admissionApplication.setId(1L);
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(0)).save(admissionApplication);
    }


    @Test
    @Transactional
    public void getAllAdmissionApplications() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        // Get all the admissionApplicationList
        restAdmissionApplicationMockMvc.perform(get("/api/admission-applications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].sourceOfApplication").value(hasItem(DEFAULT_SOURCE_OF_APPLICATION.toString())))
            .andExpect(jsonPath("$.[*].studentId").value(hasItem(DEFAULT_STUDENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].applicationDate").value(hasItem(DEFAULT_APPLICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].completionDate").value(hasItem(DEFAULT_COMPLETION_DATE.toString())))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO.intValue())))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].applicationStatus").value(hasItem(DEFAULT_APPLICATION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        // Get the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/admission-applications/{id}", admissionApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(admissionApplication.getId().intValue()))
            .andExpect(jsonPath("$.sourceOfApplication").value(DEFAULT_SOURCE_OF_APPLICATION.toString()))
            .andExpect(jsonPath("$.studentId").value(DEFAULT_STUDENT_ID.intValue()))
            .andExpect(jsonPath("$.applicationDate").value(DEFAULT_APPLICATION_DATE.toString()))
            .andExpect(jsonPath("$.completionDate").value(DEFAULT_COMPLETION_DATE.toString()))
            .andExpect(jsonPath("$.admissionNo").value(DEFAULT_ADMISSION_NO.intValue()))
            .andExpect(jsonPath("$.admissionDate").value(DEFAULT_ADMISSION_DATE.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.applicationStatus").value(DEFAULT_APPLICATION_STATUS.toString()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdmissionApplication() throws Exception {
        // Get the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/admission-applications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        int databaseSizeBeforeUpdate = admissionApplicationRepository.findAll().size();

        // Update the admissionApplication
        AdmissionApplication updatedAdmissionApplication = admissionApplicationRepository.findById(admissionApplication.getId()).get();
        // Disconnect from session so that the updates on updatedAdmissionApplication are not directly saved in db
        em.detach(updatedAdmissionApplication);
        updatedAdmissionApplication
            .sourceOfApplication(UPDATED_SOURCE_OF_APPLICATION)
            .studentId(UPDATED_STUDENT_ID)
            .applicationDate(UPDATED_APPLICATION_DATE)
            .completionDate(UPDATED_COMPLETION_DATE)
            .admissionNo(UPDATED_ADMISSION_NO)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .comments(UPDATED_COMMENTS)
            .applicationStatus(UPDATED_APPLICATION_STATUS)
            .branchId(UPDATED_BRANCH_ID)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(updatedAdmissionApplication);

        restAdmissionApplicationMockMvc.perform(put("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isOk());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeUpdate);
        AdmissionApplication testAdmissionApplication = admissionApplicationList.get(admissionApplicationList.size() - 1);
        assertThat(testAdmissionApplication.getSourceOfApplication()).isEqualTo(UPDATED_SOURCE_OF_APPLICATION);
        assertThat(testAdmissionApplication.getStudentId()).isEqualTo(UPDATED_STUDENT_ID);
        assertThat(testAdmissionApplication.getApplicationDate()).isEqualTo(UPDATED_APPLICATION_DATE);
        assertThat(testAdmissionApplication.getCompletionDate()).isEqualTo(UPDATED_COMPLETION_DATE);
        assertThat(testAdmissionApplication.getAdmissionNo()).isEqualTo(UPDATED_ADMISSION_NO);
        assertThat(testAdmissionApplication.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testAdmissionApplication.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testAdmissionApplication.getApplicationStatus()).isEqualTo(UPDATED_APPLICATION_STATUS);
        assertThat(testAdmissionApplication.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testAdmissionApplication.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAdmissionApplication.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testAdmissionApplication.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testAdmissionApplication.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(1)).save(testAdmissionApplication);
    }

    @Test
    @Transactional
    public void updateNonExistingAdmissionApplication() throws Exception {
        int databaseSizeBeforeUpdate = admissionApplicationRepository.findAll().size();

        // Create the AdmissionApplication
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmissionApplicationMockMvc.perform(put("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(0)).save(admissionApplication);
    }

    @Test
    @Transactional
    public void deleteAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        int databaseSizeBeforeDelete = admissionApplicationRepository.findAll().size();

        // Delete the admissionApplication
        restAdmissionApplicationMockMvc.perform(delete("/api/admission-applications/{id}", admissionApplication.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(1)).deleteById(admissionApplication.getId());
    }

    @Test
    @Transactional
    public void searchAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);
        when(mockAdmissionApplicationSearchRepository.search(queryStringQuery("id:" + admissionApplication.getId())))
            .thenReturn(Collections.singletonList(admissionApplication));
        // Search the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/_search/admission-applications?query=id:" + admissionApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].sourceOfApplication").value(hasItem(DEFAULT_SOURCE_OF_APPLICATION)))
            .andExpect(jsonPath("$.[*].studentId").value(hasItem(DEFAULT_STUDENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].applicationDate").value(hasItem(DEFAULT_APPLICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].completionDate").value(hasItem(DEFAULT_COMPLETION_DATE.toString())))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO.intValue())))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].applicationStatus").value(hasItem(DEFAULT_APPLICATION_STATUS)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionApplication.class);
        AdmissionApplication admissionApplication1 = new AdmissionApplication();
        admissionApplication1.setId(1L);
        AdmissionApplication admissionApplication2 = new AdmissionApplication();
        admissionApplication2.setId(admissionApplication1.getId());
        assertThat(admissionApplication1).isEqualTo(admissionApplication2);
        admissionApplication2.setId(2L);
        assertThat(admissionApplication1).isNotEqualTo(admissionApplication2);
        admissionApplication1.setId(null);
        assertThat(admissionApplication1).isNotEqualTo(admissionApplication2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionApplicationDTO.class);
        AdmissionApplicationDTO admissionApplicationDTO1 = new AdmissionApplicationDTO();
        admissionApplicationDTO1.setId(1L);
        AdmissionApplicationDTO admissionApplicationDTO2 = new AdmissionApplicationDTO();
        assertThat(admissionApplicationDTO1).isNotEqualTo(admissionApplicationDTO2);
        admissionApplicationDTO2.setId(admissionApplicationDTO1.getId());
        assertThat(admissionApplicationDTO1).isEqualTo(admissionApplicationDTO2);
        admissionApplicationDTO2.setId(2L);
        assertThat(admissionApplicationDTO1).isNotEqualTo(admissionApplicationDTO2);
        admissionApplicationDTO1.setId(null);
        assertThat(admissionApplicationDTO1).isNotEqualTo(admissionApplicationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(admissionApplicationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(admissionApplicationMapper.fromId(null)).isNull();
    }
}
