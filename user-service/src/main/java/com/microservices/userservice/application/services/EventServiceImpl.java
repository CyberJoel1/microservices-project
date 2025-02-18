package com.microservices.userservice.application.services;

import com.microservices.domains.dto.MovementPSTRq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventServiceImpl {

    private final KafkaTemplate<String, MovementPSTRq> kafkaTemplate;

    @Autowired
    public EventServiceImpl(KafkaTemplate<String, MovementPSTRq> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "transactionTopic";

    public void sendMovementEvent(MovementPSTRq movementPSTRq) {
        kafkaTemplate.send(TOPIC, movementPSTRq);
        log.info("Movimiento enviado: {}", movementPSTRq.getAccountNumber());
    }
}
