package com.microservices.domains;


import com.microservices.domains.enums.PaymentType;
import com.microservices.domains.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement {

    @Schema(description = "Id movement")
    private Integer id;

    @Schema(description = "Date of a movement")
    private LocalDateTime date;

    @NotNull
    @NotBlank
    @Schema(description = "Type of payment", allowableValues = {"D", "C"})
    @Pattern(regexp = "[DC]", message = "Payment type not valid")
    private PaymentType type;

    @Schema(description = "Amount of a movement")
    private BigDecimal amount;

    @Schema(description = "Remaining balance after a transaction")
    private BigDecimal remainingBalance;

    @Schema(description = "Movement status", allowableValues = {"true", "false"})
    private Status status;

    Account account;
}
