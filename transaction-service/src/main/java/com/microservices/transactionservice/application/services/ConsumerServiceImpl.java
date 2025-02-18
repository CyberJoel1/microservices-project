package com.microservices.transactionservice.application.services;

import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.transactionservice.domain.ports.in.MovementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl {

    private static final String TOPIC = "transactionTopic";

    private final MovementService movementService;

    @Autowired
    public ConsumerServiceImpl(MovementService movementService) {
        this.movementService = movementService;
    }

    @KafkaListener(topics = TOPIC, groupId = "transactionId")
    public void listen(MovementPSTRq movementPSTRq) {
        log.info("[ConsumerServiceImpl][listen]");
        log.info("event listened : {}", movementPSTRq);
        log.info(" MovementPSTRs : {}", movementService.registerMovement(movementPSTRq));
    }

}
