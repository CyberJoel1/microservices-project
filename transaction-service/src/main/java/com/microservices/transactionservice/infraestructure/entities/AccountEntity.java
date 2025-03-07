package com.microservices.transactionservice.infraestructure.entities;

import com.microservices.domains.enums.AccountType;
import com.microservices.domains.enums.Status;
import com.microservices.transactionservice.infraestructure.entities.converters.AccountTypeConverter;
import com.microservices.transactionservice.infraestructure.entities.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
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
    @Convert(converter = AccountTypeConverter.class)
    private AccountType type;

    @Column(name = "ac_balance")
    @PositiveOrZero
    private BigDecimal accountBalance;

    @Column(name = "ac_status")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @Column(name = "ac_identification_client")
    private Integer identification;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovementEntity> movementEntity;
}

