package com.microservices.transactionservice.application.mapper;

import com.microservices.domains.Account;
import com.microservices.domains.Movement;
import com.microservices.domains.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { MovementMapper.class })
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "accountNumber", target = "account.accountNumber")
    @Mapping(source = "accountBalance.balance", target = "account.accountBalance.balance")
    @Mapping(source = "type", target = "account.type")
    @Mapping(source = "client.clientId", target = "account.client.clientId")
    @Mapping(source = "movements", target = "account.movements")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountGetRs domainToDtoGet(Account account);

    @Mapping(source = "id", target = "account.id")
    @Mapping(source = "accountNumber", target = "account.accountNumber")
    @Mapping(source = "accountBalance.balance", target = "account.accountBalance.balance")
    @Mapping(source = "type", target = "account.type")
    @Mapping(source = "client.clientId", target = "account.client.clientId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountPSTRs domainToDtoPST(Account account);

    @Mapping(source = "accountNumber", target = "account.accountNumber")
    @Mapping(source = "accountBalance.balance", target = "account.accountBalance.balance")
    @Mapping(source = "type", target = "account.type")
    @Mapping(source = "client.clientId", target = "account.client.clientId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("AccountPTCRs")
    AccountPTCRs domainToDtoPTC(Account account);

}
