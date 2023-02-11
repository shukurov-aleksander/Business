package com.ku.business.service;

import com.ku.business.entity.Storage;
import com.ku.business.repository.spring.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService implements CrudService<Storage> {
    private final StorageRepository repository;

    public StorageService(StorageRepository repository) {
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
    public void update(Storage storage, Long id) {
        repository.update(storage, id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
