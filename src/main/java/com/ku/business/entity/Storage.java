package com.ku.business.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "storages", schema = "public")
public class Storage {
    @Id
    @Column(name = "id")
    private Long id;
    private Integer quantity;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "company_id")
    private Company companyId;
    @OneToOne
    @JoinColumn(name="service_id")

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
        Storage aThat = (Storage) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getQuantity() == null) {
            if (aThat.getQuantity() != null) {return false;}
        } else if (!getQuantity().equals(aThat.getQuantity())) {return false;}

        if (getCompanyId() == null) {
            if (aThat.getCompanyId() != null) {return false;}
        } else if (!getCompanyId().equals(aThat.getCompanyId())) {return false;}

        if (getServiceId() == null) {
            if (aThat.getServiceId() != null) {return false;}
        } else if (!getServiceId().equals(aThat.getServiceId())) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (quantity == null ? 0 : quantity.hashCode());
        result = prime * result + (companyId == null ? 0 : companyId.hashCode());
        result = prime * result + (serviceId == null ? 0 : serviceId.hashCode());
        return result;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {")
                .append("id=").append(getId())
                .append(", quantity=").append(getQuantity());
        if (getCompanyId()!= null) {
            stringBuilder.append(", companyId=").append(getCompanyId().toString());
        }
        if (getServiceId()!= null) {
            stringBuilder.append(", serviceId=").append(getServiceId().toString());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
