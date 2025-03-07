package com.microservices.transactionservice.infraestructure.entities.converters;

import com.microservices.domains.enums.PaymentType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, String> {

    @Override
    public String convertToDatabaseColumn(PaymentType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getPaymentType();
    }

    @Override
    public PaymentType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (PaymentType type : PaymentType.values()) {
            if (type.getPaymentType().equals(dbData)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}

