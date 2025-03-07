package com.microservices.transactionservice.infraestructure.mapper;

import com.microservices.domains.Account;
import com.microservices.domains.Movement;
import com.microservices.domains.dto.MovementGetRs;
import com.microservices.domains.dto.MovementPSTRq;
import com.microservices.domains.dto.MovementPSTRs;
import com.microservices.domains.dto.MovementPTCRs;
import com.microservices.transactionservice.infraestructure.entities.MovementEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    @Mapping(source = "code", target = "id")
    @Mapping(source = "remainingBalance", target = "remainingBalance")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "amount", target = "amount")
    @Mapping(target = "account", ignore = true)
    Movement domainToDto(MovementEntity movementEntity);

    @Mapping(source = "id", target = "code")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "remainingBalance", target = "remainingBalance")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(target = "account", ignore = true)
    MovementEntity dtoToDomain(Movement movement);

    @Mapping(source = "id", target = "code")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "remainingBalance", target = "remainingBalance")
    @Mapping(target = "account", ignore = true)
    @Mapping(source = "account.id",target = "accountId")
    @Mapping(source = "status", target = "status")
    MovementEntity dtoToDomainCreate(Movement movement);

    List<Movement> domainToDtos(List<MovementEntity> movementEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedMovementEntity(MovementEntity newMovement,@MappingTarget MovementEntity searchMovement);
}
