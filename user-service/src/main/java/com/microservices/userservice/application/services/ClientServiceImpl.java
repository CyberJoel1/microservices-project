package com.microservices.userservice.application.services;

import com.microservices.domains.Client;
import com.microservices.domains.dto.*;
import com.microservices.domains.enums.Status;
import com.microservices.userservice.application.mapper.ClientMapper;
import com.microservices.userservice.domain.ports.in.ClientService;
import com.microservices.userservice.domain.ports.out.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientPSTRs create(ClientPSTRq clientPSTRq) {
        clientPSTRq.getClient().setStatus(Status.ACTIVE);
        return (
                ClientMapper.INSTANCE.domainToDtoPST(
                        clientRepository.create(clientPSTRq.getClient())
                )
        );
    }

    @Override
    public ClientGetRs findByIdentification(String identification) {
        return (ClientMapper.INSTANCE.domainToDtoGet(
                clientRepository.findByIdentification(identification)
        )
        );
    }

    @Override
    public ClientGetRs findById(String id) {
        return (ClientMapper.INSTANCE.domainToDtoGet(
                clientRepository.findById(id)
        )
        );
    }

    @Override
    public ClientPTCRs update(ClientPTCRq clientPTCRq, String id) {

        return (
                ClientMapper.INSTANCE.domainToDtoPTC(
                        clientRepository.update(clientPTCRq.getClient(), Integer.parseInt(id))
                )
                );
    }

    @Override
    public void delete(String id) {
        ClientPTCRq clientPTCRq = new ClientPTCRq();
        clientPTCRq.setClient(new Client());
        clientPTCRq.getClient().setStatus(Status.NON_ACTIVE);
        clientRepository.update(clientPTCRq.getClient(), Integer.parseInt(id));
    }
}
