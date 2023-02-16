package com.ku.business.dto.content;

import com.ku.business.dto.order.OrderListDto;
import com.ku.business.dto.service.ServiceDto;

import java.util.List;

public class ContentDto {
    Long id;
    Long quantity;
    ServiceDto service;
    List<OrderListDto> orders;

    public ContentDto(
            Long id,
            Long quantity,
            ServiceDto service,
            List<OrderListDto> orders
    ) {
        this.id = id;
        this.quantity = quantity;
        this.service = service;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public List<OrderListDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderListDto> orders) {
        this.orders = orders;
    }
}
