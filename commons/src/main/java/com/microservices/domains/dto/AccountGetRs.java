package com.microservices.domains.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservices.domains.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountGetRs {
    Account account;
}
