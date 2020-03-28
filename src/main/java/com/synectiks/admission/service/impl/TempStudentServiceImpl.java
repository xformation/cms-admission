package com.synectiks.admission.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.admission.domain.TempStudent;
import com.synectiks.admission.repository.TempStudentRepository;
import com.synectiks.admission.repository.search.TempStudentSearchRepository;
import com.synectiks.admission.service.TempStudentService;
import com.synectiks.admission.service.dto.TempStudentDTO;
import com.synectiks.admission.service.mapper.TempStudentMapper;

/**
 * Service Implementation for managing {@link TempStudent}.
 */
@Service
@Transactional
public class TempStudentServiceImpl implements TempStudentService {

    private final Logger log = LoggerFactory.getLogger(TempStudentServiceImpl.class);

    private final TempStudentRepository tempStudentRepository;

    private final TempStudentMapper tempStudentMapper;

    private final TempStudentSearchRepository tempStudentSearchRepository;

    public TempStudentServiceImpl(TempStudentRepository tempStudentRepository, TempStudentMapper tempStudentMapper, TempStudentSearchRepository tempStudentSearchRepository) {
        this.tempStudentRepository = tempStudentRepository;
        this.tempStudentMapper = tempStudentMapper;
        this.tempStudentSearchRepository = tempStudentSearchRepository;
    }

    /**
     * Save a tempStudent.
     *
     * @param tempStudentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TempStudentDTO save(TempStudentDTO tempStudentDTO) {
        log.debug("Request to save TempStudent : {}", tempStudentDTO);
        TempStudent tempStudent = tempStudentMapper.toEntity(tempStudentDTO);
        tempStudent = tempStudentRepository.save(tempStudent);
        TempStudentDTO result = tempStudentMapper.toDto(tempStudent);
        tempStudentSearchRepository.save(tempStudent);
        return result;
    }

    /**
     * Get all the tempStudents.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TempStudentDTO> findAll() {
        log.debug("Request to get all TempStudents");
        return tempStudentRepository.findAll().stream()
            .map(tempStudentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tempStudent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TempStudentDTO> findOne(Long id) {
        log.debug("Request to get TempStudent : {}", id);
        return tempStudentRepository.findById(id)
            .map(tempStudentMapper::toDto);
    }

    /**
     * Delete the tempStudent by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TempStudent : {}", id);
        tempStudentRepository.deleteById(id);
        tempStudentSearchRepository.deleteById(id);
    }

    /**
     * Search for the tempStudent corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TempStudentDTO> search(String query) {
        log.debug("Request to search TempStudents for query {}", query);
        return null;
    }
}
