package com.microservices.transactionservice.infraestructure.controllers;

import com.microservices.domains.dto.*;
import com.microservices.transactionservice.domain.ports.in.MovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@Tag(name = "Movement management", description = "CRUD for managing account transactions")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    @Operation(summary = "Create a movement")
    ResponseEntity<MovementPSTRs> createMovement(@RequestBody MovementPSTRq movementPSTRq) {
        MovementPSTRs movementPSTRs = movementService.registerMovement(movementPSTRq);
        return ResponseEntity.ok(movementPSTRs);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a movement")
    ResponseEntity<MovementPTCRs> updateMovement(@RequestBody MovementPTCRq movementPTCRq, @PathVariable String id) {
        MovementPTCRs movementPTCRs = movementService.updateMovement(movementPTCRq, id);
        return ResponseEntity.ok(movementPTCRs);
    }

    @GetMapping("/by-account")
    @Operation(summary = "Search transactions from an account number")
    ResponseEntity<List<MovementGetRs>> getMovementsByNumberAccount(@RequestBody String numberAccount) {
        List<MovementGetRs> movementGetRs = movementService.getMovementsByNumberAccount(numberAccount);
        return ResponseEntity.ok(movementGetRs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search the movement from id")
    ResponseEntity<MovementGetRs> findById(@PathVariable String id) {
        MovementGetRs movementGetRs = movementService.getMovementById(Integer.parseInt(id));
        return ResponseEntity.ok(movementGetRs);
    }
}
