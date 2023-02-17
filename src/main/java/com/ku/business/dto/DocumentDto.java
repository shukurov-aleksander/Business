package com.ku.business.dto;

public class DocumentDto {
    private Long id;
    private OrderDto order;
    private String documentContent;

    public DocumentDto(Long id, OrderDto order, String documentContent) {
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

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }
}
