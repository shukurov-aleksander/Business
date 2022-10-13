package com.ku.buiseness.entity;

public class Storage {
    private Long id;
    private Integer quantity;
    private Company companyId;
    private Service serviceId;

    public Storage() {
    }

    public Storage(Long id, Integer quantity, Company companyId, Service serviceId) {
        this.id = id;
        this.quantity = quantity;
        this.companyId = companyId;
        this.serviceId = serviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Storage storage = (Storage) obj;
        return id.equals(storage.id) &&
                quantity.equals(storage.quantity) &&
                companyId.equals(storage.companyId) &&
                serviceId.equals(storage.serviceId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + quantity;
        result = prime * result + (companyId == null ? 0 : companyId.hashCode());
        result = prime * result + (serviceId == null ? 0 : serviceId.hashCode());
        return result;
    }
    public String toString() {
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                "quantity=" + quantity +
                ", companyId=" + companyId.toString() +
                ", serviceId=" + serviceId.toString() + "]";
    }
}
