package com.microservices.transactionservice.infraestructure.entities;

import com.microservices.domains.AccountBalance;
import com.microservices.domains.Client;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account", schema = "demo_Db")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_secuencial")
    private Integer code;

    @Column(name = "ac_number")
    private String accountNumber;

    @Column(name = "ac_type")
    private String type;

    @Column(name = "ac_balance")
    private BigDecimal accountBalance;

    @Column(name = "ac_status")
    private String status;

    @Column(name = "ac_identification_client")
    private Integer identification;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovementEntity> movementEntity;
}

