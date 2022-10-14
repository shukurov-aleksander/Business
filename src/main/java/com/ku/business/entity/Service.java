package com.ku.business.entity;

public class Service {
    private Long id;
    private String serviceName;
    private Long sum;
    private String serviceDescription;

    public Service() {
    }

    public Service(Long id, String serviceName, Long sum, String serviceDescription) {
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

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) {return true;}
        if (aThat == null || getClass() != aThat.getClass()) {return false;}
        Service service = (Service) aThat;
        return id.equals(service.id) &&
                sum.equals(service.sum) &&
                serviceName.equals(service.serviceName) &&
                serviceDescription.equals(service.serviceDescription);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (sum == null ? 0 : sum.hashCode());
        result = prime * result + (serviceName == null ? 0 : serviceName.hashCode());
        result = prime * result + (serviceDescription == null ? 0 : serviceDescription.hashCode());
        return result;
    }
    public String toString() {
        return  this.getClass().getSimpleName() + " [" +
                "id=" + id +
                "sum=" + sum +
                ", serviceName=' " + serviceName +
                "' , serviceDescription=" + serviceDescription + "']";
    }
}
