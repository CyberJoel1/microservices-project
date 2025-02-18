package com.microservices.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {
    private Integer clientId;
    private String password;
    private String status;
}
