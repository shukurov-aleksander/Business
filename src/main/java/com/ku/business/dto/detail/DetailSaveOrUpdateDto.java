package com.ku.business.dto.detail;

import com.ku.business.entity.OperationType;
import com.ku.business.entity.Order;

public class DetailSaveOrUpdateDto {
    Long id;
    Order order;
    OperationType operationType;

    public DetailSaveOrUpdateDto(Long id, Order order, OperationType operationType) {
        this.id = id;
        this.order = order;
        this.operationType = operationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
