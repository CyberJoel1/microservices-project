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


    @GetMapping
    @Operation(description = "Get clients by identification")
    public ResponseEntity<ClientGetRs> getClientByIdentification(
            @RequestParam(value = "identification", required = true) String identification) {
        return ResponseEntity.ok(clientService.findByIdentification(identification));
    }

    @GetMapping("/{id}")
    @Operation(description = "Get client by id")
    public ResponseEntity<ClientGetRs> getClientById( @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    @Operation(description = "Create a client")
    public ResponseEntity<ClientPSTRs> createClient(@RequestBody ClientPSTRq clientPSTRq) {
        return ResponseEntity.ok(clientService.create(clientPSTRq));
    }


    @PatchMapping("/{id}")
    @Operation(description = "Update a client")
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
