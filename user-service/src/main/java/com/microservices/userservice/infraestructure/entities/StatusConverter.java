package com.microservices.userservice.infraestructure.entities;

import com.microservices.domains.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(Status status) {
        return (status != null) ? status.getStatus() : null;
    }

    @Override
    public Status convertToEntityAttribute(Boolean status) {
        return Status.create(status);
    }
}
