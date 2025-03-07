package com.microservices.userservice.infraestructure.entities;


import com.microservices.domains.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client", schema = "demo_db")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_secuencial")
    private Integer code;

    @Column(name = "cl_password")
    private String password;

    @Column(name = "cl_status")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cl_id_person", referencedColumnName = "pr_secuencial")
    private PersonEntity personEntity;

}
