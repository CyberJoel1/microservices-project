package com.microservices.transactionservice.domain.ports.out;

import com.microservices.domains.Account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AccountRepository {
    Account create(Account client);
    Account findById(int id);
    Account findByAccountNumber(String accountNumber);
    List<Account> findByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate);
    Account update(Account client, Integer id);
}
