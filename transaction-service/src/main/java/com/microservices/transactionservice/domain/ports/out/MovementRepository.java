package com.microservices.transactionservice.domain.ports.out;

import com.microservices.domains.Movement;

import java.util.List;

public interface MovementRepository {
    Movement create(Movement client);
    Movement findById(int id);
    Movement update(Movement client, Integer id);
    List<Movement> getMovementsByNumberAccount(String numberAccount);
}
