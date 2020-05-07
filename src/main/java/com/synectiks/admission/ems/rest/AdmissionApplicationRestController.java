package com.synectiks.admission.ems.rest;

import com.synectiks.admission.business.service.CmsAdmissionApplicationService;
import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.domain.vo.CmsAdmissionApplicationVo;
import com.synectiks.admission.repository.AdmissionApplicationRepository;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static graphql.servlet.GraphQLServlet.log;

@RestController
@RequestMapping("/api")
public class AdmissionApplicationRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ENTITY_NAME = "admissionApplication";

	@Autowired
	private CmsAdmissionApplicationService cmsAdmissionApplicationService;

    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    @PostMapping("/cmsadmissionapplication-bulk-load")
    public List<CmsAdmissionApplicationVo> bulkLoad(@RequestBody List<CmsAdmissionApplicationVo> list) throws Exception {
        List<CmsAdmissionApplicationVo> failedRecords = new ArrayList<>();
        for(CmsAdmissionApplicationVo cmsAdmissionApplicationVo: list) {
            try {
                createAdmissionApplication(cmsAdmissionApplicationVo);
            }catch(Exception e) {
                failedRecords.add(cmsAdmissionApplicationVo);
                log.error("Exception. Saving of admissionApplication failed. ", e);
            }
        }

        return failedRecords;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-application-by-filters")
    public List<AdmissionApplication> getCmsAdmissionApplicationListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of CmsAdmissionApplication based on filter criteria");
        Long branchId = Long.parseLong(dataMap.get("branchId"));
        Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
        List<AdmissionApplication> list = this.cmsAdmissionApplicationService.getAdmissionApplicationList(branchId, academicYearId);
        return list;
    }



    @PostMapping("/cmsadmission-application")
    public ResponseEntity<CmsAdmissionApplicationVo> createAdmissionApplication(@Valid @RequestBody CmsAdmissionApplicationVo cmsAdmissionApplicationVo) throws Exception {
        log.debug("REST request to save a admissionApplication : {}", cmsAdmissionApplicationVo);
        if (cmsAdmissionApplicationVo.getId() != null) {
            throw new BadRequestAlertException("A new admissionApplication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmissionApplication admissionApplication = CommonUtil.createCopyProperties(cmsAdmissionApplicationVo, AdmissionApplication.class);
        AdmissionApplication result = admissionApplicationRepository.save(admissionApplication);
        CmsAdmissionApplicationVo vo = CommonUtil.createCopyProperties(admissionApplication, CmsAdmissionApplicationVo.class);
        return ResponseEntity.created(new URI("/api/cmsadmission-application/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }

    @PutMapping("/cmsadmission-application")
    public ResponseEntity<CmsAdmissionApplicationVo> updateAdmissionApplication(@Valid @RequestBody CmsAdmissionApplicationVo cmsAdmissionApplicationVo) throws URISyntaxException {
        log.debug("REST request to update a admissionApplication : {}", cmsAdmissionApplicationVo);
        if (cmsAdmissionApplicationVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmissionApplication admissionApplication = CommonUtil.createCopyProperties(cmsAdmissionApplicationVo, AdmissionApplication.class);
        AdmissionApplication result = admissionApplicationRepository.save(admissionApplication);
        CmsAdmissionApplicationVo vo = CommonUtil.createCopyProperties(admissionApplication, CmsAdmissionApplicationVo.class);

        return ResponseEntity.created(new URI("/api/cmsadmission-application/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmsadmission-application")
    public List<CmsAdmissionApplicationVo> getAllAdmissionApplication(@RequestParam Map<String, String> dataMap) {
        List<AdmissionApplication> list = null;
        List<CmsAdmissionApplicationVo> ls = null;

        AdmissionApplication obj = new AdmissionApplication();
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

        if(!CommonUtil.isNullOrEmpty(dataMap.get("academicYearId"))) {
            obj.setAcademicYearId(Long.parseLong(dataMap.get("academicYearId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("sourceOfApplication"))) {
            isFilter = true;
            String name = dataMap.get("sourceOfApplication");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setSourceOfApplication(token.nextToken());
                }
                cnt++;
            }
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
        if(isFilter) {
            list = this.admissionApplicationRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.admissionApplicationRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(AdmissionApplication fc: list) {
            CmsAdmissionApplicationVo vo = CommonUtil.createCopyProperties(fc, CmsAdmissionApplicationVo.class);
            ls.add(vo);
        }

        return ls;
    }
}
