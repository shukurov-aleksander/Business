package com.ku.business.service;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.dtomapper.DocumentDtoMapper;
import com.ku.business.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Optional<DocumentDto> findById(Long id) {
        return Optional.of(DocumentDtoMapper.toDto(documentRepository.findById(id).get()));
    }

    public List<DocumentListDto> findAll() {
        return DocumentDtoMapper.toListDto(documentRepository.findAll());
    }

    public void save(DocumentSaveDto document) {
        documentRepository.save(DocumentDtoMapper.fromSaveDto(document));
    }

    public void update(DocumentSaveDto document) {
        documentRepository.save(DocumentDtoMapper.fromSaveDto(document));
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}
