package com.microservices.transactionservice.domain.ports.out;

import com.microservices.domains.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account create(Account client);
    Optional<Account> findById(int id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate);
    Account update(Account client, Integer accountId);
}