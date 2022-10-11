package org.example.entity;

public class Documents {
    private long id;
    private long orderId;
    private String JSONDocument;

    public Documents() {
    }

    public Documents(long id, long orderId, String JSONDocument) {
        this.id = id;
        this.orderId = orderId;
        this.JSONDocument = JSONDocument;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getJSONDocument() {
        return JSONDocument;
    }

    public void setJSONDocument(String JSONDocument) {
        this.JSONDocument = JSONDocument;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Documents documents = (Documents) obj;
        return id == documents.id &&
                orderId == documents.orderId &&
                JSONDocument.equals(documents.JSONDocument);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (id - (id >>> 32));
        result = 31 * result + (int) (orderId - (orderId >>> 32));
        result = 31 * result + (JSONDocument == null ? 0 : JSONDocument.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "Documents [" +
                "id=" + id +
                ", orderId=" + orderId +
                " , JSONDocument=" + JSONDocument+"]";
    }
}
