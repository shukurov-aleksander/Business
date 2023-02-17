package com.ku.business.dto;

public class DocumentListDto {
    Long id;
    String documentContent;

    public DocumentListDto(Long id, String documentContent) {
        this.id = id;
        this.documentContent = documentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }
}
