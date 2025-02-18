package com.microservices.transactionservice.application.services;

import com.microservices.domains.Account;
import com.microservices.domains.dto.*;
import com.microservices.transactionservice.application.mapper.AccountMapper;
import com.microservices.transactionservice.domain.ports.in.AccountService;
import com.microservices.transactionservice.domain.ports.out.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl( AccountRepository accountRepository) {
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
        AccountPTCRq accountPTCRq = new AccountPTCRq();
        accountPTCRq.setAccount(new Account());
        accountPTCRq.getAccount().setStatus("C");
        accountRepository.update(accountPTCRq.getAccount(), id);
    }

    @Override
    public AccountGetRs getAccountByNumberAccount(String numberAccount) {
        Account account = accountRepository.findByAccountNumber(numberAccount);
        return AccountMapper.INSTANCE.domainToDtoGet(account);
    }

    @Override
    public List<AccountGetRs> getAllAccountsByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        List<Account> accountList = accountRepository.findByIdentification(identification, startDate, endDate);
        return accountList.stream().map(AccountMapper.INSTANCE::domainToDtoGet).toList();
    }

}
