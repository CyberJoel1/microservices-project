package com.microservices.transactionservice.application.facade;

import com.microservices.domains.Account;
import com.microservices.exception.BusinessLogicException;
import com.microservices.transactionservice.domain.ports.in.AccountService;
import com.microservices.transactionservice.domain.ports.out.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.microservices.exception.LogLevel.ERROR;

@Component
public class AccountFacade {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;


    public Account discountInAccount(String accountNumber, BigDecimal amountToDiscount){

        Account accountToUpdate = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BusinessLogicException("Account not found", ERROR));

        BigDecimal balanceAfterPayment = accountToUpdate.getAccountBalance().getBalance()
                .subtract(amountToDiscount);

        if (balanceAfterPayment.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessLogicException("Balance not available", ERROR);
        }

        accountToUpdate.getAccountBalance().setBalance(balanceAfterPayment);
        accountRepository.update(accountToUpdate, accountToUpdate.getId());

        return accountToUpdate;
    }



}
