package com.ku.business.entity;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Detail aThat = (Detail) obj;
        if ((this.id == null && aThat.id != null) || (this.id != null && aThat.id == null)) {return false;}
        if ((this.companyId == null && aThat.companyId != null) || (this.companyId != null && aThat.companyId == null)) {return false;}
        if ((this.orderId == null && aThat.orderId  != null) || (this.orderId  != null && aThat.orderId  == null)) {return false;}
        return (((this.id == aThat.id) && (aThat.id == null)) || (this.id.equals(aThat.id))) &&
                (((this.companyId == aThat.companyId) && (aThat.companyId == null)) || (this.companyId.equals(aThat.companyId))) &&
                (((this.orderId == aThat.orderId) && (aThat.orderId == null)) || (this.orderId.equals(aThat.orderId)));
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
