package com.microservices.userservice.domain.ports.in;

import com.microservices.domains.Client;
import com.microservices.domains.dto.*;

public interface ClientService {

    ClientPSTRs create(ClientPSTRq clientPSTRq);
    ClientGetRs findByIdentification(String identification);
    ClientGetRs findById(String id);
    ClientPTCRs update(ClientPTCRq clientPTCRq, String id);
    void delete(String id);


}
