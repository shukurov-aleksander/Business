package org.example.entity;

public class Storage {
    private long id;
    private int quantity;
    private long companyId;
    private long serviceId;

    public Storage() {
    }

    public Storage(long id, int quantity, long companyId, long serviceId) {
        this.id = id;
        this.quantity = quantity;
        this.companyId = companyId;
        this.serviceId = serviceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Storage storage = (Storage) obj;
        return id == storage.id &&
                quantity == storage.quantity &&
                companyId == storage.companyId &&
                serviceId == storage.serviceId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (id - (id >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (int) (companyId - (companyId >>> 32));
        result = 31 * result + (int) (serviceId - (serviceId >>> 32));
        return result;
    }
    public String toString() {
        return "Storage [" +
                "id=" + id +
                "quantity=" + quantity +
                ", companyId=" + companyId +
                ", serviceId=" + serviceId + "]";
    }
}
