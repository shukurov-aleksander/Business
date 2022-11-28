package com.ku.business.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "company_id")
    private Company companyId;
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    public Detail() {
    }

    public Detail(Long id, Company companyId, Order order, OperationType operationType) {
        this.id = id;
        this.companyId = companyId;
        this.order = order;
        this.operationType = operationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderId) {
        this.order = orderId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Detail aThat = (Detail) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getCompanyId() == null) {
            if (aThat.getCompanyId() != null) {return false;}
        } else if (!getCompanyId().equals(aThat.getCompanyId())) {return false;}

        if (getOperationType() != aThat.getOperationType()) {return false;}

        if (getOrder() == null) {
            if (aThat.getOrder() != null) {return false;}
        } else if (!getOrder().equals(aThat.getOrder())) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (companyId == null ? 0 : companyId.hashCode());
        result = prime * result + (order == null ? 0 : order.hashCode());
        result = prime * result + (operationType == null ? 0 : operationType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {id=").append(getId())
                .append(", companyId=").append(getCompanyId())
                .append(", orderId=").append(getOrder())
                .append(", operationType=").append(getOperationType())
                .append("}");
        return  stringBuilder.toString();
    }
}
