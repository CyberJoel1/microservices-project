package com.microservices.userservice.infraestructure.adapters;

import com.microservices.domains.Client;
import com.microservices.exception.InfraestructureException;
import com.microservices.userservice.domain.ports.out.ClientRepository;
import com.microservices.userservice.infraestructure.entities.ClientEntity;
import com.microservices.userservice.infraestructure.mappers.ClientMapper;
import com.microservices.userservice.infraestructure.repositories.ClientEntityRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientEntityRepositoryJPA clientEntityRepository;

    @Autowired
    public ClientRepositoryAdapter(ClientEntityRepositoryJPA clientEntityRepository) {
        this.clientEntityRepository = clientEntityRepository;
    }


    @Override
    @Transactional()
    public Client create(Client client) {
        ClientEntity clientEntityToSave = ClientMapper.INSTANCE.dtoToDomain(client);
        ClientEntity clientEntitySaved = clientEntityRepository.save(clientEntityToSave);
        return ClientMapper.INSTANCE.domainToDto(clientEntitySaved);
    }

    @Override
    public Client findByIdentification(String identification) {
        List<ClientEntity> clientEntityList = clientEntityRepository.findClientByIdentification(identification);
        if(clientEntityList== null || clientEntityList.isEmpty()) {
            throw new InfraestructureException("Client not found");
        }
        return ClientMapper.INSTANCE.domainToDto(clientEntityList.getFirst());
    }

    @Override
    public Client update(Client client, Integer id) {
        Optional<ClientEntity> clientEntity = clientEntityRepository.findById(id);

        if(!clientEntity.isPresent()) {
            throw new InfraestructureException("Client not found");
        }

        ClientEntity clientEntityNew = ClientMapper.INSTANCE.dtoToDomain(client);

        ClientMapper.INSTANCE.updatedClientEntity(clientEntityNew, clientEntity.get());

        return ClientMapper.INSTANCE.domainToDto(clientEntity.get());
    }


}
