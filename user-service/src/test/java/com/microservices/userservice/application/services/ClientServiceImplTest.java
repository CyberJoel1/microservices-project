package com.microservices.userservice.application.services;

import com.microservices.domains.Client;
import com.microservices.domains.dto.*;
import com.microservices.domains.enums.Status;
import com.microservices.userservice.domain.ports.out.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Client client = new Client();
        client.setName("John Doe");
        ClientPSTRq clientPSTRq = new ClientPSTRq();
        clientPSTRq.setClient(client);

        when(clientRepository.create(any(Client.class))).thenReturn(client);
        ClientPSTRs result = clientServiceImpl.create(clientPSTRq);

        assertNotNull(result);
        assertEquals("John Doe", result.getClient().getName());
        verify(clientRepository, times(1)).create(any(Client.class));
    }

    @Test
    void testFindByIdentification() {
        String identification = "12345";
        Client client = new Client();
        client.setName("Jane Doe");

        when(clientRepository.findByIdentification(anyString())).thenReturn(client);
        ClientGetRs result = clientServiceImpl.findByIdentification(identification);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getClient().getName());
        verify(clientRepository, times(1)).findByIdentification(anyString());
    }

    @Test
    void testUpdate() {
        String id = "1";
        Client client = new Client();
        client.setName("John Smith");
        ClientPTCRq clientPTCRq = new ClientPTCRq();
        clientPTCRq.setClient(client);

        when(clientRepository.update(any(Client.class), anyInt())).thenReturn(client);
        ClientPTCRs result = clientServiceImpl.update(clientPTCRq, id);

        assertNotNull(result);
        assertEquals("John Smith", result.getClient().getName());
        verify(clientRepository, times(1)).update(any(Client.class), anyInt());
    }

    @Test
    void testDelete() {
        String id = "1";
        Client client = new Client();
        client.setStatus(Status.NON_ACTIVE);

        when(clientRepository.update(any(Client.class), anyInt())).thenReturn(client);
        clientServiceImpl.delete(id);

        verify(clientRepository, times(1))
                .update(argThat(argument -> "C".equals(argument.getStatus())), anyInt());
    }
}