package com.ku.business.service;

import com.ku.business.entity.Storage;
import com.ku.business.repository.hibernate.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService implements CrudService<Storage> {
    private final StorageRepository repository;

    public StorageService(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Storage findById(Long id) {
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
        repository.update(storage);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
