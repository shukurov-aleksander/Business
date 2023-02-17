package com.ku.business.dto;

public class ServiceSaveOrUpdateDto {
    Long id;
    String serviceName;
    Long sum;

    public ServiceSaveOrUpdateDto(Long id, String serviceName, Long sum) {
        this.id = id;
        this.serviceName = serviceName;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
