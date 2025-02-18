package com.microservices.userservice.application.mapper;

import com.microservices.domains.Account;
import com.microservices.domains.Client;
import com.microservices.domains.Movement;
import com.microservices.domains.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "identification", target = "client.identification")
    @Mapping(source = "name", target = "client.name")
    @Mapping(source = "age", target = "client.age")
    @Mapping(source = "address", target = "client.address")
    @Mapping(source = "gender", target = "client.gender")
    @Mapping(source = "phoneNumber", target = "client.phoneNumber")
    ClientGetRs domainToDtoGet(Client client);

    @Mapping(source = "identification", target = "client.identification")
    @Mapping(source = "name", target = "client.name")
    @Mapping(source = "age", target = "client.age")
    @Mapping(source = "address", target = "client.address")
    @Mapping(source = "gender", target = "client.gender")
    @Mapping(source = "phoneNumber", target = "client.phoneNumber")
    @Mapping(source = "clientId", target = "client.clientId")
    ClientPSTRs domainToDtoPST(Client client);

    @Mapping(source = "identification", target = "client.identification")
    @Mapping(source = "name", target = "client.name")
    @Mapping(source = "age", target = "client.age")
    @Mapping(source = "address", target = "client.address")
    @Mapping(source = "gender", target = "client.gender")
    @Mapping(source = "phoneNumber", target = "client.phoneNumber")
    ClientPTCRs domainToDtoPTC(Client client);
}
