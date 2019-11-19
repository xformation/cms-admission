package com.synectiks.admission.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AdmissionKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(AdmissionKafkaConsumer.class);
    private static final String TOPIC = "topic_admission";

    @KafkaListener(topics = "topic_admission", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
