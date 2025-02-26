package com.microservices.userservice.application.services;

import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.userservice.domain.ports.in.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("payment-event")
@Slf4j
public class EventServiceImpl implements PaymentService<String> {

    private final KafkaTemplate<String, MovementPSTRq> kafkaTemplate;

    @Autowired
    public EventServiceImpl(KafkaTemplate<String, MovementPSTRq> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "transactionTopic";

    private void sendMovementEvent(MovementPSTRq movementPSTRq) {
        kafkaTemplate.send(TOPIC, movementPSTRq);
        log.info("Movimiento enviado: {}", movementPSTRq.getAccountNumber());
    }

    @Override
    public String processMovement(MovementPSTRq movementRequest) {
        this.sendMovementEvent(movementRequest);
        return "Event send";
    }
}
