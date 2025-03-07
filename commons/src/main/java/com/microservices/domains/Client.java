package com.microservices.domains;

import com.microservices.domains.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {

    @Schema(description = "Id client")
    private Integer clientId;

    @Schema(description = "password client")
    private String password;

    @Schema(description = "Client status", allowableValues = {"true", "false"})
    private Status status;
}
