package com.microservices.userservice.infraestructure.controllers;


import com.microservices.domains.Client;
import com.microservices.domains.Movement;
import com.microservices.domains.dto.*;
import com.microservices.userservice.application.services.EventServiceImpl;
import com.microservices.userservice.domain.ports.in.ClientService;
import com.microservices.userservice.domain.ports.out.ClientRepository;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    private final EventServiceImpl eventService;

    @Autowired
    public ClientController(ClientService clientService, EventServiceImpl eventService) {
        this.clientService = clientService;
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<ClientGetRs> getClientByIdentification(
            @RequestParam(value = "identification", required = true) String identification) {
        return ResponseEntity.ok(clientService.findByIdentification(identification));
    }

    @PostMapping
    public ResponseEntity<ClientPSTRs> createClient(@RequestBody ClientPSTRq clientPSTRq) {
        return ResponseEntity.ok(clientService.create(clientPSTRq));
    }

    @PostMapping("/pago")
    public ResponseEntity<String> paymentToAccount(@RequestBody PaymentEventRq paymentEventRq){
        MovementPSTRq movementPSTRq = new MovementPSTRq();
        Movement movement = new Movement();
        movement.setAmount(paymentEventRq.getAmount());
        movementPSTRq.setAccountNumber(paymentEventRq.getAccountNumber());
        movementPSTRq.setMovement(movement);
        eventService.sendMovementEvent(movementPSTRq);
        return ResponseEntity.ok("Payment successful");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientPTCRs> updateClient(@RequestBody ClientPTCRq clientPTCRq,
                                                    @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(clientService.update(clientPTCRq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "id", required = true) String id) {
        clientService.delete(id);
        return ResponseEntity.ok("Client cancelled");
    }



}
