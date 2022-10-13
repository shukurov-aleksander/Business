package com.ku.buiseness.entity;

import java.util.Collection;

public class Company {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;
    private Long userId;
    private Collection<Storage> storageList;
    private Collection<Detail> detailList;

    public Company() {
    }

    public Company(Long id, String companyName, String taxNumber, Boolean isGovernmentAgency, Long userId, Collection<Storage> storageList, Collection<Detail> detailList) {
        this.id = id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.isGovernmentAgency = isGovernmentAgency;
        this.userId = userId;
        this.storageList = storageList;
        this.detailList = detailList;
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

    public Collection<Storage> getStorageList() {
        return storageList;
    }

    public void setStorageList(Collection<Storage> storageList) {
        this.storageList = storageList;
    }

    public Collection<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(Collection<Detail> detailList) {
        this.detailList = detailList;
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
                storageList.equals(company.storageList) &&
                detailList.equals(company.detailList);

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
        for (Storage storage : storageList) {
            result = prime * result + (storage == null ? 0 : storage.hashCode());
        }
        for (Detail detail : detailList) {
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
                " , storageList=" + storageList +
                " , detailList="+ detailList + "]";
    }
}
