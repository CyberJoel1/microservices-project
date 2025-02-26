package com.microservices.userservice.application.services;

import com.microservices.domains.dto.MovementPSTRq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    @Mock
    private KafkaTemplate<String, MovementPSTRq> kafkaTemplate;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMovementEvent() {
        MovementPSTRq movementPSTRq = new MovementPSTRq();
        movementPSTRq.setAccountNumber("12345");

        eventService.processMovement(movementPSTRq);

        ArgumentCaptor<MovementPSTRq> captor = ArgumentCaptor.forClass(MovementPSTRq.class);
        verify(kafkaTemplate, times(1)).send(eq("transactionTopic"), captor.capture());
        assertEquals("12345", captor.getValue().getAccountNumber());
    }

    @Test
    void testProcessMovement() {
        MovementPSTRq movementPSTRq = new MovementPSTRq();
        movementPSTRq.setAccountNumber("12345");

        String result = eventService.processMovement(movementPSTRq);

        assertEquals("Event send", result);
        verify(kafkaTemplate, times(1)).send(eq("transactionTopic"), eq(movementPSTRq));
    }
}