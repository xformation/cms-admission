package com.synectiks.admission.service;

import com.synectiks.admission.service.dto.TempStudentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.admission.domain.TempStudent}.
 */
public interface TempStudentService {

    /**
     * Save a tempStudent.
     *
     * @param tempStudentDTO the entity to save.
     * @return the persisted entity.
     */
    TempStudentDTO save(TempStudentDTO tempStudentDTO);

    /**
     * Get all the tempStudents.
     *
     * @return the list of entities.
     */
    List<TempStudentDTO> findAll();


    /**
     * Get the "id" tempStudent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TempStudentDTO> findOne(Long id);

    /**
     * Delete the "id" tempStudent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the tempStudent corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<TempStudentDTO> search(String query);
}
