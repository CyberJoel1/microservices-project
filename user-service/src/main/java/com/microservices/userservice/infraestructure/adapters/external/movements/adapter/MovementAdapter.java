package com.microservices.userservice.infraestructure.adapters.external.movements.adapter;

import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import com.microservices.userservice.domain.ports.out.MovementPort;
import com.microservices.userservice.infraestructure.adapters.external.movements.client.MovementsClient;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovementAdapter implements MovementPort {

    private final MovementsClient movementsClient;

    public MovementAdapter(MovementsClient movementsClient) {
        this.movementsClient = movementsClient;
    }

    @Override
    public MovementPSTRs createMovementPSTRq(MovementPSTRq movementPSTRq) {
        try {
            return movementsClient.createMovement(movementPSTRq);
        } catch (WebApplicationException e) {
            String errorResponse = e.getResponse().readEntity(String.class);
            log.error("Error al ejecutar un movimiento: {}", errorResponse);
            throw e;
        }
    }

}
