package com.synectiks.admission.graphql.types.TempStudent;

import com.synectiks.admission.domain.vo.CmsTempStudentVo;

public class TempStudentPayload {
    private final CmsTempStudentVo cmsTempStudentVo;

    public TempStudentPayload(CmsTempStudentVo cmsTempStudentVo){
        this.cmsTempStudentVo = cmsTempStudentVo;
    }

	public CmsTempStudentVo getCmsTempStudentVo() {
		return cmsTempStudentVo;
	}

    
}
