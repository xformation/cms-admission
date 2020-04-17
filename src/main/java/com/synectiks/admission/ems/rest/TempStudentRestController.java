package com.synectiks.admission.ems.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.admission.business.service.CmsTempStudentService;
import com.synectiks.admission.domain.vo.CmsTempStudentVo;

@RestController
@RequestMapping("/api")
public class TempStudentRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CmsTempStudentService cmsTempStudentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmstemp-student-by-filters")
    public List<CmsTempStudentVo> getCmsTempStudentListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of CmsTempStudent based on filter criteria");
        Long branchId = Long.parseLong(dataMap.get("branchId"));
        Long academicYearId = Long.parseLong(dataMap.get("academicYearId"));
        List<CmsTempStudentVo> list = this.cmsTempStudentService.getTempStudentList(branchId, academicYearId, null);
        return list;
    }
	
}
