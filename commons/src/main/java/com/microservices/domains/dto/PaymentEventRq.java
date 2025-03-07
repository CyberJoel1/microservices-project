package com.microservices.domains.dto;

import com.microservices.domains.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEventRq {

    @NotNull
    @Schema(description = "Amount to affect", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull
    @Schema(description = "Account to affect")
    private String accountNumber;

    @NotNull
    @NotBlank
    @Schema(description = "Type of payment", allowableValues = {"D", "C"})
    @Pattern(regexp = "[DC]", message = "Payment type not valid")
    private PaymentType paymentType;

}
