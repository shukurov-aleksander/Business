package com.ku.business.service;

import com.ku.business.entity.Detail;
import com.ku.business.repository.spring.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService implements CrudService<Detail>{
    private final DetailRepository repository;

    @Autowired
    public DetailService(DetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Detail> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Detail> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Detail detail) {
        repository.save(detail);
    }

    @Override
    public void update(Detail detail, Long id) {
        repository.update(detail, id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
