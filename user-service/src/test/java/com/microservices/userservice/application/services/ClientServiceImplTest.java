package com.microservices.userservice.application.services;

import com.microservices.domains.Client;
import com.microservices.domains.dto.*;
import com.microservices.userservice.application.mapper.ClientMapper;
import com.microservices.userservice.domain.ports.in.ClientService;
import com.microservices.userservice.domain.ports.out.ClientRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    private ClientPSTRq clientPSTRq;
    private ClientGetRs clientGetRs;
    private ClientPTCRs clientPTCRs;
    private Client client;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        client = new Client();
        client.setId(1);
        client.setStatus("A");


        clientPSTRq = new ClientPSTRq();
        clientPSTRq.setClient(client);

        clientGetRs = new ClientGetRs();
        clientGetRs.setClient(new Client());
        clientGetRs.getClient().setId(1);
        clientGetRs.getClient().setName("jds");
        clientGetRs.getClient().setStatus("A");

        clientPTCRs = new ClientPTCRs();
        Client clientInstance = new Client();
        clientInstance.setId(1);
        clientInstance.setStatus("A");
        clientInstance.setName("jds");
        clientPTCRs.setClient(clientInstance);
    }


    @Test
    void testCreate() {


        when(clientRepository.create(any(Client.class))).thenReturn(client);


        when(clientMapper.domainToDtoPST(any(Client.class))).thenReturn(new ClientPSTRs());

        ClientPSTRs result = clientServiceImpl.create(clientPSTRq);

        verify(clientRepository, times(1)).create(any(Client.class));


        assertNotNull(result);
    }

    @Test
    void testFindByIdentification() {


        ClientGetRs clientGetRs = new ClientGetRs();
        clientGetRs.setClient(new Client());
        clientGetRs.getClient().setId(1);
        clientGetRs.getClient().setName("jds");
        clientGetRs.getClient().setStatus("A");

        when(clientRepository.findByIdentification(anyString())).thenReturn(client);


        assertNotNull(clientServiceImpl.findByIdentification("123"));
    }



    @Test
    void testUpdate() {
        ClientPTCRq clientPTCRq = new ClientPTCRq();
        clientPTCRq.setClient(client);


        when(clientRepository.update(any(Client.class), anyInt())).thenReturn(client);

        when(clientMapper.domainToDtoPTC(any())).thenReturn(clientPTCRs);

        assertNotNull(clientServiceImpl.update(clientPTCRq, "1").getClient());
    }

    @Test
    void testDelete() {
        ClientPTCRq clientPTCRq = new ClientPTCRq();
        clientPTCRq.setClient(client);

        clientServiceImpl.delete("1");

        verify(clientRepository, times(1)).update(any(Client.class), eq(1));
    }
}