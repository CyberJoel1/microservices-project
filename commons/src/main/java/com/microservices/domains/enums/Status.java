package com.microservices.domains.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Available statuses of object")
public enum Status {

    @Schema(description = "Active")
    ACTIVE(true),
    @Schema(description = "Not active")
    NON_ACTIVE(false);

    private final Boolean status;

    Status(Boolean status) {
        this.status = status;
    }

    @JsonValue
    public Boolean getStatus() {
        return status;
    }

    @JsonCreator
    public static Status create (Boolean value){
        if(value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        for(Status v : values()) {
            if(value.equals(v.getStatus())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }
}
