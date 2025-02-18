package com.microservices.transactionservice.infraestructure.adapter;

import com.microservices.domains.Movement;
import com.microservices.exception.InfraestructureException;
import com.microservices.transactionservice.domain.ports.out.MovementRepository;
import com.microservices.transactionservice.infraestructure.entities.AccountEntity;
import com.microservices.transactionservice.infraestructure.entities.MovementEntity;
import com.microservices.transactionservice.infraestructure.mapper.MovementMapper;
import com.microservices.transactionservice.infraestructure.repositories.AccountEntityRepositoryJPA;
import com.microservices.transactionservice.infraestructure.repositories.MovementEntityRepositoryJPA;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MovementRepositoryAdapter implements MovementRepository {
    @Autowired
    private MovementEntityRepositoryJPA movementEntityRepository;

    @Autowired
    private AccountEntityRepositoryJPA accountEntityRepository;




    @Transactional
    @Override
    public Movement create(Movement movement) {


        MovementEntity movementEntity = MovementMapper.INSTANCE.dtoToDomainCreate(movement);

        movementEntity.setDate(LocalDateTime.now());

        MovementEntity savedEntity = movementEntityRepository.save(movementEntity);


        if (savedEntity == null) {
            throw new InfraestructureException("Error when creating the movement.");
        }

        return MovementMapper.INSTANCE.domainToDto(savedEntity);
    }

    @Override
    public Movement findById(int id) {
        Optional<MovementEntity> movementEntity = movementEntityRepository.findById(id);

        if (!movementEntity.isPresent()) {
            throw new InfraestructureException("Movement with id " + id + " not found.");
        }

        return MovementMapper.INSTANCE.domainToDto(movementEntity.get());
    }


    @Transactional
    @Override
    public Movement update(Movement movement, Integer id) {
        Optional<MovementEntity> movementEntity = movementEntityRepository.findById(id);

        if (!movementEntity.isPresent()) {
            throw new InfraestructureException("Movement not found.");
        }

        MovementEntity movementEntityNew = MovementMapper.INSTANCE.dtoToDomain(movement);

        MovementMapper.INSTANCE.updatedMovementEntity(movementEntityNew, movementEntity.get());

        return MovementMapper.INSTANCE.domainToDto(movementEntity.get());
    }

    @Override
    public List<Movement> getMovementsByNumberAccount(String numberAccount) {
        AccountEntity account = accountEntityRepository.findAccountEntityByAccountNumber(numberAccount);
        List<MovementEntity> movementEntityList = account.getMovementEntity();
        return movementEntityList.stream().map(MovementMapper.INSTANCE::domainToDto).toList();
    }
}
