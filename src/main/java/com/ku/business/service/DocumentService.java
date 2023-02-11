package com.ku.business.service;

import com.ku.business.entity.Document;
import com.ku.business.repository.spring.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService implements CrudService<Document> {
    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
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
    public void update(Document document, Long id) {
        repository.update(document, id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
