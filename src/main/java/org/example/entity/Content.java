package org.example.entity;

public class Content {
    private long id;
    private long quantity;
    private long serviceId;

    public Content() {
    }

    public Content(long id, long quantity, long serviceId) {
        this.id = id;
        this.quantity = quantity;
        this.serviceId = serviceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Content content = (Content) obj;
        return id == content.id &&
                quantity == content.quantity &&
                serviceId == content.serviceId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (id - (id >>> 32));
        result = 31 * result + (int) (quantity - (quantity >>> 32));
        result = 31 * result + (int) (serviceId - (serviceId >>> 32));
        return result;
    }
    @Override
    public String toString() {
        return "Content [" +
                "id=" + id +
                ", quantity=" + quantity +
                " , serviceId=" + serviceId +"]";
    }
}
