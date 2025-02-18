package com.microservices.domains;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id;
    private String accountNumber;
    private String type;
    private AccountBalance accountBalance;
    private String status;
    private Client client;
    List<Movement> movements;
}
