package com.ku.buiseness.entity;


import java.util.Arrays;
import java.util.List;

public class Content {
    private Long id;
    private Long quantity;
    private Service serviceId;
    private List<Order> orders;

    public Content() {
    }

    public Content(Long id, Long quantity, Service serviceId, List<Order> orders) {
        this.id = id;
        this.quantity = quantity;
        this.serviceId = serviceId;
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

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        Content content = (Content) obj;
        return id.equals(content.id) &&
                quantity.equals(content.quantity) &&
                serviceId.equals(content.serviceId) &&
                orders.equals(content.orders);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (quantity == null ? 0 : quantity.hashCode());
        result = prime * result + (serviceId == null ? 0 : serviceId.hashCode());
        for (Order order : orders) {
            result = prime * result + (order == null ? 0 : order.hashCode());
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", quantity=" + quantity +
                " , serviceId=" + serviceId.toString() +
                "orderList=" + orders.toString() + "]";
    }
}
