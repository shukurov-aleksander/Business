package com.ku.business.dtomapper;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.entity.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocumentDtoMapper {
    public static DocumentDto toDto(Document document) {
        return new DocumentDto()
                .setId(document.getId())
                .setOrder(OrderDtoMapper.toDto(document.getOrder()))
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

    public static Set<DocumentListDto> toListDto(List<Document> documents) {
        Set<DocumentListDto> documentListDtos = new HashSet<>();
        for (Document document : documents) {
            documentListDtos.add(toListDto(document));
        }
        return documentListDtos;
    }
    public static DocumentSaveDto toSaveDto(Document document) {
        return new DocumentSaveDto()
                .setId(document.getId())
                .setDocumentContent(document.getDocumentContent()
        );
    }

    public static Document fromSaveDto(DocumentSaveDto saveOrUpdateDto) {
        return new Document()
                .setId(saveOrUpdateDto.getId())
                .setDocumentContent(saveOrUpdateDto.getDocumentContent()
        );
    }
}
