package com.ku.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

import java.util.List;

@Entity
@Table(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private Long quantity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id")
    private Service service;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                name = "order_content_links",
                joinColumns = @JoinColumn(name = "content_id"),
                inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;

    public Content() {
    }

    public Content(Long id, Long quantity, Service service, List<Order> orders) {
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

    public Service getService() {
        return service;
    }

    public void setService(Service serviceId) {
        this.service = serviceId;
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

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getQuantity() == null) {
            if (aThat.getQuantity() != null) {return false;}
        } else if (!getQuantity().equals(aThat.getQuantity())) {return false;}

        if (getService() == null) {
            if (aThat.getService() != null) {return false;}
        } else if (!getService().equals(aThat.getService())) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (quantity == null ? 0 : quantity.hashCode());
        result = prime * result + (service == null ? 0 : service.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(getClass().getSimpleName())
               .append(" {id=").append(getId())
               .append(", quantity=").append(getQuantity())
               .append("}");
        return stringBuilder.toString();
    }
}
