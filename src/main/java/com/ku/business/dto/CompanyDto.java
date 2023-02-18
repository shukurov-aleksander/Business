package com.ku.business.dto;

import java.util.List;
import java.util.Set;

public class CompanyDto {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;
    private Long userId;
    private List<StorageListDto> storages;
    private Set<DetailListDto> details;

    public CompanyDto(
            Long id,
            String companyName,
            String taxNumber,
            Boolean isGovernmentAgency,
            Long userId,
            List<StorageListDto> storages,
            Set<DetailListDto> details
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
        isGovernmentAgency = governmentAgency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<StorageListDto> getStorages() {
        return storages;
    }

    public void setStorages(List<StorageListDto> storages) {
        this.storages = storages;
    }

    public Set<DetailListDto> getDetails() {
        return details;
    }

    public void setDetails(Set<DetailListDto> details) {
        this.details = details;
    }
}

