package com.microservices.userservice.application.services;

import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import com.microservices.userservice.domain.ports.in.PaymentService;
import com.microservices.userservice.domain.ports.out.MovementPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("payment-rest")
public class PaymentRestImpl implements PaymentService<MovementPSTRs> {

    @Autowired
    MovementPort movementPort;

    @Override
    public MovementPSTRs processMovement(MovementPSTRq movementRequest) {
        return movementPort.createMovementPSTRq(movementRequest);
    }

}
