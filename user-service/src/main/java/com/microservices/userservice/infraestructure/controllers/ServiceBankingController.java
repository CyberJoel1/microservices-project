package com.microservices.userservice.infraestructure.controllers;

import com.microservices.domains.Movement;
import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import com.microservices.domains.dto.PaymentEventRq;
import com.microservices.domains.enums.PaymentType;
import com.microservices.exception.BusinessLogicException;
import com.microservices.exception.LogLevel;
import com.microservices.userservice.domain.ports.in.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banking")
@Tag(name = "Banking services", description = "Banking services for customers")
public class ServiceBankingController {

    @Autowired
    @Named("payment-event")
    private PaymentService<String> eventService;

    @Autowired
    @Named("payment-rest")
    private PaymentService<MovementPSTRs> paymentRestService;

    @PostMapping("/debit/event")
    @Operation(description = "Make a debit note to an account using event driven")
    public ResponseEntity<String> debitToAccount(@RequestBody PaymentEventRq paymentEventRq) {

        if (paymentEventRq == null || paymentEventRq.getAmount() == null || paymentEventRq.getAccountNumber() == null) {
            throw new BusinessLogicException("Invalid payment request", LogLevel.ERROR);
        }

        Movement movement = Movement.builder()
                .amount(paymentEventRq.getAmount())
                .type(PaymentType.DEBIT)
                .build();

        MovementPSTRq movementPSTRq = MovementPSTRq.builder()
                .accountNumber(paymentEventRq.getAccountNumber())
                .movement(movement)
                .build();

        eventService.processMovement(movementPSTRq);
        return ResponseEntity.ok("Payment successful");
    }

    @PostMapping("/debit/rest")
    @Operation(description = "Make a debit note to an account using rest")
    public ResponseEntity<MovementPSTRs> debitToAccountRest(@RequestBody PaymentEventRq paymentEventRq) {

        if (paymentEventRq == null || paymentEventRq.getAmount() == null || paymentEventRq.getAccountNumber() == null) {
            throw new BusinessLogicException("Invalid payment request", LogLevel.ERROR);
        }

        Movement movement = Movement.builder()
                .amount(paymentEventRq.getAmount())
                .type(PaymentType.DEBIT)
                .build();

        MovementPSTRq movementPSTRq = MovementPSTRq.builder()
                .accountNumber(paymentEventRq.getAccountNumber())
                .movement(movement)
                .build();

        MovementPSTRs movementPSTRs = paymentRestService.processMovement(movementPSTRq);
        return ResponseEntity.ok(movementPSTRs);
    }

    @PostMapping("/credit/event")
    @Operation(description = "Make a credit note to an account using event driven")
    public ResponseEntity<String> creditToAccount(@RequestBody PaymentEventRq paymentEventRq) {

        if (paymentEventRq == null || paymentEventRq.getAmount() == null || paymentEventRq.getAccountNumber() == null) {
            throw new BusinessLogicException("Invalid payment request", LogLevel.ERROR);
        }

        Movement movement = Movement.builder()
                .amount(paymentEventRq.getAmount())
                .type(PaymentType.CREDIT)
                .build();

        MovementPSTRq movementPSTRq = MovementPSTRq.builder()
                .accountNumber(paymentEventRq.getAccountNumber())
                .movement(movement)
                .build();

        eventService.processMovement(movementPSTRq);
        return ResponseEntity.ok("Payment successful");
    }

    @PostMapping("/credit/rest")
    @Operation(description = "Make a credit note to an account using rest")
    public ResponseEntity<MovementPSTRs> creditToAccountRest(@RequestBody PaymentEventRq paymentEventRq) {

        if (paymentEventRq == null || paymentEventRq.getAmount() == null || paymentEventRq.getAccountNumber() == null) {
            throw new BusinessLogicException("Invalid payment request", LogLevel.ERROR);
        }

        Movement movement = Movement.builder()
                .amount(paymentEventRq.getAmount())
                .type(PaymentType.CREDIT)
                .build();

        MovementPSTRq movementPSTRq = MovementPSTRq.builder()
                .accountNumber(paymentEventRq.getAccountNumber())
                .movement(movement)
                .build();

        MovementPSTRs movementPSTRs = paymentRestService.processMovement(movementPSTRq);
        return ResponseEntity.ok(movementPSTRs);
    }


}
