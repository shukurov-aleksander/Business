package org.example.entity;

public class Order {
    private long id;
    private long supplierId;
    private long companyId;
    private enum orderStatus {
        PROCESSING,
        MADE,
        COMPLETE
    };

    public Order() {
    }

    public Order(long id, long supplierId, long companyId) {
        this.id = id;
        this.supplierId = supplierId;
        this.companyId = companyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        Order order = (Order) obj;
        return id == order.id &&
                supplierId == order.supplierId &&
                companyId == order.companyId;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (int) (id - (id >>> 32));
        result = prime * result + (int) (supplierId - (supplierId >>> 32));
        result = prime * result + (int) (companyId - (companyId >>> 32));
        return result;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", supplierID=" + supplierId +
                " , companyID=" + companyId+"]";
    }
}
