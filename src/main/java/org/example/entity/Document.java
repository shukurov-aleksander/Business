package org.example.entity;

public class Document {
    private long id;
    private long orderId;
    private String documentContent;

    public Document() {
    }

    public Document(long id, long orderId, String documentContent) {
        this.id = id;
        this.orderId = orderId;
        this.documentContent = documentContent;
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

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        Document documents = (Document) obj;
        return id == documents.id &&
                orderId == documents.orderId &&
                documentContent.equals(documents.documentContent);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (int) (id - (id >>> 32));
        result = prime * result + (int) (orderId - (orderId >>> 32));
        result = prime * result + (documentContent == null ? 0 : documentContent.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", orderId=" + orderId +
                " , documentContent='" + documentContent +"']";
    }
}
