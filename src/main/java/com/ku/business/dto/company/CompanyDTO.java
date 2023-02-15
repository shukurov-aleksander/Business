package com.ku.business.dto.company;

import com.ku.business.dto.detail.DetailListDTO;
import com.ku.business.dto.storage.StorageListDTO;

import java.util.List;

public class CompanyDTO {
    Long id;
    String companyName;
    String taxNumber;
    Boolean isGovernmentAgency;
    Long userId;
    List<StorageListDTO> storages;
    List<DetailListDTO> details;

    public CompanyDTO(Long id, String companyName, String taxNumber, Boolean isGovernmentAgency, Long userId, List<StorageListDTO> storages, List<DetailListDTO> details) {
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

    public List<StorageListDTO> getStorages() {
        return storages;
    }

    public void setStorages(List<StorageListDTO> storages) {
        this.storages = storages;
    }

    public List<DetailListDTO> getDetails() {
        return details;
    }

    public void setDetails(List<DetailListDTO> details) {
        this.details = details;
    }
}

