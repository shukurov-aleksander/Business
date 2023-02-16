package com.ku.business.dto.content;

import com.ku.business.dto.order.OrderListDTO;
import com.ku.business.dto.service.ServiceDTO;

import java.util.List;

public class ContentDTO {
    Long id;
    Long quantity;
    ServiceDTO service;
    List<OrderListDTO> orders;

    public ContentDTO(Long id, Long quantity, ServiceDTO service, List<OrderListDTO> orders) {
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

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public List<OrderListDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderListDTO> orders) {
        this.orders = orders;
    }
}
