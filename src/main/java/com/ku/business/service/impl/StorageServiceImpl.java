package com.ku.business.service.impl;

import com.ku.business.entity.Storage;
import com.ku.business.repository.StorageRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements CrudService<Storage> {
    private final StorageRepository repository;

    public StorageServiceImpl(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Storage> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Storage> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Storage storage) {
        repository.save(storage);
    }

    @Override
    public void update(Storage storage) {
        repository.save(storage);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
