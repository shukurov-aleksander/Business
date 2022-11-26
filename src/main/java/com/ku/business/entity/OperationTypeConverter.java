package com.ku.business.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, String> {
    @Override
    public String convertToDatabaseColumn(OperationType operationType) {
        return operationType.getShortName();
    }

    @Override
    public OperationType convertToEntityAttribute(String dbData) {
        return OperationType.fromShortName(dbData);
    }
}
