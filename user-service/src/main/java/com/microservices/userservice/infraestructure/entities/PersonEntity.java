package com.microservices.userservice.infraestructure.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "person", schema = "demo_db")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_secuencial")
    private Integer code;

    @Column(name = "pr_name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    @Positive
    @Max(value = 200, message = "The age cannot exceed 200")
    @Min(value = 18, message = "the customer must be of legal age")
    private Integer age;

    @Column(name = "identification")
    private String identification;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToOne(mappedBy = "personEntity", cascade = CascadeType.ALL)
    ClientEntity clientEntity;
}

