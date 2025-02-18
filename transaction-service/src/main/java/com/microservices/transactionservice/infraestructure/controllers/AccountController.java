package com.microservices.transactionservice.infraestructure.controllers;


import com.microservices.domains.dto.*;
import com.microservices.transactionservice.domain.ports.in.AccountService;
import jakarta.ws.rs.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    ResponseEntity<AccountPSTRs> createMovement(@RequestBody AccountPSTRq accountPSTRq) {
        AccountPSTRs accountPSTRs = accountService.createAccount(accountPSTRq);
        return ResponseEntity.ok(accountPSTRs);
    }

    @PatchMapping("/{id}")
    ResponseEntity<AccountPTCRs> updateMovement(@RequestBody AccountPTCRq accountPTCRq, @PathVariable String id ) {
        AccountPTCRs accountPTCRs = accountService.updateAccount(accountPTCRq, id);
        return ResponseEntity.ok(accountPTCRs);
    }

    @GetMapping
    ResponseEntity<List<AccountGetRs>> getAllAccountsByIdentification(@RequestParam(name = "clientId") String clientId,
                                                                      @RequestParam(name = "startDate") LocalDateTime startDate,
                                                                      @RequestParam(name = "endDate") LocalDateTime endDate) {
        List<AccountGetRs> accountGetRsList = accountService.getAllAccountsByIdentification(clientId,startDate, endDate);
        return ResponseEntity.ok(accountGetRsList);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAccount(@PathVariable(value = "id", required = true) String id) {
        accountService.deleteAccount(Integer.parseInt(id));
        return ResponseEntity.ok("Account deleted");
    }



}
