package com.ku.business.service.impl;

import com.ku.business.entity.Service;
import com.ku.business.repository.ServiceRepository;
import com.ku.business.service.CrudService;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements CrudService<Service> {
    private final ServiceRepository repository;

    public ServiceServiceImpl(ServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Service> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Service> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Service service) {
        repository.save(service);
    }

    @Override
    public void update(Service service) {
        repository.save(service);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
