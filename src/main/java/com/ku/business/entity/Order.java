package com.ku.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "created_at_utc")
    private LocalDateTime createdAtUtc;
    @Column(name = "completed_at_utc")
    private LocalDateTime completedAtUtc;
    @ManyToMany
    @JoinTable(
                name = "order_content_links",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "content_id")

    )
    private List<Content> contents;

    public Order() {
    }

    public Order(Long id, LocalDateTime createdAtUtc, LocalDateTime completedAtUtc, List<Content> contents, OrderStatus orderStatus) {
        this.id = id;
        this.createdAtUtc = createdAtUtc;
        this.completedAtUtc = completedAtUtc;
        this.contents = contents;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(LocalDateTime createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public LocalDateTime getCompletedAtUtc() {
        return completedAtUtc;
    }

    public void setCompletedAtUtc(LocalDateTime completedAtUtc) {
        this.completedAtUtc = completedAtUtc;
    }

   public OrderStatus getOrderStatus() {
       return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
       this.orderStatus = orderStatus;
   }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contentList) {
        this.contents = contentList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Order aThat = (Order) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getCreatedAtUtc() == null) {
            if (aThat.getCreatedAtUtc() != null) {return false;}
        } else if (!getCreatedAtUtc().equals(aThat.getCreatedAtUtc())) {return false;}

        if (getCompletedAtUtc() == null) {
            if (aThat.getCompletedAtUtc() != null) {return false;}
        } else if (!getCompletedAtUtc().equals(aThat.getCompletedAtUtc())) {return false;}

        if (getOrderStatus() != aThat.getOrderStatus()) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result +  (id == null ? 0 : id.hashCode());
        result = prime * result + (createdAtUtc == null ? 0 : createdAtUtc.hashCode());
        result = prime * result + (completedAtUtc == null ? 0 : completedAtUtc.hashCode());
        result = prime * result + (orderStatus == null ? 0 : orderStatus.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {id=").append(getId())
                .append(", orderStatus=").append(getOrderStatus())
                .append(", createdAtUtc=").append(getCreatedAtUtc())
                .append(", completedAtUtc=").append(getCompletedAtUtc())
                .append("}");
        return stringBuilder.toString();
    }
}
