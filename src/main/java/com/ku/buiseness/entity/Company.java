package com.ku.buiseness.entity;

import java.util.Arrays;
import java.util.List;

public class Company {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;
    private Long userId;
    private List<Storage> storages;
    private List<Detail> details;

    public Company() {
    }

    public Company(
            Long id,
            String companyName,
            String taxNumber,
            Boolean isGovernmentAgency,
            Long userId,
            List<Storage> storages,
            List<Detail> details
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

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Company company = (Company) obj;
        return id.equals(company.id) &&
                isGovernmentAgency.equals(company.isGovernmentAgency) &&
                companyName.equals(company.companyName) &&
                taxNumber.equals(company.taxNumber) &&
                userId.equals(company.userId) &&
                storages.equals(company.storages) &&
                details.equals(company.details);

    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (isGovernmentAgency ? 0 : 1);
        result = prime * result + (companyName == null ? 0 : companyName.hashCode());
        result = prime * result + (taxNumber == null ? 0 : taxNumber.hashCode());
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        for (Storage storage : storages) {
            result = prime * result + (storage == null ? 0 : storage.hashCode());
        }
        for (Detail detail : details) {
            result = prime * result + (detail == null ? 0 : detail.hashCode());
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", companyName='" + companyName +
                "' , taxNumber='" + taxNumber +
                "' , isGovernmentAgency=" + isGovernmentAgency +
                " , userId=" + userId +
                " , storages=" + storages.toString() +
                " , details="+ details.toString() + "]";
    }
}
