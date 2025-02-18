package com.microservices.transactionservice.infraestructure.controllers;

import com.microservices.domains.dto.*;
import com.microservices.transactionservice.domain.ports.in.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    ResponseEntity<MovementPSTRs> createMovement(@RequestBody MovementPSTRq movementPSTRq) {
        MovementPSTRs movementPSTRs = movementService.registerMovement(movementPSTRq);
        return ResponseEntity.ok(movementPSTRs);
    }

    @PatchMapping("/{id}")
    ResponseEntity<MovementPTCRs> updateMovement(@RequestBody MovementPTCRq movementPTCRq, @PathVariable String id) {
        MovementPTCRs movementPTCRs = movementService.updateMovement(movementPTCRq, id);
        return ResponseEntity.ok(movementPTCRs);
    }

    @GetMapping("/by-account")
    ResponseEntity<List<MovementGetRs>> getMovementsByNumberAccount(@RequestBody String numberAccount) {
        List<MovementGetRs> movementGetRs = movementService.getMovementsByNumberAccount(numberAccount);
        return ResponseEntity.ok(movementGetRs);
    }

    @GetMapping("/{id}")
    ResponseEntity<MovementGetRs> findById(@PathVariable String id) {
        MovementGetRs movementGetRs = movementService.getMovementById(Integer.parseInt(id));
        return ResponseEntity.ok(movementGetRs);
    }
}
