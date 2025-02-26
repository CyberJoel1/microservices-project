package com.microservices.domains.dto;

import com.microservices.domains.Movement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementPSTRq {
    private String accountNumber;
    private Movement movement;
}
