package com.microservices.domains.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Available payment types")
public enum PaymentType {
    @Schema(description = "Credit note")
    CREDIT("C"),
    @Schema(description = "Debit note")
    DEBIT("D");

    private final String paymentType;

    PaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonValue
    public String getPaymentType() {
        return paymentType;
    }

    @JsonCreator
    public static PaymentType create (String value){
        if(value == null) {
            throw new IllegalArgumentException("Payment type cannot be null");
        }
        for(PaymentType v : values()) {
            if(value.equals(v.getPaymentType())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Payment type " + value + " does not exist");
    }

}
