package com.ku.business.dtomapper;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveOrUpdateDto;
import com.ku.business.entity.Document;

import java.util.HashSet;
import java.util.Set;

public class DocumentDtoMapper {
    public static DocumentDto toDto(Document document) {
        return new DocumentDto(
                document.getId(),
                new OrderDtoMapper().toDto(document.getOrder()),
                document.getDocumentContent()
        );
    }

    public static DocumentListDto toListDto(Document document) {
        return new DocumentListDto(
                document.getId(),
                document.getDocumentContent()
        );
    }

    public static Set<DocumentListDto> toDtoList(Set<Document> documents) {
        Set<DocumentListDto> documentListDtos = new HashSet<>();
        for (Document document : documents) {
            documentListDtos.add(toListDto(document));
        }
        return documentListDtos;
    }

    public static DocumentSaveOrUpdateDto toSaveOrUpdateDto(Document document) {
        return new DocumentSaveOrUpdateDto(
                document.getId(),
                document.getDocumentContent()
        );
    }

    public static Document fromSaveOrUpdateDto(DocumentSaveOrUpdateDto saveOrUpdateDto) {
        return new Document()
                .setId(saveOrUpdateDto.getId())
                .setDocumentContent(saveOrUpdateDto.getDocumentContent()
        );
    }
}
