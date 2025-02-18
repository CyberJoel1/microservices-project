package com.microservices.transactionservice.application.services;

import com.microservices.domains.dto.*;
import com.microservices.exception.BusinessLogicException;
import com.microservices.transactionservice.application.mapper.MovementMapper;
import com.microservices.domains.Account;
import com.microservices.domains.Movement;
import com.microservices.transactionservice.domain.ports.in.MovementService;
import com.microservices.transactionservice.domain.ports.out.AccountRepository;
import com.microservices.transactionservice.domain.ports.out.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.microservices.exception.LogLevel.ERROR;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountRepository accountRepository;




    @Transactional()
    @Override
    public MovementPSTRs registerMovement(MovementPSTRq movementPSTRq) {
        String accountNumber = movementPSTRq.getAccountNumber();

        Account accountToUpdate = accountRepository.findByAccountNumber(accountNumber);

        if(accountToUpdate == null){
            throw new BusinessLogicException("Account not found", ERROR);
        }

        BigDecimal balanceAfterPayment =
                accountToUpdate.getAccountBalance().getBalance().subtract(movementPSTRq.getMovement().getAmount());

        if(balanceAfterPayment.compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessLogicException("Balance not available", ERROR);
        }

        accountToUpdate.getAccountBalance().setBalance(balanceAfterPayment);

        Account AccountUpdated = accountRepository.update(accountToUpdate, accountToUpdate.getId());

        Movement movementToCreate = MovementMapper.INSTANCE.dtoToDomain(movementPSTRq);

        movementToCreate.setDate(LocalDateTime.now());
        movementToCreate.setRemainingBalance(balanceAfterPayment);
        movementToCreate.setAccount(new Account());
        movementToCreate.getAccount().setId(accountToUpdate.getId());

        Movement movementCreated = movementRepository.create(movementToCreate);

        return MovementMapper.INSTANCE.domainToDtoPST(movementCreated, accountToUpdate);
    }

    @Override
    public MovementPTCRs updateMovement(MovementPTCRq movementPTCRq, String id) {
        Movement movementUpdated = movementRepository.update(movementPTCRq.getMovement(), Integer.parseInt(id));
        return MovementMapper.INSTANCE.domainToDtoPTC(movementUpdated);
    }


    @Override
    public MovementGetRs getMovementById(int id) {
        Movement movement = movementRepository.findById(id);
        return MovementMapper.INSTANCE.domainToDtoGet(movement);
    }

    @Override
    public List<MovementGetRs> getMovementsByNumberAccount(String numberAccount) {
        List<Movement> movementList = movementRepository.getMovementsByNumberAccount(numberAccount);
        return movementList.stream().map(MovementMapper.INSTANCE::domainToDtoGet).toList();
    }

}
