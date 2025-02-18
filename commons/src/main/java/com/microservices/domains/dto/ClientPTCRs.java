package com.microservices.domains.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservices.domains.Client;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientPTCRs {
    Client client;
}
