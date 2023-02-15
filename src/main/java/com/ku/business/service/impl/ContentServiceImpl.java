package com.ku.business.service.impl;

import com.ku.business.entity.Content;
import com.ku.business.repository.ContentRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements CrudService<Content> {
    private final ContentRepository repository;

    public ContentServiceImpl(ContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Content> findById(Long id) {
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
        repository.save(content);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
