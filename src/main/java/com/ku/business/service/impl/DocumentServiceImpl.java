package com.ku.business.service.impl;

import com.ku.business.entity.Document;
import com.ku.business.repository.DocumentRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements CrudService<Document> {
    private final DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Document> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Document> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Document document) {
        repository.save(document);
    }

    @Override
    public void update(Document document) {
        repository.save(document);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
