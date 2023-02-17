package com.ku.business.dtomapper;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveOrUpdateDto;
import com.ku.business.entity.Document;

import java.util.ArrayList;
import java.util.List;

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

    public static List<DocumentListDto> toDtoList(List<Document> documents) {
        List<DocumentListDto> documentListDtos = new ArrayList<>();
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
        return new Document(
                saveOrUpdateDto.getId(),
                null,
                saveOrUpdateDto.getDocumentContent()
        );
    }
}
