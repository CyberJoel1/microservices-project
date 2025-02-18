package com.microservices.transactionservice.infraestructure.adapter;

import com.microservices.domains.Account;
import com.microservices.exception.InfraestructureException;
import com.microservices.transactionservice.domain.ports.out.AccountRepository;

import com.microservices.transactionservice.infraestructure.entities.AccountEntity;
import com.microservices.transactionservice.infraestructure.mapper.AccountMapper;
import com.microservices.transactionservice.infraestructure.repositories.AccountEntityRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryAdapter implements AccountRepository {

    private AccountEntityRepositoryJPA accountRepository;

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
    public Account findById(int id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById((long) id);
        if (!accountEntity.isPresent()) {
            throw new InfraestructureException("Account not found.");
        }

        return AccountMapper.INSTANCE.domainToDto(accountEntity.get());
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        AccountEntity accountEntity = accountRepository.findAccountEntityByAccountNumber(accountNumber);
        return AccountMapper.INSTANCE.domainToDto(accountEntity);
    }

    @Override
    public List<Account> findByIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        List<AccountEntity> accountEntityList = accountRepository.findAccountEntityByIdentification(identification, startDate, endDate);
        return accountEntityList.stream().map(AccountMapper.INSTANCE::domainToDto).toList();
    }



    @Override
    public Account update(Account account, Integer id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(Long.valueOf(id));

        if (!accountEntity.isPresent()) {
            throw new InfraestructureException("Account not found.");
        }

        AccountEntity accountEntityNew = AccountMapper.INSTANCE.dtoToDomain(account);

        AccountMapper.INSTANCE.updatedAccountEntity(accountEntityNew, accountEntity.get());

        return AccountMapper.INSTANCE.domainToDto(accountEntity.get());
    }
}
