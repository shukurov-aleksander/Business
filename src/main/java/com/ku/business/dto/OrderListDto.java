package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;

public class OrderListDto {
    Long id;
    OrderStatus orderStatus;

    public OrderListDto(Long id, OrderStatus orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
