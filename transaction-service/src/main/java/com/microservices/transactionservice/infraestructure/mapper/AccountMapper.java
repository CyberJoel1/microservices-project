package com.microservices.transactionservice.infraestructure.mapper;

import com.microservices.domains.Account;

import com.microservices.transactionservice.infraestructure.entities.AccountEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { MovementMapper.class })
public interface AccountMapper {


    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "code", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "identification", target = "client.clientId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "accountBalance", target = "accountBalance.balance")
    @Mapping(source = "movementEntity", target = "movements")
    Account domainToDto(AccountEntity accountEntity);

    @Mapping(source = "id", target = "code")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "accountBalance.balance", target = "accountBalance")
    @Mapping(source = "client.clientId", target = "identification")
    AccountEntity dtoToDomain(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedAccountEntity(AccountEntity newAccount,@MappingTarget AccountEntity searchAccount);
}
