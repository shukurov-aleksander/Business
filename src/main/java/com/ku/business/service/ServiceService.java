package com.ku.business.service;

import com.ku.business.entity.Service;
import com.ku.business.repository.spring.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService implements CrudService<Service> {
    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
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
    public void update(Service service, Long id) {
        repository.update(service, id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
