package com.microservices.domains;


import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Type Account")
    private String type;

    @Schema(description = "Amount of a movement")
    private BigDecimal amount;

    @Schema(description = "Remaining balance after a transaction")
    private BigDecimal remainingBalance;

    Account account;

}
