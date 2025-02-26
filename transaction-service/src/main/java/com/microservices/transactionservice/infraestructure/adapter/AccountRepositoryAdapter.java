package com.microservices.transactionservice.infraestructure.adapter;

import com.microservices.domains.Account;
import com.microservices.exception.InfraestructureException;
import com.microservices.transactionservice.domain.ports.out.AccountRepository;
import com.microservices.transactionservice.infraestructure.entities.AccountEntity;
import com.microservices.transactionservice.infraestructure.mapper.AccountMapper;
import com.microservices.transactionservice.infraestructure.repositories.AccountEntityRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryAdapter implements AccountRepository {

    private final AccountEntityRepositoryJPA accountRepository;

    @Autowired
    public AccountRepositoryAdapter(AccountEntityRepositoryJPA accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        AccountEntity accountEntity = AccountMapper.INSTANCE.dtoToDomain(account);
        AccountEntity savedAccount = accountRepository.save(accountEntity);
        if (savedAccount == null) {
            throw new InfraestructureException("Error when creating the account.");
        }
        return AccountMapper.INSTANCE.domainToDto(savedAccount);
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById((long) id)
                .map(AccountMapper.INSTANCE::domainToDto);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(accountRepository.findAccountEntityByAccountNumber(accountNumber))
                .map(AccountMapper.INSTANCE::domainToDto);
    }

    @Override
    public List<Account> findByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        List<AccountEntity> accountEntityList = accountRepository.findAccountEntityByIdentification(identification, startDate, endDate);
        return accountEntityList.stream()
                .map(AccountMapper.INSTANCE::domainToDto)
                .toList();
    }

    @Override
    public Account update(Account account, Integer accountId) {
        if (accountId == null) {
            throw new InfraestructureException("Account ID cannot be null.");
        }

        AccountEntity accountEntity = accountRepository.findById(accountId.longValue())
                .orElseThrow(() -> new InfraestructureException("Account not found."));

        AccountMapper.INSTANCE.updatedAccountEntity(AccountMapper.INSTANCE.dtoToDomain(account), accountEntity);

        AccountEntity updatedAccount = accountRepository.save(accountEntity);
        return AccountMapper.INSTANCE.domainToDto(updatedAccount);
    }
}