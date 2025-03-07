package com.microservices.userservice.infraestructure.mappers;

import com.microservices.domains.Client;
import com.microservices.userservice.infraestructure.entities.ClientEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "code", target = "clientId")
    @Mapping(source = "personEntity.identification", target = "identification")
    @Mapping(source = "personEntity.name", target = "name")
    @Mapping(source = "personEntity.age", target = "age")
    @Mapping(source = "personEntity.gender", target = "gender")
    @Mapping(source = "personEntity.address", target = "address")
    @Mapping(source = "personEntity.phoneNumber", target = "phoneNumber")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client domainToDto(ClientEntity clientEntity);

    @Mapping(source = "clientId", target = "code")
    @Mapping(source = "identification", target = "personEntity.identification")
    @Mapping(source = "name", target = "personEntity.name")
    @Mapping(source = "age", target = "personEntity.age")
    @Mapping(source = "gender", target = "personEntity.gender")
    @Mapping(source = "address", target = "personEntity.address")
    @Mapping(source = "phoneNumber", target = "personEntity.phoneNumber")
    @Mapping(source = "status", target = "status")
    ClientEntity dtoToDomain(Client client);

    @Mapping(source = "personEntity.code", target = "personEntity.code")
    @Mapping(source = "personEntity.age", target = "personEntity.age")
    @Mapping(source = "personEntity.name", target = "personEntity.name")
    @Mapping(source = "personEntity.gender", target = "personEntity.gender")
    @Mapping(source = "personEntity.address", target = "personEntity.address")
    @Mapping(source = "personEntity.phoneNumber", target = "personEntity.phoneNumber")
    @Mapping(source = "personEntity.identification", target = "personEntity.identification")
    @Mapping(source = "code", target = "code")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedClientEntity(ClientEntity newClient, @MappingTarget ClientEntity searchClientEntity);
}