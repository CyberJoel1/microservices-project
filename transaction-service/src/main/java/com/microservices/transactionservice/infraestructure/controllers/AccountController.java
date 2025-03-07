package com.microservices.transactionservice.infraestructure.controllers;


import com.microservices.domains.dto.*;
import com.microservices.transactionservice.domain.ports.in.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@Tag(name = "Management of bank accounts", description = "CRUD account management")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @Operation(summary = "Get an account from an account number")
    ResponseEntity<AccountGetRs> getAccount(@RequestParam("numberAccount") String numberAccount) {
        AccountGetRs accountGetRs = accountService.getAccountByNumberAccount(numberAccount);
        return ResponseEntity.ok(accountGetRs);
    }

    @PostMapping
    @Operation(summary = "Create an account")
    ResponseEntity<AccountPSTRs> createAccount(@RequestBody AccountPSTRq accountPSTRq) {
        AccountPSTRs accountPSTRs = accountService.createAccount(accountPSTRq);
        return ResponseEntity.ok(accountPSTRs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an account by id")
    ResponseEntity<AccountGetRs> getAccountById(@PathVariable("id") String id) {
        AccountGetRs accountGetRs = accountService.getAccountById(Integer.parseInt(id));
        return ResponseEntity.ok(accountGetRs);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an account")
    ResponseEntity<AccountPTCRs> updateAccount(@RequestBody AccountPTCRq accountPTCRq, @PathVariable String id ) {
        AccountPTCRs accountPTCRs = accountService.updateAccount(accountPTCRq, id);
        return ResponseEntity.ok(accountPTCRs);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an account")
    ResponseEntity<String> deleteAccount(@PathVariable(value = "id", required = true) String id) {
        accountService.deleteAccount(Integer.parseInt(id));
        return ResponseEntity.ok("Account deleted");
    }

}
