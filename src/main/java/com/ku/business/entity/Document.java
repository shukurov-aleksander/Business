package com.ku.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    @Column( name = "document_content")
    private String documentContent;

    public Document() {
    }

    public Document(Long id, Order order, String documentContent) {
        this.id = id;
        this.order = order;
        this.documentContent = documentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderId) {
        this.order = orderId;
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

        if (getOrder() == null) {
            if (aThat.getOrder() != null) {return false;}
        } else if (!getOrder().equals(aThat.getOrder())) {return false;}

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
        result = prime * result + (order == null ? 0 : order.hashCode());
        result = prime * result + (documentContent == null ? 0 : documentContent.hashCode());
        return result;
    }
        @Override
    public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {")
                .append("id=").append(getId())
                .append(", documentContent='").append(getDocumentContent())
                .append("'}");
        return  stringBuilder.toString();
    }
}
