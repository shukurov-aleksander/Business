package com.ku.business.service;

import com.ku.business.entity.Content;
import com.ku.business.repository.hibernate.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService implements CrudService<Content> {
    private final ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Content findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Content> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Content content) {
        repository.save(content);
    }

    @Override
    public void update(Content content) {
        repository.update(content);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
