package com.microservices.transactionservice.domain.ports.in;

import com.microservices.domains.dto.*;

import java.util.List;

public interface MovementService {
        MovementPSTRs registerMovement(MovementPSTRq movementPSTRq);
        MovementPTCRs updateMovement(MovementPTCRq movementPTCRq, String id);
        MovementGetRs getMovementById(int id);
        List<MovementGetRs> getMovementsByNumberAccount(String numberAccount);
}
