package com.microservices.userservice.domain.ports.out;


import com.microservices.domains.Client;

public interface ClientRepository {
    Client create(Client client);
    Client findByIdentification(String identification);
    Client update(Client client, Integer id);
}
