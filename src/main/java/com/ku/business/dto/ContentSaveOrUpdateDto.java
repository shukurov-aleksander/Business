package com.ku.business.dto;

public class ContentSaveOrUpdateDto {
    private Long id;
    private Long quantity;
    private ServiceDto service;

    public ContentSaveOrUpdateDto(Long id, Long quantity, ServiceDto service) {
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

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }
}
