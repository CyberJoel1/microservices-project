package com.microservices.domains;

import com.microservices.domains.enums.AccountType;
import com.microservices.domains.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
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

    @Schema(description = "Type of account", allowableValues = {"S", "C"})
    @Pattern(regexp = "[SC]", message = "Type account not valid")
    private AccountType type;

    private AccountBalance accountBalance;

    @Schema(description = "Account status", allowableValues = {"true", "false"})
    private Status status;

    private Client client;

    private List<Movement> movements;
}