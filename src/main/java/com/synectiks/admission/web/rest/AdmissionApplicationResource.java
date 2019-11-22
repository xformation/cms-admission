package com.synectiks.admission.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import com.synectiks.admission.service.AdmissionApplicationService;
import com.synectiks.admission.service.dto.AdmissionApplicationDTO;
import com.synectiks.admission.web.rest.errors.BadRequestAlertException;
import com.synectiks.admission.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.admission.domain.AdmissionApplication}.
 */
@RestController
@RequestMapping("/api")
public class AdmissionApplicationResource {

    private final Logger log = LoggerFactory.getLogger(AdmissionApplicationResource.class);

    private static final String ENTITY_NAME = "admissionApplication";

    private String applicationName;

    private final AdmissionApplicationService admissionApplicationService;

    public AdmissionApplicationResource(AdmissionApplicationService admissionApplicationService) {
        this.admissionApplicationService = admissionApplicationService;
    }

    /**
     * {@code POST  /admission-applications} : Create a new admissionApplication.
     *
     * @param admissionApplicationDTO the admissionApplicationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admissionApplicationDTO, or with status {@code 400 (Bad Request)} if the admissionApplication has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admission-applications")
    public ResponseEntity<AdmissionApplicationDTO> createAdmissionApplication(@RequestBody AdmissionApplicationDTO admissionApplicationDTO) throws URISyntaxException {
        log.debug("REST request to save AdmissionApplication : {}", admissionApplicationDTO);
        if (admissionApplicationDTO.getId() != null) {
            throw new BadRequestAlertException("A new admissionApplication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmissionApplicationDTO result = admissionApplicationService.save(admissionApplicationDTO);
        return ResponseEntity.created(new URI("/api/admission-applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admission-applications} : Updates an existing admissionApplication.
     *
     * @param admissionApplicationDTO the admissionApplicationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admissionApplicationDTO,
     * or with status {@code 400 (Bad Request)} if the admissionApplicationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admissionApplicationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admission-applications")
    public ResponseEntity<AdmissionApplicationDTO> updateAdmissionApplication(@RequestBody AdmissionApplicationDTO admissionApplicationDTO) throws URISyntaxException {
        log.debug("REST request to update AdmissionApplication : {}", admissionApplicationDTO);
        if (admissionApplicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmissionApplicationDTO result = admissionApplicationService.save(admissionApplicationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, admissionApplicationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admission-applications} : get all the admissionApplications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admissionApplications in body.
     */
    @GetMapping("/admission-applications")
    public List<AdmissionApplicationDTO> getAllAdmissionApplications() {
        log.debug("REST request to get all AdmissionApplications");
        return admissionApplicationService.findAll();
    }

    /**
     * {@code GET  /admission-applications/:id} : get the "id" admissionApplication.
     *
     * @param id the id of the admissionApplicationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admissionApplicationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admission-applications/{id}")
    public ResponseEntity<AdmissionApplicationDTO> getAdmissionApplication(@PathVariable Long id) {
        log.debug("REST request to get AdmissionApplication : {}", id);
        Optional<AdmissionApplicationDTO> admissionApplicationDTO = admissionApplicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admissionApplicationDTO);
    }

    /**
     * {@code DELETE  /admission-applications/:id} : delete the "id" admissionApplication.
     *
     * @param id the id of the admissionApplicationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admission-applications/{id}")
    public ResponseEntity<Void> deleteAdmissionApplication(@PathVariable Long id) {
        log.debug("REST request to delete AdmissionApplication : {}", id);
        admissionApplicationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/admission-applications?query=:query} : search for the admissionApplication corresponding
     * to the query.
     *
     * @param query the query of the admissionApplication search.
     * @return the result of the search.
     */
    @GetMapping("/_search/admission-applications")
    public List<AdmissionApplicationDTO> searchAdmissionApplications(@RequestParam String query) {
        log.debug("REST request to search AdmissionApplications for query {}", query);
        return admissionApplicationService.search(query);
    }

}
