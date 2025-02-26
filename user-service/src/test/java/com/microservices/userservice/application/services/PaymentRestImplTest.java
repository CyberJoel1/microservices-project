package com.microservices.userservice.application.services;

import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import com.microservices.userservice.domain.ports.out.MovementPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PaymentRestImplTest {

    @Mock
    private MovementPort movementPort;

    @InjectMocks
    private PaymentRestImpl paymentRestImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessMovement() {
        MovementPSTRq movementRequest = new MovementPSTRq();
        MovementPSTRs expectedResponse = new MovementPSTRs();
        when(movementPort.createMovementPSTRq(any(MovementPSTRq.class))).thenReturn(expectedResponse);

        MovementPSTRs actualResponse = paymentRestImpl.processMovement(movementRequest);

        assertEquals(expectedResponse, actualResponse);
    }
}