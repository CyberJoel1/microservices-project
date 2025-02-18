package com.microservices.transactionservice.infraestructure.entities;


import com.microservices.domains.Account;
import jakarta.persistence.*;
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
    private String type;

    @Column(name = "mv_amount")
    private BigDecimal amount;

    @Column(name = "mv_remaining")
    private BigDecimal remainingBalance;

    @Column(name = "mv_account")
    private Integer accountId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mv_account", referencedColumnName = "ac_secuencial")
    @MapsId("accountId")
    private AccountEntity account;
}

