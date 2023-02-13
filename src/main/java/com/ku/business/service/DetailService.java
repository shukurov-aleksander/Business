package com.ku.business.service;

import com.ku.business.entity.Detail;
import com.ku.business.repository.hibernate.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService implements CrudService<Detail>{
    private final DetailRepository repository;

    public DetailService(DetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Detail findById(Long id) {
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
    public void update(Detail detail) {
        repository.update(detail);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
