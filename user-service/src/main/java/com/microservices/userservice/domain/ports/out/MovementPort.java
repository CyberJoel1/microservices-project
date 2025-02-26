package com.microservices.userservice.domain.ports.out;

import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;

public interface MovementPort {
    MovementPSTRs createMovementPSTRq(MovementPSTRq movementPSTRq);
}
