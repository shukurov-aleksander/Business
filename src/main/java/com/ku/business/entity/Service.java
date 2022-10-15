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
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Service aThat = (Service) obj;
        if ((this.id==null && aThat.id != null) || (this.id != null && aThat.id == null)) {return false;}
        if ((this.sum==null && aThat.sum != null) || (this.sum != null && aThat.sum == null)) {return false;}
        if ((this.serviceName==null && aThat.serviceName != null) || (this.serviceName != null && aThat.serviceName == null)) {return false;}
        if ((this.serviceDescription==null && aThat.serviceDescription != null) || (this.serviceDescription != null && aThat.serviceDescription == null)) {return false;}
        return (((this.id == aThat.id) && (aThat.id == null)) || (this.id.equals(aThat.id))) &&
                (((this.sum == aThat.sum) && (aThat.sum == null)) || (this.sum.equals(aThat.sum))) &&
                (((this.serviceName == aThat.serviceName) && (aThat.serviceName == null)) || (this.serviceName.equals(aThat.serviceName))) &&
                (((this.serviceDescription == aThat.serviceDescription) && (aThat.serviceDescription == null)) || (this.serviceDescription.equals(aThat.serviceDescription)));
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
