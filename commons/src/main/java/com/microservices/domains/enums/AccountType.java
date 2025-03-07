package com.microservices.domains.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Available account types")
public enum AccountType {
    @Schema(description = "Savings account")
    SAVING("S"),
    @Schema(description = "Checking account")
    CHECKING("C");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonValue
    public String getAccountType() {
        return accountType;
    }

    @JsonCreator
    public static AccountType create (String value){
        if(value == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }
        for(AccountType v : values()) {
            if(value.equals(v.getAccountType())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Account type " + value + " does not exist");
    }
}
