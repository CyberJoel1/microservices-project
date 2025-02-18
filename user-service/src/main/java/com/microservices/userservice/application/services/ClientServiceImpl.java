package com.microservices.userservice.application.services;

import com.microservices.domains.Client;
import com.microservices.domains.dto.*;
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
        clientPTCRq.getClient().setStatus("C");
        clientRepository.update(clientPTCRq.getClient(), Integer.parseInt(id));
    }
}
