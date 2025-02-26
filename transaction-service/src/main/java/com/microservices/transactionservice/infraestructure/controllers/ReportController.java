package com.microservices.transactionservice.infraestructure.controllers;

import com.microservices.domains.dto.AccountGetRs;
import com.microservices.transactionservice.domain.ports.in.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@Tag(name = "Report management", description = "Report requests")
public class ReportController {

    private AccountService accountService;

    @Autowired
    public ReportController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @Operation(description = "Transaction reporting from an account")
    ResponseEntity<List<AccountGetRs>> getAllAccountsByIdentification(@RequestParam(name = "clientId") String clientId,
                                                                      @RequestParam(name = "startDate") LocalDateTime startDate,
                                                                      @RequestParam(name = "endDate") LocalDateTime endDate) {
        List<AccountGetRs> accountGetRsList = accountService.getAllAccountsByIdentification(clientId,startDate, endDate);
        return ResponseEntity.ok(accountGetRsList);
    }

}
