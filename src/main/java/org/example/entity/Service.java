package org.example.entity;

import java.util.Objects;

public class Service {
    private long id;
    private String serviceName;
    private long sum;
    private String serviceDescription;

    public Service() {
    }

    public Service(long id, String serviceName, long sum, String serviceDescription) {
        this.id = id;
        this.serviceName = serviceName;
        this.sum = sum;
        this.serviceDescription = serviceDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Service service = (Service) obj;
        return id == service.id &&
                sum == service.sum &&
                serviceName.equals(service.serviceName) &&
                serviceDescription.equals(service.serviceDescription);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (id - (id >>> 32));
        result = 31 * result + (int) (sum - (sum >>> 32));
        result = 31 * result + (serviceName == null ? 0 : serviceName.hashCode());
        result = 31 * result + (serviceDescription == null ? 0 : serviceDescription.hashCode());
        return result;
    }
    public String toString() {
        return "Service [" +
                "id=" + id +
                "sum=" + sum +
                ", serviceName=' " + serviceName +
                "' , serviceDescription=" + serviceDescription + "']";
    }
}
