package com.ku.business.dto.detail;

import com.ku.business.dto.company.CompanyDTO;
import com.ku.business.dto.order.OrderDTO;
import com.ku.business.entity.OperationType;

public class DetailDTO {
    Long id;
    CompanyDTO company;
    OrderDTO order;
    OperationType operationType;

    public DetailDTO(Long id, CompanyDTO company, OrderDTO order, OperationType operationType) {
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

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
