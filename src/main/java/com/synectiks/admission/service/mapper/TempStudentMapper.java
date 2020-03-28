package com.synectiks.admission.service.mapper;

import com.synectiks.admission.domain.*;
import com.synectiks.admission.service.dto.TempStudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TempStudent} and its DTO {@link TempStudentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TempStudentMapper extends EntityMapper<TempStudentDTO, TempStudent> {



    default TempStudent fromId(Long id) {
        if (id == null) {
            return null;
        }
        TempStudent tempStudent = new TempStudent();
        tempStudent.setId(id);
        return tempStudent;
    }
}
