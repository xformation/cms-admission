package com.synectiks.admission.ems.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.synectiks.admission.domain.AdmissionEnquiry;
import com.synectiks.admission.repository.AdmissionEnquiryRepository;
import com.synectiks.admission.service.util.CommonUtil;
import com.synectiks.admission.web.rest.errors.BadRequestAlertException;
import com.synectiks.admission.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.synectiks.admission.business.service.CmsAdmissionEnquiryService;
import com.synectiks.admission.domain.vo.CmsAdmissionEnquiryVo;

import javax.validation.Valid;
import static graphql.servlet.GraphQLServlet.log;

@RestController
@RequestMapping("/api")
public class AdmissionEnquiryRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ENTITY_NAME = "admissionEnquiry";
	@Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;

    @PostMapping("/cmsadmissionenquiry-bulk-load")
    public List<CmsAdmissionEnquiryVo> bulkLoad(@RequestBody List<CmsAdmissionEnquiryVo> list) throws Exception {
        List<CmsAdmissionEnquiryVo> failedRecords = new ArrayList<>();
        for(CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo: list) {
            try {
                createAdmissionEnquiry(cmsAdmissionEnquiryVo);
            }catch(Exception e) {
                failedRecords.add(cmsAdmissionEnquiryVo);
                log.error("Exception. Saving of admissionEnquiry failed. ", e);
            }
        }

        return failedRecords;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-enquiry-by-filters")
    public List<CmsAdmissionEnquiryVo> getCmsAdmissionEnquiryListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of CmsAdmissionEnquiry based on filter criteria");
        Long branchId = Long.parseLong(dataMap.get("branchId"));
        Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
        String enquiryStatus = dataMap.get("enquiryStatus");
        List<CmsAdmissionEnquiryVo> list = this.cmsAdmissionEnquiryService.getAdmissionEnquiryList(branchId, academicYearId, enquiryStatus);
        return list;
    }



    @PostMapping("/cmsadmission-enquiry")
    public ResponseEntity<CmsAdmissionEnquiryVo> createAdmissionEnquiry(@Valid @RequestBody CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo) throws Exception {
        log.debug("REST request to save a admissionEnquiry : {}", cmsAdmissionEnquiryVo);
        if (cmsAdmissionEnquiryVo.getId() != null) {
            throw new BadRequestAlertException("A new admissionEnquiry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmissionEnquiry admissionEnquiry = CommonUtil.createCopyProperties(cmsAdmissionEnquiryVo, AdmissionEnquiry.class);
        AdmissionEnquiry result = admissionEnquiryRepository.save(admissionEnquiry);
        CmsAdmissionEnquiryVo vo = CommonUtil.createCopyProperties(admissionEnquiry, CmsAdmissionEnquiryVo.class);
        return ResponseEntity.created(new URI("/api/cmsadmission-enquiry/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }

    @PutMapping("/cmsadmission-enquiry")
    public ResponseEntity<CmsAdmissionEnquiryVo> updateAdmissionEnquiry(@Valid @RequestBody CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo) throws URISyntaxException {
        log.debug("REST request to update a admissionEnquiry : {}", cmsAdmissionEnquiryVo);
        if (cmsAdmissionEnquiryVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmissionEnquiry admissionEnquiry = CommonUtil.createCopyProperties(cmsAdmissionEnquiryVo, AdmissionEnquiry.class);
        AdmissionEnquiry result = admissionEnquiryRepository.save(admissionEnquiry);
        CmsAdmissionEnquiryVo vo = CommonUtil.createCopyProperties(admissionEnquiry, CmsAdmissionEnquiryVo.class);

        return ResponseEntity.created(new URI("/api/cmsadmission-enquiry/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmsadmission-enquiry")
    public List<CmsAdmissionEnquiryVo> getAllAdmissionEnquiry(@RequestParam Map<String, String> dataMap) {
        List<AdmissionEnquiry> list = null;
        List<CmsAdmissionEnquiryVo> ls = null;

        AdmissionEnquiry obj = new AdmissionEnquiry();
        boolean isFilter = false;
        if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
            obj.setId(Long.parseLong(dataMap.get("id")));
            isFilter = true;
        }
        if(!CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
//    		Branch branch = this.commonService.getBranchById(Long.parseLong(dataMap.get("branchId")));
            obj.setBranchId(Long.parseLong(dataMap.get("branchId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("departmentId"))) {
            obj.setDepartmentId(Long.parseLong(dataMap.get("departmentId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("batchId"))) {
            obj.setBatchId(Long.parseLong(dataMap.get("batchId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("stateId"))) {
            obj.setStateId(Long.parseLong(dataMap.get("stateId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("cityId"))) {
            obj.setCityId(Long.parseLong(dataMap.get("cityId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("academicYearId"))) {
            obj.setAcademicYearId(Long.parseLong(dataMap.get("academicYearId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("studentName"))) {
            isFilter = true;
            String name = dataMap.get("studentName");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setStudentName(token.nextToken());
                }
                cnt++;
            }
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
        if(isFilter) {
            list = this.admissionEnquiryRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.admissionEnquiryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(AdmissionEnquiry fc: list) {
            CmsAdmissionEnquiryVo vo = CommonUtil.createCopyProperties(fc, CmsAdmissionEnquiryVo.class);
            ls.add(vo);
        }

        return ls;
    }


}
