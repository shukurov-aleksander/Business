//package com.ku.business.entity;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class OperationTypeConverter implements AttributeConverter<OperationType, String> {
//    @Override
//    public String convertToDatabaseColumn(OperationType operationType) {
//        return operationType.toString() + ":operation_type_enum";
//    }
//
//    @Override
//    public OperationType convertToEntityAttribute(String dbData) {
//        return OperationType.valueOf(dbData);
//    }
//}
