package com.ku.business.dto;

public class ServiceDto {
    Long id;
    String serviceName;
    Long sum;
    String serviceDescription;

    public ServiceDto(
            Long id,
            String serviceName,
            Long sum,
            String serviceDescription
    ) {
        this.id = id;
        this.serviceName = serviceName;
        this.sum = sum;
        this.serviceDescription = serviceDescription;
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

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
