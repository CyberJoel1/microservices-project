package com.microservices.domains.dto;

import com.microservices.domains.Movement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementPTCRq {
    Movement movement;
}
