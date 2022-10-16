package com.ku.business.entity;

public class Document {
    private Long id;
    private Order orderId;
    private String documentContent;

    public Document() {
    }

    public Document(Long id, Order orderId, String documentContent) {
        this.id = id;
        this.orderId = orderId;
        this.documentContent = documentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
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
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Document aThat = (Document) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getOrderId() == null) {
            if (aThat.getOrderId() != null) {return false;}
        } else if (!getOrderId().equals(aThat.getOrderId())) {return false;}

        if (getDocumentContent() == null) {
            if (aThat.getDocumentContent() != null) {return false;}
        } else if (!getDocumentContent().equals(aThat.getDocumentContent())) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (orderId == null ? 0 : orderId.hashCode());
        result = prime * result + (documentContent == null ? 0 : documentContent.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return  getClass().getSimpleName() + " {" +
                "id=" + id +
                ", orderId=" + orderId.toString() +
                " , documentContent='" + documentContent +"'}";
    }
}
