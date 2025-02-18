package com.microservices.domains;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Integer id;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private BigDecimal remainingBalance;
    Account account;

}
