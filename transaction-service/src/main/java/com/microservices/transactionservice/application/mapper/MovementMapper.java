package com.microservices.transactionservice.application.mapper;

import com.microservices.domains.Account;
import com.microservices.domains.Movement;
import com.microservices.domains.dto.MovementGetRs;
import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import com.microservices.domains.dto.MovementPTCRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    @Mapping(source = "movement.amount", target = "amount")
    @Mapping(source = "movement.type", target = "type")
    Movement dtoToDomain(MovementPSTRq movementPSTRq);

    @Mapping(source = "movement.type", target = "movement.type")
    @Mapping(source = "movement.amount", target = "movement.amount")
    @Mapping(source = "movement.date", target = "movement.date")
    @Mapping(source = "movement.remainingBalance", target = "movement.remainingBalance")
    MovementGetRs domainToDtoGet(Movement movement);

    @Mapping(source = "movement.type", target = "movement.type")
    @Mapping(source = "movement.amount", target = "movement.amount")
    @Mapping(source = "movement.date", target = "movement.date")
    @Mapping(source = "movement.remainingBalance", target = "movement.remainingBalance")
    MovementPSTRs domainToDtoPST(Movement movement, Account account);

    @Mapping(source = "movement.type", target = "movement.type")
    @Mapping(source = "movement.amount", target = "movement.amount")
    @Mapping(source = "movement.date", target = "movement.date")
    @Mapping(source = "movement.remainingBalance", target = "movement.remainingBalance")
    MovementPTCRs domainToDtoPTC(Movement movement);

    List<Movement> domainToDtoList(List<MovementPSTRs> movementPSTRs);

}
