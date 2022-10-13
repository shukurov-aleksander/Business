package com.ku.buiseness.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private OrderStatus orderStatus;
    private Date createdAtUtc;
    private Date completedAtUtc;
    private List<Content> contents;

    public Order() {
    }

    public Order(Long id, Date createdAtUtc, Date completedAtUtc, List<Content> contents) {
        this.id = id;
        this.createdAtUtc = createdAtUtc;
        this.completedAtUtc = completedAtUtc;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(Date createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public Date getCompletedAtUtc() {
        return completedAtUtc;
    }

    public void setCompletedAtUtc(Date completedAtUtc) {
        this.completedAtUtc = completedAtUtc;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Content> getContentList() {
        return contents;
    }

    public void setContentList(List<Content> contentList) {
        this.contents = contentList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Order order = (Order) obj;
        return id.equals(order.id) &&
                createdAtUtc.equals(order.createdAtUtc) &&
                completedAtUtc.equals(order.completedAtUtc) &&
                orderStatus.equals(order.orderStatus) &&
                contents.equals(order.contents);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result +  (id == null ? 0 : id.hashCode());
        result = prime * result + (createdAtUtc == null ? 0 : createdAtUtc.hashCode());
        result = prime * result + (completedAtUtc == null ? 0 : completedAtUtc.hashCode());
        result = prime * result + (orderStatus == null ? 0 : orderStatus.hashCode());
        for (Content content : contents) {
            result = prime * result + (content == null ? 0 : content.hashCode());
        }
        return result;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", createdAtUtc=" + createdAtUtc +
                " , completedAtUtc=" + completedAtUtc+
                "orderStatus="+ orderStatus +
                "contentList="+ contents.toString() + "]";
    }
}
