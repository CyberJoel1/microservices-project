package com.microservices.domains.dto;

import com.microservices.domains.Client;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientPSTRq {
    Client client;
}
