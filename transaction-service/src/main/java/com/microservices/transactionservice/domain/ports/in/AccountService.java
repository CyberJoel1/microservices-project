package com.microservices.transactionservice.domain.ports.in;

import com.microservices.domains.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {
    AccountPSTRs createAccount(AccountPSTRq accountPSTRq);
    AccountPTCRs updateAccount(AccountPTCRq accountPTCRq, String id);
    void deleteAccount(Integer id);
    AccountGetRs getAccountByNumberAccount(String numberAccount);
    List<AccountGetRs> getAllAccountsByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate);
}
