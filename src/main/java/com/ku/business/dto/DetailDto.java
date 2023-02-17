package com.ku.business.dto;

import com.ku.business.entity.OperationType;

public class DetailDto {
    Long id;
    CompanyDto company;
    OrderDto order;
    OperationType operationType;

    public DetailDto(
            Long id,
            CompanyDto company,
            OrderDto order,
            OperationType operationType
    ) {
        this.id = id;
        this.company = company;
        this.order = order;
        this.operationType = operationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
