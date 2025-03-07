package com.microservices.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about a person")
public class Person {

    @Schema(description = "Person ID")
    private Integer id;

    @Schema(description = "Name of a person")
    private String name;

    @Schema(description = "Gender of a person")
    private String gender;

    @Max(value = 200, message = "The age cannot exceed 200")
    @Schema(description = "Age of a person",
            example = "150", type = "integer",
            maximum = "200")
    @Positive
    @Min(value = 18, message = "the customer must be of legal age")
    private Integer age;

    @Schema(description = "Identification of a person")
    private String identification;

    @Schema(description = "Address of a person")
    private String address;

    @Schema(description = "Phone of a person")
    private String phoneNumber;
}
