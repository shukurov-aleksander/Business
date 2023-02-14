package com.ku.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id")

    private Service service;

    public Storage() {
    }

    public Storage(Long id, Integer quantity, Company company, Service service) {
        this.id = id;
        this.quantity = quantity;
        this.company = company;
        this.service = service;
    }

    public Storage(long id) {
        this.id = id;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company companyId) {
        this.company = companyId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service serviceId) {
        this.service = serviceId;
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

        if (getCompany() == null) {
            if (aThat.getCompany() != null) {return false;}
        } else if (!getCompany().equals(aThat.getCompany())) {return false;}

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
        result = prime * result + (company == null ? 0 : company.hashCode());
        result = prime * result + (service == null ? 0 : service.hashCode());
        return result;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {")
                .append("id=").append(getId())
                .append(", quantity=").append(getQuantity())
                .append("}");
        return stringBuilder.toString();
    }
}
