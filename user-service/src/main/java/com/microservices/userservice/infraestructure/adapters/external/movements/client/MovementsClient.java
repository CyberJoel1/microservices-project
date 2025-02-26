package com.microservices.userservice.infraestructure.adapters.external.movements.client;


import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transactionClient", url = "${transaction.client.url}")
public interface MovementsClient {

    @PostMapping
    MovementPSTRs createMovement(@RequestBody MovementPSTRq movementPSTRq);
}
