package com.synectiks.admission.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.admission.service.TempStudentService;
import com.synectiks.admission.service.dto.TempStudentDTO;
import com.synectiks.admission.web.rest.errors.BadRequestAlertException;
import com.synectiks.admission.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.admission.domain.TempStudent}.
 */
@RestController
@RequestMapping("/api")
public class TempStudentResource {

    private final Logger log = LoggerFactory.getLogger(TempStudentResource.class);

    private static final String ENTITY_NAME = "tempStudent";

    private final TempStudentService tempStudentService;

    public TempStudentResource(TempStudentService tempStudentService) {
        this.tempStudentService = tempStudentService;
    }

    /**
     * {@code POST  /temp-students} : Create a new tempStudent.
     *
     * @param tempStudentDTO the tempStudentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tempStudentDTO, or with status {@code 400 (Bad Request)} if the tempStudent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/temp-students")
    public ResponseEntity<TempStudentDTO> createTempStudent(@Valid @RequestBody TempStudentDTO tempStudentDTO) throws URISyntaxException {
        log.debug("REST request to save TempStudent : {}", tempStudentDTO);
        if (tempStudentDTO.getId() != null) {
            throw new BadRequestAlertException("A new tempStudent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TempStudentDTO result = tempStudentService.save(tempStudentDTO);
        return ResponseEntity.created(new URI("/api/temp-students/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /temp-students} : Updates an existing tempStudent.
     *
     * @param tempStudentDTO the tempStudentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tempStudentDTO,
     * or with status {@code 400 (Bad Request)} if the tempStudentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tempStudentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/temp-students")
    public ResponseEntity<TempStudentDTO> updateTempStudent(@Valid @RequestBody TempStudentDTO tempStudentDTO) throws URISyntaxException {
        log.debug("REST request to update TempStudent : {}", tempStudentDTO);
        if (tempStudentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TempStudentDTO result = tempStudentService.save(tempStudentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tempStudentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /temp-students} : get all the tempStudents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tempStudents in body.
     */
    @GetMapping("/temp-students")
    public List<TempStudentDTO> getAllTempStudents() {
        log.debug("REST request to get all TempStudents");
        return tempStudentService.findAll();
    }

    /**
     * {@code GET  /temp-students/:id} : get the "id" tempStudent.
     *
     * @param id the id of the tempStudentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tempStudentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/temp-students/{id}")
    public ResponseEntity<TempStudentDTO> getTempStudent(@PathVariable Long id) {
        log.debug("REST request to get TempStudent : {}", id);
        Optional<TempStudentDTO> tempStudentDTO = tempStudentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tempStudentDTO);
    }

    /**
     * {@code DELETE  /temp-students/:id} : delete the "id" tempStudent.
     *
     * @param id the id of the tempStudentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/temp-students/{id}")
    public ResponseEntity<Void> deleteTempStudent(@PathVariable Long id) {
        log.debug("REST request to delete TempStudent : {}", id);
        tempStudentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/temp-students?query=:query} : search for the tempStudent corresponding
     * to the query.
     *
     * @param query the query of the tempStudent search.
     * @return the result of the search.
     */
    @GetMapping("/_search/temp-students")
    public List<TempStudentDTO> searchTempStudents(@RequestParam String query) {
        log.debug("REST request to search TempStudents for query {}", query);
        return tempStudentService.search(query);
    }

}
