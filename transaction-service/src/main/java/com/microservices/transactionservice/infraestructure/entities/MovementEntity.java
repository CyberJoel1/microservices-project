package com.microservices.transactionservice.infraestructure.entities;


import com.microservices.domains.enums.PaymentType;
import com.microservices.domains.enums.Status;
import com.microservices.transactionservice.infraestructure.entities.converters.PaymentTypeConverter;
import com.microservices.transactionservice.infraestructure.entities.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movement", schema = "demo_db")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mv_secuencial")
    private Integer code;

    @Column(name = "mv_date")
    private LocalDateTime date;

    @Column(name = "mv_type")
    @Convert(converter = PaymentTypeConverter.class)
    private PaymentType type;

    @Column(name = "mv_amount")
    @PositiveOrZero
    private BigDecimal amount;

    @Column(name = "mv_remaining")
    private BigDecimal remainingBalance;

    @Column(name = "mv_account")
    private Integer accountId;

    @Column(name = "mv_status")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mv_account", referencedColumnName = "ac_secuencial")
    @MapsId("accountId")
    private AccountEntity account;
}

