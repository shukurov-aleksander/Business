package com.ku.business.service.impl;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.dtomapper.DocumentDtoMapper;
import com.ku.business.repository.DocumentRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DocumentServiceImpl implements CrudService<DocumentDto, DocumentListDto, DocumentSaveDto> {
    private final DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DocumentDto> findById(Long id) {
        return Optional.of(DocumentDtoMapper.toDto(repository.findById(id).get()));
    }

    @Override
    public Set<DocumentListDto> findAll() {
        return DocumentDtoMapper.toListDto(repository.findAll());
    }

    @Override
    public void save(DocumentSaveDto document) {
        repository.save(DocumentDtoMapper.fromSaveDto(document));
    }

    @Override
    public void update(DocumentSaveDto document) {
        repository.save(DocumentDtoMapper.fromSaveDto(document));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
