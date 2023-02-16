package com.ku.business.dto.content;

import com.ku.business.dto.service.ServiceDTO;

public class ContentSaveOrUpdateDTO {
    Long id;
    Long quantity;
    ServiceDTO service;

    public ContentSaveOrUpdateDTO(Long id, Long quantity, ServiceDTO service) {
        this.id = id;
        this.quantity = quantity;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }
}
