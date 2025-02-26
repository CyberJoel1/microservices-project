package com.microservices.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Schema(description = "Account information")
public class Account {

    @Schema(description = "Account ID")
    private Integer id;

    @Schema(description = "Account number")
    private String accountNumber;

    @Schema(description = "Type of account")
    private String type;

    private AccountBalance accountBalance;

    @Schema(description = "Account status", allowableValues = {"VIGENTE", "NO VIGENTE"})
    private String status;

    private Client client;

    private List<Movement> movements;
}