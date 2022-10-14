package com.ku.buiseness.entity;

public class Detail {
    private Long id;
    private Company companyId;
    private Order orderId;
    private OperationType operationType;

    public Detail() {
    }

    public Detail(Long id, Company companyId, Order orderId) {
        this.id = id;
        this.companyId = companyId;
        this.orderId = orderId;
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

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) return true;
        if (aThat == null || getClass() != aThat.getClass()) return false;
        Detail detail = (Detail) aThat;
        return id.equals(detail.id) &&
                companyId.equals(detail.companyId) &&
                orderId.equals(detail.orderId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (companyId.getId()==null ? 0 : companyId.hashCode());
        result = prime * result + (orderId.getId() == null ? 0 : orderId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", companyId='" + companyId.toString() +
                "' , orderId='" + orderId.toString() +
                "' , operationType= '" + operationType + "']";
    }

}
