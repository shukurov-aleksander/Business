package com.ku.business.entity;


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
        Content aThat = (Content) obj;
        if ((this.id == null && aThat.id != null) || (this.id != null && aThat.id == null)) {return false;}
        if ((this.quantity == null && aThat.quantity != null) || (this.quantity != null && aThat.quantity == null)) {return false;}
        if ((this.serviceId == null && aThat.serviceId != null) || (this.serviceId != null && aThat.serviceId == null)) {return false;}
        if ((this.orders == null && aThat.orders != null) || (this.orders != null && aThat.orders == null)) {return false;}
        if ((this.orders != null && aThat.orders != null) && (this.orders.size() == aThat.orders.size())) {
            for (int i = 0; i < orders.size(); i++) {
                if (!orders.get(0).getId().equals(aThat.orders.get(0).getId()))
                {return false;}
            }
        }
        return (((this.id == aThat.id) && (aThat.id == null)) || (this.id.equals(aThat.id))) &&
                (((this.quantity == aThat.quantity) && (aThat.quantity == null)) || (this.quantity.equals(aThat.quantity))) &&
                (((this.serviceId == aThat.serviceId) && (aThat.serviceId == null)) || (this.serviceId.equals(aThat.serviceId)));}

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (quantity == null ? 0 : quantity.hashCode());
        result = prime * result + (serviceId == null ? 0 : serviceId.hashCode());
        if(orders!=null) {
            for (Order order : orders
            ) {
                result = prime * result + (order!=null && order.getId()!=null ?  (order.getId().hashCode()) : 0);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder(" " + orders.get(0).getClass().getTypeName() + "{ contains");
        for (Order order1 : orders
        ) {
            order.append(" [ detail {" + order1.getId() + "},");
        }
        if (order.length() > 0) {order.setLength(order.length()-1);}
        order.append(" ]");
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", quantity=" + quantity +
                " , serviceId=" + serviceId.toString() +
                "orderList=" + order + "]";
    }
}
