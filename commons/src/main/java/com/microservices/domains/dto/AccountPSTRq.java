package com.microservices.domains.dto;


import com.microservices.domains.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountPSTRq {
    Account account;
}
