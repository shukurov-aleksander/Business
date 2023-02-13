package com.ku.business.service.impl;

import com.ku.business.entity.Detail;
import com.ku.business.repository.DetailRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements CrudService<Detail> {
    private final DetailRepository repository;

    public DetailServiceImpl(DetailRepository repository) {
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
    public void update(Detail detail) {
        repository.save(detail);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
