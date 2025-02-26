package com.microservices.transactionservice.application.services;

import com.microservices.domains.dto.*;
import com.microservices.exception.BusinessLogicException;
import com.microservices.transactionservice.application.facade.AccountFacade;
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
    private AccountFacade accountFacade;

    @Transactional
    @Override
    public MovementPSTRs registerMovement(MovementPSTRq movementPSTRq) {
        String accountNumber = movementPSTRq.getAccountNumber();
        BigDecimal amountToPay = movementPSTRq.getMovement().getAmount();

        Account accountWithDiscount = accountFacade.discountInAccount(accountNumber, amountToPay);

        Movement movementToCreate = MovementMapper.INSTANCE.dtoToDomain(movementPSTRq);
        movementToCreate.setDate(LocalDateTime.now());
        movementToCreate.setRemainingBalance(accountWithDiscount.getAccountBalance().getBalance());
        movementToCreate.setAccount(accountWithDiscount);

        Movement movementCreated = movementRepository.create(movementToCreate);

        return MovementMapper.INSTANCE.domainToDtoPST(movementCreated, accountWithDiscount);
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
