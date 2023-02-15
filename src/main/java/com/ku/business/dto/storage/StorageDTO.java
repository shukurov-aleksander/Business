package com.ku.business.dto.storage;

import com.ku.business.dto.company.CompanyDTO;
import com.ku.business.dto.service.ServiceDTO;

public class StorageDTO {
    Long id;
    Integer quantity;
    CompanyDTO company;
    ServiceDTO service;

    public StorageDTO(Long id, Integer quantity, CompanyDTO company, ServiceDTO service) {
        this.id = id;
        this.quantity = quantity;
        this.company = company;
        this.service = service;
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

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }
}
