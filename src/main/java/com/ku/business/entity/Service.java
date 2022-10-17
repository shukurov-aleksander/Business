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

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getServiceName() == null) {
            if (aThat.getServiceName() != null) {return false;}
        } else if (!getServiceName().equals(aThat.getServiceName())) {return false;}

        if (getSum() == null) {
            if (aThat.getSum() != null) {return false;}
        } else if (!getSum().equals(aThat.getSum())) {return false;}

        if (getServiceDescription() == null) {
            if (aThat.getServiceDescription() != null) {return false;}
        } else if (!getServiceDescription().equals(aThat.getServiceDescription())) {return false;}
        return true;
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(" {").append("id=").append(id).append(", sum=")
                .append(sum).append(", serviceName='").append(serviceName).append("', serviceDescription='")
                .append(serviceDescription).append("'}");
        return  stringBuilder.toString();
    }
}
