package com.ku.business.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "tax_number")
    private String taxNumber;
    @Column(name = "is_government_agency")
    private Boolean isGovernmentAgency;
    @Column(name = "user_id")
    private Long userId;
    @OneToMany(
            mappedBy = "companyId",
            fetch = FetchType.LAZY
    )
    private List<Storage> storages;
    @OneToMany(
            mappedBy = "companyId",
            fetch = FetchType.LAZY
    )
    private Set<Detail> details;

    public Company() {
    }

    public Company(
            Long id,
            String companyName,
            String taxNumber,
            Boolean isGovernmentAgency,
            Long userId,
            List<Storage> storages,
            Set<Detail> details
    ) {
        this.id = id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.isGovernmentAgency = isGovernmentAgency;
        this.userId = userId;
        this.storages = storages;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Boolean isGovernmentAgency() {
        return isGovernmentAgency;
    }

    public void setGovernmentAgency(Boolean governmentAgency) {
        this.isGovernmentAgency = governmentAgency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    public Set<Detail> getDetails() {
        return details;
    }

    public void setDetails(Set<Detail> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Company aThat = (Company) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getCompanyName() == null) {
            if (aThat.getCompanyName() != null) {return false;}
        } else if (!getCompanyName().equals(aThat.getCompanyName())) {return false;}

        if (getTaxNumber() == null) {
            if (aThat.getTaxNumber() != null) {return false;}
        } else if (!getTaxNumber().equals(aThat.getTaxNumber())) {return false;}

        if (isGovernmentAgency() == null) {
            if (aThat.isGovernmentAgency() != null) {return false;}
        } else if (!isGovernmentAgency().equals(aThat.isGovernmentAgency())) {return false;}

        if (getUserId() == null) {
            if (aThat.getUserId() != null) {return false;}
        } else if (!getUserId().equals(aThat.getUserId())) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (isGovernmentAgency == null ? 0 : (isGovernmentAgency ? 0 : 1));
        result = prime * result + (companyName == null ? 0 : companyName.hashCode());
        result = prime * result + (taxNumber == null ? 0 : taxNumber.hashCode());
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {id=").append(getId())
                .append(", companyName=").append(getCompanyName())
                .append(", taxNumber=").append(getTaxNumber())
                .append(", isGovernmentAgency=").append(isGovernmentAgency())
                .append(", uerId=").append(getUserId())
                .append("}");
        return stringBuilder.toString();
    }
}
