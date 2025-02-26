package com.microservices.userservice.infraestructure.controllers;


import com.microservices.domains.Client;
import com.microservices.domains.Movement;
import com.microservices.domains.dto.*;
import com.microservices.exception.BusinessLogicException;
import com.microservices.exception.LogLevel;
import com.microservices.userservice.application.services.EventServiceImpl;
import com.microservices.userservice.domain.ports.in.ClientService;
import com.microservices.userservice.domain.ports.in.PaymentService;
import com.microservices.userservice.domain.ports.out.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Named;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Client management", description = "CRUD for managing clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    @Named("payment-event")
    private PaymentService<String> eventService;


    @Autowired
    @Named("payment-rest")
    private PaymentService<MovementPSTRs> paymentRestService;



    @GetMapping
    @Operation(description = "Get clients by identification")
    public ResponseEntity<ClientGetRs> getClientByIdentification(
            @RequestParam(value = "identification", required = true) String identification) {
        return ResponseEntity.ok(clientService.findByIdentification(identification));
    }

    @PostMapping
    @Operation(description = "Create a client")
    public ResponseEntity<ClientPSTRs> createClient(@RequestBody ClientPSTRq clientPSTRq) {
        return ResponseEntity.ok(clientService.create(clientPSTRq));
    }

    @PostMapping("/pago/evento")
    @Operation(description = "Make a payment to an account using event driven")
    public ResponseEntity<String> paymentToAccount(@RequestBody PaymentEventRq paymentEventRq) {

        if (paymentEventRq == null || paymentEventRq.getAmount() == null || paymentEventRq.getAccountNumber() == null) {
            throw new BusinessLogicException("Invalid payment request", LogLevel.ERROR);
        }

        Movement movement = Movement.builder()
                .amount(paymentEventRq.getAmount())
                .build();

        MovementPSTRq movementPSTRq = MovementPSTRq.builder()
                .accountNumber(paymentEventRq.getAccountNumber())
                .movement(movement)
                .build();

        eventService.processMovement(movementPSTRq);
        return ResponseEntity.ok("Payment successful");
    }

    @PostMapping("/pago/rest")
    @Operation(description = "Make a payment to an account using rest")
    public ResponseEntity<MovementPSTRs> paymentToAccountRest(@RequestBody PaymentEventRq paymentEventRq) {

        if (paymentEventRq == null || paymentEventRq.getAmount() == null || paymentEventRq.getAccountNumber() == null) {
            throw new BusinessLogicException("Invalid payment request", LogLevel.ERROR);
        }

        Movement movement = Movement.builder()
                .amount(paymentEventRq.getAmount())
                .build();

        MovementPSTRq movementPSTRq = MovementPSTRq.builder()
                .accountNumber(paymentEventRq.getAccountNumber())
                .movement(movement)
                .build();

        MovementPSTRs movementPSTRs = paymentRestService.processMovement(movementPSTRq);
        return ResponseEntity.ok(movementPSTRs);
    }

    @PatchMapping("/{id}")
    @Operation(description = "Make a payment to an account using rest")
    public ResponseEntity<ClientPTCRs> updateClient(@RequestBody ClientPTCRq clientPTCRq,
                                                    @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(clientService.update(clientPTCRq, id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a client")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "id", required = true) String id) {
        clientService.delete(id);
        return ResponseEntity.ok("Client cancelled");
    }



}
