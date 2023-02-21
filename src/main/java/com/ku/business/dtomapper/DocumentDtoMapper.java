package com.ku.business.dtomapper;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveOrUpdateDto;
import com.ku.business.entity.Document;

import java.util.HashSet;
import java.util.Set;

public class DocumentDtoMapper {
    public static DocumentDto toDto(Document document) {
        return new DocumentDto()
                .setId(document.getId())
                .setOrder(new OrderDtoMapper().toDto(document.getOrder()))
                .setDocumentContent(document.getDocumentContent()
        );
    }

    public static DocumentListDto toListDto(Document document) {
        return new DocumentListDto()
                .setId(document.getId())
                .setDocumentContent(document.getDocumentContent()
        );
    }

    public static Set<DocumentListDto> toListDto(Set<Document> documents) {
        Set<DocumentListDto> documentListDtos = new HashSet<>();
        for (Document document : documents) {
            documentListDtos.add(toListDto(document));
        }
        return documentListDtos;
    }

    public static DocumentSaveOrUpdateDto toSaveOrUpdateDto(Document document) {
        return new DocumentSaveOrUpdateDto()
                .setId(document.getId())
                .setDocumentContent(document.getDocumentContent()
        );
    }

    public static Document fromSaveOrUpdateDto(DocumentSaveOrUpdateDto saveOrUpdateDto) {
        return new Document()
                .setId(saveOrUpdateDto.getId())
                .setDocumentContent(saveOrUpdateDto.getDocumentContent()
        );
    }
}
