package com.microservices.userservice.domain.ports.in;


import com.microservices.domains.dto.MovementPSTRq;

public interface PaymentService<T> {
    T processMovement(MovementPSTRq movementRequest);
}