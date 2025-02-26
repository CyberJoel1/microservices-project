package com.microservices.transactionservice.application.services;

import com.microservices.domains.Account;
import com.microservices.domains.dto.*;
import com.microservices.transactionservice.application.mapper.AccountMapper;
import com.microservices.transactionservice.domain.ports.in.AccountService;
import com.microservices.transactionservice.domain.ports.out.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountPSTRs createAccount(AccountPSTRq accountPSTRq) {
        Account accountCreated = accountRepository.create(accountPSTRq.getAccount());
        return AccountMapper.INSTANCE.domainToDtoPST(accountCreated);
    }

    @Override
    public AccountPTCRs updateAccount(AccountPTCRq accountPTCRq, String id) {
        Account accountCreated = accountRepository.update(accountPTCRq.getAccount(), Integer.parseInt(id));
        return AccountMapper.INSTANCE.domainToDtoPTC(accountCreated);
    }

    @Override
    public void deleteAccount(Integer id) {
        Account account = new Account();
        account.setStatus("C");
        accountRepository.update(account, id);
    }

    @Override
    public AccountGetRs getAccountByNumberAccount(String numberAccount) {
        Optional<Account> account = accountRepository.findByAccountNumber(numberAccount);
        return account.map(AccountMapper.INSTANCE::domainToDtoGet).orElse(null);
    }

    @Override
    public List<AccountGetRs> getAllAccountsByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        List<Account> accountList = accountRepository.findByIdentification(identification, startDate, endDate);
        return accountList.stream().map(AccountMapper.INSTANCE::domainToDtoGet).toList();
    }
}