package com.synectiks.admission.web.rest;

import com.synectiks.admission.service.AdmissionKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admission-kafka")
public class AdmissionKafkaResource {

    private final Logger log = LoggerFactory.getLogger(AdmissionKafkaResource.class);

    private AdmissionKafkaProducer kafkaProducer;

    public AdmissionKafkaResource(AdmissionKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
